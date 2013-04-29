package me.darkeh.projectd.assets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Level implements Serializable{
    private short id;
    private short dimensions;
    private int vw;
    private int xw;
    private int yw;
    private int zw;
    private HashMap<Integer, TexInfo> textures;
    private byte[][][][] map;
    private byte[][][][] data;

    public Level(short id, HashMap<Integer, TexInfo> textures, int vw, int xw, int yw, int zw){
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

    public Level(short id, HashMap<Integer, TexInfo> textures, int vw, int xw, int yw){
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

    public Level(short id, HashMap<Integer, TexInfo> textures, int vw, int xw){
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

    public Level(short id, HashMap<Integer, TexInfo> textures, int vw){
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

    public short getLevelID(){
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

    public HashMap<Integer, TexInfo> getUsedTextures(){
        return textures;
    }

    public void addTextureInstance(TexInfo texture, byte data, int v, int x, int y, int z){
        if (!textures.containsKey(texture.getID())) textures.put(texture.getID(), texture);
        map[v][x][y][z] = (byte)texture.getID();
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

    public TexInfo getTextureFromID(int id){
        return textures.get(id);
    }

    public TexInfo getTextureFromGrid(int v, int x, int y, int z){
        int texID = map[v][x][y][z];
        return textures.get(texID);
    }

    public TexInfo getTextureFromGrid(int v, int x, int y){
        return getTextureFromGrid(v, x, y, 0);
    }

    public TexInfo getTextureFromGrid(int v, int x){
        return getTextureFromGrid(v, x, 0, 0);
    }

    public TexInfo getTextureFromGrid(int v){
        return getTextureFromGrid(v, 0, 0, 0);
    }

    public void setIDs(byte[][][][] array){
        map = array;
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
