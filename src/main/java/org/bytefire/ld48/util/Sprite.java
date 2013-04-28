/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bytefire.ld48.util;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class Sprite {
    private Texture texture;
    private int width;
    private int height;
    public Sprite(TextureLoader loader, String ref) {
        try {
            texture = loader.getTexture(ref);
            width = texture.getImageWidth();
            height = texture.getImageHeight();
        } catch (IOException ioe) {
            ioe.printStackTrace();
          System.exit(-1);
        }
    }

    public int getWidth() {
        return texture.getImageWidth();
    }

    public int getHeight() {
            return texture.getImageHeight();
    }

    public void draw(int x, int y) {
            glPushMatrix();
            texture.bind();
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTranslatef(x, y, 0);
            glBegin(GL_QUADS);
                glTexCoord2f(0, 0);
                glVertex2f(0, 0);

                glTexCoord2f(0, texture.getHeight());
                glVertex2f(0, height);

                glTexCoord2f(texture.getWidth(), texture.getHeight());
                glVertex2f(width, height);

                glTexCoord2f(texture.getWidth(), 0);
                glVertex2f(width, 0);
            glEnd();
            glPopMatrix();
    }
}
