import java.util.ArrayList;

public class Node{
	
	private Node parent;
	private Node[] children;
	private Board board;
	private int justPlayed;
	private int isWinning = -1;
	
	private int visits;
	private double wins;
	
	
	public Node(Board board, Node parent, int justPlayed) { 
		this.visits = 0;
		this.wins = 0;
		
		this.board = board;
		this.parent = parent;
		this.justPlayed = justPlayed;
		
		this.children = null;
	}
	    
	

	public double ucb(double c) {
		if(visits == 0) return Integer.MAX_VALUE;
		return (double) wins / visits + c * Math.log((double)parent.getVisits() / visits);
	}
	
	public void expand() {
		ArrayList<Integer> moves = board.getPossibleMoves();
		
		if(moves.size() == 0 || checkWinning() == 1 || checkWinning() == 2) return;
		
		
		children = new Node[moves.size()];
		
		for(int i = 0; i < moves.size(); i++) {
			children[i] = new Node(new Board(board.content), this, Game.flip(justPlayed));
			children[i].getBoard().addTile(moves.get(i),  Game.flip(justPlayed));
		}
	}
	
	public int getJustPlayed() {
		return justPlayed;
	}

	public int checkWinning() {
		if(isWinning == -1) {
			
			if(board.checkWin(1)) {
				isWinning = 1;
				
			} else if(board.checkWin(2)) {
				isWinning = 2;
			} else {
				isWinning = 0;
			}
			
			return isWinning;
			
			
		} else {
			return isWinning;
		}
	}


	public void setJustPlayed(int justPlayed) {
		this.justPlayed = justPlayed;
	}



	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}
	
	public Node[] getChildren() {
		return children;
	}



	public void setChildren(Node[] children) {
		this.children = children;
	}



	public Board getBoard() {
		return board;
	}



	public void setBoard(Board board) {
		this.board = board;
	}



	public double getWins() {
		return wins;
	}



	public void setWins(double wins) {
		this.wins = wins;
	}



	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	

}
