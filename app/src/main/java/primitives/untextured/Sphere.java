package primitives.untextured;

import java.util.ArrayList;

import testproj.Color3;
import testproj.Face;
import testproj.Vector3;

public class Sphere extends testproj.Object3D {
    public Sphere(String name, Vector3 position, Vector3 rotation, Vector3 scale, Color3 color, int resolution, boolean invertedMesh) {
        super(name, position, rotation, scale, generateFaces(color, resolution), invertedMesh);
    }

    private static Face[] generateFaces(Color3 color, int res) {
        ArrayList<Face> faces = new ArrayList<>();

        // 6 cube faces
        Vector3[] normals = {
            new Vector3( 1, 0, 0),
            new Vector3(-1, 0, 0),
            new Vector3( 0, 1, 0),
            new Vector3( 0,-1, 0),
            new Vector3( 0, 0, 1),
            new Vector3( 0, 0,-1)
        };

        for (Vector3 n : normals) {
            buildFace(faces, n, res, color);
        }

        return faces.toArray(new Face[0]);
    }

    private static void buildFace(
        ArrayList<Face> faces,
        Vector3 normal,
        int res,
        Color3 color
    ) {
        Vector3 axisA = new Vector3(normal.y, normal.z, normal.x);
        Vector3 axisB = normal.cross(axisA);

        for (int y = 0; y < res; y++) {
            for (int x = 0; x < res; x++) {

                float u0 = (x / (float)res) * 2f - 1.0f;
                float v0 = (y / (float)res) * 2f - 1.0f;
                float u1 = ((x+1) / (float)res) * 2f - 1.0f;
                float v1 = ((y+1) / (float)res) * 2f - 1.0f;

                Vector3 p0 = cubeToSphere(normal, axisA, axisB, u0, v0);
                Vector3 p1 = cubeToSphere(normal, axisA, axisB, u1, v0);
                Vector3 p2 = cubeToSphere(normal, axisA, axisB, u1, v1);
                Vector3 p3 = cubeToSphere(normal, axisA, axisB, u0, v1);

                faces.add(new Face(
                    new Vector3[] {p3, p2, p1, p0},
                    color
                ));
            }
        }
    }

    private static Vector3 cubeToSphere(Vector3 normal, Vector3 axisA, Vector3 axisB, float u, float v) {
        Vector3 p = normal.add(axisA.multiply(u)).add(axisB.multiply(v));
        return p.normalize();
    }
}
