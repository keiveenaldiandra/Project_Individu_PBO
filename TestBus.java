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

public static void main(String[] args) {
        Bus bus = new Bus();
        int pilihan = 0;

        while (pilihan != 5) {
            System.out.println("\n=== Menu Bus Trans Koetaradja ===");
            System.out.println("1. Naikkan Penumpang");
            System.out.println("2. Turunkan Penumpang");
            System.out.println("3. Lihat Penumpang");
            System.out.println("4. Top Up Saldo Penumpang");
            System.out.println("5. Keluar");
            pilihan = bacaIntPositif("Pilih: ");
            scanner.nextLine(); // Consume newline

            switch (pilihan) {
                case 1:
                    int id = bacaIntPositif("ID: ");
                    scanner.nextLine();
                    System.out.print("Nama: ");
                    String nama = scanner.nextLine();
                    int umur = bacaIntPositif("Umur: ");
                    boolean hamil = bacaBooleanYaTidak("Hamil (y/n): ");
                    Penumpang p = new Penumpang(id, nama, umur, hamil);
                    if (bus.naikkanPenumpang(p)) {
                        System.out.println("Penumpang berhasil naik!");
                    } else {
                        System.out.println("Gagal naik.");
                    }
                    break;
                case 2:
                    int idTurun = bacaIntPositif("ID penumpang: ");
                    if (bus.turunkanPenumpang(idTurun)) {
                        System.out.println("Penumpang berhasil turun!");
                    } else {
                        System.out.println("Penumpang dengan ID tersebut tidak ditemukan.");
                    }
                    break;
                case 3:
                    System.out.println(bus.toString());
                    break;
                case 4:
                    int idTopUp = bacaIntPositif("ID penumpang untuk top up: ");
                    int jumlahTopUp = bacaIntPositif("Jumlah top up: ");
                    Penumpang target = bus.cariPenumpangById(idTopUp);
                    if (target != null) {
                        target.tambahSaldo(jumlahTopUp);
                        System.out.println("Saldo berhasil ditambah! Saldo baru: " + target.getSaldo());
                    } else {
                        System.out.println("Penumpang dengan ID tersebut tidak ditemukan.");
                    }
                    break;
                case 5:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
    }
}
