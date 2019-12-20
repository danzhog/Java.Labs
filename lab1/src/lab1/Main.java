package laba_1C;

import java.lang.reflect.Constructor;
import java.util.*;

public class Main
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception
    {
        Food[] breakfast = new Food[14];
        breakfast[0]=breakfast[1]=new Apple("маленькое");
        breakfast[2]=breakfast[3]=new Apple("среднее");
        breakfast[4]=breakfast[5]=new Apple("большое");
        breakfast[6]=breakfast[7]=new Cheese();
        breakfast[8]=breakfast[9]=new Sandwich("ветчина","сыр");
        breakfast[10]=breakfast[11]=new Sandwich("помидор","сыр");
        breakfast[12]=breakfast[13]=new Sandwich("ветчина","помидор");
        int i = 1;
        boolean var1, var2;
        var1 = var2 = false;
args=new String[1];
args[0]="-calories";

        for (String arg : args)
        {
            String[] parts = arg.split(("/"));
             try {
                Class myClass = Class.forName("laba_1C." + parts[0]);
                if (parts.length == 1) {
                    Constructor constructor = myClass.getConstructor();
                    breakfast[i] = (Food) constructor.newInstance();
                    i++;
                } else if (parts.length == 2) {
                    Constructor constructor = myClass.getConstructor(String.class);
                    breakfast[i] = (Food) constructor.newInstance(parts[1]);
                    i++;
                } else if (parts.length == 3) {
                    Constructor constructor = myClass.getConstructor(String.class, String.class);
                    breakfast[i] = (Food) constructor.newInstance(parts[1], parts[2]);
                    i++;
                }
            } catch (ClassNotFoundException e)
            {
                switch (parts[0])
                {
                    case "-sort":
                        var1 = true;
                        break;
                    case "-calories":
                        var2 = true;
                        break;
                    default:
                        System.out.println("Класс " + parts[0] + " не найден.");
                        break;
                }

            }
            catch (NoSuchMethodException e)
            {
                System.out.println("Метод класса " + parts[0] + " не был найден.");
            }
        }

        System.out.println("Завтрак: ");
        for (Food item : breakfast) {
            if (item != null)
            {
                if (item.calculateCalories()==0.0)
                {
                    System.out.print("Такой продукт не предусмотрен (" + item.name);
                    if(item.par1!=null)
                        System.out.print(", " + item.par1);
                    if(item.par2!=null)
                        System.out.print(", " + item.par2);
                    System.out.println(")");
                    continue;
                }
                item.consume();
                System.out.println(" " + item.calculateCalories()+" калорий");
            } else {
                break;
            }
        }

        if (var1)
        {
            Arrays.sort(breakfast, new Comparator() {
                public int compare(Object o1, Object o2)
                {
                    if (o1 == null || ((Food)o1).name.length() > ((Food)o2).name.length())
                        return 1;
                    if (o2 == null || ((Food)o1).name.length() < ((Food)o2).name.length())
                        return -1;
                    else return 0;
                }
            });

            System.out.println("Завтрак (отсортированный вариант):");
            for (Food item : breakfast)
            {
                if (item != null)
                {
                    if (item.calculateCalories()==0.0)
                        continue;
                    item.consume();
                    System.out.println(" " + item.calculateCalories());
                }
                else
                    break;
            }
        }
        if (var2)
        {
            double CaloriesCounter = 0.0;
            for (Food item : breakfast)
            {
                if (item != null)
                    CaloriesCounter += item.calculateCalories();
                else
                    break;
            }
            System.out.println("Общее количество калорий: " + CaloriesCounter);

        }
       int eatten_a1, eatten_a2, eatten_a3, eatten_c, eatten_s1, eatten_s2, eatten_s3;
       eatten_a1 = eatten_a2 = eatten_a3 = eatten_c = eatten_s1 = eatten_s2 = eatten_s3 = 0;
        for(Food item: breakfast)
        {
            if(item == null)
                break;
            if(item.name.equals("Яблоко"))
            {
                if(item.par1.equals("маленькое"))
                    eatten_a1++;
                else if(item.par1.equals("среднее"))
                    eatten_a2++;
                else if(item.par1.equals("большое"))
                    eatten_a3++;
            }
            if(item.name.equals("Сыр"))
                eatten_c++;
            if(item.name.equals("Бутерброд"))
            {
                if(item.par1.equals("сыр") || item.par2.equals("сыр"))
                {
                    if(item.par1.equals("ветчина") || item.par2.equals("ветчина"))
                        eatten_s1++;
                    if(item.par1.equals("помидор") || item.par2.equals("помидор"))
                        eatten_s2++;
                }
                if(item.par1.equals("ветчина") || item.par2.equals("ветчина"))
                {
                    if(item.par1.equals("помидор") || item.par2.equals("помидор"))
                        eatten_s3++;
                }
            }
        }
        System.out.println("На завтрак съедено:");
        System.out.println(" маленьких яблок - " + eatten_a1 + ", средних яблок - " + eatten_a2 + ", больших яблок - " + eatten_a3);
        System.out.println(" кусочков сыра - " + eatten_c);
        System.out.println(" бутербродов с ветчиной и сыром - " + eatten_s1);
        System.out.println(" бутербродов с помидором и сыром - " + eatten_s2);
        System.out.println(" бутербродов с ветчиной и помидором - " + eatten_s3);



    }

}