package cinema;

import java.util.Scanner;

public class Cinema {
    // inspired by: https://hyperskill.org/projects/133/stages/713/implement#solutions-640421
    public static void main(String[] args) {
        Input input = new Input();
        Seats seats = new Seats(input);
        while (true) {
            switch (input.chooseAction()) {
                case 1:
                    seats.show();
                    break;
                case 2:
                    seats.reserve();
                    break;
                case 3:
                    seats.stats();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Error: invalid input!");
                    break;
            }
        }
    }
}

class Seats {
    private final Input input;
    private final int[][] seats;
    private final int rowsCount;
    private final int seatsByRow;
    private int purchased = 0;

    public Seats(Input input) {
        this.input = input;
        this.rowsCount = input.readRows();
        this.seatsByRow = input.readSeats();
        this.seats = new int[rowsCount][seatsByRow];
    }

    public void show() {
        System.out.println("Cinema: ");
        for (int i = 0; i < rowsCount + 1; i++) {
            for (int j = 0; j < seatsByRow + 1; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0) {
                    System.out.printf("%d ", j);
                } else if (j == 0) {
                    System.out.printf("%d ", i);
                } else {
                    System.out.print(seats[i - 1][j - 1] == 0 ? "S " : "B ");
                }
            }
            System.out.println();
        }
    }

    public void reserve() {
        if (purchased == rowsCount * seatsByRow) {
            System.out.println("Sorry! all tickets are sold!");
            return;
        }
        boolean reserved = false;
        do {
            int row = input.readRow();
            int seat = input.readSeat();
            if (row <= 0 || row > rowsCount || seat <= 0 || seat > seatsByRow) {
                System.out.println("Wrong input!\n");
            } else if (seats[row - 1][seat - 1] == 1) {
                System.out.println("That ticket has already been purchased!\n");
            } else {
                seats[row - 1][seat - 1] = 1; // reserve the seat
                purchased++;
                reserved = true;
                System.out.printf("Ticket price: $%d\n", calcPrice(row));
            }
        } while (!reserved);
    }

    private int calcPrice(int row) {
        int price;
        if (rowsCount * seatsByRow <= 60 || row <= rowsCount / 2) {
            price = 10;
        } else {
            price = 8;
        }
        return price;
    }

    /**
     * Calc the total & actual income
     * @param allSeats if true count all seats income, otherwise count only the reserved seats!
     */
    private int income(boolean allSeats) {
        int income = 0;
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < seatsByRow; j++) {
                if (allSeats || seats[i][j] == 1) { // reserved seat!
                    income += calcPrice(i + 1);
                }
            }
        }
        return income;
    }

    public void stats() {
        System.out.printf("Number of purchased tickets: %d\n", purchased);
        System.out.printf("Percentage: %.2f%s\n", purchased * 100.0 / (rowsCount * seatsByRow), "%");
        System.out.printf("Current income: $%d\n", income(false));
        System.out.printf("Total income: $%d\n", income(true));
    }
}

class Input {
    private Scanner scanner = new Scanner(System.in);

    public int readRows() {
        System.out.println("Enter the number of rows:");
        return scanner.nextInt();
    }

    public int readSeats() {
        System.out.println("Enter the number of seats in each row:");
        return scanner.nextInt();
    }

    public int readRow() {
        System.out.println("Enter a row number:");
        return scanner.nextInt();
    }

    public int readSeat() {
        System.out.println("Enter a seat number in that row:");
        return scanner.nextInt();
    }

    public int chooseAction() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int choice = scanner.nextInt();
        System.out.println();
        return choice;
    }
}