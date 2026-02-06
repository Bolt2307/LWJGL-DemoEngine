package testproj;

public class Face {
    public Vector3[] vertices;
    public Color3 color;
    public Texture texture;
    public boolean textured;

    public Face (Vector3[] vertices, Color3 color) {
        this.vertices = vertices;
        this.color = color;
        this.textured = false;
    }

    public Face (Vector3[] vertices, Texture texture) {
        this.vertices = vertices;
        this.texture = texture;
        this.color = new Color3(1.0f, 1.0f, 1.0f, 1.0f);
        this.textured = true;
    }

    public Face (Vector3[] vertices, Color3 color, Texture texture, boolean textured) {
        this.vertices = vertices;
        this.texture = texture;
        this.color = color;
        this.textured = textured;
    }

    public Face copy () {
        Vector3[] newVertices = new Vector3[this.vertices.length];
        for (int i = 0; i < this.vertices.length; i++) {
            newVertices[i] = new Vector3(this.vertices[i].x, this.vertices[i].y, this.vertices[i].z);
        }

        return new Face(newVertices, new Color3(this.color.r, this.color.g, this.color.b, this.color.a), this.texture, this.textured);
    }
}
