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

    public Vector3 add (Vector3 other) {
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3 substract (Vector3 other) {
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }
    
    public Vector3 multiply (Vector3 other) {
        return new Vector3(this.x * other.x, this.y * other.y, this.z * other.z);
    }

    public Vector3 rotate (Vector3 rotation) {
        float x = this.x;
        float y = this.y * (float)Math.cos(rotation.x) - this.z * (float)Math.sin(rotation.x);
        float z = this.y * (float)Math.sin(rotation.x) + this.z * (float)Math.cos(rotation.x);

        x = x * (float)Math.cos(rotation.y) + z * (float)Math.sin(rotation.y);
        z = -x * (float)Math.sin(rotation.y) + z * (float)Math.cos(rotation.y);

        x = x * (float)Math.cos(rotation.z) - y * (float)Math.sin(rotation.z);
        y = x * (float)Math.sin(rotation.z) + y * (float)Math.cos(rotation.z);

        return new Vector3(x, y, z);
    }

    public Vector3 projectToCamera (Camera camera) {
        float projectedX = ((this.x / (this.z/15)) * camera.fov) / camera.dimensions.x;
        float projectedY = ((this.y / (this.z/15)) * camera.fov) / camera.dimensions.y;

        return new Vector3(projectedX, projectedY, this.z);
    }

    public Vector3 copy () {
        return new Vector3(this.x, this.y, this.z);
    }

    public String toString () {
        return "Vector3: (" + this.x + ", " + this.y + ", " + this.z + ")";
    }
}
