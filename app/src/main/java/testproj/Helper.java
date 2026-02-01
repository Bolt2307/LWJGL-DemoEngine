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

    public static boolean overlap (float x1Min, float x1Max, float x2Min, float x2Max) {
        return x1Min <= x2Max && x2Min <= x1Max;
    }

    public static boolean overlap (Vector3 aMin, Vector3 aMax, Vector3 bMin, Vector3 bMax) {
        return overlap(aMin.x, aMax.x, bMin.x, bMax.x) || overlap(aMin.y, aMax.y, bMin.y, bMax.y) || overlap(aMin.z, aMax.z, bMin.z, bMax.z);
    }
}