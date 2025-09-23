package reversi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Screen extends JFrame {
    Square[] squares = new Square[64];
    char player;

    public Screen (char player){
        this.player = player;
    }

    public void fillScreen (Reversi game, ActionListener squarePressed){
        int i;
        for(i=0;i<64;i++)
        {
            if (i==27 | i==36){
                squares[i] = new Square(
                        'w',
                        50, 50,
                        5,
                        new Color(245, 200, 150),
                        Color.BLACK,
                        game,
                        squarePressed,
                        player,
                        i
                );
            }
            else if(i==28 | i==35){
                squares[i] = new Square(
                        'b',
                        50, 50,
                        5,
                        new Color(245, 200, 150),
                        Color.BLACK,
                        game,
                        squarePressed,
                        player,
                        i
                );
            }
            else{
                squares[i] = new Square(
                        'e',
                        50, 50,
                        5,
                        new Color(245, 200, 150),
                        Color.BLACK,
                        game,
                        squarePressed,
                        player,
                        i
                );
            }
        }
    }

    public void updateScreen (Board board){
        int i;

        for(i = 0; i < board.spaces.length; i++){
            if (player == 'w'){
                squares[i].updateSquare(board.spaces[i]);
            }
            else{
                squares[board.spaces.length-1-i].updateSquare(board.spaces[i]);
            }
        }
    }
}
