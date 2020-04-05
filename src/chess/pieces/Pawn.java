package chess.pieces;

import chess.playboard.Initializer;

public class Pawn extends ChessPiece {

    private boolean firstMove;
    private int one;

    /** 
     * Constructs a new Pawn piece.
     * @param owner Owner string.
     * @param initialLocation Location to set Pawn in.
     * @param game Game that the Pawn belongs too.
     */
    public Pawn(String owner, Location initialLocation, Initializer game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            id = 'P';
            one = 1;
        } else if (owner.equalsIgnoreCase("player2")) {
            id = 'p';
            one = -1;
        }
        firstMove = true;
    }

    /** Checks if more is valid for Pawns, then moves the piece.
     * @return Valid move or not.
     */
    @Override
    public boolean moveTo(Location location) {
        if (location.getCol() == chessLocation.getCol()) {
            if (location.getRow() - chessLocation.getRow() == one) {
                if (firstMove) {
                    firstMove = false;
                }
                return !chessGame.getChessBoard().isPieceAt(location.getRow(), location.getCol()) && super.moveTo(location);
            } else if (firstMove && (location.getRow() - chessLocation.getRow() == (one * 2))) {
                if (firstMove) {
                    firstMove = false;
                }
                return !chessGame.getChessBoard().isPieceAt(location.getRow(), location.getCol()) && super.moveTo(location);
            }
        } else if (Math.abs(location.getCol() - chessLocation.getCol()) == 1) {
            if (chessGame.getChessBoard().isPieceAt(location.getRow(), location.getCol()) &&
                location.getRow() - chessLocation.getRow() == one) {

                if (firstMove) {
                    firstMove = false;
                }
                return super.moveTo(location);
            }
        }
        return false;
    }

    /**
     * Updates the threatening locations.
     */
    @Override
    protected void updateThreateningLocation() {
        int one = 0;
        if (owner.equalsIgnoreCase("player1") &&
            chessLocation.getRow() <= 6) {
            one = 1;
        } else if (owner.equalsIgnoreCase("player2") &&
                    chessLocation.getRow() >= 1) {
            one = -1;
        }

        threateningLocations.clear();

        if (chessLocation.getCol() >= 1) {
            threateningLocations.add(new Location(chessLocation.getRow() + one, chessLocation.getCol() - 1));
        }
        if (chessLocation.getCol() <= 6) {
            threateningLocations.add(new Location(chessLocation.getRow() + one, chessLocation.getCol() + 1));
        }
    }
}
