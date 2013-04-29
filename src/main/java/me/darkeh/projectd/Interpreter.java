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
import java.util.HashMap;
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
        HashMap<Integer, TexInfo> textures = new HashMap<Integer, TexInfo>();
        textures.put(1, new TexInfo(1, "TallGrass", 0, 0, 8, 8));
        textures.put(2, new TexInfo(2, "Grass", 8, 0, 16, 8));
        textures.put(3, new TexInfo(3, "Dirt", 16, 0, 24, 8));
        textures.put(4, new TexInfo(4, "Gravel", 24, 0, 32, 8));
        textures.put(5, new TexInfo(5, "Water1", 32, 0, 40, 8));
        textures.put(6, new TexInfo(6, "RedFlower", 40, 0, 48, 8));
        textures.put(7, new TexInfo(7, "BlueFlower", 48, 0, 56, 8));
        textures.put(8, new TexInfo(8, "YellowFlower", 56, 0, 64, 8));
        textures.put(9, new TexInfo(9, "UpLogAlt1", 64, 0, 72, 8));
        textures.put(10, new TexInfo(10, "LogLeftAlt1", 72, 0, 80, 8));
        
        textures.put(11, new TexInfo(11, "TallGrassTrans1", 0, 8, 8, 16));
        textures.put(12, new TexInfo(12, "GrassTrans1", 8, 8, 16, 16));
        textures.put(13, new TexInfo(13, "DirtTrans1", 16, 8, 24, 16));
        textures.put(14, new TexInfo(14, "GravelTrans1", 24, 8, 32, 16));
        textures.put(15, new TexInfo(15, "Water2", 32, 8, 40, 16));
        textures.put(16, new TexInfo(16, "RedPotion", 40, 8, 48, 16));
        textures.put(17, new TexInfo(17, "BluePotion", 48, 8, 56, 16));
        textures.put(18, new TexInfo(18, "YelowPotion", 56, 8, 64, 16));
        textures.put(19, new TexInfo(19, "LogVerticalAlt1", 64, 8, 72, 16));
        textures.put(20, new TexInfo(20, "LogHorizontalAlt1", 72, 8, 80, 16));
        
        textures.put(20, new TexInfo(21, "TallGrassTrans2", 0, 16, 8, 24));
        textures.put(20, new TexInfo(22, "GrassTrans2", 8, 16, 16, 24));
        textures.put(20, new TexInfo(23, "DirstTrans2", 16, 16, 24, 24));
        textures.put(20, new TexInfo(24, "GravelTrans2", 24, 16, 32, 24));
        textures.put(20, new TexInfo(25, "Water3", 32, 16, 40, 24));
        textures.put(20, new TexInfo(26, "TallGrassTop", 40, 16, 48, 24));
        textures.put(20, new TexInfo(27, "TallGrassShort", 48, 16, 56, 24));
        textures.put(20, new TexInfo(28, "Reagent", 56, 16, 64, 24));
        textures.put(20, new TexInfo(29, "LogHorizontalAlt1", 64, 16, 72, 24));
        textures.put(20, new TexInfo(30, "LogHorizontalAlt1", 72, 16, 80, 24));
        
        
        Level level11 = new Level((short)11,textures , 64, 64);
        level11.setIDs(byteArray11);
        level11.printIds();
        Asset asset11 = new Asset("Level1-1.d", false);
        asset11.write(level11);

        Level level12 = new Level((short)12, new HashMap<Integer, TexInfo>(), 64, 64);
        level11.setIDs(byteArray11);
        level11.printIds();
        Asset asset12 = new Asset("Level1-2.d", false);
        asset12.write(level12);

        Level level21 = new Level((short)21, new HashMap<Integer, TexInfo>(), 64, 64);
        level21.setIDs(byteArray21);
        level21.printIds();
        Asset asset21 = new Asset("Level2-1.d", false);
        asset21.write(level21);

        Level level22 = new Level((short)22, new HashMap<Integer, TexInfo>(), 64, 64);
        level22.setIDs(byteArray22);
        level22.printIds();
        Asset asset22 = new Asset("Level2-2.d", false);
        asset22.write(level22);
    }
}
