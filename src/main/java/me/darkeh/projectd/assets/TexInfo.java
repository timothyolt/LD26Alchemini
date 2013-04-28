package me.darkeh.projectd.assets;

import java.io.Serializable;

public class TexInfo implements Serializable{
    private int id;
    private String name;
    private short vw;
    private short xw;
    private short yw;
    private short zw;

    public TexInfo(int id, String name, short vw, short xw, short yw, short zw){
        this.id = id;
        this.name = name;
        this.vw = vw;
        this.xw = xw;
        this.yw = yw;
        this.zw = zw;
    }

    public TexInfo(int id, String name, short vw, short xw, short yw){
        this.id = id;
        this.name = name;
        this.vw = vw;
        this.xw = xw;
        this.yw = yw;
        this.zw = 1;
    }

    public TexInfo(int id, String name, short vw, short xw){
        this.id = id;
        this.name = name;
        this.vw = vw;
        this.xw = xw;
        this.yw = 1;
        this.zw = 1;
    }

    public TexInfo(int id, String name, short vw){
        this.id = id;
        this.name = name;
        this.vw = vw;
        this.xw = 1;
        this.yw = 1;
        this.zw = 1;
    }

    public int getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public short getDim1Width(){
        return vw;
    }

    public short getDim2Width(){
        return xw;
    }

    public short getDim3Width(){
        return yw;
    }

    public short getDim4Width(){
        return zw;
    }
}
