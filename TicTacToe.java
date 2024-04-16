import java.util.ArrayList;
import java.util.List;

public class TicTacToe {
    public static String[][] board = {
            {"", "", ""},
            {"", "", ""},
            {"", "", ""}
    };

    public static List<int[]> availableMoves(String[][] board) {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!board[i][j].equals("X") && !board[i][j].equals("O")) {
                    moves.add(new int[]{i, j});
                }
            }
        }
        return moves;
    }

    public static boolean hasWon(String[][] board, String player) {
        for (String[] row : board) {
            if (countOccurrences(row, player) == 3) return true;
        }

        for (int i = 0; i < 3; ++i) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player))
                return true;
        }

        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
            return true;
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))
            return true;

        return false;
    }

    private static int countOccurrences(String[] array, String player) {
        int count = 0;
        for (String element : array) {
            if (element != null && element.equals(player)) count++;
        }
        return count;
    }


    public static boolean doMove(String[][] board, int[] move, String player) {
        int row = move[0];
        int col = move[1];

        if (!board[row][col].equals("X") && !board[row][col].equals("O")) {
            board[row][col] = player;
            return true;
        }

        return false;
    }

    public static boolean isGameOver(String[][] board) {
        return hasWon(board, "X") || hasWon(board, "O") || availableMoves(board).isEmpty();
    }

    public static int evaluate(String[][] board) {
        if (hasWon(board, "X")) return 1;
        if (hasWon(board, "O")) return -1;
        return 0;
    }

    private static String[][] copyBoard(String[][] original) {
        String[][] copy = new String[3][3];
        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original[i].length; j++) {
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }

    private static void printBoard(String[][] board) {
        for (String[] row : board) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static Object[] minimax(String[][] inBoard, boolean isMaximizing, int depth) {
        if (depth == 0 || isGameOver(inBoard)) return new Object[]{evaluate(inBoard), new int[0]};

        int[] bestMove = new int[2];
        int bestValue;
        String symbol;

        if (isMaximizing) {
            bestValue = Integer.MIN_VALUE;
            symbol = "X";
        } else {
            bestValue = Integer.MAX_VALUE;
            symbol = "O";
        }

        for (int[] move : availableMoves(inBoard)) {
            String[][] newBoard = copyBoard(inBoard);
            doMove(newBoard, move, symbol);
            int hypotheticalValue = (int) minimax(newBoard, !isMaximizing, depth - 1)[0];

            if ((isMaximizing && hypotheticalValue > bestValue) || (!isMaximizing && hypotheticalValue < bestValue)) {
                bestValue = hypotheticalValue;
                bestMove = move;
            }
        }

        return new Object[]{bestValue, bestMove};
    }

    public static void resetGame() {
        board = new String[][]{
                {"", "", ""},
                {"", "", ""},
                {"", "", ""}
        };
    }
}
