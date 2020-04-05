package chess.pieces;

import chess.playboard.Game;

public class Queen extends Piece {
    
    /**
     * Creates a new Queen piece.
     * @param owner Owner string.
     * @param initialLocation Location to set Queen in.
     * @param game Game that the Queen belongs too.
     */
    public Queen(String owner, Location initialLocation, Game game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            type = 'Q';
        } else if (owner.equalsIgnoreCase("player2")) {
            type = 'q';
        }
    }

    /** Checks if more is valid for Queen, then moves the piece.
     * @return Valid move or not.
     */
    @Override
    public boolean moveTo(Location location) {
        return checkLineOfSightBetweenTwoLocations(this.location, location) && super.moveTo(location);
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

        super.updateThreateningLocationsByDiagonal(1, 1);
        super.updateThreateningLocationsByDiagonal(-1, 1);
        super.updateThreateningLocationsByDiagonal(1, -1);
        super.updateThreateningLocationsByDiagonal(-1, -1);
    }
}
