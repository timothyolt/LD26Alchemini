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
        ((Level)new Asset("Level1-1.d", true).read()).printIds();
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
                layer1 = (Level)(new Asset("Level1-1.d", true).read());
                layer2 = (Level)(new Asset("Level1-2.d", true).read());
                break;
            case ONE:
                break;
            case TWO:
                break;
            default:
        }

//        System.out.println("Vx:   " + Integer.toString((int)view.getPoint1().getX()));
//        System.out.println("Vy:   " + Integer.toString((int)view.getPoint1().getY()));
//        System.out.println("Vxx:   " + Integer.toString((int)view.getPoint2().getX()));
//        System.out.println("Vyy:   " + Integer.toString((int)view.getPoint2().getY()));

        int xStart = (int)view.getPoint1().getX() / 8;
        int yStart = (int)view.getPoint1().getY() / 8;

        Location dummy = new Location(center.getGame(),
                (center.getX() * -1) + (15 * 8),
                (center.getY() * -1) + (20 * 8), 0);
        System.out.println("Px:   " + Double.toString(center.getX()/8));
        System.out.println("Py:   " + Double.toString(center.getY()/8));
        System.out.println("Dx:   " + Double.toString(dummy.getX()/8));
        System.out.println("Dy:   " + Double.toString(dummy.getY()/8));

        if (layer1 != null && layer2 != null)
        for (int x = 0; x < 64; x++)
        for (int y = 0; y < 64; y++){
//            System.out.println("---------");
//            System.out.println(xStart);
//            System.out.println(yStart);
//            System.out.println(((view.getPoint2().getX() / 8)) + 1);
//            System.out.println(((view.getPoint2().getY() / 8)) + 1);
//            System.out.println(x);
//            System.out.println(y);
//            System.out.println(view.getPoint1().getX() + ((x - xStart)* 8));
//            System.out.println(view.getPoint1().getY() + ((y - yStart)* 8));
            if (Math.floor(dummy.distance(x * 4, y * 4)) < 80){
                TexInfo t;
                t = layer1.getTextureFromGrid(Math.abs(x), Math.abs(y));
                Tile tile = new Tile(terrain, t);
                tile.draw((int)view.getPoint1().getX() + (x * 8), (int)view.getPoint1().getY() + (y * 8));
            }
        }
    }
}
