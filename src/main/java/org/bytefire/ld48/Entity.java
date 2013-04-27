package org.bytefire.ld48;

public interface Entity {
    double getX();

    double getY();

    double getZ();

    void setX(double x);

    void setY(double y);

    void setZ(double z);

    boolean getDraw();

    void setDraw(boolean draw);

    void drawEntity(Game game);

    boolean getPhysics();

    void setPhysics(boolean phys);

    void doPhysics(Game game);

    void destroy(Game game);
}
