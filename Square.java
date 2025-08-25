package reversi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;

public class Square extends JButton{

    Color drawColor;
    Color borderColor;
    int borderSize;
    char state;
    boolean wPlaceable;
    boolean bPlaceable;
    int index;

    public Square(char selState, int width, int height, Color color,
    int borderWidth, Color borderCol, int pos){
        state = selState;
        wPlaceable=false;
        bPlaceable=false;
        borderSize = borderWidth;
		drawColor = color;
		borderColor = borderCol;
        setMinimumSize( new Dimension(width, height) );
		setPreferredSize( new Dimension(width, height) );
        index = pos;
    }

    public void setState(char state){
        if (state=='w' & wPlaceable){
            this.state=state;
        }
        else if(state=='b' & bPlaceable){
            this.state=state;
        }
    }

    public char getState(){
        return this.state;
    }

    public void captured(char state){
        this.state=state;
    }

    public void setPlace(char place){
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