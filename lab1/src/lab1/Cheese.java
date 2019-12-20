package laba_1C;

public class Cheese extends Food
{

    public Cheese()
    {
        super("Сыр");
    }

    public Double calculateCalories()
    {
        calories = 30.0;
        return calories;
    }

    public void consume()
    {
        System.out.println(this + " съеден");
    }

}