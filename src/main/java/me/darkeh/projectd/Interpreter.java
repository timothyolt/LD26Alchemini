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
        FileInputStream file11 = null;
        FileInputStream file12 = null;
        FileInputStream file21 = null;
        FileInputStream file22 = null;
        try {
            file11 = new FileInputStream("level1-1.txt");
            file12 = new FileInputStream("level1-2.txt");
            file21 = new FileInputStream("level2-1.txt");
            file22 = new FileInputStream("level2-2.txt");
        } catch (FileNotFoundException ex) {}
        InputStreamReader reader11 = null;
        InputStreamReader reader12 = null;
        InputStreamReader reader21 = null;
        InputStreamReader reader22 = null;
        try {
            reader11 = new InputStreamReader(file11, "UTF-8");
            reader12 = new InputStreamReader(file12, "UTF-8");
            reader21 = new InputStreamReader(file21, "UTF-8");
            reader22 = new InputStreamReader(file22, "UTF-8");
        } catch (UnsupportedEncodingException ex) {System.out.println("Yell at tim for assuming things");}
        byte[][][][] byteArray11 = null;
        byte[][][][] byteArray12 = null;
        byte[][][][] byteArray21 = null;
        byte[][][][] byteArray22 = null;

        for (int i = 0; i < 4; i++){
            InputStreamReader reader = null;
            switch(i){
                case 0:
                    reader = reader11;
                    break;
                case 1:
                    reader = reader12;
                    break;
                case 2:
                    reader = reader21;
                    break;
                case 3:
                    reader = reader22;
                default:
            }

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

            switch(i){
                case 0:
                    byteArray11 = byteArray;
                    break;
                case 1:
                    byteArray12 = byteArray;
                    break;
                case 2:
                    byteArray21 = byteArray;
                    break;
                case 3:
                    byteArray22 = byteArray;
                default:
            }
        }

        Level level11 = new Level((short)11, new ArrayList<TexInfo>(), 64, 64);
        level11.setIDs(byteArray11);
        level11.printIds();
        Asset asset11 = new Asset("Level1-1.d", false);
        asset11.write(level11);

        Level level12 = new Level((short)12, new ArrayList<TexInfo>(), 64, 64);
        level11.setIDs(byteArray11);
        level11.printIds();
        Asset asset12 = new Asset("Level1-2.d", false);
        asset12.write(level12);

        Level level21 = new Level((short)21, new ArrayList<TexInfo>(), 64, 64);
        level21.setIDs(byteArray21);
        level21.printIds();
        Asset asset21 = new Asset("Level2-1.d", false);
        asset21.write(level21);

        Level level22 = new Level((short)22, new ArrayList<TexInfo>(), 64, 64);
        level22.setIDs(byteArray22);
        level22.printIds();
        Asset asset22 = new Asset("Level2-2.d", false);
        asset22.write(level22);
    }
}
