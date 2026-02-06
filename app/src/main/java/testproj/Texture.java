package testproj;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class Texture {
    public static final String TEXTURE_PATH = "app/src/main/textures/";
    public static ArrayList<Texture> textureRef = new ArrayList<>();
    public static int currentTextureID = 0;

    public ByteBuffer buffer;
    public Vector2 dimensions;
    public int textureID;

    public Texture (String path) {
        this.loadTexture(path);
    }

    public Texture (int textureID, ByteBuffer buffer, Vector2 dimensions) {
        this.textureID = textureID;
        this.buffer = buffer;
        this.dimensions = dimensions;
    }

    public void getImageDim (String path) {
        try {
            BufferedImage image = ImageIO.read(new File(TEXTURE_PATH + path));

            if (image == null) {
                throw new Exception("Failed to load texture: " + path);
            }

            dimensions = new Vector2(image.getWidth(), image.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pushTexture () {
        if (textureID != currentTextureID) {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, (int)dimensions.x, (int)dimensions.y, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
            currentTextureID = textureID;
        }
    }

    public void loadTexture(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(TEXTURE_PATH + path));

            if (image == null) {
                throw new Exception("Failed to load texture: " + path);
            }

            dimensions = new Vector2(image.getWidth(), image.getHeight());

            int width  = (int) dimensions.x;
            int height = (int) dimensions.y;

            int[] pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);

            buffer = BufferUtils.createByteBuffer(width * height * 4);

            for (int y = height - 1; y >= 0; y--) {
                for (int x = 0; x < width; x++) {
                    int pixel = pixels[y * width + x];

                    buffer.put((byte)((pixel >> 16) & 0xFF));
                    buffer.put((byte)((pixel >> 8) & 0xFF));
                    buffer.put((byte)(pixel & 0xFF));
                    buffer.put((byte)((pixel >> 24) & 0xFF));
                }
            }

            buffer.flip();

            textureID = GL11.glGenTextures();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Texture copy () {
        return new Texture(this.textureID, this.buffer,this.dimensions);
    }
}