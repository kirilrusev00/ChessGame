package chess.pieces;

import chess.playboard.Game;

public class Rook extends Piece {
    
    /**
     * Creates a new Rook piece.
     * @param owner Owner string.
     * @param initialLocation Location to set Rook in.
     * @param game Game that the Rook belongs too.
     */
    public Rook(String owner, Location initialLocation, Game game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            type = 'R';
        } else if (owner.equalsIgnoreCase("player2")) {
            type = 'r';
        }
    }

    /** Checks if more is valid for Rook, then moves the piece.
     * @return Valid move or not.
     */
    @Override
    public boolean moveTo(Location location) {
        if ((this.location.getRow() == location.getRow()) !=
            (this.location.getCol() == location.getCol())) {

            return checkLineOfSightBetweenTwoLocations(this.location, location) && super.moveTo(location);
        }
        return false;
    }

    /**
     * Updates the threatening locations.
     */
    @Override
    protected void updateThreateningLocation() {
        threateningLocations.clear();

        super.updateThreateningLocationsByVertical(1);
        super.updateThreateningLocationsByVertical(-1);
        super.updateThreateningLocationsByHorizontal(1);
        super.updateThreateningLocationsByHorizontal(-1);
    }
}
