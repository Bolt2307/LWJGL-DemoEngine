package testproj;

public class Vector3 {
    public static final Vector3 ZERO = new Vector3(0, 0, 0);
    public static final Vector3 ONE = new Vector3(1, 1, 1);
    public static final Vector3 X_VECTOR = new Vector3(1, 0, 0);
    public static final Vector3 Y_VECTOR = new Vector3(0, 1, 0);
    public static final Vector3 Z_VECTOR = new Vector3(0, 0, 1);

    public float x, y, z = 0.0f;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float magnitude () {
        return (float)Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
    }

    public Vector3 normalize () {
        float mag = this.magnitude();
        
        if (mag == 0) {
            return Vector3.ZERO.copy();
        }

        return new Vector3(this.x / mag, this.y / mag, this.z / mag);
    }

    public Vector3 toRadians () {
        return new Vector3(
            (float)(this.x*Math.PI/180),
            (float)(this.y*Math.PI/180),
            (float)(this.z*Math.PI/180)
        );
    }

    public float sumInternal () {
        return this.x + this.y + this.z;
    }

    public Vector3 add (Vector3 other) {
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3 subtract (Vector3 other) {
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

    public Vector3 divide (Vector3 other) {
        return new Vector3(this.x / other.x, this.y / other.y, this.z / other.z);
    }

    public Vector3 divide (float other) {
        return new Vector3(this.x / other, this.y / other, this.z / other);
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

    public Vector3 cross (Vector3 other) {
        return new Vector3(
            this.y * other.z - this.z * other.y,
            this.z * other.x - this.x * other.z,
            this.x * other.y - this.y * other.x
        );
    }
    
    public float dot (Vector3 other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector3 rotateAxis (Vector3 axis, float angle) {
        axis = axis.normalize();

        float cos = (float)Math.cos(angle);
        float sin = (float)Math.sin(angle);

        return this.multiply(cos).add(axis.cross(this).multiply(sin)).add(axis.multiply(axis.dot(this) * (1 - cos)));
    }

    public void lerp (Vector3 target, float t) {
        this.x += (target.x - this.x) * t;
        this.y += (target.y - this.y) * t;
        this.z += (target.z - this.z) * t;
    }

    public Vector3 projectToCamera (Camera camera) {
        float projectedX = ((-this.x / (this.z/camera.fov)) * camera.fov) / camera.window.getDimensions().x;
        float projectedY = ((this.y / (this.z/camera.fov)) * camera.fov) / camera.window.getDimensions().y;

        //float projectedX = (-this.x / ((this.z/camera.fov)*(this.z)) * camera.fov) / camera.window.getDimensions().x;
        //float projectedY = (this.y / ((this.z/camera.fov)*(this.z)) * camera.fov) / camera.window.getDimensions().y;

        //float projectedX = ((-this.x * (this.z/camera.fov)) * camera.fov) / camera.window.getDimensions().x;
        //float projectedY = ((this.y * (this.z/camera.fov)) * camera.fov) / camera.window.getDimensions().y;

        //float projectedX = (-this.x*50.0f / camera.window.getDimensions().x);
        //float projectedY = (this.y*50.0f / camera.window.getDimensions().y);

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
