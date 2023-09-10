import java.util.ArrayList;

public class Tree {

	private Node root;
	private ArrayList<Node> path;
	private double RATE;
	
	public Tree(Board board, int justPlayed) {
		root = new Node(board, null, justPlayed);
		path = new ArrayList<Node>(0);
	}
	
	public int run(int iter, int numRollouts, double rate) {
		
		RATE = rate;
		
		root.expand();
		
		for(int i = 0; i < iter; i++) {
			Node selected = selection();
			Node expanded = expansion(selected);
			
			double[] totalWins = rollout(expanded, numRollouts);
			backpropogation(totalWins);
		}
		
		ArrayList<Integer>  bestIndicies = new ArrayList<Integer>(0);
		bestIndicies.add(0);
		Node[] rootChildren = root.getChildren();
		for(int i = 0; i < rootChildren.length; i++) {
			System.out.print(rootChildren[i].getVisits() + ": ");
			System.out.print((int)(rootChildren[i].getWins() / rootChildren[i].getVisits() * 100)+ " ");
			if(rootChildren[i].getVisits() >  rootChildren[bestIndicies.get(0)].getVisits()) {
				bestIndicies = new ArrayList<Integer>(0);
				bestIndicies.add(i);
			} else if(rootChildren[i].getVisits() ==  rootChildren[bestIndicies.get(0)].getVisits()) {
				bestIndicies.add(i);
			}
		}
		System.out.println();
		ArrayList<Integer> moves = root.getBoard().getPossibleMoves();
		
		if(bestIndicies.size() > 1) {
			int best = bestIndicies.get(0);
			for(Integer i:bestIndicies) {
				
				if(rootChildren[i].getWins() > rootChildren[best].getWins()) {
					best = i;
				}
			}
			return moves.get(best);
			
		} else {
			return moves.get(bestIndicies.get(0));
		}
		
	}
	
	public Node selection() {
		Node currentNode = root; 
		path.add(currentNode);
		Node[] currentNodeChildren = currentNode.getChildren();
		
		
		while(currentNodeChildren != null) {
			Node bestChild = currentNodeChildren[0];
			double bestUCB =  Integer.MIN_VALUE;
			for(Node n : currentNodeChildren) {
				double ucb = n.ucb(RATE);
				
				boolean isLosing = false;
				
				if(n.checkWinning() == n.getJustPlayed()) {
					isLosing = false;
	
				} else {
					ArrayList<Integer> moves = n.getBoard().getPossibleMoves();
				
					for(Integer move: moves) {
						Board testBoard = new Board(n.getBoard().content);
						testBoard.addTile(move, Game.flip(n.getJustPlayed()));
						
						if(testBoard.checkWin(Game.flip(n.getJustPlayed()))) isLosing = true;
					}
				
				}

					
				if(ucb > bestUCB && !isLosing) {
					bestChild = n;
					bestUCB = bestChild.ucb(RATE);
				}
			}
			
			currentNode = bestChild;
			currentNodeChildren = currentNode.getChildren();
			path.add(currentNode);
		}
		
		return currentNode;
	}
	
	public Node expansion(Node node) {
		node.expand();
		
		if(node.getChildren() == null) return node;
		
		int guess = (int)(Math.random() * node.getChildren().length);
		
		Node bottom = node.getChildren()[guess];
		path.add(bottom);
		
		return bottom;
		
	}
	
	public double[] rollout(Node node, int numRollouts) {
		double[] totalWins = {0, 0};
		
		for(int i = 0; i < numRollouts; i++) {
			Board rolloutBoard = new Board(node.getBoard().content);
			
			if(rolloutBoard.checkWin(1)) {
				totalWins[0] += 1;
				
		
			} else if(rolloutBoard.checkWin(2)) {
				totalWins[1] += 1;
			
			} else {
				
			int aboutToMove = Game.flip(node.getJustPlayed());
			double counter = 0;
			
			while(rolloutBoard.getPossibleMoves().size() != 0) {	
				ArrayList<Integer> moves = rolloutBoard.getPossibleMoves();
				
				int guess = (int)(Math.random() * moves.size());
				
				rolloutBoard.addTile(moves.get(guess), aboutToMove);
				aboutToMove = Game.flip(aboutToMove);
				counter++;
				
				if(rolloutBoard.checkWin(1)) {
//					totalWins[0] += 1;
					totalWins[0] += (1.0 - counter / 84.0);
//					totalWins[1] -= counter / 168.0;
					break;
				} else if(rolloutBoard.checkWin(2)) {
//					totalWins[1] += 1;
					totalWins[1] += (1.0 - counter / 84.0);
//					totalWins[0] -= (counter / 168.0);
					break;
				}
			}
			
			if(!rolloutBoard.checkWin(1) && !rolloutBoard.checkWin(2)) {
				totalWins[0] += 0.5;
				totalWins[1] += 0.5;
			} 
			
			}
			
		}
		
		totalWins[0] = totalWins[0] / numRollouts;
		totalWins[1] = totalWins[1] / numRollouts;
		
//		if(totalWins[0] > totalWins[1]) {
//			totalWins[0] = 1;
//			totalWins[1] = 0;
//		} else {
//			totalWins[0] = 0;
//			totalWins[1] = 1;
//		}
		
		
		return totalWins;
	}
	
	public void backpropogation(double[] totalWins) {

		for(Node n: path) {
			n.setVisits(n.getVisits() + 1);
			
			if(n.getJustPlayed() == 1) {
				n.setWins(n.getWins() + totalWins[0]);
			} else if(n.getJustPlayed() == 2) {
				n.setWins(n.getWins() + totalWins[1]);
			}
		}
		
		path = new ArrayList<Node>(0);
	}
	
}
