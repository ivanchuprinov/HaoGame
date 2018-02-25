import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Surface extends JPanel {

	private char[][] map;
	private Image plUP;
	private Image plDOWN;
	private Image plLEFT;
	private Image plRIGHT;
	private Image floorTile;
	private Image exitTile;
	private Image wallTile;

	public Surface(char[][] map) {
		this.map = map;
		loadImage();
		setSurfaceSize();
	}

	private void loadImage() {
		plUP = new ImageIcon("PlayerUpTile.png").getImage();
		plDOWN = new ImageIcon("PlayerDownTile.png").getImage();
		plLEFT = new ImageIcon("PlayerLeftTile.png").getImage();
		plRIGHT = new ImageIcon("PlayerRightTile.png").getImage();
		floorTile = new ImageIcon("FloorTile.png").getImage();
		exitTile = new ImageIcon("ExitTile.png").getImage();
		wallTile = new ImageIcon("WallTile.png").getImage();
	}
	
	private void setSurfaceSize() {

		Dimension d = new Dimension();
		d.width = 512;
		d.height = 512;
		setPreferredSize(d);		
	}

	private void doDrawing(Graphics g, char[][] map) {

		Graphics2D g2d = (Graphics2D) g;
		for(int i = 0; i<map.length; i++){
			for(int j = 0; j<map[i].length; j++){
				switch(map[i][j]){
					case ' ':
						g2d.drawImage(floorTile, j*64, i*64, null);
						break;
					case '*':
						g2d.drawImage(wallTile, j*64, i*64, null);
						break;
					case 'E':
						g2d.drawImage(exitTile, j*64, i*64, null);
						break;
					case '^':
						g2d.drawImage(plUP, j*64, i*64, null);
						break;
					case '>':
						g2d.drawImage(plRIGHT, j*64, i*64, null);
						break;
					case 'V':
						g2d.drawImage(plDOWN, j*64, i*64, null);
						break;
					case '<':
						g2d.drawImage(plLEFT, j*64, i*64, null);
						break;
				}
			}
		}
	}


	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		doDrawing(g, map);
		repaint();
	}
}

public class GameClient2d extends JFrame {

	public GameClient2d(char[][] map){

		initUI(map);
	}

	private void initUI(char[][] map) {

		add(new Surface(map));

		pack();
		
		setTitle("Escape Hell");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			int level = 0;
			
			@Override
			public void run() {
				try{
					GameBuilder gam = new GameBuilder(level);
					char[][] map = gam.getGame().getMap();
					
					GameClient2d ex = new GameClient2d(map);
					ex.setVisible(true);
					boolean wonLevel = false;
					
					JButton left = new JButton("Turn left");
					JButton right = new JButton("Turn right");
					JButton move = new JButton("Jump forward");
					JTextField tf = new JTextField();  
					tf.setBounds(50,520, 412,20);
					left.setBounds(50,560,90,45);
					left.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
							gam.turn("left");
							tf.setText("Left turn successful.");
						}
					});
					move.setBounds(191,560,130,45);
					move.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
							int status = gam.move();
							if(status == 1){
								tf.setText("Level complete!");
								level++;
								ex.dispose();
								run();
							}else if(status == 0)
								tf.setText("Move forward successful.");
							else
								tf.setText("Cannot move forward.");
						}
					});
					right.setBounds(372,560,90,45);
					right.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
							gam.turn("right");
							tf.setText("Right turn successful.");
						}
					});
					
					ex.add(left);
					ex.add(move);
					ex.add(right);
					ex.add(tf);
					ex.setSize(528,670);
					ex.setLayout(null);
					ex.setVisible(true);
				}
				catch(ArrayIndexOutOfBoundsException e){
					System.out.println("Congrats! You've won. Thanks for playing.");
					System.exit(0);
				}
			}
		});
		
		
	}
}