package reversi;


public class Board {
    Space[] spaces = new Space[64];
    int greedy=29;

    public void fillBoard(){
        int i;
        for(i=0;i<64;i++)
        {
            if (i==27 | i==36){
                spaces[i] = new Space(
                        'w',
                        i
                );
            }
            else if(i==28 | i==35){
                spaces[i] = new Space(
                        'b',
                        i
                );
            }
            else{
                spaces[i] = new Space(
                        'e',
                        i
                );
            }
        }
    }

    public int[] countBoard(){
        int[] noPieces = {0,0};
        for (Space space : spaces) {
            if (space.getState() == 'w') {
                noPieces[0]++;
            }
            if (space.getState() == 'b') {
                noPieces[1]++;
            }
        }
        return noPieces;
    }

    public boolean setPlaceable(char turn){
        int i;
        int j;
        int index;
        int optsCount = 0;
        int counter = 0;
        int counter1 = 0;
        int[] adj= {9, 8, 7, 1, -1, -7, -8, -9};
        boolean moveRight;
        boolean moveLeft;

        for(i=0;i<64;i++){
            spaces[i].resetPlaceable();
        }


        for (i = 0; i < 64; i++) {
            if (spaces[i].state != 'e' && spaces[i].state == turn) {
                for (index = 0; index < adj.length; index++) {

                    j = i + adj[index];

                    if (j >= 0 && j <= 63 && spaces[j].state != 'e' && spaces[j].state != turn) {
                        moveLeft = adj[index] == -9 || adj[index] == 7 || adj[index] == -1;
                        moveRight = adj[index] == 9 || adj[index] == -7 || adj[index] == 1;

                        if ((moveRight && j % 8 == 0) || (moveLeft) && j % 8 == 7)
                            continue;

                        counter1++;

                        while (j >= 0 && j <= 63) {
                            if (moveRight && j % 8 == 0)
                                break;

                            if (moveLeft && j % 8 == 7)
                                break;

                            if (spaces[j].state == turn)
                                break;

                            counter1++;
                            if (spaces[j].state == 'e') {
                                spaces[j].setPlaceable();
                                optsCount++;
                                if (counter1 >= counter) {
                                    counter = counter1;
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

        return optsCount == 0;
    }


    public void capture(int piece, char turn){
        int i;
        int cap;
        int[] adj = {9, 8, 7, 1, -1, -7, -8, -9};

        for(i = 0; i < adj.length; i++) {

            cap = piece;
            cap += adj[i];

            while (cap >= 0 && cap < 64 && spaces[cap].state != 'e') {
                if ((
                        (adj[i] == 9 || adj[i] == -7 || adj[i] == 1) && cap % 8 == 0) ||
                        ((adj[i] == -9 || adj[i] == 7 || adj[i] == -1) && cap % 8 == 7))

                    break;

                if (spaces[cap].state == turn) {
                    cap -= adj[i];
                    while (spaces[cap].state != turn) {
                        spaces[cap].captured(turn);
                        cap -= adj[i];
                    }
                    break;
                }

                cap += adj[i];
            }
        }
    }
}
