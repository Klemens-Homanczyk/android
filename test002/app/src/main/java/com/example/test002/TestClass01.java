package com.example.test002;

import java.util.Random;

public class TestClass01 {

    public String getExampleText()
    {
        Random random = new Random();
        return String.valueOf(random.nextInt());
    }
}
