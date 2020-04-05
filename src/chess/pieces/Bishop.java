package chess.pieces;

import chess.playboard.Initializer;

public class Bishop extends Piece {
    
    /** Creates a new Bishop piece.
     * @param owner Owner string.
     * @param initialLocation Location to set Bishop in.
     * @param game Game that the Bishop belongs too.
     */
    public Bishop(String owner, Location initialLocation, Initializer game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            id = 'B';
        } else if (owner.equalsIgnoreCase("player2")) {
            id = 'b';
        }
    }

    /** Checks if more is valid for Bishop, then moves the piece.
     * @return Valid move or not.
     */
    @Override
    public boolean moveTo(Location location) {
        if (Math.abs(chessLocation.getRow() - location.getRow()) == 
            Math.abs(chessLocation.getCol() - location.getCol())) {
            
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
        super.updateDiagonal(1, 1);
        super.updateDiagonal(-1, 1);
        super.updateDiagonal(1, -1);
        super.updateDiagonal(-1, -1);
    }
}
