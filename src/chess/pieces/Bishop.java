package chess.pieces;

import chess.playboard.Game;

public class Bishop extends Piece {
    
    /** Creates a new Bishop piece.
     * @param owner Owner string.
     * @param initialLocation Location to set Bishop in.
     * @param game Game that the Bishop belongs too.
     */
    public Bishop(String owner, Location initialLocation, Game game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            type = 'B';
        } else if (owner.equalsIgnoreCase("player2")) {
            type = 'b';
        }
    }

    /** Checks if more is valid for Bishop, then moves the piece.
     * @return Valid move or not.
     */
    @Override
    public boolean moveTo(Location location) {
        if (Math.abs(this.location.getRow() - location.getRow()) ==
            Math.abs(this.location.getCol() - location.getCol())) {
            
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
        super.updateThreateningLocationsByDiagonal(1, 1);
        super.updateThreateningLocationsByDiagonal(-1, 1);
        super.updateThreateningLocationsByDiagonal(1, -1);
        super.updateThreateningLocationsByDiagonal(-1, -1);
    }
}
