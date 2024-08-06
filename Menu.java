import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JFrame {

    private JButton easy;
    private JButton normal;
    private JButton hard;

    private JPanel buttonsPanel;

    public Menu() {
        super("Game Menu");

        easy = new JButton("Easy");
        normal = new JButton("Normal");
        hard = new JButton("Hard");

        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(easy);
        buttonsPanel.add(normal);
        buttonsPanel.add(hard);

        //buttonsPanel.setBackground(Color.BLACK);

        easy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                window w = new window(50, 150);
                w.setVisible(true);
            }
        });

        normal.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                window w = new window(20, 100);
                w.setVisible(true);
            }
        });

        hard.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                window w = new window(10, 50);
                w.setVisible(true);
            }
        });

        add(buttonsPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100); 
        setLocationRelativeTo(null);
        setVisible(true); 
    }
}
