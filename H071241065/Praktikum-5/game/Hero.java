package game;

public class Hero {
    public String name;

    public Hero(String name) {
        this.name = name;
    }

    public int health;
    public int attackPower;

    public Hero(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public void attack() {
        System.out.println(name + " menyerang dengan kekuatan " + attackPower);
    }
}
