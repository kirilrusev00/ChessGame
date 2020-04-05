package chess.pieces;

import chess.playboard.Game;

public class Bishop extends Piece {

    public Bishop(String owner, Location location, Game game) {
        super(owner, location, game);
        if (owner.equalsIgnoreCase("player1")) {
            type = 'B';
        } else if (owner.equalsIgnoreCase("player2")) {
            type = 'b';
        }
    }

    @Override
    public boolean moveToIfPossible(Location location) {
        if (Math.abs(this.location.getRow() - location.getRow()) ==
                Math.abs(this.location.getCol() - location.getCol())) {

            return checkLineOfSightBetweenTwoLocations(this.location, location) && super.moveToIfPossible(location);
        }
        return false;
    }

    @Override
    protected void updateThreateningLocation() {
        threateningLocations.clear();
        super.updateThreateningLocationsByDiagonal(1, 1);
        super.updateThreateningLocationsByDiagonal(-1, 1);
        super.updateThreateningLocationsByDiagonal(1, -1);
        super.updateThreateningLocationsByDiagonal(-1, -1);
    }
}
