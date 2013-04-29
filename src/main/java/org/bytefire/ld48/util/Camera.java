package org.bytefire.ld48.util;

import java.io.IOException;
import java.util.logging.Logger;
import me.darkeh.projectd.assets.Asset;
import me.darkeh.projectd.assets.Level;
import me.darkeh.projectd.assets.TexInfo;
import org.bytefire.ld48.Entity;

public class Camera{
    private Rect view;
    private Entity center;
    public enum LevelID {TEST, ONE, TWO};
    TileLoader terrain;
    public Camera(Rect view, Entity center){
        this.view = view;
        this.center = center;
        terrain = new TileLoader("terrain.png");
    }

    public boolean onScreen(Entity e){
        return onScreen((int)e.getX(), (int)e.getY());
    }

    public boolean onScreen(int x, int y){
        view.setLocation(center.getLoc());
        return view.isWithin(x, y);
    }

    public void drawMap(LevelID levelID){
        Level layer1 = null;
        Level layer2 = null;
        switch(levelID){
            case TEST:
                layer1 = (Level)(new Asset("level1-1.d", true).read());
                layer2 = (Level)(new Asset("level1-2.d", true).read());
                break;
            case ONE:
                break;
            case TWO:
                break;
            default:
        }

        if (layer1 != null && layer2 != null);
        for (int x = (int)(((view.getPoint1().getX()) / 8) - 1); x <= ((view.getPoint1().getX()) / 8) + 1; x++)
        for (int y = (int)((view.getPoint1().getY()) / 8) - 1; y <= ((view.getPoint1().getY()) / 8) + 1; y++){
            TexInfo t = layer1.getTextureFromGrid(y, x);
            try {
                terrain.getTexture(t.getX1(), t.getY1(), t.getX2(), t.getY2());
            } catch (IOException ex) {}
        }
    }
}
