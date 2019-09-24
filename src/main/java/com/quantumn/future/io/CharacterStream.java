package com.quantumn.future.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CharacterStream {
    public static void main(String[] args) throws Exception {
        BufferedReader buf = new BufferedReader( new FileReader("/Users/huajun"
                + File.separator + "my.txt"));
        String str = null;
        while ((str = buf.readLine()) != null) {
            System.out.println(str);
        }
        buf.close();
    }


}
