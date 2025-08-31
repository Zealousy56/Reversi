package reversi;

import javax.swing.*;
import java.awt.*;

public class Menu {
    JFrame frame = new JFrame();

    public void createGUI(){

        JLabel choose = new JLabel("Choose Mode");
        JPanel panel = new JPanel();
        JButton button = new JButton("Player vs COM");
        JButton button1 = new JButton("Player vs Player");

        button.setActionCommand("c");
        button1.setActionCommand("p");
        //button.addActionListener(new Reversi.GreedyPressed());

        panel.setLayout(new GridLayout(1,2));
        panel.add(button);
        panel.add(button1);

        frame.setTitle("Reversi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(choose, BorderLayout.NORTH);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
