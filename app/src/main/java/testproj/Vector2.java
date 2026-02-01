package testproj;

public class Vector2 {
    public static final Vector2 ZERO = new Vector2(0.0f, 0.0f);
    public static final Vector2 ONE = new Vector2(1.0f, 1.0f);
    public static final Vector2 VECTOR_X = new Vector2(1.0f, 0.0f);
    public static final Vector2 VECTOR_Y = new Vector2(0.0f, 1.0f);

    public float x, y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 rotate (float angle) {
        return new Vector2((float)(x * Math.cos(angle) - y * Math.sin(angle)), (float)(x * Math.sin(angle) + y * Math.cos(angle)));
    }

    public Vector2 copy () {
        return new Vector2(x, y);
    }

    public Vector2 negate () {
        return new Vector2(-x, -y);
    }

    public Vector2 add (Vector2 other) {
        return new Vector2(x + other.x, y + other.y);
    }

    public Vector2 subtract (Vector2 other) {
        return new Vector2(x - other.x, y - other.y);
    }

    public Vector2 multiply (Vector2 other) {
        return new Vector2(x * other.x, y * other.y);
    }

    public Vector2 multiply (float scalar) {
        return new Vector2(x * scalar, y * scalar);
    }

    public Vector2 lerp (Vector2 target, float t) {
        return new Vector2(
            x + (target.x - x) * t,
            y + (target.y - y) * t
        );
    }
}
