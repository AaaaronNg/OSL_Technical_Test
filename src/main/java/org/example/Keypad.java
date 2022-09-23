package org.example;

import java.util.Scanner;

public class Keypad {
    private Scanner input;

    public Keypad() {
        input = new Scanner(System.in);
    }

    public Integer getInteger(){
        return (Integer) input.nextInt();
    }

    public double getDouble(){
        return input.nextDouble();
    }

    public String getString(){
        return input.nextLine();
    }
}
