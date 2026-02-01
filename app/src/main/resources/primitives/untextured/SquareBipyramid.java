package primitives.untextured;

import testproj.Color3;
import testproj.Face;
import testproj.Vector3;

public class SquareBipyramid extends testproj.Object3D {
    public SquareBipyramid (String name, Vector3 position, Vector3 rotation, Vector3 scale, Color3[] colors, boolean invertedMesh) {
        super(
            name,
            position,
            rotation,
            scale,
            new Face[] {
                new Face(new Vector3[] {
                    new Vector3(-1.0f, 0.0f, 0.0f), // TLF Face
                    new Vector3(0.0f, 0.0f, -1.0f),
                    new Vector3(0.0f, 1.0f, 0.0f)
                }, colors[0]),
                new Face(new Vector3[] {
                    new Vector3(0.0f, 1.0f, 0.0f), // TRF Face
                    new Vector3(0.0f, 0.0f, -1.0f),
                    new Vector3(1.0f, 0.0f, 0.0f)
                }, colors[1]),
                new Face(new Vector3[] {
                    new Vector3(0.0f, -1.0f, 0.0f), // BLF Face
                    new Vector3(0.0f, 0.0f, -1.0f),
                    new Vector3(-1.0f, 0.0f, 0.0f)
                }, colors[2]),
                new Face(new Vector3[] {
                    new Vector3(1.0f, 0.0f, 0.0f), // BRF Face
                    new Vector3(0.0f, 0.0f, -1.0f),
                    new Vector3(0.0f, -1.0f, 0.0f)
                }, colors[3]),
                new Face(new Vector3[] {
                    new Vector3(0.0f, 1.0f, 0.0f), // TLB Face
                    new Vector3(0.0f, 0.0f, 1.0f),
                    new Vector3(-1.0f, 0.0f, 0.0f)
                }, colors[4]),
                new Face(new Vector3[] {
                    new Vector3(1.0f, 0.0f, 0.0f), // TRB Face
                    new Vector3(0.0f, 0.0f, 1.0f),
                    new Vector3(0.0f, 1.0f, 0.0f)
                }, colors[5]),
                new Face(new Vector3[] {
                    new Vector3(-1.0f, 0.0f, 0.0f), // BLB Face
                    new Vector3(0.0f, 0.0f, 1.0f),
                    new Vector3(0.0f, -1.0f, 0.0f)
                }, colors[6]),
                new Face(new Vector3[] {
                    new Vector3(0.0f, -1.0f, 0.0f), // BRB Face
                    new Vector3(0.0f, 0.0f, 1.0f),
                    new Vector3(1.0f, 0.0f, 0.0f)
                }, colors[7])
            },
            invertedMesh
        );
    }
}