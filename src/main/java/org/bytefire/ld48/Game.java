package org.bytefire.ld48;

import java.util.ArrayList;
import me.darkeh.projectd.assets.Asset;
import me.darkeh.projectd.assets.Level;
import org.bytefire.ld48.util.Camera;
import org.bytefire.ld48.util.Camera.LevelID;
import static org.bytefire.ld48.util.Camera.LevelID.ONE;
import static org.bytefire.ld48.util.Camera.LevelID.TEST;
import static org.bytefire.ld48.util.Camera.LevelID.TWO;
import org.bytefire.ld48.util.Location;
import org.bytefire.ld48.util.Rect;
import org.bytefire.ld48.util.Sprite;
import org.bytefire.ld48.util.TextureLoader;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Game{
    private ArrayList<Entity> gameEntities;
    private ArrayList<Entity> removeEntities;
    private ArrayList<Integer> pressedKeys;
    private ArrayList<Integer> releasedKeys;
    private TextureLoader tex;
    private final int height;
    private final int width;
    private final int viewHeight;
    private final int viewWidth;
    private LevelID currentLevel;
    private Camera cam;

    private Game(){
        gameEntities = new ArrayList<Entity>();
        removeEntities = new ArrayList<Entity>();
        pressedKeys = new ArrayList<Integer>();
        releasedKeys = new ArrayList<Integer>();
        tex = new TextureLoader();
        height = 480;
        width = 640;
        viewHeight = 120;
        viewWidth = 160;
        gameEntities.add(new Player(new Location(this, 80D, 80D, 1D), true, true));
        cam = new Camera(new Rect(this, 0, 0, viewHeight, viewWidth), gameEntities.get(0));
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
        Display.setDisplayMode(new DisplayMode(width,height));
        Display.create();
        } catch (LWJGLException e) {}

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();

        GL11.glOrtho(0, viewWidth, viewHeight, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glViewport(0, 0, width, height);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        currentLevel = LevelID.TEST;
    }

    public LevelID getCurrentLevel(){
        return currentLevel;
    }

    public Player getPlayer(){
        return (Player)gameEntities.get(0);
    }

    private void initEntities(){
        if(currentLevel==1){
            gameEntities.add(new FreeTermite(new Location(this, 160, 72, 0), true, true));
            gameEntities.add(new FreeTermite(new Location(this, 240, 40, 0), true, true));
            gameEntities.add(new FreeTermite(new Location(this, 200, 112, 0), true, true));
            gameEntities.add(new FreeTermite(new Location(this, 224, 96, 0), true, true));
            gameEntities.add(new FreeTermite(new Location(this, 144, 208, 0), true, true));
            gameEntities.add(new FreeTermite(new Location(this, 200, 216, 0), true, true));
            gameEntities.add(new FreeTermite(new Location(this, 200, 200, 0), true, true));
            gameEntities.add(new FreeTermite(new Location(this, 232, 192, 0), true, true));
            gameEntities.add(new FreeTermite(new Location(this, 88, 176, 0), true, true));
            gameEntities.add(new FreeBigAnt(new Location(this, 176, 240, 0), true, true));
        }
        else{
            gameEntities.add(new FreeAnt(new Location(this, 448, 32, 0), true, true));
            gameEntities.add(new FreeAnt(new Location(this, 416, 32, 0), true, true));
            gameEntities.add(new FreeAnt(new Location(this, 392, 24, 0), true, true));
            gameEntities.add(new FreeAnt(new Location(this, 360, 16, 0), true, true));
            gameEntities.add(new ScriptedBee1(new Location(this, 448, 88, 0), true, true, new Location(this, 448, 88, 0), new Location(this, 472, 88, 0)));
            gameEntities.add(new ScriptedBee1(new Location(this, 272, 184, 0), true, true, new Location(this, 272, 184, 0), new Location(this, 296, 184, 0)));
            gameEntities.add(new FreeAnt(new Location(this, 432, 168, 0), true, true));
            gameEntities.add(new FreeAnt(new Location(this, 480, 184, 0), true, true));
            gameEntities.add(new FreeAnt(new Location(this, 432, 232, 0), true, true));
            gameEntities.add(new FreeAnt(new Location(this, 424, 256, 0), true, true));
            gameEntities.add(new FreeAnt(new Location(this, 376, 240, 0), true, true));
            gameEntities.add(new FreeAnt(new Location(this, 336, 272, 0), true, true));
            gameEntities.add(new FreeBigAnt(new Location(this, 384, 176, 0), true, true));
            
            
        }
        
    }

    private void doPhysics(float i){
        for (Entity e: gameEntities){
            if (removeEntities.contains(e)) gameEntities.remove(e);
            else e.doPhysics(this);
        }
    }

    public Level getPhysicsLayer(){
        switch(currentLevel){
            case TEST:
            case ONE:
                return (Level)(new Asset("Level1-3.d", true).read());
            case TWO:
                return (Level)(new Asset("Level2-3.d", true).read());
            default:
        }
        return null;
    }

    private void doDraw(float i){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        cam.drawMap(currentLevel);
        for (Entity e: gameEntities){
            e.drawEntity(this);
        }
        getSprite("inventory.png").draw(59, 96);
        Display.update();
    }

    public Camera getView(){
        return cam;
    }

    public Sprite getSprite(String ref) {
        return new Sprite(tex, ref);
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
