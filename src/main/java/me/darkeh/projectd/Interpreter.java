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
        textures.put(9, new TexInfo(9, "LogVert1Alt1", 64, 0, 72, 8));
        textures.put(10, new TexInfo(10, "LogHor1Alt1", 72, 0, 80, 8));

        textures.put(11, new TexInfo(11, "TallGrassTrans1", 0, 8, 8, 16));
        textures.put(12, new TexInfo(12, "GrassTrans1", 8, 8, 16, 16));
        textures.put(13, new TexInfo(13, "DirtTrans1", 16, 8, 24, 16));
        textures.put(14, new TexInfo(14, "GravelTrans1", 24, 8, 32, 16));
        textures.put(15, new TexInfo(15, "Water2", 32, 8, 40, 16));
        textures.put(16, new TexInfo(16, "RedPotion", 40, 8, 48, 16));
        textures.put(17, new TexInfo(17, "BluePotion", 48, 8, 56, 16));
        textures.put(18, new TexInfo(18, "YelowPotion", 56, 8, 64, 16));
        textures.put(19, new TexInfo(19, "LogVert2Alt1", 64, 8, 72, 16));
        textures.put(20, new TexInfo(20, "LogHor2Alt1", 72, 8, 80, 16));

        textures.put(21, new TexInfo(21, "TallGrassTrans2", 0, 16, 8, 24));
        textures.put(22, new TexInfo(22, "GrassTrans2", 8, 16, 16, 24));
        textures.put(23, new TexInfo(23, "DirtTrans2", 16, 16, 24, 24));
        textures.put(24, new TexInfo(24, "GravelTrans2", 24, 16, 32, 24));
        textures.put(25, new TexInfo(25, "Water3", 32, 16, 40, 24));
        textures.put(26, new TexInfo(26, "TallGrassTop", 40, 16, 48, 24));
        textures.put(27, new TexInfo(27, "TallGrassShort", 48, 16, 56, 24));
        textures.put(28, new TexInfo(28, "Reagent", 56, 16, 64, 24));
        textures.put(29, new TexInfo(29, "LogVert3Alt1", 64, 16, 72, 24));
        textures.put(30, new TexInfo(30, "LogHor3Alt1", 72, 16, 80, 24));

        textures.put(31, new TexInfo(31, "TallGrassTrans3", 0, 24, 8, 32));
        textures.put(32, new TexInfo(32, "GrassTrans3", 8, 24, 16, 32));
        textures.put(33, new TexInfo(33, "DirtTrans3", 16, 24, 24, 32));
        textures.put(34, new TexInfo(34, "GravelTrans3", 24, 24, 32, 32));
        textures.put(35, new TexInfo(35, "Water4", 32, 24, 40, 32));
        textures.put(36, new TexInfo(36, "TallGrassBottom", 40, 24, 48, 32));
        textures.put(37, new TexInfo(37, "LogCorner1", 48, 24, 56, 32));
        textures.put(38, new TexInfo(38, "LogCorner2", 56, 24, 64, 32));
        textures.put(39, new TexInfo(39, "LogCorner3", 64, 24, 72, 32));
        textures.put(40, new TexInfo(40, "LogCorner4", 72, 24, 80, 32));

        textures.put(41, new TexInfo(41, "TallGrassTrans4", 0, 32, 8, 40));
        textures.put(42, new TexInfo(42, "GrassTrans4", 8, 32, 16, 40));
        textures.put(43, new TexInfo(43, "DirtTrans4", 16, 32, 24, 40));
        textures.put(44, new TexInfo(44, "GravelTrans4", 24, 32, 32, 40));
        textures.put(45, new TexInfo(45, "LogVert3Alt2", 32, 32, 40, 40));
        textures.put(46, new TexInfo(46, "LogHor3Alt2", 40, 32, 48, 40));
        textures.put(47, new TexInfo(47, "LogCorner5", 48, 32, 56, 40));
        textures.put(48, new TexInfo(48, "LogCorner6", 56, 32, 64, 40));
        textures.put(49, new TexInfo(49, "LogCorner7", 64, 32, 72, 40));
        textures.put(50, new TexInfo(50, "LogCorner8", 72, 32, 80, 40));

        textures.put(51, new TexInfo(51, "TallGrassTrans5", 0, 40, 8, 48));
        textures.put(52, new TexInfo(52, "GrassTrans5", 8, 40, 16, 48));
        textures.put(53, new TexInfo(53, "DirtTrans5", 16, 40, 24, 48));
        textures.put(54, new TexInfo(54, "GravelTrans5", 24, 40, 32, 48));
        textures.put(55, new TexInfo(55, "LogVert2Alt2", 32, 40, 40, 48));
        textures.put(56, new TexInfo(56, "LogHor2Alt2", 40, 40, 48, 48));
        textures.put(57, new TexInfo(57, "StoneHoriz1", 48, 40, 56, 48));
        textures.put(58, new TexInfo(58, "StoneHoriz2", 56, 40, 64, 48));
        textures.put(58, new TexInfo(59, "StoneHoriz3", 64, 40, 72, 48));
        textures.put(60, new TexInfo(60, "StoneVert1", 72, 40, 80, 48));

        textures.put(61, new TexInfo(61, "TallGrassTrans6", 0, 48, 8, 56));
        textures.put(62, new TexInfo(62, "GrassTrans6", 8, 48, 16, 56));
        textures.put(63, new TexInfo(63, "DirtTrans6", 16, 48, 24, 56));
        textures.put(64, new TexInfo(64, "GravelTrans6", 24, 48, 32, 56));
        textures.put(65, new TexInfo(65, "LogVert3Alt2", 32, 48, 40, 56));
        textures.put(66, new TexInfo(66, "LogHor3Alt2", 40, 48, 48, 56));
        textures.put(67, new TexInfo(67, "BigStone1", 48, 48, 56, 56));
        textures.put(68, new TexInfo(68, "BigStone2", 56, 48, 64, 56));
        textures.put(69, new TexInfo(69, "BigStone3", 64, 48, 72, 56));
        textures.put(70, new TexInfo(70, "StoneVert2", 72, 48, 80, 56));

        textures.put(71, new TexInfo(71, "TallGrassTrans7", 0, 56, 8, 64));
        textures.put(72, new TexInfo(72, "GrassTrans7", 8, 56, 16, 64));
        textures.put(73, new TexInfo(73, "DirtTrans7", 16, 56, 24, 64));
        textures.put(74, new TexInfo(74, "GravelTrans7", 24, 56, 32, 64));
        textures.put(75, new TexInfo(75, "LilyPad2", 32, 56, 40, 64));
        textures.put(76, new TexInfo(76, "LilyPad1", 40, 56, 48, 64));
        textures.put(77, new TexInfo(77, "BigStone4", 48, 56, 56, 64));
        textures.put(78, new TexInfo(78, "BigStone5", 56, 56, 64, 64));
        textures.put(79, new TexInfo(79, "BigStone6", 64, 56, 72, 64));
        textures.put(80, new TexInfo(80, "StoneVert3", 72, 56, 80, 64));

        textures.put(81, new TexInfo(81, "TallGrassTrans8", 0, 64, 8, 72));
        textures.put(82, new TexInfo(82, "GrassTrans8", 8, 64, 16, 72));
        textures.put(83, new TexInfo(83, "DirtTrans8", 16, 64, 24, 72));
        textures.put(84, new TexInfo(84, "GravelTrans8", 24, 64, 32, 72));
        textures.put(85, new TexInfo(85, "NULL", 32, 64, 40, 72));
        textures.put(86, new TexInfo(86, "NULL", 40, 64, 48, 72));
        textures.put(87, new TexInfo(87, "BigStone7", 48, 64, 56, 72));
        textures.put(88, new TexInfo(88, "BigStone8", 56, 64, 64, 72));
        textures.put(89, new TexInfo(89, "BigStone9", 64, 64, 72, 72));
        textures.put(90, new TexInfo(90, "Stone", 72, 64, 80, 72));




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
