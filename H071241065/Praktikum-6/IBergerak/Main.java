
public class Main {
    public static void main(String[] args) {

        Mobil mobil = new Mobil("Toyota", "Avanza");
        mobil.setTahunProduksi(2020);
        mobil.setWarna("Hitam");
        mobil.setJumlahPintu(4);
        mobil.setJumlahKursi(7);
        mobil.setKapasitasMesin(150);
        mobil.setBahanBakar("Bensin");
        mobil.mulai();
        mobil.setKecepatan(80);
        System.out.println("Mobil: " + mobil.getTipeKendaraan());
        System.out.println("Pajak: Rp" + mobil.hitungPajak());
        System.out.println("Biaya Servis: Rp" + mobil.hitungBiayaServis());
        System.out.println("Servis Berikutnya: " + mobil.getWaktuServisBerikutnya());
        mobil.berhenti();
        System.out.println();

        Motor motor = new Motor("Honda", "CBR");
        motor.setJenisMotor("Sport");
        motor.setKapasitasTangki(15);
        motor.setTipeSuspensi("Monoshock");
        motor.setTahunProduksi(2019);
        motor.setWarna("Merah");
        motor.mulai();
        motor.setKecepatan(100);
        System.out.println("Motor: " + motor.getTipeKendaraan());
        System.out.println("Pajak: Rp" + motor.hitungPajak());
        System.out.println("Biaya Servis: Rp" + motor.hitungBiayaServis());
        System.out.println("Servis Berikutnya: " + motor.getWaktuServisBerikutnya());
        motor.berhenti();
        System.out.println();

        Sepeda sepeda = new Sepeda("Polygon", "Premier");
        sepeda.setJenisSepeda("Gunung");
        sepeda.setJumlahGear(21);
        sepeda.setUkuranRoda(27);
        sepeda.setTahunProduksi(2021);
        sepeda.setWarna("Biru");
        sepeda.mulai();
        sepeda.setKecepatan(25);
        System.out.println("Sepeda: " + sepeda.getTipeKendaraan());
        System.out.println("Pajak: Rp" + sepeda.hitungPajak());
        System.out.println("Biaya Servis: Rp" + sepeda.hitungBiayaServis());
        System.out.println("Servis Berikutnya: " + sepeda.getWaktuServisBerikutnya());
        sepeda.berhenti();
        System.out.println();

        SepatuRoda sepatu = new SepatuRoda("Adidas", "Street");
        sepatu.setUkuranSize(32.0);
        sepatu.setTahunProduksi(2022);
        sepatu.setWarna("Hijau");
        sepatu.mulai();
        sepatu.setKecepatan(10);
        System.out.println("sepatu Roda: " + sepatu.getTipeKendaraan());
        System.out.println("Pajak: Rp" + sepatu.hitungPajak());
        System.out.println("Kecepatan: " + sepatu.getKecepatan() + " km/h");
        sepatu.berhenti();
    }
}
