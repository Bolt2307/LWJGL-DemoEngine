package primitives;

import testproj.Color3;
import testproj.Face;
import testproj.Vector3;

public class Cube extends testproj.Object {
    public Cube (String name, Vector3 position, Vector3 rotation, Vector3 scale, Color3[] colors, boolean invertedMesh) {
        super(
            name,
            position,
            rotation,
            scale,
            new Face[] {
                new Face(new Vector3[] {
                    new Vector3(1.0f, -1.0f, -1.0f), // Front Face
                    new Vector3(1.0f, 1.0f, -1.0f),
                    new Vector3(-1.0f, 1.0f, -1.0f),
                    new Vector3(-1.0f, -1.0f, -1.0f)
                }, colors[0]),
                new Face(new Vector3[] {
                    new Vector3(-1.0f, -1.0f, 1.0f), // Back Face
                    new Vector3(-1.0f, 1.0f, 1.0f),
                    new Vector3(1.0f, 1.0f, 1.0f),
                    new Vector3(1.0f, -1.0f, 1.0f)
                }, colors[1]),
                new Face(new Vector3[] {
                    new Vector3(-1.0f, -1.0f, -1.0f), // Left Face
                    new Vector3(-1.0f, 1.0f, -1.0f),
                    new Vector3(-1.0f, 1.0f, 1.0f),
                    new Vector3(-1.0f, -1.0f, 1.0f)
                }, colors[2]),
                new Face(new Vector3[] {
                    new Vector3(1.0f, -1.0f, 1.0f), // Right Face
                    new Vector3(1.0f, 1.0f, 1.0f),
                    new Vector3(1.0f, 1.0f, -1.0f),
                    new Vector3(1.0f, -1.0f, -1.0f)
                }, colors[3]),
                new Face(new Vector3[] {
                    new Vector3(1.0f, 1.0f, -1.0f), // Top Face
                    new Vector3(1.0f, 1.0f, 1.0f),
                    new Vector3(-1.0f, 1.0f, 1.0f),
                    new Vector3(-1.0f, 1.0f, -1.0f)
                }, colors[4]),
                new Face(new Vector3[] {
                    new Vector3(-1.0f, -1.0f, -1.0f), // Bottom Face
                    new Vector3(-1.0f, -1.0f, 1.0f),
                    new Vector3(1.0f, -1.0f, 1.0f),
                    new Vector3(1.0f, -1.0f, -1.0f)
                }, colors[5])
            },
            invertedMesh
        );
    }
}