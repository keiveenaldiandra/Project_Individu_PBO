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

private boolean tempatkanPenumpangPrioritas(Penumpang p) {
        if (penumpangPrioritas.size() < KAPASITAS_PRIORITAS) {
            penumpangPrioritas.add(p);
            return true;
        } else if (penumpangBiasa.size() < KAPASITAS_BIASA) {
            penumpangBiasa.add(p);
            return true;
        } else if (penumpangBerdiri.size() < KAPASITAS_BERDIRI) {
            penumpangBerdiri.add(p);
            return true;
        }
        return false;
    }

    private boolean tempatkanPenumpangBiasa(Penumpang p) {
        if (penumpangBiasa.size() < KAPASITAS_BIASA) {
            penumpangBiasa.add(p);
            return true;
        } else if (penumpangBerdiri.size() < KAPASITAS_BERDIRI) {
            penumpangBerdiri.add(p);
            return true;
        }
        return false;
    }

    public boolean naikkanPenumpang(Penumpang penumpang) {
        if (getTotalPenumpang() >= KAPASITAS_TOTAL) {
            System.out.println("Gagal naik: Kapasitas bus penuh.");
            return false;
        }
        if (cariPenumpangById(penumpang.getId()) != null) {
            System.out.println("Gagal naik: ID penumpang sudah ada di bus.");
            return false;
        }

        boolean adaSlot = false;
        PrioritasType type = penumpang.getPrioritasType();
        if (type != PrioritasType.BIASA) {
            adaSlot = tempatkanPenumpangPrioritas(penumpang);
        } else {
            adaSlot = tempatkanPenumpangBiasa(penumpang);
        }

        if (!adaSlot) {
            System.out.println("Gagal naik: Tidak ada slot tersedia.");
            return false;
        }

        try {
            penumpang.bayar(ONGKOS_BUS);
            totalPendapatan += ONGKOS_BUS;
            return true;
        } catch (SaldoTidakCukupException e) {
            // Rollback: Hapus dari semua list untuk aman (meski sebenarnya hanya satu yang terpengaruh)
            penumpangPrioritas.remove(penumpang);
            penumpangBiasa.remove(penumpang);
            penumpangBerdiri.remove(penumpang);
            System.out.println("Gagal naik: " + e.getMessage());
            return false;
        }
    }
