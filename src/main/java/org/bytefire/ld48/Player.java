package org.bytefire.ld48;

import java.util.HashMap;
import me.darkeh.projectd.assets.TexInfo;
import org.bytefire.ld48.Game.KeyState;
import org.bytefire.ld48.util.Location;
import org.bytefire.ld48.util.Tile;
import org.bytefire.ld48.util.TileLoader;
import org.lwjgl.input.Keyboard;

public class Player implements Entity{
    Location loc;
    boolean draw;
    boolean phys;
    TileLoader spriteSheet;
    private HashMap<Integer, TexInfo> tiles;
    public Player(Location loc, boolean draw, boolean physics){
        this.loc = loc;
        this.draw = draw;
        this.phys = physics;
        spriteSheet = new TileLoader("player.png");
        tiles = new HashMap<Integer, TexInfo>();
        tiles.put(0, new TexInfo(0, "DownStill", 0, 0, 8, 8));
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
            //Sprite spr = game.getSprite("player.png");
            Tile spr = new Tile(spriteSheet, tiles.get(1));
            spr.draw(76, 56);
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
            final int speed = 2;
            if (game.getKey(Keyboard.KEY_D) == KeyState.Pressed) setX(getX()-speed);
            if (game.getKey(Keyboard.KEY_A) == KeyState.Pressed) setX(getX()+speed);
            if (game.getKey(Keyboard.KEY_W) == KeyState.Pressed) setY(getY()+speed);
            if (game.getKey(Keyboard.KEY_S) == KeyState.Pressed) setY(getY()-speed);
        }
    }

    public void destroy(Game game){
        game.removeEntity(this);
    }

}
