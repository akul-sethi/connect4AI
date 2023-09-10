import java.util.ArrayList;
import java.util.Arrays;

public class Board {

	public int[][] content;
	private int justPlayed;
	
	public Board(int[][] array) {
		
		this.content = new int[7][6];
		
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				this.content[i][j] = array[i][j];
			}
		}
		
		
	}
	
	public Board() {
		this.content = new int[7][6];
	}
	
	public void printBoard() {
		for(int[] i:content) {
			for(int j:i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
	
	public Board inverse() {
		Board b = new Board();
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				if(content[i][j] != 0) b.content[i][j] = Game.flip(content[i][j]);
			}
		}
		
		return b;
		
	}

	public void addTile(int move, int player) {
		int[] column = content[move];
		for(int i = 0; i < column.length - 1; i++) {
			if(column[i+1] != 0 && column[0] == 0) {
				column[i] = player;
			
				return;
			}
		}
		
		if(column[0] != 0) {
			return;
		}
		
		column[column.length-1] = player;
		
	
	}
	
	
	
	public ArrayList<Integer> getPossibleMoves() {
		ArrayList<Integer> moves = new ArrayList<Integer>(0);
		
		for(int i = 0; i < content.length; i++) {
			int numEmpties = 0;
			
			for(int j = 0; j < content[i].length; j++) {
				if(content[i][j] == 0) numEmpties++;
			}
			
			if(numEmpties != 0) moves.add(i);
			
		}
		return moves;
	}
	
	
	public  boolean checkWin(int player) {
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				if(checkUp(i, j, player) || checkRight(i, j, player) || checkDownRight(i, j, player) || checkUpRight(i, j, player)) {
					return true;
				}
			}
		}
		return false;
			

	}
	
	public boolean checkUp(int x, int y, int player){
		int counter = 0;
		int i = x;
		int j = y;
	
		while(true) {
			if(content[i][j] == player) {
				counter++;
			} else {
				return false;
			}
			
			if (counter == 4) {
				return true;
			}
			
			j--;
			
			if(j < 0) {
				return false;
			}
		}
	}
	
	public boolean checkRight(int x, int y, int player){
		int counter = 0;
		int i = x;
		int j = y;
	
		while(true) {
			if(content[i][j] == player) {
				counter++;
			} else {
				return false;
			}
			
			if (counter == 4) {
				return true;
			}
			
			i++;
			
			if(i > 6) {
				return false;
			}
		}
	}
	
	public boolean checkDownRight(int x, int y, int player){
		int counter = 0;
		int i = x;
		int j = y;
	
		while(true) {
			if(content[i][j] == player) {
				counter++;
			} else {
				return false;
			}
			
			if (counter == 4) {
				return true;
			}
			
			i++;
			j++;
			
			if(i > 6) {
				return false;
			}
			
			if(j > 5) {
				return false;
			}
		}
	}	
	

	public boolean checkUpRight(int x, int y, int player){
		int counter = 0;
		int i = x;
		int j = y;
	
		while(true) {
			if(content[i][j] == player) {
				counter++;
			} else {
				return false;
			}
			
			if (counter == 4) {
				return true;
			}
			
			i++;
			j--;
			
			if(i > 6) {
				return false;
			}
			
			if(j < 0) {
				return false;
			}
		}
	}
	
}
