package me.darkeh.jdbox2dtest;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class App{
    int cPressed = 0;
    int oPressed = 0;
    int wPressed = 0;
    private App(){
    }
    public static void main( String[] args ){
        App app = new App();
        app.init(args);
    }
    
    private void init(String[] args){
        
        try {
            Display.setTitle("Cow");
            Display.setDisplayMode(new DisplayMode(1440,900));
            Display.setResizable(true);
            Display.create();
        } catch (LWJGLException ex) {}
        
        GL11.glMatrixMode(GL11.GL_PROJECTION);
	GL11.glLoadIdentity();
	GL11.glOrtho(0, 800, 0, 600, 1, -1);
	GL11.glMatrixMode(GL11.GL_MODELVIEW);
        
        while (!Display.isCloseRequested()){
            pollInput();
            Display.update();
        }
        
    }
    
    public void pollInput(){
        if (Mouse.isButtonDown(0)) {
            
            int x = Mouse.getX();
            int y = Mouse.getY();
                    GL11.glColor3f(1.0f,1.0f,1.0f);
                    
                    GL11.glBegin(GL11.GL_QUADS);
                        GL11.glVertex2f(x,y);
                        GL11.glVertex2f(x+100,y);
                        GL11.glVertex2f(x+100,y+100);
                        GL11.glVertex2f(x,y+100);
                    GL11.glEnd();
                    Display.update();
        }
        
        while(Keyboard.next()){
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_C) {
                        cPressed = 1;
                        GL11.glColor3f(1.0f,1.0f,1.0f);
                        GL11.glBegin(GL11.GL_QUADS);
                        GL11.glVertex2f(130,430);
                        GL11.glVertex2f(130+100,430);
                        GL11.glVertex2f(130+100,430+100);
                        GL11.glVertex2f(130,430+100);
                        GL11.glEnd();
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_O && cPressed == 1) {
                        oPressed += 1;
                        GL11.glColor3f(1.0f,1.0f,1.0f);
                        GL11.glBegin(GL11.GL_QUADS);
                        GL11.glVertex2f(360,320);
                        GL11.glVertex2f(360+100,320);
                        GL11.glVertex2f(360+100,320+100);
                        GL11.glVertex2f(360,320+100);
                        GL11.glEnd();
                }
                
                if (Keyboard.getEventKey() == Keyboard.KEY_W && oPressed == 1) {
                        GL11.glColor3f(1.0f,1.0f,1.0f);
                        GL11.glBegin(GL11.GL_QUADS);
                        GL11.glVertex2f(520,140);
                        GL11.glVertex2f(520+100,140);
                        GL11.glVertex2f(520+100,140+100);
                        GL11.glVertex2f(520,140+100);
                        GL11.glEnd();
                }
                
                if(Keyboard.getEventKey() == Keyboard.KEY_D){
                    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
                    cPressed = 0;
                    oPressed = 0;
                }
                
            }
        }
    }
}
