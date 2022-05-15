package games.icebreaker;

public class Board {
    private int[][] _board;
    private int _size;

    public Board(int size) {
        _size = size;
        _board = new int[2 * size - 1][2 * size - 1];
        resetBoard();
    }

    public int get(int x, int y) {
        if(outOfBound(x, y)) {
            System.out.println("Coordinates out of bound");

            return -1;
        }
        return _board[y][x];
    }

    public void set(int x, int y, int value) {
        if(outOfBound(x, y)) {
            System.out.println("Coordinates out of bound");
            return;
        }
        _board[y][x] = value;
    }

    private boolean outOfBound(int x, int y){
        return x > 2 * _size - 2 || y > 2 * _size - 2 || x < 0 || y < 0 ||
                (x >= _size && ((y <= x - _size) || (y >= 3 * _size - x - 2)));
    }

    private void resetBoard() {
        for(int x = 0; x < 2 * _size - 1; x++) {
            for(int y = 0; y < 2 * _size - 1; y++) {
                if(!outOfBound(x, y)) {
                    _board[y][x] = 1;
                }
            }
        }
        int[][] boats = {{0, 0}, {2 * (_size - 1), _size - 1}, {0, 2 * (_size - 1)}, {_size - 1, 0}, {0, _size - 1}, {_size - 1, 2 * (_size - 1)}};
        for(int[] boat : boats) {
            _board[boat[1]][boat[0]] = 0;
        }
    }
}
