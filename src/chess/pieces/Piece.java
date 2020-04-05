package chess.pieces;

import chess.playboard.TurnHandler;
import chess.playboard.Game;

import java.util.ArrayList;

public abstract class Piece {

    protected Game game;
    protected String owner;
    protected Location location;
    protected char type;
    protected ArrayList<Location> threateningLocations;
    protected static int BOARD_SIZE = 8;

    protected abstract void updateThreateningLocation();

    public Piece(String owner, Location initialLocation, Game game) {
        this.owner = owner;
        location = null;
        this.game = game;
        threateningLocations = new ArrayList<>();
        this.game.getTurnHandler().placePieceAt(this, initialLocation);
    }

    private boolean isVerticalLineOfSightClear(Location start, Location end) {
        int one = (start.getRow() - end.getRow() < 0) ? 1 : -1;
        for (int row = start.getRow() + one; row < end.getRow(); row += one) {
            if (game.getTurnHandler().isPieceAt(row, start.getCol())) {
                return false;
            }
        }
        return true;
    }

    private boolean isHorizontalLineOfSightClear(Location start, Location end) {
        int one = (start.getCol() - end.getCol() < 0) ? 1 : -1;
        for (int col = start.getCol() + one; col < end.getCol(); col += one) {
            if (game.getTurnHandler().isPieceAt(start.getRow(), col)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBottomRightDiagonalLineOfSightClear(Location start, Location end) {
        int one = (start.getRow() - end.getRow() < 0) ? 1 : -1;
        for (int inc = one; Math.abs(inc) < Math.abs(start.getRow() - end.getRow()); inc += one) {
            if (game.getTurnHandler().isPieceAt(start.getRow() + inc, start.getCol() + inc)) {
                return false;
            }
        }
        return true;
    }

    private boolean isUpRightDiagonalLineOfSightClear(Location start, Location end) {
        int one = (start.getRow() - end.getRow() < 0) ? 1 : -1;
        int negOne = one * -1;
        for (int inc = one; Math.abs(inc) < Math.abs(start.getRow() - end.getRow()); inc += one) {
            if (game.getTurnHandler().isPieceAt(start.getRow() + inc, start.getCol() + (inc * negOne))) {
                return false;
            }
        }
        return true;
    }

    private boolean areLocationsOnVertical(Location start, Location end) {
        return (start.getCol() == end.getCol());
    }

    private boolean areLocationsOnHorizontal(Location start, Location end) {
        return (start.getRow() == end.getRow());
    }

    private boolean areLocationsOnBottomRightDiagonal(Location start, Location end) {
        return (start.getCol() - end.getCol() == start.getRow() - end.getRow());
    }

    private boolean areLocationsOnUpRightDiagonal(Location start, Location end) {
        return (start.getCol() - end.getCol() * -1 == start.getRow() - end.getCol());
    }

    protected boolean checkLineOfSightBetweenTwoLocations(Location start, Location end) {
        if (areLocationsOnVertical(start, end)) {
            return isVerticalLineOfSightClear(start, end);
        }

        if (areLocationsOnHorizontal(start, end)) {
            return isHorizontalLineOfSightClear(start, end);
        }

        if (areLocationsOnBottomRightDiagonal(start, end)) {
            return isBottomRightDiagonalLineOfSightClear(start, end);

        } else if (areLocationsOnUpRightDiagonal(start, end)) {
            return isUpRightDiagonalLineOfSightClear(start, end);
        }
        return false;
    }

    protected void updateThreateningLocationsByVertical(int direction) {
        Location location = new Location(this.location.getRow() + direction, this.location.getCol());
        while (TurnHandler.isLocationInBounds(location)) {
            Piece piece = game.getTurnHandler().getPieceAt(location);
            if (piece != null) {
                if (!piece.getOwner().equalsIgnoreCase(owner)) {
                    threateningLocations.add(location);
                    return;
                } else if (!this.location.equals(location)) {
                    threateningLocations.add(new Location(location.getRow() - direction, location.getCol()));
                    return;
                }
            } else {
                location = new Location(location.getRow() + direction, location.getCol());
            }
        }
    }

    protected void updateThreateningLocationsByHorizontal(int direction) {
        Location location = new Location(this.location.getRow(), this.location.getCol() + direction);
        while (TurnHandler.isLocationInBounds(location)) {
            Piece piece = game.getTurnHandler().getPieceAt(location);
            if (piece != null) {
                if (!piece.getOwner().equalsIgnoreCase(owner)) {
                    threateningLocations.add(location);
                    return;
                } else if (!this.location.equals(location)) {
                    threateningLocations.add(new Location(location.getRow(), location.getCol() - direction));
                    return;
                }
            } else {
                location = new Location(location.getRow(), location.getCol() + direction);
            }
        }
    }

    protected void updateThreateningLocationsByDiagonal(int rowDirection, int colDirection) {
        Location location = new Location(this.location.getRow() + rowDirection,
                this.location.getCol() + colDirection);
        while (TurnHandler.isLocationInBounds(location)) {
            Piece piece = game.getTurnHandler().getPieceAt(location);
            if (piece != null) {
                if (!piece.getOwner().equalsIgnoreCase(owner)) {
                    threateningLocations.add(location);
                    return;
                } else if (!this.location.equals(location)) {
                    threateningLocations.add(new Location(location.getRow() - rowDirection,
                            location.getCol() - colDirection));
                    return;
                }
            } else {
                location = new Location(location.getRow() + rowDirection, location.getCol() + colDirection);
            }
        }
    }

    public boolean moveToIfPossible(Location newLocation) {
        TurnHandler board = game.getTurnHandler();
        Piece oldPiece = board.getPieceAt(newLocation);

        if (oldPiece == null || !owner.equals(oldPiece.getOwner())) {
            board.placePieceAt(this, newLocation);
            return true;
        }
        return false;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getOwner() {
        return owner;
    }

    public char getType() {
        return type;
    }

    ArrayList<Location> getThreateningLocations() {
        return threateningLocations;
    }
}
