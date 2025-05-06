package game;

public class Petarung extends Hero {
  public Petarung(String name, int health, int attackPower) {
    super(name, health, attackPower);
  }

  public void attack() {
    System.out.println(name + " attacks with a sword.");
    super.attack();
  }

  public Petarung(String name) {
    super(name);

  }

}
