package conversionToLinux.testFolder;
// https://www.youtube.com/watch?v=0MqWS9nkN9E


import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;

public class CircleGui extends JFrame {
	
	public CircleGui(){
		setTitle("CircleGui");
		setSize(960, 960);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawOval(480, 480, 200, 200);
		g.fillOval(480, 480, 200, 200);
		g.setColor(Color.ORANGE);
		g.fillOval(240, 240, 200, 100);
	}

	public static void main(String[] args) {
		CircleGui c = new CircleGui();
		c.paint(null);
	}

}
