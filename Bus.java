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
