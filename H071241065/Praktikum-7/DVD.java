public class DVD extends LibraryItem {
  private int duration;

  public DVD(String title, int itemId, int duration) {
    super(title, itemId);
    this.duration = duration;
  }

  @Override
  public String getDescription() {
    return "DVD: " + title + ", durasi " + duration + " menit, ID: " + itemId;
  }

  @Override
  public String borrowItem(int days) {
    if (borrowed)
      throw new IllegalArgumentException("Item sudah dipinjam.");
    if (days > 7)
      throw new IllegalArgumentException("Maksimal peminjaman DVD adalah 7 hari.");
    borrowed = true;
    return "Item " + title + " berhasil dipinjam selama " + days + " hari";
  }

  @Override
  public double calculateFine(int daysLate) {
    return daysLate * 25000;
  }
}
