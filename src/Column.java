

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Column extends JButton implements ActionListener{

	private int[] tiles;
	private Color[] colors = {new Color(56, 140, 214), Color.YELLOW, Color.RED};
	
	int x, y, w, h, r, ygap, xgap, n;
	Game game;
	
	public Column(int x, int y, int w, int h, int r, int n, Game game) {
		this.w = w;
		this.h = h;
		this.x = x;
		this.y = y;
		this.r = r;
		this.game = game;
		this.ygap = (h - 6*r)/7;
		this.xgap = (w-r)/2;
		this.tiles =  new int[6];
		this.n = n;
		
		
		this.setBounds(x,y,w,h);
		this.addActionListener(this);
		this.setOpaque(true);


	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		
		
		g2.setColor(new Color(27, 98, 161));
		g2.fill(this.getVisibleRect());
		
		for(int i = 0; i < tiles.length; i++) {
			g2.setColor(colors[tiles[i]]);
			g2.fillOval(xgap, (i+1)*ygap + i*r, r, r);
				
		}
		
		
	}
	
	public void addTile() {
		for(int i = 0; i < tiles.length - 1; i++) {
			if(tiles[i+1] != 0 && tiles[0] == 0) {
				tiles[i] = game.player;
				
				game.player = Game.flip(game.player);
				return;
			}
		}
		
		if(tiles[0] != 0) {
			return;
		}
		
		tiles[tiles.length-1] = game.player;
		
		game.player = Game.flip(game.player);
	}
	
	public int flip(int a) {
		if(a == 1) {
			return 2;
		}else {
			return 1;
		}
	}
	
	public void click() {
		if(game.playing) {
			addTile();
			game.board[n] = tiles;
			repaint();
			
			if(game.checkWin(flip(game.player))) {
				game.playing = false;
				game.add(game.resetButton);
				
				if(game.player == 2) {
					game.message = "Yellow player Won!";
				}
				if(game.player == 1) {
					game.message = "Red player Won!";
				}
			}
			
			game.repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this) {
			click();
		}
	}
	
}
