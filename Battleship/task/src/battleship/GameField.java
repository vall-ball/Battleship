package battleship;

import java.util.ArrayList;
import java.util.List;

public class GameField {

    char[][] field = new char[10][10];
    char[][] openField = new char[10][10];
    List<Ship> ships = new ArrayList<>();

    public GameField() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = '~';
                openField[i][j] = '~';
            }
        }
    }

    public void addShip(Coordinate begin, Coordinate end, TypeOfShips type) {
        ships.add(new Ship(begin, end, type));
        if (begin.row == end.row) {
            for (int i = begin.column; i <= end.column; i++) {
                field[begin.row][i] = 'O';
            }
        } else {
            for (int i = begin.row; i <= end.row; i++) {
                field[i][begin.column] = 'O';
            }
        }
    }

    public boolean checkCoordinate(Coordinate begin, Coordinate end) {
        if (begin.row == end.row) {
            return true;
        } else if (begin.column == end.column) {
            return true;
        } else {
            return false;
        }
    }

    public int lengthOfShip(Coordinate begin, Coordinate end) {
        if (begin.row == end.row) {
            return Math.abs(end.column - begin.column) + 1;
        } else {
            return Math.abs(end.row - begin.row) + 1;
        }
    }

    public boolean checkLength(int length, TypeOfShips type) {
        return type.getLength() == length;
    }

    private boolean shipConflict(Ship first,  Ship second) {
        boolean answer = false;
        for (Coordinate firstCoordinate : first.coordinateList) {
            for (Coordinate secondCoordinate : second.coordinateList) {
                if (conflictCoordinates(firstCoordinate, secondCoordinate)) {
                    answer = true;
                    break;
                }
            }
        }
        return answer;
    }

    public boolean shipsConflicts(List<Ship> ships, Ship ship) {
        boolean answer = false;
        if (ships.size() == 0) {
            return false;
        } else {
            for (Ship s :ships) {
                if (shipConflict(ship, s)) {
                    answer = true;
                    break;
                }
            }
        }
        return answer;
    }

    public Coordinate parse(String str) {
        char[] answer = str.toCharArray();
        if (str.length() == 0) {
            return new Coordinate(10, 10);
        }
        if (answer.length == 2) {
            return new Coordinate(answer[0] - 65, Character.getNumericValue(answer[1]) - 1);
        } else if (answer[1] == '1' && answer[2] == '0'){
            return new Coordinate(answer[0] - 65, 9);
        } else {
            return new Coordinate(10, 10);
        }
    }

    public boolean conflictCoordinates(Coordinate first, Coordinate second) {
        return Math.abs(first.row - second.row) <= 1 && Math.abs(first.column - second.column) <= 1;
    }

    public Coordinate[] pairOfCoordinate(String str) {
        Coordinate[] answer = new Coordinate[2];
        String[] a = str.split(" ");
        Coordinate first = parse(a[0]);
        Coordinate second = parse(a[1]);
        if (first.row == second.row && first.column < second.column) {
            answer[0] = first;
            answer[1] = second;
        } else if (first.column == second.column && first.row < second.row) {
            answer[0] = first;
            answer[1] = second;
        } else {
            answer[0] = second;
            answer[1] = first;
        }
        return answer;
    }

    public boolean shot(Coordinate coordinate) {
        if (field[coordinate.row][coordinate.column] == 'O' || field[coordinate.row][coordinate.column] == 'X') {
            field[coordinate.row][coordinate.column] = 'X';
            openField[coordinate.row][coordinate.column] = 'X';
            return true;
        } else {
            field[coordinate.row][coordinate.column] = 'M';
            openField[coordinate.row][coordinate.column] = 'M';
            return false;
        }
    }

    public void print() {
        System.out.print("  ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        char coor = 'A';
        for (char[] arr : field) {
            System.out.print(coor + " ");
            for (char c : arr) {
                System.out.print(c + " ");
            }
            System.out.println();
            coor++;
        }
    }

    public Ship shipByCoordinate(Coordinate coordinate) {
        for (Ship s : ships) {
            if (s.hasCoordinate(coordinate)) {
                return s;
            }
        }
        return null;
    }

    public boolean isShipSank(Ship ship) {
        for (Coordinate c : ship.coordinateList) {
            if (charByCoordinate(c) != 'X') {
                return false;
            }
        }
        return true;
    }

    public boolean isAllShipsSank() {
        for (Ship s : ships) {
            if (!isShipSank(s)) {
                return false;
            }
        }
        return true;
    }

    public char charByCoordinate(Coordinate coordinate) {
        return field[coordinate.row][coordinate.column];
    }

    public void printOpen() {
        System.out.print("  ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        char coor = 'A';
        for (char[] arr : openField) {
            System.out.print(coor + " ");
            for (char c : arr) {
                System.out.print(c + " ");
            }
            System.out.println();
            coor++;
        }
    }
}
