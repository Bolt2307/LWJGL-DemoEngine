package testproj;

public class Face {
    public Vector3[] vertices;
    public Color3 color;

    public float zMin;
    public float zMax;

    public Face(Vector3[] vertices, Color3 color) {
        this.vertices = vertices;
        this.color = color;
        computeZBounds();
    }

    public Face copy () {
        Vector3[] newVertices = new Vector3[this.vertices.length];
        for (int i = 0; i < this.vertices.length; i++) {
            newVertices[i] = new Vector3(this.vertices[i].x, this.vertices[i].y, this.vertices[i].z);
        }

        return new Face(newVertices, new Color3(this.color.r, this.color.g, this.color.b, this.color.a));
    }

    public void computeZBounds() {
        zMin = Float.POSITIVE_INFINITY;
        zMax = Float.NEGATIVE_INFINITY;

        for (Vector3 v : vertices) {
            if (v.z < zMin) zMin = v.z;
            if (v.z > zMax) zMax = v.z;
        }
    }
}
