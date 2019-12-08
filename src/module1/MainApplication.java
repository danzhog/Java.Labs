package module1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Возможные программные аргументы:
 * [-calories] - вывод общей калорийности завтрака
 * [-sort] - сортирует продукты по убыванию количества символов в названии
 * [Apple / (small || medium || big)]
 * [Cheese]
 * [Sandwich / (cheese || butter) / (hun || snug)]
 *
 * p.s. Аргументы [-sort] и [-calories] писать после всех продуктов!
 */

public class MainApplication {

    public static void main(String[] args) throws IllegalAccessException,
            InvocationTargetException, InstantiationException {

        int itemSoFar = 0;
        int calories = 0;

        Food[] breakfast = new Food[20];
        Food type = null;

        for (String arg : args) {

            try {
                if (arg.equals("-calories")) {
                    for (Food food : breakfast) {
                        if (food != null) {
                            calories += food.calculateCalories();
                        } else {
                            break;
                        }
                    }
                } else if (arg.equals("-sort")) {

                    Arrays.sort(breakfast, new Comparator() {
                        public int compare(Object o1, Object o2) {

                            if (o1 == null || ((Food) o1).getName().length() > ((Food) o2).getName().length())
                                return 1;
                            if (o2 == null || ((Food) o1).getName().length() < ((Food) o2).getName().length())
                                return -1;
                            else return 0;
                        }
                    });

                } else {
                    String[] parts = arg.split("/");
                    Class myClass = Class.forName("module1." + parts[0]);

                    if (parts.length == 1) {
                        Constructor constructor = myClass.getConstructor();
                        breakfast[itemSoFar] = (Food) constructor.newInstance();
                    } else if (parts.length == 2) {
                        Constructor constructor = myClass.getConstructor(String.class);
                        breakfast[itemSoFar] = (Food) constructor.newInstance(parts[1]);
                    } else if (parts.length == 3) {
                        Constructor constructor = myClass.getConstructor(String.class, String.class);
                        breakfast[itemSoFar] = (Food) constructor.newInstance(parts[1], parts[2]);
                    }
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Класс не найден.");
            } catch (NoSuchMethodException e) {
                System.out.println("Метод не найден.");
            }

            itemSoFar++;

        }

        int cnt = 0;
        for (Food food : breakfast) {
            if (food != null) {
                if (food.equals(type)) {
                    cnt++;
                }
            } else {
                break;
            }
        }


        for (Food item : breakfast) {
            if (item != null) {
                item.consume();
                System.out.print(",калорийность - " + item.calculateCalories());
                System.out.println();
            } else {
                break;
            }
        }

        if (calories != 0) {
            System.out.println("\n----------------------------------");
            System.out.println("Общая калорийность завтрака - " + calories);
            System.out.println("----------------------------------\n");
        }

        if (type != null) {
            System.out.println("\n" + type + " in breakfast - " + cnt + "\n");
        }

        System.out.println("Всего хорошего!");
    }

}