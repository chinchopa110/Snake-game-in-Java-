import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;

public class window extends JFrame {
    public window(int x, int speed) {
        this.add(new GamePanel(x, speed));
		this.setTitle("Snake");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();

		this.setVisible(true);
		this.setLocationRelativeTo(null);
    }
}


        
        