package org.bytefire.ld48;

import org.bytefire.ld48.Game.KeyState;
import org.bytefire.ld48.util.Location;
import org.bytefire.ld48.util.Sprite;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

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
            Sprite spr = game.getSprite("player.png");
            spr.draw((int)getX(), (int)getY());
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
            if (game.getKey(Keyboard.KEY_D) == KeyState.Pressed) setX(getX()+5);
            if (game.getKey(Keyboard.KEY_A) == KeyState.Pressed) setX(getX()-5);
            if (game.getKey(Keyboard.KEY_W) == KeyState.Pressed) setY(getY()-5);
            if (game.getKey(Keyboard.KEY_S) == KeyState.Pressed) setY(getY()+5);
        }
    }

    public void destroy(Game game){
        game.removeEntity(this);
    }

}
