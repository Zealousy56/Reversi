package reversi;

public class Space {
    char state;
    boolean placeable = false;
    int index;

    public Space(
            char state,
            int pos
    ){
        this.state = state;
        index = pos;
    }

    public void placePiece(char turn){
        if (turn=='w' & placeable){
            this.state = turn;
        }
        else if(turn=='b' & placeable){
            this.state = turn;
        }
    }

    public char getState(){ return this.state; }

    public boolean getPlaceable(){ return this.placeable; }

    public void captured(char state){
        this.state=state;
    }

    public void setPlaceable(){
        placeable = true;
    }

    public void resetPlaceable(){
        this.placeable = false;
    }
}
