import java.util.ArrayList;

public class Bus {
    private ArrayList<Penumpang> penumpangBiasa;
    private ArrayList<Penumpang> penumpangPrioritas;
    private ArrayList<Penumpang> penumpangBerdiri;
    public static final int ONGKOS_BUS = 2000;
    public static final int KAPASITAS_BIASA = 16;
    public static final int KAPASITAS_PRIORITAS = 4;
    public static final int KAPASITAS_BERDIRI = 20;
    public static final int KAPASITAS_TOTAL = KAPASITAS_BIASA + KAPASITAS_PRIORITAS + KAPASITAS_BERDIRI; // Hitung otomatis, bukan hardcode 40
    private int totalPendapatan;

public Bus() {
        penumpangBiasa = new ArrayList<>(KAPASITAS_BIASA);
        penumpangPrioritas = new ArrayList<>(KAPASITAS_PRIORITAS);
        penumpangBerdiri = new ArrayList<>(KAPASITAS_BERDIRI);
        totalPendapatan = 0;
    }

    public ArrayList<Penumpang> getPenumpangBiasa() {
        return new ArrayList<>(penumpangBiasa); // Return copy untuk encapsulation
    }

    public ArrayList<Penumpang> getPenumpangPrioritas() {
        return new ArrayList<>(penumpangPrioritas);
    }

    public ArrayList<Penumpang> getPenumpangBerdiri() {
        return new ArrayList<>(penumpangBerdiri);
    }

    public int getJumlahPenumpangBiasa() {
        return penumpangBiasa.size();
    }

    public int getJumlahPenumpangPrioritas() {
        return penumpangPrioritas.size();
    }

    public int getJumlahPenumpangBerdiri() {
        return penumpangBerdiri.size();
    }

    public int getTotalPenumpang() {
        return getJumlahPenumpangBiasa() + getJumlahPenumpangPrioritas() + getJumlahPenumpangBerdiri();
    }

    public Penumpang cariPenumpangById(int id) {
        for (ArrayList<Penumpang> list : new ArrayList[]{penumpangBiasa, penumpangPrioritas, penumpangBerdiri}) {
            for (Penumpang p : list) {
                if (p.getId() == id) {
                    return p;
                }
            }
        }
        return null;
    }
