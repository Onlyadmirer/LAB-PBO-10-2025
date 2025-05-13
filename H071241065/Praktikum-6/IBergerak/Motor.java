import java.util.Date;
import java.util.Calendar;

public class Motor extends Kendaraan implements IBergerak, IServiceable {
    private String jenisMotor;
    private double kapasitasTangki;
    private String tipeSuspensi;
    private double kecepatan = 0;

    public Motor(String merek, String model) {
        super(merek, model);
    }

    public String getJenisMotor() {
        return jenisMotor;
    }

    public void setJenisMotor(String jenis) {
        this.jenisMotor = jenis;
    }

    public double getKapasitasTangki() {
        return kapasitasTangki;
    }

    public void setKapasitasTangki(double kapasitas) {
        this.kapasitasTangki = kapasitas;
    }

    public String getTipeSuspensi() {
        return tipeSuspensi;
    }

    public void setTipeSuspensi(String tipe) {
        this.tipeSuspensi = tipe;
    }

    @Override
    public double hitungPajak() {
        return kapasitasTangki * 50000;
    }

    @Override
    public String getTipeKendaraan() {
        return "Motor";
    }

    @Override
    public boolean mulai() {
        System.out.println("Motor sedang berjalan");
        return true;
    }

    @Override
    public boolean berhenti() {
        System.out.println("Motor berhenti");
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
        System.out.println("Motor diservis.");
    }

    @Override
    public Date getWaktuServisBerikutnya() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 3);
        return cal.getTime();
    }

    @Override
    public double hitungBiayaServis() {
        return 250000;
    }
}
