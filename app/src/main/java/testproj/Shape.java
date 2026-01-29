package testproj;

import java.util.*;
import java.nio.ByteBuffer;

public class Shape {
    private static ArrayList<Shape> shapes = new ArrayList<Shape>();

    public ByteBuffer vertices;
    public ByteBuffer colors;
    public ByteBuffer indices;
    public int dimensions;
    public int vertexCount;

    public Shape (double[] vertices, double[] colors, int[] indices, int dimensions) {
        this.vertices = Helper.storeArrayInBuffer(vertices);
        this.colors = Helper.storeArrayInBuffer(colors);
        this.indices = Helper.storeArrayInBuffer(indices);
        this.dimensions = dimensions;
        vertexCount = vertices.length / dimensions;

        shapes.add(this);
    }

    public static void init () {
        new Shape(new double[]{
            0.5, 0.5,
            0.5, -0.5,
            -0.5, -0.5
        }, new double[]{
            1.0, 1.0, 1.0,
            1.0, 1.0, 1.0,
            1.0, 1.0, 1.0
        }, new int[]{0, 1, 2}, 2);
    }

    public static ArrayList<Shape> getShapes () {
        return shapes;
    }
}