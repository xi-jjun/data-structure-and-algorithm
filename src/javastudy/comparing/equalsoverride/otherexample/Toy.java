package javastudy.comparing.equalsoverride.otherexample;

import java.util.Objects;

public class Toy {
    int code;
    String name;
    int price;

    public Toy(String name, int price) {
        this.code = (int) (Math.random() * 1000000);
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (Objects.isNull(o) || o.getClass() != this.getClass()) return false;

        Toy other = (Toy) o;
        return this.name.equals(other.name) && this.price == other.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
