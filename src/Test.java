import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		
		Board b = new Board();
		b.addTile(0, 2);
		b.addTile(0, 2);
		b.addTile(0, 2);
		b.addTile(0, 1);
		
//		b.printBoard();
		Node n = new Node(b, null, 1);
////		n.expand();
//		
//		Tree t = new Tree(b, 1);
//		double[] a = t.rollout(n, 100);
//		System.out.println(a[0]);
//		System.out.println(a[1]);
//		n.getChildren()[0].getBoard().printBoard();
		
		ArrayList<Node> losingMoves = new ArrayList<Node>(0);
		System.out.println(losingMoves.contains(n));
		losingMoves.add(n);
		System.out.println(losingMoves.contains(n));

	}


}




