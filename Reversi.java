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
    String yourTurn = "'s turn - click a square to place your piece";
    String notYourTurn = "'s Turn - not your turn";
    String AITurn = "The Greediest AI is thinking";
    JLabel wLabel = new JLabel("White" + yourTurn);
    JLabel bLabel = new JLabel("Black" + notYourTurn);

    char turn = 'w';

    public void startPVP(){
        board.fillBoard();
        board.setPlaceable(turn);

        screen1 = new Screen('w');
        screen2 = new Screen('b');
        screen1.fillScreen(this, this. new SquarePressedPVP());
        screen2.fillScreen(this, this. new SquarePressedPVP());

        updateScreens(2);

        this.createGUI(screen1, wLabel, "White");
        this.createGUI(screen2, bLabel, "Black");
    }

    public void startCOM(){
        board.fillBoard();
        board.setPlaceable(turn);

        screen1 = new Screen('w');
        screen1.fillScreen(this, this.new SquarePressedCOM());

        updateScreens(1);

        this.createGUI(screen1, wLabel, "White");
    }

    public char getTurn() {return turn; }

    public void changeTurn(){
        if (turn == 'w')
            this.turn='b';
        else
            this.turn='w';
    }

    public void playerMove(Square clickedSquare, Space chosenSpace, int players){
        if (clickedSquare.getPlayer() == turn) {
            if (chosenSpace.state == 'e') {
                chosenSpace.placePiece(turn);

                if (chosenSpace.state == turn) {
                    board.capture(chosenSpace.index, turn);
                    changeTurn();

                    if (turn == 'b') {
                        wLabel.setText(AITurn);
                        if(players == 2) {
                            wLabel.setText("Black" + notYourTurn);
                            bLabel.setText("Black" + yourTurn);
                        }
                    } else {
                        wLabel.setText("White" + yourTurn);
                        bLabel.setText("White" + notYourTurn);
                    }
                }
                board.setPlaceable(turn);
                updateScreens(players);
                screen1.repaint();
                if(players == 2){ screen2.repaint();}
            }
        }
    }

    public void AIMove(){
        Space greedySpace;
        if(turn == 'b'){
            greedySpace = board.spaces[board.greedy];
            if (greedySpace.state == 'e') {
                greedySpace.placePiece(turn);

                if (greedySpace.state == turn) {
                    board.capture(board.greedy, turn);
                    changeTurn();
                    wLabel.setText("White" + yourTurn);
                }
            }
        }

        board.setPlaceable(turn);
        updateScreens(1);
        screen1.repaint();
    }


    public void updateScreens(int players){
        screen1.updateScreen(board);
        if(players == 2){ screen2.updateScreen(board);}
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

    public class SquarePressedPVP implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Square source = (Square) e.getSource();
            Space space = board.spaces[source.getSpace()];
            playerMove(source, space, 2);
        }
    }


    public class SquarePressedCOM implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            Square source = (Square) e.getSource();
            Space space = board.spaces[source.getSpace()];
            playerMove(source, space, 1);

            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException d) {
                    Thread.currentThread().interrupt();
                }
                AIMove();
            }).start();
        }
    }
}
    
