package chess.playboard;

import chess.pieces.Piece;
import chess.pieces.Location;

public class TurnHandler {

    private static final int BOARD_SIZE = 8;
    private Piece[][] board;

    public TurnHandler() {
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
    }

    public boolean isPieceAt(int row, int col) {
        return board[row][col] != null;
    }

    public void placePieceAt(Piece piece, Location location) {
        if (isPieceAt(location.getRow(), location.getCol())) {
            removePieceFrom(location);
        }
        if (piece.getLocation() != null) {
            removePieceFrom(piece.getLocation());
        }
        board[location.getRow()][location.getCol()] = piece;
        piece.setLocation(location);
    }

    private void removePieceFrom(Location location) {
        board[location.getRow()][location.getCol()] = null;
    }

    public Piece getPieceAt(Location location) {
        return board[location.getRow()][location.getCol()];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("  0 1 2 3 4 5 6 7\n");
        for (int row = 0; row < BOARD_SIZE; row++) {
            s.append(row);
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] != null) {
                    s.append(" ").append(board[row][col].getType());
                } else {
                    s.append(" -");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }
}
