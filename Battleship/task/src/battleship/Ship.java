package battleship;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    List<Coordinate> coordinateList = new ArrayList<>();
    TypeOfShips type;

    public Ship(Coordinate begin, Coordinate end, TypeOfShips type) {
        if (begin.row == end.row) {
            for (int i = begin.column; i <= end.column; i++) {
                coordinateList.add(new Coordinate(begin.row, i));
            }
        } else {
            for (int i = begin.row; i <= end.row; i++) {
                coordinateList.add(new Coordinate(i, begin.column));
            }
        }

        this.type = type;

    }

    public boolean hasCoordinate(Coordinate coordinate) {
        for (Coordinate c : coordinateList) {
            if (c.equals(coordinate)) {
                return true;
            }
        }
        return false;
    }


}
