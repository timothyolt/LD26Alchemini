package org.bytefire.ld48;

import org.bytefire.ld48.Game.KeyState;
import org.bytefire.ld48.util.Camera;
import org.bytefire.ld48.util.Location;
import org.bytefire.ld48.util.Sprite;
import org.bytefire.ld48.util.TileLoader;
import org.lwjgl.input.Keyboard;

public class Player implements Entity{
    Location loc;
    boolean draw;
    boolean phys;
    TileLoader sprite;
    public Player(Location loc, boolean draw, boolean physics){
        this.loc = loc;
        this.draw = draw;
        this.phys = physics;
        sprite = new TileLoader("player.png");
    }
    public Game getGame(){
        return loc.getGame();
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

    public Location getLoc(){
        return loc;
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

    public void setLoc(Location loc){
        this.loc = loc;
    }

    public boolean getDraw(){
        return draw;
    }

    public void setDraw(boolean draw){
        this.draw = draw;
    }

    public void drawEntity(Game game){
        if (draw){
            Location origin = game.getView().getBounds().getPoint1();
            Sprite spr = game.getSprite("player.png");
            spr.draw((int)getX() - (int)origin.getX(), (int)getY() - (int)origin.getY());
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
            if (game.getKey(Keyboard.KEY_D) == KeyState.Pressed) setX(getX()-2);
            if (game.getKey(Keyboard.KEY_A) == KeyState.Pressed) setX(getX()+2);
            if (game.getKey(Keyboard.KEY_W) == KeyState.Pressed) setY(getY()+2);
            if (game.getKey(Keyboard.KEY_S) == KeyState.Pressed) setY(getY()-2);
        }
    }

    public void destroy(Game game){
        game.removeEntity(this);
    }

}
