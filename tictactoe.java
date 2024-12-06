import java.util.Scanner;

public class tictactoe {
    final static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    
    public static void main(String[] args) {
        initializeBoard();

        // Try-with-resources to manage Scanner
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                printBoard();
                System.out.println("Player " + currentPlayer + ", enter row and column (0-2): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                
                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Invalid input. Please enter numbers between 0 and 2.");
                    continue;
                }

                if (isCellEmpty(row, col)) {
                    board[row][col] = currentPlayer;
                    
                    if (isWinner()) {
                        printBoard();
                        System.out.println("Player " + currentPlayer + " wins!");
                        break;
                    }
                    
                    if (isBoardFull()) {
                        printBoard();
                        System.out.println("It's a draw!");
                        break;
                    }

                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch player
                } else {
                    System.out.println("Cell is already taken, try again.");
                }
            }
        } // Scanner automatically closed here
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    private static boolean isCellEmpty(int row, int col) {
        return board[row][col] == ' ';
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) || 
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        
        return false;
    }
}

