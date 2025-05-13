public class SepatuRoda extends Kendaraan implements IBergerak {
    private double size;
    private double kecepatan;

    public SepatuRoda(String merek, String model) {
        super(merek, model);
    }

    public double getUkuranSize() {
        return size;
    }

    public void setUkuranSize(double size) {
        this.size = size;
    }

    @Override
    public boolean mulai() {
        System.out.println("Sepatu roda sedang bergerak");
        return true;
    }

    @Override
    public boolean berhenti() {
        System.out.println("Sepatu roda berhenti");
        return false;
    }

    @Override
    public double getKecepatan() {
        return kecepatan;
    }

    @Override
    public void setKecepatan(double kecepatan) {
        this.kecepatan = kecepatan;
    }

    @Override
    public double hitungPajak() {
        return 0;
    }

    @Override
    public String getTipeKendaraan() {
        return "Sepatu Roda";
    }
}
