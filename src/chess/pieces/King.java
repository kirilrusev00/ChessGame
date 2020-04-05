package chess.pieces;

import chess.playboard.TurnHandler;
import chess.playboard.Game;

public class King extends Piece {

    public King(String owner, Location initialLocation, Game game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            type = 'K';
        } else if (owner.equalsIgnoreCase("player2")) {
            type = 'k';
        }
    }

    @Override
    public boolean moveToIfPossible(Location location) {
        if (Math.abs(this.location.getRow() - location.getRow()) <= 1 &&
                Math.abs(this.location.getCol() - location.getCol()) <= 1) {

            return checkLineOfSightBetweenTwoLocations(this.location, location) && super.moveToIfPossible(location);
        }
        return false;
    }

    @Override
    protected void updateThreateningLocation() {
        threateningLocations.clear();
        for (int row = -1; row <= 1; row++) {
            for (int col = -1; col <= 1; col++) {
                Location location = new Location(this.location.getRow() + row,
                        this.location.getCol() + col);
                if (location.isInBoardBounds()) {
                    Piece piece = game.getTurnHandler().getPieceAt(location);
                    if (piece != null &&
                            !piece.getOwner().equalsIgnoreCase(owner)) {

                        threateningLocations.add(location);
                    }
                }
            }
        }
    }

    public Piece getPieceThreateningKing() {
        TurnHandler board = game.getTurnHandler();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = board.getPieceAt(new Location(row, col));
                if (piece != null &&
                        !piece.getOwner().equals(owner)) {

                    piece.updateThreateningLocation();
                    for (Location l : piece.getThreateningLocations()) {
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
