package testproj;

public class Camera {
    public Vector3 position;
    public Vector3 rotation;
    public float fov;
    public Vector2 dimensions;

    public Camera (Vector3 position, Vector3 rotation, float fov, Window window) {
        this.position = position;
        this.rotation = rotation;
        this.fov = fov;
        this.dimensions = window.getDimensions();
    }
}
