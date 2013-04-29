package org.bytefire.ld48;

import org.bytefire.ld48.util.Location;
public interface Entity {
    
    public enum PotionState {NORMAL, BIG, STRONG, SMALL};
    
    Game getGame();

    double getX();

    double getY();

    double getZ();

    Location getLoc();

    void setX(double x);

    void setY(double y);

    void setZ(double z);

    void setLoc(Location loc);

    boolean getDraw();

    void setDraw(boolean draw);

    void drawEntity(Game game);

    boolean getPhysics();

    void setPhysics(boolean phys);

    void doPhysics(Game game);
    
    PotionState getPotionState();
    
    int getPotionTicks();
    
    void setPotionState(PotionState state);

    void destroy(Game game);
}
