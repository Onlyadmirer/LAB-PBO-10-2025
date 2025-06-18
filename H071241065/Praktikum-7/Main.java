import java.util.*;

public class Main {
  private static final Scanner sc = new Scanner(System.in);
  private static final Library library = new Library();

  public static void main(String[] args) {
    while (true) {
      System.out.println("\n=== Sistem Manajemen Perpustakaan ===");
      System.out.println("1. Tambah Item");
      System.out.println("2. Tambah Anggota");
      System.out.println("3. Pinjam Item");
      System.out.println("4. Kembalikan Item");
      System.out.println("5. Lihat Status Perpustakaan");
      System.out.println("6. Lihat Log Aktivitas");
      System.out.println("7. Lihat Item yang Dipinjam Anggota");
      System.out.println("8. Keluar");
      System.out.print("Pilih opsi: ");
      int opsi = sc.nextInt();
      sc.nextLine();

      try {
        switch (opsi) {
          case 1 -> tambahItem();
          case 2 -> tambahAnggota();
          case 3 -> pinjamItem();
          case 4 -> kembalikanItem();
          case 5 -> System.out.println(library.getLibraryStatus());
          case 6 -> System.out.println(library.getAllLogs());
          case 7 -> lihatPinjaman();
          case 8 -> {
            System.out.println("Terima kasih!");
            return;
          }
          default -> System.out.println("Opsi tidak valid.");
        }
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }

  static void tambahItem() {
    System.out.print("Jenis item (1. Buku, 2. DVD): ");
    int jenis = sc.nextInt();
    sc.nextLine();
    System.out.print("Judul: ");
    String judul = sc.nextLine();
    System.out.print("ID: ");
    int id = sc.nextInt();
    sc.nextLine();
    if (jenis == 1) {
      System.out.print("Penulis: ");
      String author = sc.nextLine();
      Book book = new Book(judul, id, author);
      System.out.println(library.addItem(book));
      library.getLogger().logActivity(book.getTitle() + " berhasil ditambahkan");
    } else {
      System.out.print("Durasi (menit): ");
      int durasi = sc.nextInt();
      DVD dvd = new DVD(judul, id, durasi);
      System.out.println(library.addItem(dvd));
      library.getLogger().logActivity(dvd.getTitle() + " berhasil ditambahkan");
    }

  }

  static void tambahAnggota() {
    System.out.print("Nama: ");
    String nama = sc.nextLine();
    System.out.print("ID Anggota: ");
    int id = sc.nextInt();
    System.out.println(library.addMember(new Member(nama, id)));
  }

  static void pinjamItem() {
    System.out.print("ID Anggota: ");
    int memberId = sc.nextInt();
    System.out.print("ID Item: ");
    int itemId = sc.nextInt();
    System.out.print("Jumlah hari pinjam: ");
    int days = sc.nextInt();

    Member m = library.findMemberById(memberId);
    LibraryItem item = library.findItemById(itemId);
    String result = m.borrow(item, days);
    System.out.println(result);
    String jenis = item instanceof Book ? "Buku" : "DVD";
    library.getLogger().logActivity(jenis + " \"" + item.getTitle() + "\" dipinjam oleh " + m.getName());

  }

  static void kembalikanItem() {
    System.out.print("ID Anggota: ");
    int memberId = sc.nextInt();
    System.out.print("ID Item: ");
    int itemId = sc.nextInt();
    System.out.print("Jumlah hari keterlambatan: ");
    int daysLate = sc.nextInt();

    Member m = library.findMemberById(memberId);
    LibraryItem item = library.findItemById(itemId);
    String result = m.returnItem(item, daysLate); // ← DI SINI DIPANGGIL returnItem() MILIK MEMBER
    System.out.println(result);
    library.getLogger().logActivity(item.getTitle() + " dikembalikan oleh " + m.getName());
  }

  static void lihatPinjaman() {
    System.out.print("ID Anggota: ");
    int memberId = sc.nextInt();
    Member m = library.findMemberById(memberId);
    System.out.println(m.getBorrowedItems());
  }
}
