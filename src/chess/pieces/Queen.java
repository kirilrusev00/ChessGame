package chess.pieces;

import chess.playboard.Game;

public class Queen extends Piece {
    
    public Queen(String owner, Location initialLocation, Game game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            type = 'Q';
        } else if (owner.equalsIgnoreCase("player2")) {
            type = 'q';
        }
    }

    public boolean moveToIfPossible(Location location) {
        return checkLineOfSightBetweenTwoLocations(this.location, location) && super.moveToIfPossible(location);
    }

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
