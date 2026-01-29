package testproj;

public class Vector3 {
    public float x, y, z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector2 projectToCamera (Camera camera) {
        float relativeX = x - camera.position.x;
        float relativeY = y - camera.position.y;
        float relativeZ = z - camera.position.z;

        float projectedX = ((relativeX / relativeZ) * camera.fov) / camera.dimensions.x;
        float projectedY = ((relativeY / relativeZ) * camera.fov) / camera.dimensions.y;

        return new Vector2(projectedX, projectedY);
    }
}
