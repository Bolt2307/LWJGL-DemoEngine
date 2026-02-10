package testproj;

import java.util.ArrayList;
import java.util.Arrays;

public class Camera {
    public final float NEAR_PLANE = 0.001f;

    public Vector3 position;
    public float fov;
    public float renderDistance;
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
        this.renderDistance = 2000.0f;
        this.window = window;

        this.forward = new Vector3(0.0f, 0.0f, 1.0f);
        this.right = new Vector3(1.0f, 0.0f, 0.0f);
        this.up = new Vector3(0.0f, 1.0f, 0.0f);
    }

    public Vector3[] zClip (Vector3[] vertices) {
        int countBehind = 0;
        ArrayList<Vector3> clipped = new ArrayList<>(Arrays.asList(vertices));

        for (int i = 0; i < clipped.size(); i++) {
            if (clipped.get(i).z < NEAR_PLANE) {
                countBehind++;

                if (countBehind == vertices.length) {
                    return new Vector3[] {};
                }

                Vector3 current = clipped.get(i);
                Vector3 last = clipped.get((i - 1 + clipped.size()) % clipped.size());
                Vector3 next = clipped.get((i + 1) % clipped.size());

                Vector3 v1 = current.subtract(last);
                Vector3 v2 = next.subtract(current);

                float d1 = (NEAR_PLANE - last.z) / (v1.z);
                float d2 = (NEAR_PLANE - current.z) / (v2.z);

                clipped.set(i, last.add(v1.multiply(d1)));
                clipped.add(i+1, current.add(v2.multiply(d2)));
            }
        }

        return clipped.toArray(new Vector3[0]);
    }

    @Override
    public String toString() {
        return "Camera{position" + position + "}";
    }
}
