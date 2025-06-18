import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LibraryLogger {
  private List<String> logs = new ArrayList<>();

  public String logActivity(String activity) {
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String entry = timestamp + " " + activity;
    logs.add(entry);
    return entry;
  }

  public String getLogs() {
    return logs.isEmpty() ? "Belum ada aktivitas." : String.join("\n", logs);
  }

  public void clearLogs() {
    logs.clear();
  }
}
