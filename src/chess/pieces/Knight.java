package chess.pieces;

import chess.playboard.TurnHandler;
import chess.playboard.Game;

public class Knight extends Piece {

    public Knight(String owner, Location initialLocation, Game game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            type = 'N';
        } else if (owner.equalsIgnoreCase("player2")) {
            type = 'n';
        }
    }

    @Override
    public boolean moveToIfPossible(Location location) {
        if (Math.abs(this.location.getRow() - location.getRow()) == 2 &&
                Math.abs(this.location.getCol() - location.getCol()) == 1) {

            return super.moveToIfPossible(location);
        } else if (Math.abs(this.location.getRow() - location.getRow()) == 1 &&
                Math.abs(this.location.getCol() - location.getCol()) == 2) {

            return super.moveToIfPossible(location);
        }
        return false;
    }

    @Override
    protected void updateThreateningLocation() {
        final int[] rowMoves = {-2, -1, 1, 2, -2, -1, 1, 2};
        final int[] colMoves = {1, 2, 2, 1, -1, -2, -2, -1};

        threateningLocations.clear();
        for (int i = 0; i < BOARD_SIZE; i++) {
            Location location = new Location(rowMoves[i], colMoves[i]);
            if (location.isInBoardBounds()) {
                Piece piece = game.getTurnHandler().getPieceAt(location);

                if (piece != null &&
                        !piece.getOwner().equals(owner)) {

                    threateningLocations.add(location);
                }
            }
        }
    }
}
