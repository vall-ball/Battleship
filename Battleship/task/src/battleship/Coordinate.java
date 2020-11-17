package battleship;

public class Coordinate {
    int row;
    int column;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "row " + row + " column " + column;
    }

    public boolean equals(Coordinate coor) {
        return this.row == coor.row && this.column == coor.column;
    }



}
