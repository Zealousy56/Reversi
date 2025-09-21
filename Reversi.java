package reversi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Reversi {
    Board board = new Board();
    Screen screen1;
    Screen screen2;
    String yourTurn = " Player - click a square to place your piece";
    String notYourTurn = " Player - not your turn";
    JLabel wLabel = new JLabel("White" + yourTurn);
    JLabel bLabel = new JLabel("Black" + notYourTurn);

    char turn = 'w';

    public void startPVP(){
        board.fillBoard();
        board.setPlaceable(turn);

        screen1 = new Screen('w');
        screen2 = new Screen('b');
        screen1.fillScreen(this);
        screen2.fillScreen(this);

        updateScreens();

        this.createGUI(screen1,wLabel, "White");
        this.createGUI(screen2,bLabel, "Black");
    }

    public void startCOM(){
        board.fillBoard();
        board.setPlaceable(turn);

        screen1 = new Screen('w');

        this.createGUI(screen1,wLabel, "White");
    }

    public char getTurn() {return turn; }

    public void changeTurn(){
        if (turn == 'w')
            this.turn='b';
        else
            this.turn='w';
    }

    public void updateScreens(){
        screen1.updateScreen(board);
        screen2.updateScreen(board);
    }


    public void createGUI(Screen guiFrame,JLabel label,String playerCol){
        int i;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8,8));
        for(i=0;i<64;i++){
            panel.add(guiFrame.squares[i]);
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
        public void actionPerformed(ActionEvent e) {
            Square source = (Square) e.getSource();
            Space space = board.spaces[source.getSpace()];
            if (source.getPlayer() == turn) {
                if (space.state == 'e') {
                    space.placePiece(turn);
                    if (space.state == turn) {
                        board.capture(space.index, turn);
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
                    updateScreens();
                    screen1.repaint();
                    screen2.repaint();
                }
            }
        }
    }

/*
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
            screen1.repaint();
            screen2.repaint();
        }

    }*/
}
    
