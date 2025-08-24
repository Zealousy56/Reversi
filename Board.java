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
                squareArray[i] = new Square('w',50, 50,Color.GREEN, 5, Color.BLACK,i);
            }
            else if(i==28 | i==35){
                squareArray[i] = new Square('b',50, 50,Color.GREEN, 5, Color.BLACK,i);
            }
            else{
                squareArray[i] = new Square('e',50, 50,Color.GREEN, 5, Color.BLACK,i);
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
        int counter1=0;
        for(i=0;i<64;i++){
            squareArray[i].resetPlace();
        }
        if(turn==player){
            for(i=0;i<64;i++){
                if(squareArray[i].state!='e' && squareArray[i].state==turn){
                    if(i-9 >= 0 && (i-9) % 8 != 0 && squareArray[i-9].state != 'e' && squareArray[i-9].state != turn){
                        j=i-9;
                        counter1+=1;
                        while(j>=0 && j % 8 != 7){
                            counter1++;
                            if (squareArray[j].state=='e'){
                                squareArray[j].setPlace(turn);
                                if(counter1>=counter){
                                    this.counter=counter1;
                                    greedy=j;
                                    counter1=0;
                                }
                                break;
                            }
                            j-=9;
                        }
                        counter1=0;
                    }
                    if(i-8 >= 0 && squareArray[i-8].state != 'e' && squareArray[i-8].state != turn){
                        j=i-8;
                        counter+=1;
                        while(j>=0){
                            
                            counter1++;
                            if (squareArray[j].state=='e'){
                                squareArray[j].setPlace(turn);
                                if(counter1>=counter){
                                    this.counter=counter1;
                                    greedy=j;
                                    counter1=0;
                                }
                                break;
                            }
                            j-=8;
                        }
                        counter1=0;
                    }
                    if(i-7 >= 0 && (i-7) % 8 != 0 && squareArray[i-7].state != 'e' && squareArray[i-7].state != turn){
                        j=i-7;
                        counter1+=1;
                        while(j>=0 && (i-7) % 8 != 0){
                            
                            counter1++;
                            if (squareArray[j].state=='e'){
                                squareArray[j].setPlace(turn);
                                if(counter1>=counter){
                                    this.counter=counter1;
                                    greedy=j;
                                    counter1=0;
                                }
                                break;
                            }
                            j-=7;
                        }
                        counter1=0;
                    }
                    if(i-1 >= 0 && (i-1) % 8 != 0 && squareArray[i-1].state != 'e' && squareArray[i-1].state != turn){
                        j=i-1;
                        counter1+=1;
                        while(j>=0 && j % 8 != 7){
                            counter1++;
                            if (squareArray[j].state=='e'){
                                squareArray[j].setPlace(turn);
                                if(counter1>=counter){
                                    this.counter=counter1;
                                    greedy=j;
                                    counter1=0;
                                }
                                break;
                            }
                            j-=1;
                        }
                        counter1=0;
                    }
                    if(i+1 <= 63 && (i+1) % 8 != 7 && squareArray[i+1].state != 'e' && squareArray[i+1].state != turn){
                        j=i+1;
                        counter1+=1;
                        while(j<=63 && j % 8 != 0){
                            
                            counter1++;
                            if (squareArray[j].state=='e'){
                                squareArray[j].setPlace(turn);
                                if(counter1>=counter){
                                    this.counter=counter1;
                                    greedy=j;
                                    counter1=0;
                                }
                                break;
                            }
                            j+=1;
                        }
                        counter1=0;
                    }
                    if(i+7 <= 63 && (i+7) % 8 != 0 && squareArray[i+7].state != 'e' && squareArray[i+7].state != turn){
                        j=i+7;
                        counter1+=1;
                        while(j<=63 && j % 8 != 7){
                            
                            counter1++;
                            if (squareArray[j].state=='e'){
                                squareArray[j].setPlace(turn);
                                if(counter1>=counter){
                                    this.counter=counter1;
                                    greedy=j;
                                    counter1=0;
                                }
                                break;
                            }
                            j+=7;
                        }
                        counter1=0;
                    }
                    if(i+8 <= 63 && squareArray[i+8].state != 'e' && squareArray[i+8].state != turn){
                        j=i+8;
                        counter1+=1;
                        while(j<=63){
                            counter1++;
                            if (squareArray[j].state=='e'){
                                squareArray[j].setPlace(turn);
                                if(counter1>=counter){
                                    this.counter=counter1;
                                    greedy=j;
                                    counter1=0;
                                }
                                break;
                            }
                            j+=8;
                        }
                        counter1=0;
                    }
                    if(i+9 <= 63 && (i+9) % 8 != 7 && squareArray[i+9].state != 'e' && squareArray[i+9].state != turn){
                        j=i+9;
                        counter1+=1;
                        while(j<=63 && j % 8 != 0){
                            
                            counter1++;
                            if (squareArray[j].state=='e'){
                                squareArray[j].setPlace(turn);
                                if(counter1>=counter){
                                    this.counter=counter1;
                                    greedy=j;
                                    counter1=0;
                                }
                                break;
                            }
                            j+=9;
                        }
                        counter1=0;
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
