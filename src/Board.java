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

    public void randomOneTile() {
        List<Tile> flat = new LinkedList<>();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (board[i][j].getId() == 0) {
                    flat.add(board[i][j]);
                }
            }
        }
        flat.get(random.nextInt(flat.size())).setId(2);

    }

    public void top() {
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                for (int k = size - 1; k > i; k--) {
                    board[k][j].moveTo(board[k - 1][j]);
                }
            }
            for (int i = 0; i < size - 1; i++) {
                board[i][j].merge(board[i + 1][j]);
            }
            for (int i = 0; i < size; i++) {
                for (int k = size - 1; k > i; k--) {
                    board[k][j].moveTo(board[k - 1][j]);
                }
            }
        }
    }
    public void left() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = size - 1; k > j; k--) {
                    board[i][k].moveTo(board[i][k - 1]);
                }
            }
            for (int j = 0; j < size - 1; j++) {
                board[i][j].merge(board[i][j + 1]);
            }
            for (int j = 0; j < size; j++) {
                for (int k = size - 1; k > j; k--) {
                    board[i][k].moveTo(board[i][k - 1]);
                }
            }
        }
    }
    public void down() {
        for (int j = 0; j < size; j++) {
            for (int i = size - 1; i >= 0; i--) {
                for (int k = 0; k < i; k++) {
                    board[k][j].moveTo(board[k + 1][j]);
                }
            }
            for (int i = size - 1; i > 0; i--) {
                board[i][j].merge(board[i - 1][j]);
            }
            for (int i = size - 1; i >= 0; i--) {
                for (int k = 0; k < i; k++) {
                    board[k][j].moveTo(board[k + 1][j]);
                }
            }
        }
    }
    public void right() {
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j > 0; j--) {
                for (int k = 0; k < j; k++) {
                    board[i][k].moveTo(board[i][k + 1]);
                }
            }
            for (int j = size - 1; j > 0; j--) {
                board[i][j].merge(board[i][j - 1]);
            }
            for (int j = size - 1; j > 0; j--) {
                for (int k = 0; k < j; k++) {
                    board[i][k].moveTo(board[i][k + 1]);
                }
            }
        }
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
}
