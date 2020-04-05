package chess.pieces;

import chess.playboard.Initializer;

public class Rook extends Piece {
    
    /**
     * Creates a new Rook piece.
     * @param owner Owner string.
     * @param initialLocation Location to set Rook in.
     * @param game Game that the Rook belongs too.
     */
    public Rook(String owner, Location initialLocation, Initializer game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            id = 'R';
        } else if (owner.equalsIgnoreCase("player2")) {
            id = 'r';
        }
    }

    /** Checks if more is valid for Rook, then moves the piece.
     * @return Valid move or not.
     */
    @Override
    public boolean moveTo(Location location) {
        if ((chessLocation.getRow() == location.getRow()) !=
            (chessLocation.getCol() == location.getCol())) {

            return checkLineOfSight(chessLocation, location) && super.moveTo(location);
        }
        return false;
    }

    /**
     * Updates the threatening locations.
     */
    @Override
    protected void updateThreateningLocation() {
        threateningLocations.clear();

        super.updateVertical(1);
        super.updateVertical(-1);
        super.updateHorizontal(1);
        super.updateHorizontal(-1);
    }
}
