package javastudy.comparing.equalsoverride.otherexample;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Toy toy1 = new Toy("로봇", 12000);
        Toy toy2 = new Toy("트럭", 9000);
        Toy toy3 = new Toy("로봇", 12000);

        System.out.println(toy1.equals(toy3)); // equals 를 재정의 한 것만으로 true 가 된다.
        System.out.println(toy1 == toy3); // equals 만으로는 false --> hashCode 재정의해도 얘는 false...

        Set<Toy> set = new HashSet<>();
        set.add(toy1);
        set.add(toy3);
        System.out.println(set.size()); // equals 만 재정의하면, 내가 원하는 값으로 '논리적 동일성' 은 정의가능.
        // 그러나 'identity' 는 다르다. --> hashcode 를 override 해야함
    }

}
