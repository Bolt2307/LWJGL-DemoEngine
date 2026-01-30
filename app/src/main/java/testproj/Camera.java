package testproj;

public class Camera {
    public Vector3 position;
    public Vector3 rotation;
    public float focalLength;
    public Vector2 dimensions;

    public Camera (Vector3 position, Vector3 rotation, float focalLength, Window window) {
        this.position = position;
        this.rotation = rotation;
        this.focalLength = focalLength;
        this.dimensions = window.getDimensions();
    }
}
