import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MiniGame extends JFrame {

    private static int ticTacToeDifficultyLevel;
    private JButton[][] buttons;
    private Tamagotchi tamagotchi;
    private boolean isPlayerX;
    private static int gamePicked;

    public MiniGame(Tamagotchi tamagotchi) {
        this.tamagotchi = tamagotchi;
        setTitle("Мини-игры");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        if (gamePicked == 0) {
            setSize(1024, 768);
            setResizable(false);
            setLayout(new GridLayout(3, 3));
            buttons = new JButton[3][3];
            isPlayerX = true;
            initializeButtons();
        }

        SwingUtilities.invokeLater(() -> {
            init();
        });
    }

    private static int showDifficultyDialog() {
        String[] options = { "Легкий", "Средний", "Сложный", "Невозможный" };
        int response = JOptionPane.showOptionDialog(null, "Выберите сложность", "Сложность", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        return response;
    }

    private static int showGameChooser() {
        String[] options = { "Крестики-нолики", "Ударь крота" };
        int response = JOptionPane.showOptionDialog(null, "Выберите игру", "Мини-игры", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        return response;
    }

    private void init() {
        int gameChoice = showGameChooser();
        if (gameChoice != -1) {
            switch (gameChoice) {
                case 0:
                    gamePicked = 0;
                    pickTicTacToe();
                    break;
                case 1:
                    gamePicked = 1;
                    new Whackamole();
                    break;
            }
        } else {
            dispose();
        }
    }

    private void pickTicTacToe() {
        int choice = showDifficultyDialog();
        if (choice != -1) {
            switch (choice) {
                case 1:
                    ticTacToeDifficultyLevel = 3;
                    break;
                case 2:
                    ticTacToeDifficultyLevel = 4;
                    break;
                case 3:
                    ticTacToeDifficultyLevel = 8;
                    break;
                default:
                    ticTacToeDifficultyLevel = 2;
            }
        } else {
            dispose();
            return;
        }
        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton();
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                button.addActionListener(new ButtonClickListener(i, j));
                buttons[i][j] = button;
                add(button);
            }
        }
    }

    private boolean isGameOver() {
        return TicTacToe.hasWon(TicTacToe.board, "X") || TicTacToe.hasWon(TicTacToe.board, "O")
                || TicTacToe.availableMoves(TicTacToe.board).isEmpty();
    }

    private void endGame() {
        String result;
        if (TicTacToe.hasWon(TicTacToe.board, "X")) {
            result = "Вы победили";
        } else if (TicTacToe.hasWon(TicTacToe.board, "O")) {
            result = tamagotchi.getNickname() + " победил";
        } else {
            result = "Ничья";
        }
        JOptionPane.showMessageDialog(this, result);
        resetGame();
    }

    private void playComputerMove() {
        Object[] result = TicTacToe.minimax(TicTacToe.board, false, ticTacToeDifficultyLevel);
        int[] bestMove = (int[]) result[1];
        int row = bestMove[0];
        int col = bestMove[1];
        buttons[row][col].setText("O");
        TicTacToe.doMove(TicTacToe.board, new int[] { row, col }, "O");

        if (isGameOver()) {
            endGame();
        } else {
            isPlayerX = !isPlayerX;
        }
    }

    private void resetGame() {
        TicTacToe.resetGame();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");

            }
        }
        isPlayerX = true;
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (TicTacToe.board[row][col].isEmpty()) {
                String symbol = isPlayerX ? "X" : "O";
                buttons[row][col].setText(symbol);
                TicTacToe.doMove(TicTacToe.board, new int[] { row, col }, symbol);

                if (isGameOver()) {
                    endGame();
                } else {
                    isPlayerX = !isPlayerX;
                    if (!isPlayerX) {
                        playComputerMove();
                    }
                }
            }
        }
    }
}
