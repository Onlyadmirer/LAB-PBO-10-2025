public abstract class LibraryItem {
  protected String title;
  protected int itemId;
  public boolean borrowed;

  public LibraryItem(String title, int itemId) {
    this.title = title;
    this.itemId = itemId;
    this.borrowed = false;
  }

  public abstract String getDescription();

  public abstract String borrowItem(int days);

  public abstract double calculateFine(int daysLate);

  public String returnItem() {
    this.borrowed = false;
    return title + " dikembalikan";

  }

  public boolean isBorrowed() {
    return borrowed;
  }

  public void setBorrowed(boolean borrowed) {
    this.borrowed = borrowed;
  }

  public String getTitle() {
    return title;
  }

  public int getItemId() {
    return itemId;
  }
}
