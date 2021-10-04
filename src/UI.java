import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UI extends JFrame implements KeyListener {

    static boolean isGameOver = false;
    static long score = 0;

    Board board;
    int size;

    JPanel buttonsPanel;
    JLabel scoreLabel;
    JButton[][] jButtons;

    public UI() {
        this.size = 4;
        board = new Board(size);
        int[][] array = board.getArray();

        Font font = new Font("黑体", Font.BOLD, 28);

        scoreLabel = new JLabel("当前的分数为：" + score + "\n");
        scoreLabel.setFont(font);
        scoreLabel.setVerticalTextPosition(JLabel.TOP);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 4, 4, 4));


        jButtons = new JButton[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                jButtons[i][j] = new JButton(String.valueOf(array[i][j]));
                jButtons[i][j].setEnabled(false);
                jButtons[i][j].setFont(font);
                jButtons[i][j].setBackground(Color.PINK);

                buttonsPanel.add(jButtons[i][j]);
            }
        }

        this.add(scoreLabel, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.CENTER);
        this.setSize(400, 420);
        this.setLocation(200, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37:
                score += board.left();
                break;
            case 38:
                score += board.top();
                break;
            case 39:
                score += board.right();
                break;
            case 40:
                score += board.down();
                break;
            default:
                break;
        }
        isGameOver = (board.randomOneTile() == 0);

        if (isGameOver) {
            JOptionPane.showMessageDialog(this, "你的分数为" + score, "游戏结束", JOptionPane.WARNING_MESSAGE);
            board = new Board(size);
            isGameOver = false;
            score = 0;
        }
        updateUI();
    }

    private void updateUI() {
        scoreLabel.setText("当前的分数为：" + score + "\n");
        int[][] array = board.getArray();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                jButtons[i][j].setText(String.valueOf(array[i][j]));
            }
        }
    }

    public static void main(String[] args) {
        new UI();
    }
}
