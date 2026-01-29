package testproj;

import java.util.*;

public class Face {
    private static ArrayList<Face> shapes = new ArrayList<Face>();

    public Vector3[] vertices;
    public Color3 color;
    public float zOrder;

    public Face (Vector3[] vertices, Color3 color) {
        this.vertices = vertices;
        this.color = color;

        for (Vector3 vertex : vertices) {
            this.zOrder += vertex.z;
        }

        zOrder /= vertices.length;

        shapes.add(this);
    }

    public static void init () {
        new Face(new Vector3[]{
            new Vector3(100, 100, 2),
            new Vector3(100, -100, 2),
            new Vector3(-100, -100, 2)
        }, new Color3(1.0f, 0.0f, 0.0f));
    }

    public static ArrayList<Face> getShapes () {
        return shapes;
    }
}