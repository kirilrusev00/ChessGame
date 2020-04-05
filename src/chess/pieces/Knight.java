package chess.pieces;

import chess.playboard.TurnHandler;
import chess.playboard.Game;

public class Knight extends Piece {

    /**
     * Sets the private members of the Knight. Such as it's owner
     * the lcoation and the game it belongs to.
     * @param owner Owner string.
     * @param initialLocation Location to set knight in.
     * @param game Game that the knight belongs too.
     */
    public Knight(String owner, Location initialLocation, Game game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            type = 'N';
        } else if (owner.equalsIgnoreCase("player2")) {
            type = 'n';
        }
    }

    /**
     * Checks if the move is valid for knight piece, then moves the piece.
     * @param location Checks if the location is a valid move.
     * @return Boolean if the location is valid or not.
     */
    @Override
    public boolean moveTo(Location location) {
        if (Math.abs(this.location.getRow() - location.getRow()) == 2 &&
            Math.abs(this.location.getCol() - location.getCol()) == 1) {

            return super.moveTo(location); 
        } else if (Math.abs(this.location.getRow() - location.getRow()) == 1 &&
                   Math.abs(this.location.getCol() - location.getCol()) == 2) {

            return super.moveTo(location); 
        }
        return false;
    }

    /**
     * Updates the threatening locations.
     */
    @Override
    protected void updateThreateningLocation() {
        int[] rowMoves = { -2, -1, 1, 2, -2, -1, 1, 2 };
        int[] colMoves = { 1, 2, 2, 1, -1, -2, -2, -1 };

        threateningLocations.clear();
        for (int i = 0; i < 8; i++) {
            Location location = new Location(rowMoves[i], colMoves[i]);
            if (TurnHandler.locationInBounds(location)) {
                Piece piece = game.getChessBoard().getPieceAt(location);
                
                if (piece != null && 
                    !piece.getOwner().equals(owner)) {

                    threateningLocations.add(location);
                }
            }
        }
    }
}
