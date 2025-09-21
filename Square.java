package reversi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

public class Square extends JButton{

    Color drawColor;
    Color borderColor;
    int borderSize;
    char state;
    char player;
    Reversi game;
    boolean placeable;
    int space;

    public Square(
            char selState,
            int width, int height,
            int borderWidth,
            Color color,
            Color borderCol,
            Reversi reversi,
            char player,
            int space
    ){
        state = selState;
        borderSize = borderWidth;
		drawColor = color;
		borderColor = borderCol;
        setMinimumSize( new Dimension(width, height) );
		setPreferredSize( new Dimension(width, height) );
        game = reversi;
        this.addActionListener(reversi.new SquarePressed());
        this.player = player;

        if(player == 'w') {
            this.space = space;
        }
        else{
            this.space = reversi.board.spaces.length - 1 - space;
        }
    }

    public int getSpace() { return space;}

    public char getPlayer() {return player; }

    public void updateSquare (Space space){
        this.state = space.getState();
        if (game.getTurn() == player){
            this.placeable = space.getPlaceable();
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(borderColor);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(drawColor);
        g.fillRect(borderSize, borderSize, getWidth()-borderSize*2, getHeight()-borderSize*2);

        if (state == 'e'){

            if (player == 'w' && placeable && game.getTurn() == 'w')
                g.setColor(Color.WHITE);
            else if (player == 'b' && placeable && game.getTurn() == 'b')
                g.setColor(Color.BLACK);

            g.fillOval(borderSize+1, borderSize+1, getWidth()-(borderSize+1)*2, getHeight()-(borderSize+1)*2);
            g.setColor(drawColor);
        }
        else if (state=='b'){
            g.setColor(Color.WHITE);
            g.fillOval(borderSize+1, borderSize+1, getWidth()-(borderSize+1)*2, getHeight()-(borderSize+1)*2);
            g.setColor(Color.BLACK);
        }
        else if (state=='w'){
            g.setColor(Color.BLACK);
            g.fillOval(borderSize+1, borderSize+1, getWidth()-(borderSize+1)*2, getHeight()-(borderSize+1)*2);
            g.setColor(Color.WHITE);
        }

        g.fillOval(borderSize+5, borderSize+5, getWidth()-(borderSize+5)*2, getHeight()-(borderSize+5)*2);
    }


}
