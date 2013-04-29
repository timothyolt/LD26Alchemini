package org.bytefire.ld48.util;

import org.bytefire.ld48.Game;

public class Location {
    private Game game;
    private double x;
    private double y;
    private double z;
    public Location(Game game, double x, double y, double z){
        this.game = game;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Game getGame(){
        return game;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ(){
        return z;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setZ(double z){
        this.z = z;
    }

    public double distance(int xx, int yy){
        return Math.sqrt(((xx - x)*(xx - x))+((yy - y)*(yy - y)));
    }
}
