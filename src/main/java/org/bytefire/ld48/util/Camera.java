package org.bytefire.ld48.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.bytefire.ld48.Entity;

public class Camera{
    private Rect view;
    private Entity center;
//    TMXMapReader tmx;
//    OrthogonalRenderer rTest;
//    Map test;
    public enum Level {TEST, ONE, TWO};
    public Camera(Rect view, Entity center){
        this.view = view;
        this.center = center;
//        InputStream mapstream = null;
//        try {
//            mapstream = new FileInputStream("test.tmx");
//        } catch (FileNotFoundException e) {
//            System.out.println("error reading map");
//            e.printStackTrace();
//        }
//        tmx = new TMXMapReader();
//        try{
//            System.out.println(mapstream.read());
//        } catch (IOException e) {
//            System.out.println("error reading map");
//            e.printStackTrace();
//        }
//        try {
//            test = tmx.readMap(mapstream);
//System.out.println("readMap");
//            rTest = new OrthogonalRenderer(test);
//        } catch(Exception e) {e.printStackTrace(System.out);}
//        if (test == null) System.out.println("error 11");
    }

    public boolean onScreen(Entity e){
        return onScreen((int)e.getX(), (int)e.getY());
    }

    public boolean onScreen(int x, int y){
        view.setLocation(center.getLoc());
        return view.isWithin(x, y);
    }

    public void drawMap(Level level){
//        Map map = null;
//        OrthogonalRenderer render = null;
//        switch(level){
//            case TEST:
//                map = test;
//                render = rTest;
//                break;
//            case ONE:
//                break;
//            case TWO:
//                break;
//            default:
        //} //if (map != null && render != null)

//        if (test == null){
//            System.out.println("error 22");
//            return;
//        }
//        for (MapLayer l: test.getLayers()){
//            TileLayer layer = (TileLayer)l;
//System.out.println("drawMap");
//            rTest.paintTileLayer(view, layer);
//        }
    }
}
