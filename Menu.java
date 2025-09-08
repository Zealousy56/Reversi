package reversi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    Reversi reversi = new Reversi();
    JFrame frame = new JFrame();

    public void createGUI(){

        JLabel choose = new JLabel("Choose Mode");
        JPanel panel = new JPanel();
        JButton button = new JButton("Player vs COM");
        JButton button1 = new JButton("Player vs Player");

        button.setActionCommand("c");
        button1.setActionCommand("p");
        button.addActionListener(new Menu.ModePressed());
        button1.addActionListener(new Menu.ModePressed());

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

    public class ModePressed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){

            if (e.getActionCommand().charAt(0) == 'c')
                reversi.startCOM();

            else{
                reversi.startPVP();
            }
        }
    }
}
