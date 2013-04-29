package org.bytefire.ld48.util;

import org.bytefire.ld48.Entity;
import org.bytefire.ld48.Game;
import org.bytefire.ld48.util.Location;

public class Rect {
    private Game g;
    private int x;
    private int y;
    private int xx;
    private int yy;
    public Rect(Game g, int x, int y, short w, short h){
        this.g = g;
        this.x = x;
        this.y = y;
        xx = x + w;
        yy = y + h;
    }

    public Rect(Game g, int x, int y, int xx, int yy){
        this.g = g;
        this.x = x;
        this.y = y;
        this.xx = xx;
        this.yy = yy;
    }

    public Rect(Game g, Location p1, Location p2){
        this.g = g;
        x = (int)p1.getX();
        y = (int)p1.getY();
        xx = (int)p2.getX();
        yy = (int)p2.getY();
    }

    public Location getPoint1(){
        return new Location(g, x, y, 0);
    }

    public Location getPoint2(){
        return new Location(g, xx, yy, 0);
    }

    public void setPoint1(Location loc){
        x = (int)loc.getX();
        y = (int)loc.getY();
    }

    public void setPoint2(Location loc){
        xx = (int)loc.getX();
        yy = (int)loc.getY();
    }

    public void setLocation(Location loc){
        int w = (xx - x)/2;
        int h = (yy - y)/2;
        x = (int)loc.getX() - w;
        xx = (int)loc.getX() + w;
        y = (int)loc.getY() - h;
        yy = (int)loc.getY() + h;
    }

    public int getWidth(){
        return xx-x;
    }

    public int getHeight(){
        return yy-y;
    }

    public void setWidth(int w){
        xx = x + w;
    }

    public void setHeight(int h){
        yy = y + h;
    }

    public boolean isWithin(Entity e){
        return isWithin((int)e.getX(), (int)e.getY());
    }

    public boolean isWithin(int x, int y){
        if(x + 16 > this.x && x - 16 < this.xx &&
            y + 16 > this.y && y - 16 < this.yy ) return true;
        else return false;
    }
}
