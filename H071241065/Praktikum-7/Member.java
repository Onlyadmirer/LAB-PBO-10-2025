import java.util.*;

public class Member {
  private String name;
  private int memberId;
  private List<LibraryItem> borrowedItems;

  public Member(String name, int memberId) {
    this.name = name;
    this.memberId = memberId;
    this.borrowedItems = new ArrayList<>();
  }

  public String borrow(LibraryItem item, int days) {
    if (item.isBorrowed())
      throw new IllegalStateException("Item tidak tersedia.");
    String result = item.borrowItem(days);
    borrowedItems.add(item);
    return result;
  }

  public String returnItem(LibraryItem item, int daysLate) {
    borrowedItems.remove(item);
    item.returnItem(); // ← INI PENTING!
    double fine = item.calculateFine(daysLate);
    return "Item " + item.getTitle() + " berhasil dikembalikan dengan denda: Rp " + String.format("%,.0f", fine);
  }

  public String getBorrowedItems() {
    if (borrowedItems.isEmpty())
      return "Tidak ada item yang dipinjam";
    StringBuilder sb = new StringBuilder();
    for (LibraryItem item : borrowedItems)
      sb.append(item.getDescription()).append("\n");
    return sb.toString();
  }

  public int getMemberId() {
    return memberId;
  }

  public String getName() {
    return name;
  }
}
