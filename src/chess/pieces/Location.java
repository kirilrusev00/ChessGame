package chess.pieces;

/**
 * The ChessLocation class is responsiable for holding
 * and setting the location of the pieces.
 */
public class Location {
    private int row;
    private int col;

    /**
     * Sets the row, col.
     * @param row Row to set.
     * @param col Col to set.
     */
    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Location) {
            Location l = (Location) obj;
            return (row == l.getRow() &&
                    col == l.getCol());
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
