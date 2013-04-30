package org.bytefire.ld48.util;

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
        ((Level)new Asset("Level1-2.d", true).read()).printIds();
    }

    public boolean onScreen(Entity e){
        return onScreen((int)e.getX(), (int)e.getY());
    }

    public boolean onScreen(int x, int y){
        view.setLocation(center.getLoc());
        return view.isWithin(x, y);
    }

    public Rect getBounds(){
        return view;
    }

    public void drawMap(LevelID levelID){
        int w = view.getWidth();
        int h = view.getHeight();
        view.setPoint1(new Location(center.getGame(), center.getX() - 76, center.getY() - 56, 0));
        view.setWidth(w);
        view.setHeight(h);
        Level layer1 = null;
        Level layer2 = null;
        switch(levelID){
            case TEST:
            case ONE:
                layer1 = (Level)(new Asset("Level1-1.d", true).read());
                layer2 = (Level)(new Asset("Level1-2.d", true).read());
                break;
            case TWO:
                layer1 = (Level)(new Asset("Level2-1.d", true).read());
                layer2 = (Level)(new Asset("Level2-2.d", true).read());
                break;
            default:
        }

        Location dummy = new Location(center.getGame(),
                (center.getX() * -1/2) + 75,
                (center.getY() * -1/2) + 60, 0);

        if (layer1 != null && layer2 != null)
        for (int x = 0; x < 64; x++)
        for (int y = 0; y < 64; y++){
            if (Math.floor(dummy.distance(x * 4, y * 4)) < 55){
                TexInfo t1 = layer1.getTextureFromGrid(Math.abs(x), Math.abs(y));
                TexInfo t2 = layer2.getTextureFromGrid(Math.abs(x), Math.abs(y));

                if (t1 != null && t1.getID() != 0){
                    Tile tile1 = new Tile(terrain, t1);
                    tile1.draw((int)view.getPoint1().getX() + (x * 8), (int)view.getPoint1().getY() + (y * 8));
                }

                if (t2 != null && t2.getID() != 0){
                    Tile tile2 = new Tile(terrain, t2);
                    tile2.draw((int)view.getPoint1().getX() + (x * 8), (int)view.getPoint1().getY() + (y * 8));
                }
            }
        }
    }
}
