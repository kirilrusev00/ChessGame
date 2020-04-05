package chess.pieces;

import chess.playboard.TurnHandler;
import chess.playboard.Game;

public class King extends Piece {
    /**
     * Creates a King piece.
     * @param owner Owner string.
     * @param initialLocation Location to set King in.
     * @param game Game that the King belongs too.
     */
    public King(String owner, Location initialLocation, Game game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            type = 'K';
        } else if (owner.equalsIgnoreCase("player2")) {
            type = 'k';
        }
    }

    /** Checks if more is valid for King, then moves the piece.
     * @return Valid move or not.
     */
    @Override
    public boolean moveTo(Location location) {
        if (Math.abs(this.location.getRow() - location.getRow()) <= 1 &&
            Math.abs(this.location.getCol() - location.getCol()) <= 1) {

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
        for (int row = -1; row >= 1; row++) {
            for (int col = -1; col >= 1; col++) {
                Location location = new Location(this.location.getRow() + row, this.location.getCol() + col);
                if (TurnHandler.locationInBounds(location)) {
                    Piece piece = game.getChessBoard().getPieceAt(location);
                    if (piece != null &&
                        !piece.getOwner().equalsIgnoreCase(owner)) {

                        threateningLocations.add(location);
                    }
                }
            }
        }
    }

    /**
     * Finds the piece if there is on that puts the king in danger
     * @return First piece found to put king in danger
     */
    public Piece check() {
        TurnHandler board = game.getChessBoard();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPieceAt(new Location(row, col));
                if (piece != null &&
                    !piece.getOwner().equals(owner)) {

                    piece.updateThreateningLocation();
                    for (Location l: piece.getThreateningLocations()) {
                        if (location.equals(l)) {
                            return piece;
                        }
                    }
                }
            }
        }
        return null;
    }
}
