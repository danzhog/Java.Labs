package module1;

public class Apple extends Food {

    private String size;

    public Apple(String size) {
        super("Яблоко");
        if(!size.equals("small") && !size.equals("medium") && !size.equals("big")) {
            System.out.println("Неизвестный размер яблока - " + size);
            this.size = "unknown";
        }else {
            this.size = size;
        }
    }

    public int calculateCalories() {
        if (size.equals("small")) {
            return 78;
        } else if (size.equals("medium")) {
            return 95;
        } else if (size.equals("big")) {
            return 116;
        }

        return 0;
    }

    public void consume() {
        System.out.print(this + " съедено");
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean equals(Object o) {
        if (super.equals(o)) {
            if (!(o instanceof Apple)) return false;
            if (size == null || ((Apple) o).size == null) return false;
            return size.equals(((Apple) o).size);
        }
        return false;
    }

    public String toString() {
        return super.toString() + " размера '" + size.toUpperCase() + "'";
    }
}