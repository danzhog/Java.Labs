package module1;

public class Cheese extends Food {

    public Cheese() {
        super("Сыр");
    }

    public int calculateCalories() {
        return 402;
    }

    public void consume() {
        System.out.print(this + " съеден");
    }

}