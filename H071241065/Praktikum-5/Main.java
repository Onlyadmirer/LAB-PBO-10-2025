import game.*;
import java.util.Scanner;

public class Main {
  public static Scanner sc = new Scanner(System.in);
  public static Pemanah pemanah;
  public static Penyihir penyihir;
  public static Petarung petarung;
  public static int pilihMenu;

  public static void main(String[] args) {
    runApp();

  }

  public static void runApp() {
    pemanah = new Pemanah("pemanah", 70, 17);
    penyihir = new Penyihir("penyihir", 70, 19);
    petarung = new Petarung("petarung", 100, 18);

    System.out.println("Pilih karakter: ");
    System.out.println("1. penyihir ");
    System.out.println("2. pemanah ");
    System.out.println("3. petarung ");
    System.out.print("> ");

    pilihMenu = sc.nextInt();
    // sc.nextLine();
    switch (pilihMenu) {
      case 1:
        wizard();
        break;
      case 2:
        archer();
        break;
      case 3:
        fighter();
        break;
      default:
        System.out.println("Pilihan tidak valid");
        runApp();
    }
  }

  // Pemanah
  public static void archer() {
    while (true) {

      System.out.println("\nMenu: ");
      System.out.println("1. serang ");
      System.out.println("2. keluar ");
      System.out.print("> ");

      int pilihAksi = sc.nextInt();
      // sc.nextLine();
      switch (pilihAksi) {
        case 1:
          serang();
          break;
        case 2:
          keluar();
          break;
        default:
          System.out.println("invalid");
          System.exit(0);
      }
    }
  }

  // Penyihir
  public static void wizard() {
    while (true) {

      System.out.println("\nMenu: ");
      System.out.println("1. serang ");
      System.out.println("2. keluar ");
      System.out.print("> ");

      int pilihAksi = sc.nextInt();
      // sc.nextLine();
      switch (pilihAksi) {
        case 1:
          serang();
          break;
        case 2:
          keluar();
          break;
        default:
          System.out.println("invalid");
          System.exit(0);
      }
    }
  }

  // Petarung
  public static void fighter() {
    while (true) {

      System.out.println("\nMenu: ");
      System.out.println("1. serang ");
      System.out.println("2. keluar ");
      System.out.println("pilih aksi: ");
      System.out.print("> ");

      int pilihAksi = sc.nextInt();
      // sc.nextLine();
      switch (pilihAksi) {
        case 1:
          serang();
          break;
        case 2:
          keluar();
          break;
        default:
          System.out.println("invalid");
          System.exit(0);
      }
    }
  }

  public static void serang() {
    if (pilihMenu == 1) {
      penyihir.attack();
    } else if (pilihMenu == 2) {
      pemanah.attack();
    } else if (pilihMenu == 3) {
      petarung.attack();
    } else {
      runApp();
    }
  }

  public static void keluar() {
    System.out.println("game selesai");
    System.exit(0);
  }

}