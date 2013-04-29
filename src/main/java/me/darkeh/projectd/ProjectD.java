package me.darkeh.projectd;

import java.util.ArrayList;
import me.darkeh.projectd.assets.Asset;
import me.darkeh.projectd.assets.Asset.Container;
import me.darkeh.projectd.assets.Level;
import me.darkeh.projectd.assets.TexInfo;

public class ProjectD {

    public static void main(String[] args) {
        Asset assetExtLevel = new Asset("extLevel.d", false);
        Asset assetResLevel = new Asset(Container.LEVEL);

        Level levelExt = new Level((short) 1, new ArrayList<TexInfo>(), 16, 16);
        Level levelRes;

        TexInfo air = new TexInfo(0, "Air", (short) 1, (short) 1);
        TexInfo water = new TexInfo(1, "Water", (short) 1, (short) 1);
        TexInfo dirt = new TexInfo(2, "Dirt", (short) 1, (short) 1);
        TexInfo grass = new TexInfo(3, "Grass", (short) 1, (short) 1);
        TexInfo tallGrass = new TexInfo(4, "Tall Grass", (short) 1, (short) 1);
        TexInfo gravel = new TexInfo(5, "Gravel", (short) 1, (short) 1);

        assetExtLevel. write(levelExt);

        levelRes = (Level) assetResLevel.read();
        levelExt = (Level) assetExtLevel.read();

        if (levelExt == null) {
            System.out.println("Could not locate external resource \'extLevel.d\'!");
        } else {
            System.out.println("External Level:");
            System.out.println("  ID:" + Short.toString(levelExt.getID()));
            Short dim = levelExt.getDimensions();
            System.out.println("  Dimensions:" + Short.toString(dim));
            switch (dim) {
                case 1:
                    System.out.println("    Dimension 1 Width:" + Integer.toString(levelExt.getDim1Width()));
                    break;
                case 2:
                    System.out.println("    Dimension 1 Width:" + Integer.toString(levelExt.getDim1Width()));
                    System.out.println("    Dimension 2 Width:" + Integer.toString(levelExt.getDim2Width()));
                    break;
                case 3:
                    System.out.println("    Dimension 1 Width:" + Integer.toString(levelExt.getDim1Width()));
                    System.out.println("    Dimension 2 Width:" + Integer.toString(levelExt.getDim2Width()));
                    System.out.println("    Dimension 3 Width:" + Integer.toString(levelExt.getDim3Width()));
                    break;
                case 4:
                    System.out.println("    Dimension 1 Width:" + Integer.toString(levelExt.getDim1Width()));
                    System.out.println("    Dimension 2 Width:" + Integer.toString(levelExt.getDim2Width()));
                    System.out.println("    Dimension 3 Width:" + Integer.toString(levelExt.getDim3Width()));
                    System.out.println("    Dimension 4 Width:" + Integer.toString(levelExt.getDim4Width()));
                    break;
            }

            System.out.println("  Used Textures:");
            for (TexInfo tex : levelExt.getUsedTextures()) {
                System.out.println("    " + Integer.toString(tex.getID()) + ": " + tex.getName());
                System.out.println("      Dimension 1 Width:" + Integer.toString(tex.getDim1Width()));
                System.out.println("      Dimension 2 Width:" + Integer.toString(tex.getDim2Width()));
                System.out.println("      Dimension 3 Width:" + Integer.toString(tex.getDim3Width()));
                System.out.println("      Dimension 4 Width:" + Integer.toString(tex.getDim4Width()));
            }
            System.out.println("  Map:");
            levelExt.printIds();
            System.out.println("  Data:");
            levelExt.printData();
        }
        if (levelRes == null) {
            System.out.println("Could not locate internal level asset resource!");
        } else {
            System.out.println("Resource Level:");
            System.out.println("  ID:" + Short.toString(levelRes.getID()));
            Short dim2 = levelRes.getDimensions();
            System.out.println("  Dimensions:" + Short.toString(dim2));
            switch (dim2) {
                case 1:
                    System.out.println("    Dimension 1 Width:" + Integer.toString(levelRes.getDim1Width()));
                    break;
                case 2:
                    System.out.println("    Dimension 1 Width:" + Integer.toString(levelRes.getDim1Width()));
                    System.out.println("    Dimension 2 Width:" + Integer.toString(levelRes.getDim2Width()));
                    break;
                case 3:
                    System.out.println("    Dimension 1 Width:" + Integer.toString(levelRes.getDim1Width()));
                    System.out.println("    Dimension 2 Width:" + Integer.toString(levelRes.getDim2Width()));
                    System.out.println("    Dimension 3 Width:" + Integer.toString(levelRes.getDim3Width()));
                    break;
                case 4:
                    System.out.println("    Dimension 1 Width:" + Integer.toString(levelRes.getDim1Width()));
                    System.out.println("    Dimension 2 Width:" + Integer.toString(levelRes.getDim2Width()));
                    System.out.println("    Dimension 3 Width:" + Integer.toString(levelRes.getDim3Width()));
                    System.out.println("    Dimension 4 Width:" + Integer.toString(levelRes.getDim4Width()));
                    break;
            }
            System.out.println("  Used Textures:");
            for (TexInfo tex : levelRes.getUsedTextures()) {
                System.out.println("    " + Integer.toString(tex.getID()) + ": " + tex.getName());
                System.out.println("      Dimension 1 Width:" + Integer.toString(tex.getDim1Width()));
                System.out.println("      Dimension 2 Width:" + Integer.toString(tex.getDim2Width()));
                System.out.println("      Dimension 3 Width:" + Integer.toString(tex.getDim3Width()));
                System.out.println("      Dimension 4 Width:" + Integer.toString(tex.getDim4Width()));
            }
            System.out.println("  Map:");
            levelRes.printIds();
            System.out.println("  Data:");
            levelRes.printData();
        }
    }
}
