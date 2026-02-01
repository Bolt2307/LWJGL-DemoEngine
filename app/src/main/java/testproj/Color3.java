package testproj;

public class Color3 {
    public static final Color3 RED = new Color3(1.0f, 0.0f, 0.0f);
    public static final Color3 ORANGE = new Color3(1.0f, 0.5f, 0.0f);
    public static final Color3 YELLOW = new Color3(1.0f, 1.0f, 0.0f);
    public static final Color3 GREEN = new Color3(0.0f, 1.0f, 0.0f);
    public static final Color3 CYAN = new Color3(0.0f, 1.0f, 1.0f);
    public static final Color3 BLUE = new Color3(0.0f, 0.0f, 1.0f);
    public static final Color3 PURPLE = new Color3(1.0f, 0.0f, 1.0f);

    public static final Color3 BLACK = new Color3(0.0f, 0.0f, 0.0f);
    public static final Color3 WHITE = new Color3(1.0f, 1.0f, 1.0f);

    public float r, g, b, a;

    public Color3 (float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 1.0f;
    }

    public Color3 (float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
}