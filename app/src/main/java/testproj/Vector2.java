package testproj;

public class Vector2 {
    public float x, y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 rotate (float angle) {
        return new Vector2((float)(x * Math.cos(angle) - y * Math.sin(angle)), (float)(x * Math.sin(angle) + y * Math.cos(angle)));
    }
}
