import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Bus {

    // Kapasitas bus
    public static final int KAP_BIASA = 16;
    public static final int KAP_PRIORITAS = 4;
    public static final int KAP_BERDIRI = 20;
    public static final int KAP_TOTAL = KAP_BIASA + KAP_PRIORITAS + KAP_BERDIRI;

    public static final int ONGKOS_BUS = 2000;

    // Data penumpang
    private ArrayList<Penumpang> biasa = new ArrayList<>(KAP_BIASA);
    private ArrayList<Penumpang> prioritas = new ArrayList<>(KAP_PRIORITAS);
    private ArrayList<Penumpang> berdiri = new ArrayList<>(KAP_BERDIRI);

    // Log
    private ArrayList<String> logAktivitas = new ArrayList<>();
    private DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");

    // Pendapatan harian
    private int totalPendapatan = 0;

    // Logging internal
    private void log(String level, String msg) {
        if (!level.equals("NAIK") && !level.equals("TURUN")
                && !level.equals("ERROR") && !level.equals("INFO"))
            level = "INFO";

        logAktivitas.add("[" + LocalTime.now().format(tf) + "][" + level + "] " + msg);
    }

    // Getter read-only
    public List<Penumpang> getPenumpangBiasa() { return Collections.unmodifiableList(biasa); }
    public List<Penumpang> getPenumpangPrioritas() { return Collections.unmodifiableList(prioritas); }
    public List<Penumpang> getPenumpangBerdiri() { return Collections.unmodifiableList(berdiri); }

    public int getJumlahBiasa() { return biasa.size(); }
    public int getJumlahPrioritas() { return prioritas.size(); }
    public int getJumlahBerdiri() { return berdiri.size(); }

    // Cari penumpang dari ID unik
    public Penumpang cariById(int id) {
        for (Penumpang p : biasa) if (p.getId() == id) return p;
        for (Penumpang p : prioritas) if (p.getId() == id) return p;
        for (Penumpang p : berdiri) if (p.getId() == id) return p;
        return null;
    }

    // Penempatan prioritas
    private boolean tempatkanPrioritas(Penumpang p) {
        if (prioritas.size() < KAP_PRIORITAS) { prioritas.add(p); return true; }
        if (biasa.size() < KAP_BIASA) { biasa.add(p); return true; }
        if (berdiri.size() < KAP_BERDIRI) { berdiri.add(p); return true; }
        return false;
    }

    // Penempatan biasa
    private boolean tempatkanBiasa(Penumpang p) {
        if (biasa.size() < KAP_BIASA) { biasa.add(p); return true; }
        if (berdiri.size() < KAP_BERDIRI) { berdiri.add(p); return true; }
        return false;
    }

    // Naik
    public boolean naikkanPenumpang(Penumpang p) {

        if (cariById(p.getId()) != null) {
            log("ERROR", "ID " + p.getId() + " sudah ada di bus.");
            return false;
        }

        int total = getJumlahBiasa() + getJumlahPrioritas() + getJumlahBerdiri();
        if (total >= KAP_TOTAL) {
            log("ERROR", "Bus penuh. Tidak bisa naikkan " + p.getNama());
            return false;
        }

        boolean masuk;
        if (p.getPrioritasType() == PrioritasType.BIASA)
            masuk = tempatkanBiasa(p);
        else
            masuk = tempatkanPrioritas(p);

        if (!masuk) {
            log("ERROR", "Slot tidak tersedia untuk " + p.getNama());
            return false;
        }

        try {
            p.bayar(ONGKOS_BUS);
            totalPendapatan += ONGKOS_BUS;
            log("NAIK", p.getNama() + " naik (ID:" + p.getId() + ")");
            return true;

        } catch (SaldoTidakCukupException e) {

            biasa.remove(p);
            prioritas.remove(p);
            berdiri.remove(p);

            log("ERROR", "Pembayaran gagal untuk ID " + p.getId());
            return false;
        }
    }

    // Turun
    public boolean turunkanPenumpang(int id) {

        Penumpang t = cariById(id);
        if (t == null) return false;

        biasa.remove(t);
        prioritas.remove(t);
        berdiri.remove(t);

        log("TURUN", t.getNama() + " turun (ID:" + t.getId() + ")");
        return true;
    }

    // ASCII Layout
    private String layoutKursi() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n[PRIORITAS (").append(getJumlahPrioritas()).append("/").append(KAP_PRIORITAS).append(")]\n");
        if (prioritas.isEmpty()) sb.append("<kosong>\n");
        else for (Penumpang p : prioritas) sb.append("[").append(p.getNama()).append("] ");

        sb.append("\n\n[BIASA (").append(getJumlahBiasa()).append("/").append(KAP_BIASA).append(")]\n");
        if (biasa.isEmpty()) sb.append("<kosong>\n");
        else for (Penumpang p : biasa) sb.append("[").append(p.getNama()).append("] ");

        sb.append("\n\n[BERDIRI (").append(getJumlahBerdiri()).append("/").append(KAP_BERDIRI).append(")]\n");
        if (berdiri.isEmpty()) sb.append("<kosong>\n");
        else for (Penumpang p : berdiri) sb.append("[").append(p.getNama()).append("] ");

        sb.append("\n");
        return sb.toString();
    }

    // Print kondisi bus
    @Override
    public String toString() {
        return "===== KONDISI BUS TRANS KOETARADJA =====\n"
                + layoutKursi()
                + "Total Pendapatan : " + totalPendapatan + "\n"
                + "Total Penumpang  : "
                + (getJumlahBiasa() + getJumlahPrioritas() + getJumlahBerdiri()) + "\n"
                + "==========================================";
    }

    public void tampilkanLog() {
        System.out.println("===== LOG AKTIVITAS =====");
        for (String s : logAktivitas)
            System.out.println(s);
    }
}
