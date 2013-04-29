package me.darkeh.projectd.assets;

import java.io.Serializable;

public class TexInfo implements Serializable{
    private int id;
    private String ref;
    private int x;
    private int y;
    private int xx;
    private int yy;
    private short vw;
    private short xw;
    private short yw;
    private short zw;

    public TexInfo(int id, String ref, int x, int y, int xx, int yy){
        this.id = id;
        this.ref = ref;
        this.x = x;
        this.y = y;
        this.xx = xx;
        this.yy = yy;
        this.vw = 1;
        this.xw = 1;
        this.yw = 1;
        this.zw = 1;
    }

    public int getID(){
        return id;
    }

    public String getRef(){
        return ref;
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

    public void setDim1Width(short vw){
        this.vw = vw;
    }

    public void setDim2Width(short xw){
        this.xw = xw;
    }

    public void setDim3Width(short yw){
        this.yw = yw;
    }

    public void setDim4Width(short zw){
        this.zw = zw;
    }

    public int getX1(){
        return x;
    }

    public int getX2(){
        return xx;
    }

    public int getY1(){
        return y;
    }

    public int getY2(){
        return yy;
    }

    public void setX1(int x){
        this.x = x;
    }

    public void setX2(int xx){
        this.xx = xx;
    }

    public void setY1(int y){
        this.y = y;
    }

    public void setY2(int yy){
        this.yy = yy;
    }
}
