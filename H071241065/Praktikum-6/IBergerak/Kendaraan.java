abstract class Kendaraan {
    protected String id;
    protected String merek;
    protected String model;
    protected int tahunProduksi;
    protected String warna;

    public Kendaraan(String merek, String model) {
        this.merek = merek;
        this.model = model;
        this.id = generateId();
    }

    private String generateId() {
        return merek.substring(0, 2).toUpperCase() + model.substring(0, 2).toUpperCase() + System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public String getMerek() {
        return merek;
    }

    public String getModel() {
        return model;
    }

    public int getTahunProduksi() {
        return tahunProduksi;
    }

    public void setTahunProduksi(int tahun) {
        this.tahunProduksi = tahun;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public abstract double hitungPajak();

    public abstract String getTipeKendaraan();
}
