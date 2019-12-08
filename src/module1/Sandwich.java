package module1;

public class Sandwich extends Food{

    private String filling1;
    private String filling2;

    public Sandwich(String filling1,String filling2) {
        super("Бутерброд");
        this.filling1 = filling1;
        this.filling2 = filling2;
    }


    @Override
    public void consume() {
        System.out.print(this + "съеден");
    }

    @Override
    public int calculateCalories() {
        int sum = 0;
        if(filling1.equals("cheese")) {
            sum += 50;
        }
        if(filling1.equals("butter")) {
            sum += 30;
        }
        if(filling2.equals("ham")) {
            sum += 60;
        }
        if(filling2.equals("snag")) {
            sum += 70;
        }

        return sum;
    }

    public String getFilling1() {
        return filling1;
    }

    public void setFilling1(String filling1) {
        this.filling1 = filling1;
    }

    public String getFilling2() {
        return filling2;
    }

    public void setFilling2(String filling2) {
        this.filling2 = filling2;
    }

    public boolean equals(Object o) {
        if(super.equals(o)) {
            if(!(o instanceof Sandwich)) return false;
            if(filling1 == null || filling2 == null) return false;
            Sandwich san = (Sandwich)o;
            if(!(filling1.equals(filling1))) return false;
            return filling2.equals(san.filling2);
        }
        return false;
    }

    public String toString() {
        return super.toString() + " c " + "'" + filling1.toUpperCase() + "'" + " и " + "'" + filling2.toUpperCase() + "' ";
    }
}