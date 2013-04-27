package org.bytefire.ld48;

import org.bytefire.ld48.util.Location;

public class Player implements Entity{
    Location loc;
    boolean draw;
    boolean phys;
    public Player(Location loc, boolean draw, boolean physics){
        this.loc = loc;
        this.draw = draw;
        this.phys = physics;
    }

    public double getX(){
        return loc.getX();
    }

    public double getY(){
        return loc.getY();
    }

    public double getZ(){
        return loc.getZ();
    }

    public void setX(double x){
        loc.setX(x);
    }

    public void setY(double y){
        loc.setY(y);
    }

    public void setZ(double z){
        loc.setZ(z);
    }

    public boolean getDraw(){
        return draw;
    }

    public void setDraw(boolean draw){
        this.draw = draw;
    }

    public void drawEntity(Game game){
        if (draw){

        }
    }

    public boolean getPhysics(){
        return phys;
    }

    public void setPhysics(boolean phys){
        this.phys = phys;
    }

    public void doPhysics(Game game){
        if (phys){

        }
    }

    public void destroy(Game game){
        game.removeEntity(this);
    }

}
