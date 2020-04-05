package chess.pieces;

import chess.playboard.Game;

public class Pawn extends Piece {

    private static final int FIRST_MOVE_LENGTH = 2;
    private boolean firstMove;
    private int direction;

    public Pawn(String owner, Location initialLocation, Game game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            type = 'P';
            direction = 1;
        } else if (owner.equalsIgnoreCase("player2")) {
            type = 'p';
            direction = -1;
        }
        firstMove = true;
    }

    private boolean moveForwardWithOnePositionIfPossible() {
        if (firstMove) {
            firstMove = false;
        }
        return !game.getTurnHandler().isPieceAt(location.getRow(), location.getCol())
                && super.moveToIfPossible(location);
    }

    private boolean moveForwardWithTwoPositionsIfPossible() {
        if (firstMove) {
            firstMove = false;
        }
        return !game.getTurnHandler().isPieceAt(location.getRow(), location.getCol())
                && super.moveToIfPossible(location);
    }

    @Override
    public boolean moveToIfPossible(Location location) {
        if (location.getCol() == this.location.getCol()) {
            if (location.getRow() - this.location.getRow() == direction) {
                return moveForwardWithOnePositionIfPossible();
            } else if (firstMove && (location.getRow() - this.location.getRow() == (direction * FIRST_MOVE_LENGTH))) {
                return moveForwardWithTwoPositionsIfPossible();
            }
        } else if (Math.abs(location.getCol() - this.location.getCol()) == 1) {
            if (game.getTurnHandler().isPieceAt(location.getRow(), location.getCol()) &&
                    location.getRow() - this.location.getRow() == direction) {

                if (firstMove) {
                    firstMove = false;
                }
                return super.moveToIfPossible(location);
            }
        }
        return false;
    }

    @Override
    protected void updateThreateningLocation() {
        final int LAST_BUT_ONE_ROW_FOR_PLAYER1 = 6;
        final int LAST_BUT_ONE_ROW_FOR_PLAYER2 = 1;
        int direction = 0;

        if (owner.equalsIgnoreCase("player1")
                && location.getRow() <= LAST_BUT_ONE_ROW_FOR_PLAYER1) {
            direction = 1;
        } else if (owner.equalsIgnoreCase("player2")
                && location.getRow() >= LAST_BUT_ONE_ROW_FOR_PLAYER2) {
            direction = -1;
        }

        threateningLocations.clear();

        if (location.getCol() >= 1) {
            threateningLocations.add(new Location(location.getRow() + direction, location.getCol() - 1));
        }
        if (location.getCol() <= 6) {
            threateningLocations.add(new Location(location.getRow() + direction, location.getCol() + 1));
        }
    }
}
