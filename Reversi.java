package reversi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Reversi {
    Board board = new Board('w');
    Board board2 = new Board('b');
    JFrame frame1 = new JFrame();
    JFrame frame2 = new JFrame();
    String yourTurn= " Player - click a square to place your piece";
    String notYourTurn = " Player - not your turn";
    JLabel wLabel = new JLabel("White"+yourTurn);
    JLabel bLabel = new JLabel("Black"+notYourTurn);

    char turn = 'w';

    public void startPVP(){
        board.fillBoard(this);
        board2.fillBoard(this);

        board.setPlaceable(turn);
        board2.setPlaceable(turn);

        board2.reverseBoard();

        this.createGUI(frame1,wLabel,board, "White");
        this.createGUI(frame2,bLabel,board2, "Black");
    }

    public void startCOM(){
        board.fillBoard(this);
        board.setPlaceable(turn);
        changeTurn();

        this.createGUI(frame1,wLabel,board, "White");
    }

    public void changeTurn(){
        if (turn == 'w')
            this.turn='b';
        else
            this.turn='w';
    }

    public void update(){
        int i;
        for(i=0;i<64;i++){
            if(turn==board.player){
                board2.squareArray[63-i].captured(board.squareArray[i].getState());
            }
            if(turn== board2.player){
                board.squareArray[i].captured(board2.squareArray[63-i].getState());
            }
        }
    }

    public void createGUI(JFrame guiFrame,JLabel label,Board player,String playerCol){
        int i;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8,8));
        for(i=0;i<64;i++){
            panel.add(player.squareArray[i]);
        }
        guiFrame.setTitle("Reversi - " + playerCol + " Player");
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.getContentPane().setLayout(new BorderLayout());
        guiFrame.getContentPane().add(label, BorderLayout.NORTH);
        guiFrame.getContentPane().add(panel);
        guiFrame.pack();
        guiFrame.setLocationRelativeTo(null);
        guiFrame.setVisible(true);
    }

    public class SquarePressed implements ActionListener {

        Board curPlayer;
        public SquarePressed(Board player){
            curPlayer = player;
        }

        public void actionPerformed(ActionEvent e) {
            Square source = (Square) e.getSource();
            if (source.state == 'e') {
                source.placePiece(turn);
                if (source.state == turn) {
                    curPlayer.capture(source.index, turn);
                    Reversi.this.update();
                    changeTurn();
                    if (turn == 'b') {
                        wLabel.setText("White" + notYourTurn);
                        bLabel.setText("Black" + yourTurn);
                    } else {
                        wLabel.setText("White" + yourTurn);
                        bLabel.setText("Black" + notYourTurn);
                    }
                }
                board.setPlaceable(turn);
                board2.setPlaceable(turn);
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
                greedySq=board.squareArray[board.greedy];
                if (greedySq.state=='e'){
                    greedySq.placePiece(turn);
                }
                if(greedySq.state==turn){
                    board.capture(board.greedy, turn);
                    changeTurn();
                    wLabel.setText("White" + notYourTurn);
                    bLabel.setText("Black" + yourTurn);
                }
            }

            changeTurn();
            board.setPlaceable(turn);
            frame1.repaint();
            frame2.repaint();
        }

    }
}
    
