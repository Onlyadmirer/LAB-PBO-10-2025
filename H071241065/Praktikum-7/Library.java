import java.util.*;

public class Library {
  private List<LibraryItem> items = new ArrayList<>();
  private List<Member> members = new ArrayList<>();
  private LibraryLogger logger = new LibraryLogger();

  public String addItem(LibraryItem item) {
    items.add(item);
    return item.getTitle() + " berhasil ditambahkan";
  }

  public String addMember(Member member) {
    members.add(member);
    return "Anggota " + member.getName() + " berhasil ditambahkan";
  }

  public LibraryItem findItemById(int id) {
    for (LibraryItem item : items) {
      if (item.getItemId() == id) {
        return item;
      }
    }
    throw new NoSuchElementException("Item tidak ditemukan.");
  }

  public Member findMemberById(int id) {
    for (Member m : members) {
      if (m.getMemberId() == id) {
        return m;
      }
    }
    throw new NoSuchElementException("Anggota tidak ditemukan.");
  }

  public String getLibraryStatus() {
    StringBuilder sb = new StringBuilder();
    sb.append("+---------+----------------+-----------+\n");
    sb.append("| ID Item | Judul          | Status    |\n");
    sb.append("+---------+----------------+-----------+\n");
    for (LibraryItem item : items) {
      String status = item.isBorrowed() ? "Dipinjam" : "Tersedia";
      sb.append(String.format("| %-7d | %-14s | %-9s |\n",
          item.getItemId(),
          item.getTitle().length() > 14 ? item.getTitle().substring(0, 14) : item.getTitle(),
          status));
    }
    sb.append("+---------+----------------+-----------+");
    return sb.toString();
  }

  public String getAllLogs() {
    return logger.getLogs();
  }

  public LibraryLogger getLogger() {
    return logger;
  }
}
