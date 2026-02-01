package testproj;

public class Camera {
    public Vector3 position;
    public float fov;
    public Window window;

    public Vector3 rotation;

    public Vector3 forward = new Vector3(0.0f, 0.0f, 1.0f);
    public Vector3 right = new Vector3(-1.0f, 0.0f, 0.0f);
    public Vector3 up = new Vector3(0.0f, 1.0f, 0.0f);

    public float pitch = 0.0f;
    public float yaw = 0.0f;
    public float roll = 0.0f;

    public Camera (Vector3 position, float fov, Window window) {
        this.position = position;
        this.fov = fov;
        this.window = window;

        this.forward = new Vector3(0.0f, 0.0f, 1.0f);
        this.right = new Vector3(1.0f, 0.0f, 0.0f);
        this.up = new Vector3(0.0f, 1.0f, 0.0f);
    }

    @Override
    public String toString() {
        return "Camera{position" + position + "}";
    }
}
