package com.quantumn.future.core;

public class Apple {

    @FruitProvider(id = 1, name = "陕西红富士集团", address = "陕西省西安市延安路")
    public String appleProvider;

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;

    }
    public String getAppleProvider() {
        return appleProvider;
    }

    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("com.quantumn.future.core.Apple");
            FruitInfoUtil.getFruitInfo(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
