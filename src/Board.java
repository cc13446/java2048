import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Board {

    private Tile[][] board;

    private final int size;

    private Random random;

    public Board(int size) {
        this.size = size;
        this.board = new Tile[this.size][this.size];
        random = new Random();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                board[i][j] = new Tile(0);
            }
        }
        randomOneTile();
        randomOneTile();
    }

    public int randomOneTile() {
        List<Tile> flat = new LinkedList<>();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (board[i][j].getId() == 0) {
                    flat.add(board[i][j]);
                }
            }
        }
        if (flat.size() == 0) {
            return 0;
        }
        // 大概率出2 小概率出4
        int newID = 2;
        if (random.nextInt(100) >= 90) {
           newID = 4;
        }
        flat.get(random.nextInt(flat.size())).setId(newID);
        return flat.size();
    }

    public long top() {
        long res = 0;
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                for (int k = size - 1; k > i; k--) {
                    board[k][j].moveTo(board[k - 1][j]);
                }
            }
            for (int i = 0; i < size - 1; i++) {
                res += board[i][j].merge(board[i + 1][j]);
            }
            for (int i = 0; i < size; i++) {
                for (int k = size - 1; k > i; k--) {
                    board[k][j].moveTo(board[k - 1][j]);
                }
            }
        }
        return res;
    }
    public long left() {
        long res = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = size - 1; k > j; k--) {
                    board[i][k].moveTo(board[i][k - 1]);
                }
            }
            for (int j = 0; j < size - 1; j++) {
                res += board[i][j].merge(board[i][j + 1]);
            }
            for (int j = 0; j < size; j++) {
                for (int k = size - 1; k > j; k--) {
                    board[i][k].moveTo(board[i][k - 1]);
                }
            }
        }
        return res;
    }
    public long down() {
        long res = 0;
        for (int j = 0; j < size; j++) {
            for (int i = size - 1; i >= 0; i--) {
                for (int k = 0; k < i; k++) {
                    board[k][j].moveTo(board[k + 1][j]);
                }
            }
            for (int i = size - 1; i > 0; i--) {
                res += board[i][j].merge(board[i - 1][j]);
            }
            for (int i = size - 1; i >= 0; i--) {
                for (int k = 0; k < i; k++) {
                    board[k][j].moveTo(board[k + 1][j]);
                }
            }
        }
        return res;
    }
    public long right() {
        long res = 0;
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j > 0; j--) {
                for (int k = 0; k < j; k++) {
                    board[i][k].moveTo(board[i][k + 1]);
                }
            }
            for (int j = size - 1; j > 0; j--) {
                res += board[i][j].merge(board[i][j - 1]);
            }
            for (int j = size - 1; j > 0; j--) {
                for (int k = 0; k < j; k++) {
                    board[i][k].moveTo(board[i][k + 1]);
                }
            }
        }
        return res;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                sb.append(board[i][j].toString());
                sb.append("\t");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public int[][] getArray() {
        int[][] array = new int[size][size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                array[i][j] = board[i][j].getId();
            }
        }
        return array;
    }
}
