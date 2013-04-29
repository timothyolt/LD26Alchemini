/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bytefire.ld48;

import org.bytefire.ld48.util.Location;
import org.bytefire.ld48.util.Sprite;


/**
 *
 * @author kendall
 */
public class FreeTermite implements Entity{
    
    Location loc;
    boolean draw;
    boolean phys;
    public FreeTermite(Location loc, boolean draw, boolean physics){
        this.loc = loc;
        this.draw = draw;
        this.phys = physics;
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
            Sprite spr = game.getSprite("termite.png");
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
            if (game.getView().onScreen(this)){
                if(game.getEntities().get(0).getX()>getX()) setX(getX()+5);
                if(game.getEntities().get(0).getX()<getX()) setX(getX()-5);
                if(game.getEntities().get(0).getY()>getY()) setY(getY()+5);
                if(game.getEntities().get(0).getY()>getY()) setY(getY()-5);
                
                
            }
        }
    }        
        
    private void idleAI(){
        
    }

    public void destroy(Game game){
        game.removeEntity(this);
    }

    public PotionState getPotionState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPotionState(PotionState state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getPotionTicks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

