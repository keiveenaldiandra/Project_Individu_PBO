import java.util.*;

public class TestBus {

    private static Scanner sc = new Scanner(System.in);

    private static int bacaInt(String label) {
        while (true) {
            System.out.print(label);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Masukkan angka valid.");
            }
        }
    }

    private static boolean bacaBool(String label) {
        while (true) {
            System.out.print(label);
            String s = sc.nextLine().trim().toLowerCase();
            if (s.equals("y")) return true;
            if (s.equals("n")) return false;
            System.out.println("Jawab y/n.");
        }
    }

    public static void main(String[] args) {

        Bus bus = new Bus();
        int pilih = 0;

        while (pilih != 5) {

            System.out.println("\n===== MENU BUS TRANS KOETARADJA =====");
            System.out.println("1. Naikkan Penumpang");
            System.out.println("2. Turunkan Penumpang (ID)");
            System.out.println("3. Lihat Penumpang");
            System.out.println("4. Lihat Log Aktivitas");
            System.out.println("5. Keluar");

            pilih = bacaInt("Pilih: ");

            switch (pilih) {

                case 1:
                    int id = bacaInt("ID: ");
                    System.out.print("Nama: ");
                    String nama = sc.nextLine();
                    int umur = bacaInt("Umur: ");
                    boolean hamil = bacaBool("Hamil? (y/n): ");

                    Penumpang p = new Penumpang(id, nama, umur, hamil);
                    boolean ok = bus.naikkanPenumpang(p);

                    if (ok) System.out.println("Penumpang berhasil naik.");
                    else System.out.println("Gagal menaikkan penumpang.");
                    break;

                case 2:
                    int turunId = bacaInt("Masukkan ID: ");
                    if (bus.turunkanPenumpang(turunId))
                        System.out.println("Penumpang berhasil turun.");
                    else
                        System.out.println("ID tidak ditemukan.");
                    break;

                case 3:
                    System.out.println(bus);
                    break;

                case 4:
                    bus.tampilkanLog();
                    break;

                case 5:
                    System.out.println("Terima kasih.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
