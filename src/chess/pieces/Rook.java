package chess.pieces;

import chess.playboard.Game;

public class Rook extends Piece {
    
    public Rook(String owner, Location initialLocation, Game game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            type = 'R';
        } else if (owner.equalsIgnoreCase("player2")) {
            type = 'r';
        }
    }

    @Override
    public boolean moveToIfPossible(Location location) {
        if ((this.location.getRow() == location.getRow()) !=
            (this.location.getCol() == location.getCol())) {

            return checkLineOfSightBetweenTwoLocations(this.location, location) && super.moveToIfPossible(location);
        }
        return false;
    }

    @Override
    protected void updateThreateningLocation() {
        threateningLocations.clear();

        super.updateThreateningLocationsByVertical(1);
        super.updateThreateningLocationsByVertical(-1);
        super.updateThreateningLocationsByHorizontal(1);
        super.updateThreateningLocationsByHorizontal(-1);
    }
}
