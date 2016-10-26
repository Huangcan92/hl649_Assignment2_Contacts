package android.contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    private int id;
    private String name;
    private String number;
    private boolean isChoose;

    private List<Person> ship;
    private String ids;
    public Person(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
        ship = new ArrayList<>();
    }

    public Person(String name, String number) {
        this.name = name;
        this.number = number;
        ship = new ArrayList<>();
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public List<Person> getShip() {
        return ship;
    }

    public void setShip(List<Person> ship) {
        this.ship = ship;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name='" + name + '\'' + ", number='" + number + '\'' + ", isChoose=" + isChoose + ", ship=" + ship + '}';
    }
}

