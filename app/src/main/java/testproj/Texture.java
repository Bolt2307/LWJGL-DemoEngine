package testproj;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class Texture {
    public static final String TEXTURE_PATH = "app/src/main/textures/";
    public static ArrayList<Texture> textureRef = new ArrayList<>();

    public Vector2 dimensions;
    public int textureID;

    public Texture (String path) {
        this.loadTexture(path);
    }

    public Texture (int textureID, Vector2 dimensions) {
        this.textureID = textureID;
        this.dimensions = dimensions;
    }

    public Texture (int textureID, String path) {
        this.textureID = textureID;
        this.getImageDim(path);
    }

    public void getImageDim (String path) {
        try {
            BufferedImage image = ImageIO.read(new File(TEXTURE_PATH + path));
            dimensions = new Vector2(image.getWidth(), image.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTexture(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(TEXTURE_PATH + path));
            dimensions = new Vector2(image.getWidth(), image.getHeight());

            int width  = (int) dimensions.x;
            int height = (int) dimensions.y;

            int[] pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);

            ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4);

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
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

            System.out.println("Texture valid: " + GL11.glIsTexture(textureID));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Texture copy () {
        return new Texture(this.textureID, this.dimensions);
    }
}