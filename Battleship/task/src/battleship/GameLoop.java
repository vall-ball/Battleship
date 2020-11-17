package battleship;

import java.util.Scanner;

public class GameLoop {
    GameField gameFieldOfFirstPlayer = new GameField();
    GameField gameFieldOfSecondPlayer = new GameField();
    Scanner scanner = new Scanner(System.in);

    public void game() {
        prepair(gameFieldOfFirstPlayer, "Player 1");
        prepair(gameFieldOfSecondPlayer, "Player 2");
        shootingRounds();
    }

    public void prepair(GameField gameField, String playerName) {
        System.out.println(playerName + ", place your ships on the game field");
        gameField.print();
        shipEnter(TypeOfShips.AIRCRAFTCARIER, gameField);
        shipEnter(TypeOfShips.BATTLESHIP, gameField);
        shipEnter(TypeOfShips.SUBMARINE, gameField);
        shipEnter(TypeOfShips.CRUISER, gameField);
        shipEnter(TypeOfShips.DESTROER, gameField);
        System.out.println("Press Enter and pass the move to another player");
        System.out.println("...");
        scanner.nextLine();
    }

    public void shootingRounds() {
        while (!gameFieldOfFirstPlayer.isAllShipsSank() && !gameFieldOfSecondPlayer.isAllShipsSank()) {
            oneShot("Player 1", gameFieldOfFirstPlayer, gameFieldOfSecondPlayer);
            if (gameFieldOfSecondPlayer.isAllShipsSank()) {
                gameFieldOfSecondPlayer.printOpen();
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }
            System.out.println("Press Enter and pass the move to another player");
            System.out.println("...");
            scanner.nextLine();

            oneShot("Player 2", gameFieldOfSecondPlayer, gameFieldOfFirstPlayer);
            if (gameFieldOfFirstPlayer.isAllShipsSank()) {
                gameFieldOfFirstPlayer.printOpen();
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }
            System.out.println("Press Enter and pass the move to another player");
            System.out.println("...");
            scanner.nextLine();
            System.out.println();

        }

    }

    public void oneShot(String playerName, GameField yourGameField, GameField opponentGameField) {
        opponentGameField.printOpen();
        System.out.println("---------------------");
        yourGameField.print();
        System.out.println();
        System.out.println(playerName + ", it's your turn:");
        System.out.println();
        String str = scanner.nextLine();
        System.out.println();
     /*   while (true) {
            str = scanner.nextLine();
            if (str.length() != 0) {
                break;
            } else {
               // System.out.println("Wrong coordinate");
            }
        }*/

        Coordinate coordinate = opponentGameField.parse(str);
        while (true) {
            if (coordinate.row > 9 || coordinate.column > 9) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                coordinate = opponentGameField.parse(scanner.nextLine());
            } else {
                break;
            }
        }
        if (opponentGameField.shot(coordinate)) {
            if (opponentGameField.isShipSank(opponentGameField.shipByCoordinate(coordinate))) {
                System.out.println("You sank a ship!");
            } else {
                System.out.println("You hit a ship!");
            }

        } else {
            System.out.println("You missed!");
        }
    }


    public void shipEnter(TypeOfShips type, GameField gameField) {
        System.out.println("Enter the coordinates of the " + type.getName() + " (" + type.getLength() + " cells):");
        String str = null;
        Coordinate[] pair = null;
        Ship ship = null;

        while (true) {
            str = scanner.nextLine();
            pair = gameField.pairOfCoordinate(str);
            if (!gameField.checkCoordinate(pair[0], pair[1])) {
                System.out.println("Error! Wrong ship location! Try again:");
                continue;
            }
            if (!gameField.checkLength(gameField.lengthOfShip(pair[0], pair[1]), type)) {
                System.out.println("Error! Wrong length of the " + type.getName() + " ! Try again:");
                continue;
            }

            ship = new Ship(pair[0], pair[1], type);

            if (gameField.shipsConflicts(gameField.ships, ship)) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                continue;
            } else {
                break;
            }
        }
        gameField.addShip(pair[0], pair[1], type);
        gameField.print();
    }



}

/*




    public void game() {
        System.out.println();
        System.out.println("The game starts!");
        System.out.println();
        gameField.printOpen();
        System.out.println("Take a shot!");
        while (true) {
//!gameField.isAllShipsSank()
            Coordinate coordinate = gameField.parse(scanner.nextLine());

            while (true) {
                if (coordinate.row > 9 || coordinate.column > 9) {
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                    coordinate = gameField.parse(scanner.nextLine());
                } else {
                    break;
                }
            }
            if (gameField.shot(coordinate)) {
                gameField.printOpen();
                System.out.println();
                if (gameField.isShipSank(gameField.shipByCoordinate(coordinate))) {
                    System.out.println("You sank a ship! Specify a new target:");
                } else {
                    System.out.println("You hit a ship! Try again:");
                }

            } else {
                gameField.printOpen();
                System.out.println("You missed. Try again:");
            }
            if (gameField.isAllShipsSank()) {
                gameField.printOpen();
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }

        }


    }

}
 */