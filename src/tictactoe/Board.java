package tictactoe;

public class Board {

    public int[] fields;

    public Board() {
        fields = new int[9];
        for (int i = 0; i < 9; i++) {
            fields[0] = 0;
        }
    }
}
