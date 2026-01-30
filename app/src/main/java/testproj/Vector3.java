package testproj;

public class Vector3 {
    public static final Vector3 ZERO = new Vector3(0, 0, 0);
    public static final Vector3 ONE = new Vector3(1, 1, 1);
    public static final Vector3 X_VECTOR = new Vector3(1, 0, 0);
    public static final Vector3 Y_VECTOR = new Vector3(0, 1, 0);
    public static final Vector3 Z_VECTOR = new Vector3(0, 0, 1);

    public float x, y, z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 toRadians () {
        return new Vector3(
            (float)(this.x*Math.PI/180),
            (float)(this.y*Math.PI/180),
            (float)(this.z*Math.PI/180)
        );
    }

    public Vector3 add (Vector3 other) {
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3 substract (Vector3 other) {
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector3 negate () {
        return new Vector3(-this.x, -this.y, -this.z);
    }
    
    public Vector3 multiply (Vector3 other) {
        return new Vector3(this.x * other.x, this.y * other.y, this.z * other.z);
    }

    public Vector3 multiply (float other) {
        return new Vector3(this.x * other, this.y * other, this.z * other);
    }

    public Vector3 rotate (Vector3 rotation) {
        Vector3 copy = this.copy();

        Vector2 yz = new Vector2(copy.y, copy.z).rotate(rotation.x);
        copy.y = yz.x;
        copy.z = yz.y;

        Vector2 xz = new Vector2(copy.x, copy.z).rotate(rotation.y);
        copy.x = xz.x;
        copy.z = xz.y;

        Vector2 xy = new Vector2(copy.x, copy.y).rotate(rotation.z);
        copy.x = xy.x;
        copy.y = xy.y;

        return copy;
    }

    public Vector3 projectToCamera (Camera camera) {
        float projectedX = ((this.x / (this.z/15)) * camera.focalLength) / camera.dimensions.x;
        float projectedY = ((this.y / (this.z/15)) * camera.focalLength) / camera.dimensions.y;

        return new Vector3(projectedX, projectedY, this.z);
    }

    public Vector3 copy () {
        return new Vector3(this.x, this.y, this.z);
    }

    @Override
    public String toString () {
        return "Vector3: (" + this.x + ", " + this.y + ", " + this.z + ")";
    }
}
