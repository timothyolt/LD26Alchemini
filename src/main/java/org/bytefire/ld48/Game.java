package org.bytefire.ld48;

import java.util.ArrayList;
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
        init();

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 0, 600, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        while(!Display.isCloseRequested()){
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // set the color of the quad (R,G,B,A)
            GL11.glColor3f(1.0f,1.0f,1.0f);
            // draw quad
            GL11.glBegin(GL11.GL_QUADS);
                GL11.glVertex2f(100,100);
                GL11.glVertex2f(100+100,100);
                GL11.glVertex2f(100+100,100+100);
                GL11.glVertex2f(100,100+100);
            GL11.glEnd();
            GL11.glBegin(GL11.GL_QUADS);
                GL11.glVertex2f(150,150);
                GL11.glVertex2f(150+100,150);
                GL11.glVertex2f(150+100,150+100);
                GL11.glVertex2f(150,150+100);
            GL11.glEnd();
            GL11.glBegin(GL11.GL_QUADS);
                GL11.glVertex2f(250,90);
                GL11.glVertex2f(250+100,90);
                GL11.glVertex2f(250+100,90+100);
                GL11.glVertex2f(250,90+100);
            GL11.glEnd();
            Display.update();
        }
        Display.destroy();
    }

    private void init(){
        try{
        Display.setTitle("Cow");
        Display.setDisplayMode(new DisplayMode(640,480));
        Display.create();
        } catch (LWJGLException e) {}
    }

    public enum KeyState {Pressed, Released, Unpressed};

    private void keyboard(){
        while (!releasedKeys.isEmpty()){
            pressedKeys.remove(releasedKeys.get(0));
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