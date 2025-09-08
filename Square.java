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
    boolean wPlaceable;
    boolean bPlaceable;
    int index;

    public Square(
            char selState,
            int width, int height,
            Color color,
            int borderWidth,
            Color borderCol,
            int pos,
            Reversi reversi,
            char player
    ){
        state = selState;
        wPlaceable=false;
        bPlaceable=false;
        borderSize = borderWidth;
		drawColor = color;
		borderColor = borderCol;
        setMinimumSize( new Dimension(width, height) );
		setPreferredSize( new Dimension(width, height) );
        index = pos;
        if(player == 'w')
            this.addActionListener(reversi.new SquarePressed(reversi.board));
        else
            this.addActionListener(reversi.new SquarePressed(reversi.board2));
    }

    public void placePiece(char turn){
        if (turn=='w' & wPlaceable){
            this.state = turn;
        }
        else if(turn=='b' & bPlaceable){
            this.state = turn;
        }
    }

    public char getState(){
        return this.state;
    }

    public void captured(char state){
        this.state=state;
    }

    public void setPlaceable(char place){
        if (place=='w'){
            this.wPlaceable=true;
            this.bPlaceable=false;
        }
        else{
            this.bPlaceable=true;
            this.wPlaceable=false;
        }
    }

    public void resetPlace(){
        this.wPlaceable=false;
        this.bPlaceable=false;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(borderColor);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(drawColor);
        g.fillRect(borderSize, borderSize, getWidth()-borderSize*2, getHeight()-borderSize*2);

        if (state == 'e'){

            if (wPlaceable)
                g.setColor(Color.WHITE);
            else if (bPlaceable)
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
