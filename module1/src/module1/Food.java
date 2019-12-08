package module1;

public abstract class Food implements Consumable, Nutritious {

    private String name = null;

    public Food(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Food)) return false;
        if (name == null || ((Food) o).name == null) return false;
        return name.equals(((Food) o).name);
    }

    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}