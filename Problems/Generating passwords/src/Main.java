import java.util.Scanner;

// KISS solution, inspired by: https://hyperskill.org/learn/daily/3789#solutions-610513
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int upperL = scanner.nextInt(); // A
        int lowerL = scanner.nextInt(); // B
        int digits = scanner.nextInt(); // C
        int length = scanner.nextInt(); // N
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (upperL > 0) {
                password.append((char) (65 + i % 26));
                upperL--;
            } else if (lowerL > 0) {
                password.append((char) (97 + i % 26));
                lowerL--;
            } else if (digits > 0) {
                password.append(i % 10);
                digits--;
            } else {
                password.append((char) (33 + i % (127 - 33))); // any printable ASCII char
            }
        }
        System.out.println(password);
    }
}