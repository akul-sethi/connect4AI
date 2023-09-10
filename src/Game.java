

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game extends JPanel{
  
  //Hyperparameters
  private final int ITERATIONS = 30000;
  private final int NUM_ROLLOUTS = 10;
  private final double UCB_RATE = 0.7;

	public static final int PREF_H = 875;
	public static final int PREF_W = 1000;
	
	public int player = 1;
	public int[][] board = new int[7][6];
	public boolean playing = true;
	public String message = "";
	public JButton resetButton, AIButton;
	
	
	private Column[] columns;
	
	
	private int w = 805;
	private int h = 600;
	private int x = (PREF_W-w)/2;
	private int y = (PREF_H-h)/2;
	
	
	public Game() {
		 this.setFocusable(true);
		 this.setBackground(Color.LIGHT_GRAY);
		 this.setLayout(null);
		 
		 
		 this.columns = new Column[7];
		 
		 for(int i = 0; i < 7; i++) {
			 columns[i] = new Column(x + i*w/7, y, w/7, h, 75, i, this);
			 this.add(columns[i]);
		 }
		 
		 resetButton = new JButton("Click to restart");
		 resetButton.setBounds(x + (w-200)/2 - 150, y+h+20, 200, 50);
		 resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!playing && e.getSource() == resetButton) {
					resetGame();
					repaint();
				}
				
			}
			 
		 });
		 
		 AIButton = new JButton("Click to AI move");
		 AIButton.setBounds(x + (w-200)/2 + 150, y+h+20, 200, 50);
		 AIButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Board b = new Board(board);
//				if(player == 2) b = b.inverse();
				Tree tree = new Tree(b, flip(player));
				System.out.println(flip(player));
				columns[tree.run(ITERATIONS, NUM_ROLLOUTS, UCB_RATE)].click();
					
			}
			 
		 });
		 
		 this.add(AIButton);
		 
//	AKULS CONNECT 4 STANDS NO CHANCE!!
	}
	
	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable()
	      {
	         public void run()
	         {
	            createAndShowGUI();
	         }
	      });
	   }
	
	public Dimension getPreferredSize()
	   {
	      return new Dimension(PREF_W, PREF_H);
	   }

	public static void createAndShowGUI() {
		
		JFrame frame = new JFrame("Connect 4");
		JPanel panel = new Game();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setFocusable(true);
	    frame.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		
		int rr;
		int yr;
		
		Polygon leftArrow = new Polygon(new int[]{170, 185, 185},new int[]{y/2, y/2+5, y/2-5},3);
		Polygon rightArrow = new Polygon(new int[] {830, 815, 815}, new int[]{y/2, y/2+5, y/2-5},3);
		
		g2.setColor(Color.BLACK);
		if(player == 1) {
			 rr = 65;
			 yr = 45;
			 g2.fill(leftArrow);
		} else {
			 rr = 45;
			 yr = 65;
			g2.fill(rightArrow);
		}
		
		g2.setColor(Color.YELLOW);
		g2.fillOval(110-rr/2, (y-rr)/2, rr, rr);
		
		g2.setColor(Color.RED);
		g2.fillOval(PREF_W - ((110-yr/2)+yr), (y-yr)/2, yr, yr);
		
	
		
		
		
		
		
		g2.setFont(new Font("Arial", Font.PLAIN, 40));
	    FontMetrics fm = g2.getFontMetrics(); 
	   
	     
	     int width = fm.stringWidth(message);
	     g2.setColor(Color.BLACK);
	     g2.drawString(message, x+(w-width)/2, y-45);
	     
	     
	     
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
			if(board[i][j] == player) {
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
			if(board[i][j] == player) {
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
			if(board[i][j] == player) {
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
			if(board[i][j] == player) {
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

	public void printBoard() {
		for(int[] i:board) {
			for(int j:i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
	
	public static int flip(int a) {
		if(a == 1) {
			return 2;
		}else {
			return 1;
		}
	}
	
	public void resetGame() {
		message = "";
		player = 1;
		board = new int[7][6];
		playing = true;
		
		this.columns = new Column[7];
		this.removeAll();
		 
		 for(int i = 0; i < 7; i++) {
			 columns[i] = new Column(x + i*w/7, y, w/7, h, 75, i, this);
			 this.add(columns[i]);
		 }
		 
		 this.add(AIButton);
		
	}

	

	
}
