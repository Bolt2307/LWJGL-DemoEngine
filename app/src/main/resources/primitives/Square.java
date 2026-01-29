package primitives;

import testproj.*;

public class Square extends testproj.Object {
    public Square (Vector3 position, Vector3 rotation, Vector3 scale, Color3 color) {
        super(
            position,
            rotation,
            scale,
            new Face[] {
                new Face(new Vector3[] {
                    new Vector3(-1.0f, -1.0f, -1.0f), // Front Face
                    new Vector3(-1.0f, 1.0f, -1.0f),
                    new Vector3(1.0f, 1.0f, -1.0f),
                    new Vector3(1.0f, -1.0f, -1.0f),
                }, color),
                new Face(new Vector3[] {
                    new Vector3(-1.0f, -1.0f, 1.0f), // Back Face
                    new Vector3(-1.0f, 1.0f, 1.0f),
                    new Vector3(1.0f, 1.0f, 1.0f),
                    new Vector3(1.0f, -1.0f, 1.0f),
                }, color),
                new Face(new Vector3[] {
                    new Vector3(-1.0f, -1.0f, -1.0f), // Left Face
                    new Vector3(-1.0f, 1.0f, -1.0f),
                    new Vector3(-1.0f, 1.0f, 1.0f),
                    new Vector3(-1.0f, -1.0f, 1.0f),
                }, new Color3(0.0f, 1.0f, 0.0f)),
                new Face(new Vector3[] {
                    new Vector3(1.0f, -1.0f, -1.0f), // Right Face
                    new Vector3(1.0f, 1.0f, -1.0f),
                    new Vector3(1.0f, 1.0f, 1.0f),
                    new Vector3(1.0f, -1.0f, 1.0f),
                }, color),
                new Face(new Vector3[] {
                    new Vector3(-1.0f, 1.0f, -1.0f), // Top Face
                    new Vector3(-1.0f, 1.0f, 1.0f),
                    new Vector3(1.0f, 1.0f, 1.0f),
                    new Vector3(1.0f, 1.0f, -1.0f)
                }, color),
                new Face(new Vector3[] {
                    new Vector3(-1.0f, -1.0f, -1.0f), // Bottom Face
                    new Vector3(-1.0f, -1.0f, 1.0f),
                    new Vector3(1.0f, -1.0f, 1.0f),
                    new Vector3(1.0f, -1.0f, -1.0f)
                }, color)
            }
        );
    }
}