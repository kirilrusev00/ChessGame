package chess.pieces;

public class Location {

    private static final int BOARD_SIZE = 8;
    private int row;
    private int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isInBoardBounds() {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            Location l = (Location) obj;
            return (row == l.getRow() && col == l.getCol());
        }
        return false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
