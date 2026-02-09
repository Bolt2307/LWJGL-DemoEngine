package testproj;

public class Helper {
    public static float shoelace (Vector3[] vertices) {
        float area = 0.0f;
        int n = vertices.length;

        for (int i = 0; i < n; i++) {
            Vector3 current = vertices[i];
            Vector3 next = vertices[(i + 1) % n];
            area += current.x * next.y;
            area -= next.x * current.y;
        }

        return area / 2.0f;
    }

    public static int boolToInt (boolean value) {
        return value ? 1 : -1;
    }

    private static Vector3 lerpToNear(Camera camera, Vector3 a, Vector3 b) {
    float t = (camera.NEAR_PLANE - a.z) / (b.z - a.z);
    return new Vector3(
        a.x + (b.x - a.x) * t,
        a.y + (b.y - a.y) * t,
        camera.NEAR_PLANE
    );
}

}