import javax.swing.text.MutableAttributeSet;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int u1 = scanner.nextInt();
        int u2 = scanner.nextInt();
        int v1 = scanner.nextInt();
        int v2 = scanner.nextInt();
        int dotUV = u1 * v1 + u2 * v2;
        //double lengthU = Math.sqrt(Math.pow(u1, 2) + Math.pow(u2, 2));
        double lengthU = Math.hypot(u1, u2);
        //double lengthV = Math.sqrt(Math.pow(v1, 2) + Math.pow(v2, 2));
        double lengthV = Math.hypot(v1, v2);
        double cosAngle = dotUV / (lengthU * lengthV);
        double angle = Math.acos(cosAngle);
        System.out.println(Math.toDegrees(angle));
    }
}