package reversi;

import java.awt.Color;


public class Board {
    Square[] squareArray = new Square[64];
    char player;
    char turn = 'w';
    int counter=0;
    int greedy=29;

    public Board(char play){
        player=play;
    }

    public void changeTurn(){
        if (turn == 'w')
            this.turn='b';
        else
            this.turn='w';
    }

    public void fillBoard(){
        int i;
        for(i=0;i<64;i++)
        {
            if (i==27 | i==36){
                squareArray[i] = new Square('w',50, 50, new Color(245, 200, 150), 5, Color.BLACK,i);
            }
            else if(i==28 | i==35){
                squareArray[i] = new Square('b',50, 50, new Color(245, 200, 150), 5, Color.BLACK,i);
            }
            else{
                squareArray[i] = new Square('e',50, 50, new Color(245, 200, 150), 5, Color.BLACK,i);
            }
        }
    }
    
    public void reverseBoard(){
        for (int i=0; i < 64/2;i++){
            squareArray[i].index=64-1-i;
            squareArray[64-1-i].index=i;
            Square temp = squareArray[i];
            squareArray[i]=squareArray[64-1-i];
            squareArray[64-1-i]=temp;   
        }
    }
    public void setPlaceable(){
        int i;
        int j;
        int index;
        int counter1=0;
        int[] adj= {9, 8, 7, 1, -1, -7, -8, -9};

        for(i=0;i<64;i++){
            squareArray[i].resetPlace();
        }

        if(turn==player){
            for(i=0;i<64;i++){
                if(squareArray[i].state!='e' && squareArray[i].state==turn){
                    for(index = 0; index < adj.length; index++) {

                        j = i + adj[index];

                        if (j >= 0 && j <= 63 && squareArray[j].state != 'e' && squareArray[j].state != turn) {
                            if (((adj[index] == 9 || adj[index] == -7) && j % 8 == 0) || ((adj[index] == -9 || adj[index] == 7) && j % 8 == 7))
                                continue;

                            counter1++;

                            while (j >= 0 && j <= 63) {
                                if ((adj[index] == 9 || adj[index] == -7) && j % 8 == 0)
                                    break;

                                if ((adj[index] == -9 || adj[index] == 7) && j % 8 == 7)
                                    break;

                                if (squareArray[j].state == turn)
                                    break;

                                counter1++;
                                if (squareArray[j].state == 'e') {
                                    squareArray[j].setPlace(turn);
                                    if (counter1 >= counter) {
                                        this.counter = counter1;
                                        greedy = j;
                                    }
                                    break;
                                }
                                j += adj[index];
                            }
                            counter1 = 0;
                        }
                    }
                }
            }
        }
    }

    public void capture(int index){
        int i=index;
        i+=9;
        while (i>=0 && i<64 && squareArray[i].state != 'e'){
            if(squareArray[i].state == turn){
                i-=9;
                while(squareArray[i].state != turn){
                    squareArray[i].captured(turn);
                    i-=9;
                }
                break;
            }
            i+=9;
        }
        
        i=index;
        i+=8;
        while (i>=0 && i<64 && squareArray[i].state != 'e'){
            if(squareArray[i].state == turn){
                i-=8;
                while(squareArray[i].state != turn){
                    squareArray[i].captured(turn);
                    i-=8;
                }
                break;
            }
            i+=8;
        }
        
        i=index;
        i+=7;
        while (i>=0 && i<64 && squareArray[i].state != 'e'){
            if(squareArray[i].state == turn){
                i-=7;
                while(squareArray[i].state != turn){
                    squareArray[i].captured(turn);
                    i-=7;
                }
                break;
            }
            i+=7;
        }

        i=index;
        i+=1;
        while (i>=0 && i<64 && squareArray[i].state != 'e'){
            if(squareArray[i].state == turn){
                i-=1;
                while(squareArray[i].state != turn){
                    squareArray[i].captured(turn);
                    i-=1;
                }
                break;
            }
            i+=1;
        }

        i=index;
        i-=1;
        while (i>=0 && i<64 && squareArray[i].state != 'e' ){
            if(squareArray[i].state == turn){
                i+=1;
                while(squareArray[i].state != turn){
                    squareArray[i].captured(turn);
                    i+=1;
                }
                break;
            }
            i-=1;
        }

        i=index;
        i-=7;
        while (i>=0 && i<64 && squareArray[i].state != 'e'){
            if(squareArray[i].state == turn){
                i+=7;
                while(squareArray[i].state != turn){
                    squareArray[i].captured(turn);
                    i+=7;
                }
                break;
            }
            i-=7;
        }

        i=index;
        i-=8;
        while (i>=0 && i<64 && squareArray[i].state != 'e'){
            if(squareArray[i].state == turn){
                i+=8;
                while(squareArray[i].state != turn){
                    squareArray[i].captured(turn);
                    i+=8;
                }
                break;
            }
            i-=8;
        }

        i=index;
        i-=9;
        while (i>=0 && i<64 && squareArray[i].state != 'e'){
            if(squareArray[i].state == turn){
                i+=9;
                while(squareArray[i].state != turn){
                    squareArray[i].captured(turn);
                    i+=9;
                }
                break;
            }
            i-=9;
        }
    }
}
