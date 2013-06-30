package org.bytefire.ld48;

import java.util.HashMap;
import me.darkeh.projectd.assets.Level;
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
            Location dummy = new Location(game,
                ((loc.getX() * -1/2) + 76)/4,
                ((loc.getY() * -1/2) + 60)/4, 0);
            Level physicsLayer = game.getPhysicsLayer();
            System.out.println(Double.toString(Math.abs(dummy.getX())) + ", " + Double.toString(Math.abs(dummy.getY())));

            if (game.getKey(Keyboard.KEY_D) == KeyState.Pressed){
                TexInfo flag1 = physicsLayer.getTextureFromGrid(
                        (int)Math.abs(Math.floor(dummy.getX())-1),
                        (int)Math.abs(Math.floor(dummy.getY())));
                TexInfo flag2 = physicsLayer.getTextureFromGrid(
                        (int)Math.abs(Math.floor(dummy.getX())-1),
                        (int)Math.abs(Math.ceil(dummy.getY())));
                if ((flag1 == null || flag1.getID() == 0)&&(flag2 == null || flag2
                        .getID() == 0)) setX(getX()-speed);
                else System.out.println(Double.toString(Math.abs(dummy.getX()-1)) + ", " + Double.toString(Math.abs(dummy.getY())));

                setX(getX()-speed);
            }

            if (game.getKey(Keyboard.KEY_A) == KeyState.Pressed){
//                TexInfo flag = physicsLayer.getTextureFromGrid((int)Math.abs(dummy.getX()+1), (int)Math.abs(dummy.getY()));
//                if (flag == null || flag.getID() == 0) setX(getX()+speed);
//                else System.out.println(Double.toString(Math.abs(dummy.getX()+1)) + ", " + Double.toString(Math.abs(dummy.getY())));

                setX(getX()+speed);
            }

            if (game.getKey(Keyboard.KEY_W) == KeyState.Pressed){
//                TexInfo flag = physicsLayer.getTextureFromGrid((int)Math.abs(dummy.getX()), (int)Math.abs(dummy.getY()+1));
//                if (flag == null || flag.getID() == 0) setY(getY()+speed);
//                else System.out.println(Double.toString(Math.abs(dummy.getX())) + ", " + Double.toString(Math.abs(dummy.getY()+1)));

                setY(getY()+speed);
            }

            if (game.getKey(Keyboard.KEY_S) == KeyState.Pressed){
//                TexInfo flag = physicsLayer.getTextureFromGrid((int)Math.abs(dummy.getX()), (int)Math.abs(dummy.getY()-1));
//                if (flag == null || flag.getID() == 0) setY(getY()-speed);
//                else System.out.println(Double.toString(Math.abs(dummy.getX()-1)) + ", " + Double.toString(Math.abs(dummy.getY()-1)));

                setY(getY()-speed);
            }

        }
    }

    public void destroy(Game game){
        game.removeEntity(this);
    }

    public PotionState getPotionState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getPotionTicks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPotionState(PotionState state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int health() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
