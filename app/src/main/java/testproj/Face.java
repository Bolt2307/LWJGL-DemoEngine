package testproj;

public class Face {
    public Vector3[] vertices;
    public Color3 color;
    public float zOrder;

    public Face (Vector3[] vertices, Color3 color) {
        this.vertices = vertices;
        this.color = color;
    }

    public float getZOrderValue () {
        float zAvg = 0.0f;

        for (Vector3 vertex : vertices) {
            zAvg += vertex.z;
        }

        zOrder = zAvg / vertices.length;
        return zOrder;
    }
}