package laba_7A;

              /*Клоасс - тип данных "Пользователь" с функциями*/
public class User {

    private String name;
    private String address;

    public User (String name, String addres) {
        this.name = name;
        this.address = addres;
    }

    public String getName() {
        return name;
    }

    public String getAddres() {
        return address;
    }
}
