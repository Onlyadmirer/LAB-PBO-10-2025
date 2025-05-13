import java.util.Date;
import java.util.Calendar;

public class Mobil extends Kendaraan implements IBergerak, IServiceable {
    private int jumlahPintu;
    private double kapasitasMesin;
    private int jumlahKursi;
    private String bahanBakar;
    private double kecepatan = 0;

    public Mobil(String merek, String model) {
        super(merek, model);
    }

    public int getJumlahPintu() {
        return jumlahPintu;
    }

    public void setJumlahPintu(int jumlah) {
        this.jumlahPintu = jumlah;
    }

    public double getKapasitasMesin() {
        return kapasitasMesin;
    }

    public void setKapasitasMesin(double kapasitas) {
        this.kapasitasMesin = kapasitas;
    }

    public int getJumlahKursi() {
        return jumlahKursi;
    }

    public void setJumlahKursi(int jumlah) {
        this.jumlahKursi = jumlah;
    }

    public String getBahanBakar() {
        return bahanBakar;
    }

    public void setBahanBakar(String bahanBakar) {
        this.bahanBakar = bahanBakar;
    }

    @Override
    public double hitungPajak() {
        return kapasitasMesin * 10000;
    }

    @Override
    public String getTipeKendaraan() {
        return "Mobil";
    }

    @Override
    public boolean mulai() {
        System.out.println("Mobil sedang berjalan");

        return true;
    }

    @Override
    public boolean berhenti() {
        System.out.println("Mobil berhenti");
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
        System.out.println("Mobil diservis.");
    }

    @Override
    public Date getWaktuServisBerikutnya() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 6);
        return cal.getTime();
    }

    @Override
    public double hitungBiayaServis() {
        return 500000;
    }
}
