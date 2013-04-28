package me.darkeh.projectd.assets;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Asset {
    public enum Container {TEXTURE, LEVEL, CUSTOM};

    private String name;
    private boolean resource;

    public Asset(Container asset){
        switch(asset){
            case TEXTURE: name = "textures.d";
                break;
            case LEVEL: name = "levels.d";
                break;
            case CUSTOM: name = "others.d";
                break;
        }
        this.resource = true;
    }

    public Asset(String name, boolean resource){
        this.name = name;
        this.resource = resource;
    }

    public void write(Object object){
        if (resource) return;
        ObjectOutputStream objectStream = null;
        try {
            objectStream = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(name)));
            objectStream.writeObject(object);
        } catch (IOException e) {}
        finally {
            try {
                if(objectStream != null) objectStream.close();
            } catch (IOException e) {}
        }
    }

    public Object read(){
        Object object = null;
        ObjectInputStream objectStream = null;
        try {
            if (resource) objectStream = new ObjectInputStream(new GZIPInputStream(ClassLoader.getSystemResourceAsStream("me/darkeh/projectd/resources/levels.d")));
            else objectStream = new ObjectInputStream(new GZIPInputStream(new FileInputStream(name)));
            try {object = objectStream.readObject();} catch(ClassNotFoundException e) {}
        } catch (IOException e) {}
        finally {
            try {
                if(objectStream != null) objectStream.close();
            } catch (IOException e) {}
        }
        return object;
    }
}
