package me.darkeh.projectd.assets;

import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable{
    private short id;
    private short dimensions;
    private int vw;
    private int xw;
    private int yw;
    private int zw;
    private ArrayList<TexInfo> textures;
    private byte[][][][] map;
    private byte[][][][] data;

    public Level(short id, ArrayList<TexInfo> textures, int vw, int xw, int yw, int zw){
        this.id = id;
        this.dimensions = 4;
        this.vw = vw;
        this.xw = xw;
        this.yw = yw;
        this.zw = zw;
        this.textures = textures;
        map = new byte[vw][xw][yw][zw];
        data = new byte[vw][xw][yw][zw];
    }

    public Level(short id, ArrayList<TexInfo> textures, int vw, int xw, int yw){
        this.id = id;
        this.dimensions = 3;
        this.vw = vw;
        this.xw = xw;
        this.yw = yw;
        this.zw = 1;
        this.textures = textures;
        map = new byte[vw][xw][yw][zw];
        data = new byte[vw][xw][yw][zw];
    }

    public Level(short id, ArrayList<TexInfo> textures, int vw, int xw){
        this.id = id;
        this.dimensions = 2;
        this.vw = vw;
        this.xw = xw;
        this.yw = 1;
        this.zw = 1;
        this.textures = textures;
        map = new byte[vw][xw][yw][zw];
        data = new byte[vw][xw][yw][zw];
    }

    public Level(short id, ArrayList<TexInfo> textures, int vw){
        this.id = id;
        this.dimensions = 1;
        this.vw = vw;
        this.xw = 1;
        this.yw = 1;
        this.zw = 1;
        this.textures = textures;
        map = new byte[vw][xw][yw][zw];
        data = new byte[vw][xw][yw][zw];
    }

    public short getID(){
        return id;
    }

    public short getDimensions(){
        return dimensions;
    }

    public int getDim1Width(){
        return vw;
    }

    public int getDim2Width(){
        return xw;
    }

    public int getDim3Width(){
        return yw;
    }

    public int getDim4Width(){
        return zw;
    }

    public ArrayList<TexInfo> getUsedTextures(){
        return textures;
    }

    public void addTextureInstance(TexInfo texture, byte data, int v, int x, int y, int z){
        if (!textures.contains(texture)) textures.add(texture);
        map[v][x][y][z] = (byte)textures.indexOf(texture);
        this.data[v][x][y][z] = data;
    }

    public void addTextureInstance(TexInfo texture, int v, int x, int y, int z){
        addTextureInstance(texture, (byte)0, v, x, y, z);
    }

    public void addTextureInstance(TexInfo texture, byte data, int v, int x, int y){
        addTextureInstance(texture, data, v, x, y, 0);
    }

    public void addTextureInstance(TexInfo texture, int v, int x, int y){
        addTextureInstance(texture, (byte)0, v, x, y, 0);
    }

    public void addTextureInstance(TexInfo texture, byte data, int v, int x){
        addTextureInstance(texture, data, v, x, 0, 0);
    }

    public void addTextureInstance(TexInfo texture, int v, int x){
        addTextureInstance(texture, (byte)0, v, x, 0, 0);
    }

    public void addTextureInstance(TexInfo texture, byte data, int v){
        addTextureInstance(texture, data, v, 0, 0, 0);
    }

    public void addTextureInstance(TexInfo texture, int v){
        addTextureInstance(texture, (byte)0, v, 0, 0, 0);
    }

    public void delTextureInstance(int v, int x, int y, int z){
        map[v][x][y][z] = 0;
        data[v][x][y][z] = 0;
    }

    public void delTextureInstance(int v, int x, int y){
        delTextureInstance(v, x, y, 0);
    }

    public void delTextureInstance(int v, int x){
        delTextureInstance(v, x, 0, 0);
    }

    public void delTextureInstance(int v){
        delTextureInstance(v, 0, 0, 0);
    }

    public void printIds(){
        for (int z = 0; z < zw; z++){
            for (int y = 0; y < yw; y++){
                for (int x = 0; x < xw; x++){
                    StringBuilder string = new StringBuilder();
                    for (int v = 0; v < vw; v++) string.append(Byte.toString(map[v][x][y][z]));
                    System.out.println(string.toString());
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    public void printData(){
        for (int z = 0; z < zw; z++){
            for (int y = 0; y < yw; y++){
                for (int x = 0; x < xw; x++){
                    StringBuilder string = new StringBuilder();
                    for (int v = 0; v < vw; v++) string.append(Byte.toString(data[v][x][y][z]));
                    System.out.println(string.toString());
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }
}
