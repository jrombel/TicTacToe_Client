package tictactoe;

import java.io.Serializable;

public class Board implements Serializable {

    private static Board instance = null;

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    protected Board() {
        fields = new int[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = 0;
        }
    }

    public int[] fields;
    // 0 - empty
    // 1 - X
    // 2 - O
}
