/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.darkeh.projectd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import me.darkeh.projectd.assets.Asset;
import me.darkeh.projectd.assets.Level;
import me.darkeh.projectd.assets.TexInfo;

/**
 *
 * @author kendall
 */
public class Interpreter {
    @SuppressWarnings({"null", "ConstantConditions"})
    public static void main(String[] args){
        FileInputStream file = null;
        try {
            file = new FileInputStream("level1.txt");
        } catch (FileNotFoundException ex) {}
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(file, "UTF-8");
        } catch (UnsupportedEncodingException ex) {System.out.println("Yell at tim for assuming things");}
        StringBuilder string = new StringBuilder();
        
        for (int charnumber = 0; charnumber <= 4096; charnumber++){
            try {
                string.append(String.copyValueOf(Character.toChars(reader.read())));
                
            } catch (IOException ex) {}
        }
        String[] array = string.toString().split(",");
        byte[][][][] byteArray = new byte[64][64][1][1];
        for (int y = 0; y < 64; y++) for(int x = 0; x < 64; x++){
            if (y*64 + x < array.length - 1){
                System.out.println(y*64 + x);
                String temp = array[y*64 + x];
                if (temp.charAt(0) == ' ') temp = temp.substring(1);
                try {byteArray[x][y][0][0] = Byte.valueOf(temp);} catch(NumberFormatException e){}
            }
        }
        
        Level level1 = new Level((short)11, new ArrayList<TexInfo>(), 64, 64);
        level1.setIDs(byteArray);
        level1.printIds();
        Asset level = new Asset("Level1-2.d", false);
        level.write(level1);
    }
}
