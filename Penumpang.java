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
