public class Penumpang extends Orang implements Payable {
    private int id;
    private boolean hamil;
    private int saldo;

    public Penumpang(int id, String nama, int umur, boolean hamil) {
        super(nama, umur);
        this.id = id;
        this.hamil = hamil;
        this.saldo = 10000; // Saldo awal
    }

    public int getId() {
        return id;
    }

    public boolean isHamil() {
        return hamil;
    }

    public int getSaldo() {
        return saldo;
    }

    public void tambahSaldo(int saldobaru) {
        if (saldobaru > 0) {
            this.saldo += saldobaru;
        }
    }

// Overloading buat tambahSaldo sebagai kreativitas
    public void tambahSaldo(int saldobaru, boolean bonus) {
        if (bonus) {
            this.saldo += saldobaru + 500; // Bonus 500
        } else {
            tambahSaldo(saldobaru);
        }
    }

    @Override
    public void bayar(int ongkos) throws SaldoTidakCukupException {
        if (saldo < ongkos) {
            throw new SaldoTidakCukupException("Saldo tidak cukup untuk membayar ongkos sebesar " + ongkos + ". Saldo saat ini: " + saldo);
        }
        saldo -= ongkos;
    }

    @Override
    public PrioritasType getPrioritasType() {
        if (umur > 60) return PrioritasType.LANSIA;
        if (umur < 10) return PrioritasType.ANAK;
        if (hamil) return PrioritasType.HAMIL;
        return PrioritasType.BIASA;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Umur: " + umur + ", Hamil: " + hamil + ", Saldo: " + saldo;
    }
}
