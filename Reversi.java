package reversi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Random;


public class Reversi {
    Board board1 = new Board('w');
    Board board2 = new Board('b');
    JFrame frame1 = new JFrame();
    JFrame frame2 = new JFrame();
    String yourTurn= " Player - click a square to place your piece";
    String notYourTurn = " Player - not your turn";
    JLabel wLabel = new JLabel("White"+yourTurn);
    JLabel bLabel = new JLabel("Black"+notYourTurn);

    char turn = 'w';

    public void start(Board player1, Board player2){
        int i;
        player1.fillBoard();
        player1.setPlaceable();
        player2.fillBoard();
        player2.reverseBoard();
        player2.setPlaceable();
        for(i=0;i<64;i++){
            player1.squareArray[i].addActionListener(new SquarePressed(player1));
            player2.squareArray[i].addActionListener(new SquarePressed(player2));
        }
        this.createGUI(frame1,wLabel,player1, "White");
        this.createGUI(frame2,bLabel,player2, "Black");
    }

    public void createGUI(JFrame guiFrame,JLabel label,Board player,String playerCol){
        int i;
        JPanel panel = new JPanel();
        JButton button = new JButton("Greedy AI (play " +playerCol+ ")");
        button.setActionCommand(playerCol);
        button.addActionListener(new GreedyPressed());
        panel.setLayout(new GridLayout(8,8));
        for(i=0;i<64;i++){
            panel.add(player.squareArray[i]);
        }
        guiFrame.setTitle("Reversi - " + playerCol + " Player");
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.getContentPane().setLayout(new BorderLayout());
        guiFrame.getContentPane().add(label, BorderLayout.NORTH);
        guiFrame.getContentPane().add(panel);
        guiFrame.getContentPane().add(button,BorderLayout.SOUTH);
        guiFrame.pack();
        guiFrame.setLocationRelativeTo(null);
        guiFrame.setVisible(true);
    }

    public void update(Board player1, Board player2){
        int i;
        for(i=0;i<64;i++){
            if(turn==player1.player){
                player2.squareArray[63-i].captured(player1.squareArray[i].getState());
            }
            if(turn==player2.player){
                player1.squareArray[i].captured(player2.squareArray[63-i].getState());
            }
        }
    }

    public class SquarePressed implements ActionListener{
        
        Board board;
        public SquarePressed(Board player){
            board=player;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            Square source=(Square) e.getSource();
            if (source.state=='e'){
                source.setState(turn);
                if(source.state==turn){
                    board.capture(source.index);
                    Reversi.this.update(board1,board2);
                    board1.changeTurn();
                    board2.changeTurn();
                    if(turn=='w'){
                        turn='b';
                        wLabel.setText("White" + notYourTurn);
                        bLabel.setText("Black" + yourTurn);
                    }
                    else{
                        turn='w';
                        wLabel.setText("White" + yourTurn);
                        bLabel.setText("Black" + notYourTurn);
                    }
                }
                board1.setPlaceable();
                board2.setPlaceable();
                frame1.repaint();
                frame2.repaint();
            }
        }
    }

    public class GreedyPressed implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            char greed = e.getActionCommand().toLowerCase().charAt(0);
            Square greedySq;
            if(greed == turn){
                if(greed=='w'){
                    greedySq=board1.squareArray[board1.greedy];
                    if (greedySq.state=='e'){
                        greedySq.setState(turn);
                    }
                    if(greedySq.state==turn){
                        board1.capture(board1.greedy);
                        Reversi.this.update(board1,board2);
                        turn='b';
                        wLabel.setText("White" + notYourTurn);
                        bLabel.setText("Black" + yourTurn);
                    }
                }
                if(greed=='b'){
                    greedySq=board2.squareArray[board2.greedy];
                    if (greedySq.state=='e'){
                        greedySq.setState(turn);
                    }
                    if(greedySq.state==turn){
                        board2.capture(board2.greedy);
                        Reversi.this.update(board1,board2);
                        turn='w';
                        wLabel.setText("White" + yourTurn);
                        bLabel.setText("Black" + notYourTurn);
                    }
                    
                }

                System.out.println(1);
                System.out.println(board1.greedy);
                System.out.println(board1.squareArray[board1.greedy].state);
                board1.changeTurn();
                board2.changeTurn();
                System.out.println(board1.squareArray[board1.greedy].state);
                board1.setPlaceable();
                board2.setPlaceable();
                System.out.println(board1.squareArray[board1.greedy].state);
                frame1.repaint();
                frame2.repaint();
                System.out.println(board1.squareArray[board1.greedy].state);
            } 
        }
    }
}
    
