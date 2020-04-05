package chess;

import java.util.*;

import chess.pieces.*;
import chess.playboard.*;

public class Application {

    public static void main(String[] args) {
        Game game = new Game();
        boolean isGameOver = false;
        String currentPlayer = "player1";
        Scanner scanner = new Scanner(System.in);
        String input;

        while (!isGameOver) {
            try {
                printMessageBeforeEveryMove(game, currentPlayer);
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("Q") || input.equalsIgnoreCase("QUIT")) {
                    isGameOver = true;
                    System.out.println("===== GAME HAS ENDED =====");
                } else if (input.equalsIgnoreCase("R") ||
                        input.equalsIgnoreCase("RESTART")) {
                    game = new Game();
                    System.out.println("===== GAME RESTARTED =====");
                } else if (input.equalsIgnoreCase("M") || input.equalsIgnoreCase("MOVE")) {
                    Location newLocation = getNewLocation();
                    Piece currentPiece = getCurrentPiece(game, currentPlayer);
                    King king;

                    if (currentPlayer.equals("player1")) {
                        king = game.getKingOfPlayer1();
                    } else {
                        king = game.getKingOfPlayer2();
                    }
                    Piece threatForKing = king.getPieceThreateningKing();
                    if (threatForKing != null) {
                        System.out.println("Your King is in Check from piece at: (" +
                                threatForKing.getLocation().getRow() + ", " +
                                threatForKing.getLocation().getCol() + ").");
                    }
                    if (currentPiece.moveToIfPossible(newLocation)) {
                        currentPlayer = (currentPlayer.equalsIgnoreCase("player1")) ? "player2" : "player1";
                    } else {
                        System.out.println("Move was invalid, try again.");
                    }
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Couldn't parse input.");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("NullPointerException :(, GL Debugging");
                e.printStackTrace();
            }
        }
    }

    private static void printMessageBeforeEveryMove(Game game, String currentPlayer) {
        System.out.println(game.getTurnHandler().toString());
        System.out.println(currentPlayer + "'s Turn:");
        System.out.println("M - Move a piece");
        System.out.println("Q - Quit game");
        System.out.println("R - Reset the game");
    }

    private static Piece getCurrentPiece(Game chessGame, String currentPlayer) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Location currentLocation;
        Piece currentPiece;

        while (true) {
            System.out.println("Move from: row, col");
            input = scanner.nextLine();
            currentLocation = createChessLocation(input);
            if (!currentLocation.isInBoardBounds()) {
                System.out.println("Location not on board, try again.");
                continue;
            }
            currentPiece = chessGame.getTurnHandler().getPieceAt(currentLocation);
            if (currentPiece == null) {
                System.out.println("Invalid piece selected, out of bounds.");
            } else if (currentPiece.getOwner().equalsIgnoreCase(currentPlayer)) {
                return currentPiece;
            } else {
                System.out.println("Invalid piece selected, not your piece.");
            }
        }
    }

    private static Location getNewLocation() {
        Scanner scanner = new Scanner(System.in);
        String input;

        Location newLocation;

        while (true) {
            System.out.println("Move to: row, col");
            input = scanner.nextLine();
            newLocation = createChessLocation(input);
            if (!newLocation.isInBoardBounds()) {
                System.out.println("Invalid location selected, out of bounds.");
            } else {
                return newLocation;
            }
        }
    }

    private static Location createChessLocation(String input) {
        int row = Integer.parseInt(input.split(",")[0].trim());
        int col = Integer.parseInt(input.split(",")[1].trim());
        return new Location(row, col);
    }
}
