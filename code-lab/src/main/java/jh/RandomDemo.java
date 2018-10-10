package jh;

import java.util.Random;

public class RandomDemo {
    public static void main(String[] args) {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int i1 = r.nextInt(2);
            System.out.println(i1);
        }
    }
}
