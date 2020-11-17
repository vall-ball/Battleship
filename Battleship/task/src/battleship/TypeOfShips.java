package battleship;

public enum TypeOfShips {
    AIRCRAFTCARIER("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROER("Destroyer", 2);

    private final String name;
    private final int length;
    TypeOfShips(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }
}
