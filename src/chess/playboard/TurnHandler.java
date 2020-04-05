package chess.playboard;

import chess.pieces.ChessPiece;
import chess.pieces.Location;

/**
 * The Chessboard class is responsible for manipulating the board
 * by moving and removing pieces.
 */
public class TurnHandler {
    private ChessPiece[][] board;

    /**
     * Creates the board instance.
     */
    public TurnHandler() {
        board = new ChessPiece[8][8];
    }

    /**
     * Checks if piece is at a specificed location.
     * @param row Row of the location.
     * @param col Col of the location.
     * @return Boolean of if theres a piece on location or not.
     */
    public boolean isPieceAt(int row, int col) {
        return board[row][col] != null;
    }

    /**
     * Places piece at location, if piece already exisit there, the old piece will be overwritten.
     * @param piece The piece to move.
     * @param location The location to move to.
     */
    public void placePieceAt(ChessPiece piece, Location location) {
        if (isPieceAt(location.getRow(), location.getCol())) {
            removePieceAt(location);
        }
        if (piece.getChessLocation() != null) {
            removePieceAt(piece.getChessLocation());
        }
        board[location.getRow()][location.getCol()] = piece;
        piece.setChessLocation(location);
    }

    /**
     * Removes piece at location.
     * @param location The location to remove at.
     */
    private void removePieceAt(Location location) {
        board[location.getRow()][location.getCol()] = null;
    }

    /**
     * Checks if the location is within the board.
     * @param location The location to check.
     * @return Boolean of if the location is in bounds or not.
     */
    public static boolean locationInBounds(Location location) {
        return location.getRow() >= 0 && 
               location.getRow() < 8 && 
               location.getCol() >= 0 &&
               location.getCol() < 8;
    }

    /**
     * Gets piece at location of board.
     * @param location Location to find piece at.
     * @return Piece at location/
     */
    public ChessPiece getPieceAt(Location location) {
        return board[location.getRow()][location.getCol()];
    }

    /**
     * Displays board with P being Piece.
     */
    @Override
    public String toString() {
        String s = "  0 1 2 3 4 5 6 7\n";
        for (int row = 0; row < 8; row++) {
            s += row;
            for (int col = 0; col < 8; col++) {
                if (board[row][col] != null) {
                    s += " " + board[row][col].getId();
                } else {
                    s += " -";
                }
            }
            s += "\n";
        }
        return s;
    }
}
