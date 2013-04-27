package org.bytefire.ld48;

import java.util.ArrayList;
import org.bytefire.ld48.util.Location;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Game{
    ArrayList<Entity> gameEntities;
    ArrayList<Entity> removeEntities;
    ArrayList<Integer> pressedKeys;
    ArrayList<Integer> releasedKeys;
    private Game(){
        gameEntities = new ArrayList<Entity>();
        removeEntities = new ArrayList<Entity>();
        pressedKeys = new ArrayList<Integer>();
        releasedKeys = new ArrayList<Integer>();
    }

    public static void main(String[] args){
        Game main = new Game();
        main.Game(args);
    }

    private void Game(String[] args){
        System.out.println("Initializing...");
        init();
        initEntities();

        for(float i = 0; !Display.isCloseRequested(); i++){
            long time = System.currentTimeMillis();
            System.out.println(i);
            long time2 = System.currentTimeMillis();

            keyboard();

            doPhysics(i);

            doDraw(i);

            if (time2-time < 50) try{
                Thread.sleep(50-(time2-time));
            } catch (InterruptedException e){}
        }
        Display.destroy();
    }

    private void init(){
        try{
        Display.setTitle("Cow");
        Display.setDisplayMode(new DisplayMode(640,480));
        Display.create();
        } catch (LWJGLException e) {}

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 640, 0, 480, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    private void initEntities(){
        gameEntities.add(new Player(new Location(this, 100D, 100D, 1D), true, true));
    }

    private void doPhysics(float i){
        for (Entity e: gameEntities){
            if (removeEntities.contains(e)) gameEntities.remove(e);
            else e.doPhysics(this);
        }
    }

    private void doDraw(float i){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glColor3f(1.0f,1.0f,1.0f);
        for (Entity e: gameEntities){
            e.drawEntity(this);
        }

        Display.update();
    }

    public enum KeyState {Pressed, Released, Unpressed};

    private void keyboard(){
        while (!releasedKeys.isEmpty()){
            pressedKeys.remove(releasedKeys.get(0));
            releasedKeys.remove(0);
        }
        while (Keyboard.next()){
            int key = Keyboard.getEventKey();
            if (Keyboard.getEventKeyState()){
                if (!pressedKeys.contains(key)) pressedKeys.add(key);
            }
            else {
                if (!releasedKeys.contains(key)) releasedKeys.add(key);
            }
        }
    }

    public KeyState getKey(int key){
        if (pressedKeys.contains(key)) return KeyState.Pressed;
        else if (releasedKeys.contains(key)) return KeyState.Released;
        else return KeyState.Unpressed;
    }

    protected void startGame(){

    }

    protected ArrayList<Entity> getEntities(){
        return gameEntities;
    }

    protected void addEntity(Entity entity){
        gameEntities.add(entity);
    }

    protected ArrayList<Entity> getRemoveEntities(){
        return gameEntities;
    }

    protected void removeEntity(Entity entity){
        removeEntities.add(entity);
    }
}
