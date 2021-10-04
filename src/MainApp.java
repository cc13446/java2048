import java.util.Scanner;

public class MainApp {

    static boolean isGameOver = false;
    static long score = 0;


    public static void main(String[] args) {
        Board board = new Board(4);
        System.out.println("当前的分数为：" + score + "\n");
        System.out.println(board);

        Scanner scanner = new Scanner(System.in);
        while (!MainApp.isGameOver) {
            String key = scanner.next();
            switch (key) {
                case "a":
                    score += board.left();
                    break;
                case "w":
                    score += board.top();
                    break;
                case "d":
                    score += board.right();
                    break;
                case "s":
                    score += board.down();
                    break;
                default:
                    break;
            }
            isGameOver = (board.randomOneTile() == 0);
            System.out.println("当前的分数为：" + score + "\n");
            System.out.println(board);

        }
        System.out.println("游戏结束");
    }
}
