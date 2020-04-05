package chess.pieces;

import chess.playboard.Initializer;

public class Queen extends ChessPiece {
    
    /**
     * Creates a new Queen piece.
     * @param owner Owner string.
     * @param initialLocation Location to set Queen in.
     * @param game Game that the Queen belongs too.
     */
    public Queen(String owner, Location initialLocation, Initializer game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            id = 'Q';
        } else if (owner.equalsIgnoreCase("player2")) {
            id = 'q';
        }
    }

    /** Checks if more is valid for Queen, then moves the piece.
     * @return Valid move or not.
     */
    @Override
    public boolean moveTo(Location location) {
        return checkLineOfSight(chessLocation, location) && super.moveTo(location);
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

        super.updateDiagonal(1, 1);
        super.updateDiagonal(-1, 1);
        super.updateDiagonal(1, -1);
        super.updateDiagonal(-1, -1);
    }
}
