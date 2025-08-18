import java.util.Scanner;

public class IO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter an integer: ");
            int number = scanner.nextInt();
            System.out.println("You entered: " + number);
        } catch (Exception e) {
            System.out.println("Input error: Please enter a valid integer.");
        } finally {
            scanner.close();
        }
    }
}