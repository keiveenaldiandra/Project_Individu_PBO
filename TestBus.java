import java.util.Scanner;
import java.util.InputMismatchException;

public class TestBus {
    private static Scanner scanner = new Scanner(System.in);

    // Helper method buat baca int positif (umur, id, dll.)
    private static int bacaIntPositif(String label) {
        while (true) {
            System.out.print(label);
            try {
                int value = scanner.nextInt();
                if (value < 0) {
                    System.out.println("Nilai harus positif atau nol.");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Input harus angka. Coba lagi.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    // Helper method buat baca boolean (y/n)
    private static boolean bacaBooleanYaTidak(String label) {
        while (true) {
            System.out.print(label);
            String input = scanner.next().toLowerCase();
            if (input.equals("y")) return true;
            if (input.equals("n")) return false;
            System.out.println("Jawab y atau n.");
        }
    }
