public abstract class Orang {
    protected String nama;
    protected int umur;

    public Orang(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

    public String getNama() {
        return nama;
    }

    public int getUmur() {
        return umur;
    }

    public abstract PrioritasType getPrioritasType();
}
