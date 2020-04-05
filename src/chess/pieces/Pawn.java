package chess.pieces;

import chess.playboard.Game;

public class Pawn extends Piece {

    private boolean firstMove;
    private int one;

    /** 
     * Constructs a new Pawn piece.
     * @param owner Owner string.
     * @param initialLocation Location to set Pawn in.
     * @param game Game that the Pawn belongs too.
     */
    public Pawn(String owner, Location initialLocation, Game game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            type = 'P';
            one = 1;
        } else if (owner.equalsIgnoreCase("player2")) {
            type = 'p';
            one = -1;
        }
        firstMove = true;
    }

    /** Checks if more is valid for Pawns, then moves the piece.
     * @return Valid move or not.
     */
    @Override
    public boolean moveTo(Location location) {
        if (location.getCol() == this.location.getCol()) {
            if (location.getRow() - this.location.getRow() == one) {
                if (firstMove) {
                    firstMove = false;
                }
                return !game.getChessBoard().isPieceAt(location.getRow(), location.getCol()) && super.moveTo(location);
            } else if (firstMove && (location.getRow() - this.location.getRow() == (one * 2))) {
                if (firstMove) {
                    firstMove = false;
                }
                return !game.getChessBoard().isPieceAt(location.getRow(), location.getCol()) && super.moveTo(location);
            }
        } else if (Math.abs(location.getCol() - this.location.getCol()) == 1) {
            if (game.getChessBoard().isPieceAt(location.getRow(), location.getCol()) &&
                location.getRow() - this.location.getRow() == one) {

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
            location.getRow() <= 6) {
            one = 1;
        } else if (owner.equalsIgnoreCase("player2") &&
                    location.getRow() >= 1) {
            one = -1;
        }

        threateningLocations.clear();

        if (location.getCol() >= 1) {
            threateningLocations.add(new Location(location.getRow() + one, location.getCol() - 1));
        }
        if (location.getCol() <= 6) {
            threateningLocations.add(new Location(location.getRow() + one, location.getCol() + 1));
        }
    }
}
