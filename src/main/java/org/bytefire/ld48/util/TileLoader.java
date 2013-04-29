package org.bytefire.ld48.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.ImageIcon;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;

public class TileLoader {
    private HashMap<int[], Texture> table;
    private ColorModel glAlphaColorModel;
    private ColorModel glColorModel;
    private IntBuffer textureIDBuffer = BufferUtils.createIntBuffer(1);
    private String ref;

    public TileLoader(String ref) {
        this.table = new HashMap<int[], Texture>();
        this.ref = ref;
        glAlphaColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
            new int[] {8,8,8,8},
            true,
            false,
            ComponentColorModel.TRANSLUCENT,
            DataBuffer.TYPE_BYTE);

        glColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
            new int[] {8,8,8,0},
            false,
            false,
            ComponentColorModel.OPAQUE,
            DataBuffer.TYPE_BYTE);
    }

    private int createTextureID() {
      glGenTextures(textureIDBuffer);
      return textureIDBuffer.get(0);
    }

    public Texture getTexture(int x, int y, int xx, int yy) throws IOException {
        int[] array = {x, y, xx, yy};
        Texture tex = table.get(array);
        if (tex != null) {
            return tex;
        }
        tex = getTexture(ref, x, y, xx, yy,
            GL_TEXTURE_2D, // target
            GL_RGBA,     // dst pixel format
            GL_NEAREST, // min filter (unused)
            GL_NEAREST);
        table.put(array,tex);
        return tex;
    }

    public Texture getTexture(String resourceName, int x, int y, int xx, int yy,
        int target,
        int dstPixelFormat,
        int minFilter,
        int magFilter) throws IOException {

        int srcPixelFormat;
        int textureID = createTextureID();
        Texture texture = new Texture(target,textureID);

        glBindTexture(target, textureID);
        BufferedImage bufferedImage = loadImage(resourceName);

        BufferedImage writable = new BufferedImage(xx - x, yy - y, bufferedImage.getType());

        Graphics2D gr = writable.createGraphics();
        gr.drawImage(bufferedImage, 0, 0, xx - x, yy - y, x, y, xx, yy, null);
        gr.dispose();

        bufferedImage = writable;

        texture.setWidth(bufferedImage.getWidth());
        texture.setHeight(bufferedImage.getHeight());

        if (bufferedImage.getColorModel().hasAlpha()) {
            srcPixelFormat = GL_RGBA;
        } else {
            srcPixelFormat = GL_RGB;
        }

        ByteBuffer textureBuffer = convertImageData(bufferedImage,texture);

        if (target == GL_TEXTURE_2D) {
            glTexParameteri(target, GL_TEXTURE_MIN_FILTER, minFilter);
            glTexParameteri(target, GL_TEXTURE_MAG_FILTER, magFilter);
        }

        glTexImage2D(target,
            0,
            dstPixelFormat,
            get2Fold(bufferedImage.getWidth()),
            get2Fold(bufferedImage.getHeight()),
            0,
            srcPixelFormat,
            GL_UNSIGNED_BYTE,
            textureBuffer );

        return texture;
    }

    private static int get2Fold(int fold) {
        int ret = 2;
        while (ret < fold) {
            ret *= 2;
        }
        return ret;
    }

    private ByteBuffer convertImageData(BufferedImage bufferedImage,Texture texture) {
        ByteBuffer imageBuffer;
        WritableRaster raster;
        BufferedImage texImage;

        int texWidth = 2;
        int texHeight = 2;

        while (texWidth < bufferedImage.getWidth()) {
            texWidth *= 2;
        }
        while (texHeight < bufferedImage.getHeight()) {
            texHeight *= 2;
        }

        texture.setTextureHeight(texHeight);
        texture.setTextureWidth(texWidth);

        if (bufferedImage.getColorModel().hasAlpha()) {
            raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,texWidth,texHeight,4,null);
            texImage = new BufferedImage(glAlphaColorModel,raster,false,new Hashtable());
        } else {
            raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,texWidth,texHeight,3,null);
            texImage = new BufferedImage(glColorModel,raster,false,new Hashtable());
        }

        Graphics g = texImage.getGraphics();
        g.setColor(new Color(0f,0f,0f,0f));
        g.fillRect(0,0,texWidth,texHeight);
        g.drawImage(bufferedImage,0,0,null);

        byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer()).getData();

        imageBuffer = ByteBuffer.allocateDirect(data.length);
        imageBuffer.order(ByteOrder.nativeOrder());
        imageBuffer.put(data, 0, data.length);
        imageBuffer.flip();

        return imageBuffer;
    }

    private BufferedImage loadImage(String ref) throws IOException {
        URL url = TileLoader.class.getClassLoader().getResource(ref);

        if (url == null) {
            throw new IOException("Cannot find: " + ref);
        }

        Image img = new ImageIcon(url).getImage();
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();

        return bufferedImage;
    }
}