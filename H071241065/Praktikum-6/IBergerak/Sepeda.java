import java.util.Date;
import java.util.Calendar;

public class Sepeda extends Kendaraan implements IBergerak, IServiceable {
    private String jenisSepeda;
    private int jumlahGear;
    private int ukuranRoda;
    private double kecepatan = 0;

    public Sepeda(String merek, String model) {
        super(merek, model);
    }

    public String getJenisSepeda() {
        return jenisSepeda;
    }

    public void setJenisSepeda(String jenis) {
        this.jenisSepeda = jenis;
    }

    public int getJumlahGear() {
        return jumlahGear;
    }

    public void setJumlahGear(int jumlah) {
        this.jumlahGear = jumlah;
    }

    public int getUkuranRoda() {
        return ukuranRoda;
    }

    public void setUkuranRoda(int ukuran) {
        this.ukuranRoda = ukuran;
    }

    @Override
    public double hitungPajak() {
        return 0;
    }

    @Override
    public String getTipeKendaraan() {
        return "Sepeda";
    }

    @Override
    public boolean mulai() {
        System.out.println("Sepeda sedang berjalan");
        ;
        return true;
    }

    @Override
    public boolean berhenti() {
        System.out.println("sepeda berhenti");
        ;
        return !false;
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
    public boolean periksaKondisi() {
        return true;
    }

    @Override
    public void lakukanServis() {
        System.out.println("Sepeda diservis.");
    }

    @Override
    public Date getWaktuServisBerikutnya() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }

    @Override
    public double hitungBiayaServis() {
        return 100000;
    }
}
