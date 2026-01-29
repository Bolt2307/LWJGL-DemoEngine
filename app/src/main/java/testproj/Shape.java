package testproj;

import java.util.*;

public class Shape {
    private static ArrayList<Shape> shapes = new ArrayList<Shape>();

    public Vector3[] vertices;
    public Color3 color;

    public Shape (Vector3[] vertices, Color3 color) {
        this.vertices = vertices;
        this.color = color;

        shapes.add(this);
    }

    public static void init () {
        new Shape(new Vector3[]{
            new Vector3(100, 100, 2),
            new Vector3(100, -100, 2),
            new Vector3(-100, -100, 2)
        }, new Color3(1.0f, 0.0f, 0.0f));
    }

    public static ArrayList<Shape> getShapes () {
        return shapes;
    }
}