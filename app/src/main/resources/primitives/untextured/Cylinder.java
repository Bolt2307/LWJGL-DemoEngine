package primitives.untextured;

import java.util.ArrayList;

import testproj.Color3;
import testproj.Face;
import testproj.Vector3;

public class Cylinder extends testproj.Object3D {

    public Cylinder(String name, Vector3 position, Vector3 rotation, Vector3 scale, Color3 color, int radialRes, int heightRes, boolean capped, boolean invertedMesh) {
        super(name, position, rotation, scale, generateFaces(color, radialRes, heightRes, capped), invertedMesh);
    }

    private static Face[] generateFaces(Color3 color, int radialRes, int heightRes, boolean capped) {
        ArrayList<Face> faces = new ArrayList<>();

        // ===== SIDE WALLS =====
        for (int y = 0; y < heightRes; y++) {
            float v0 = y / (float) heightRes;
            float v1 = (y + 1) / (float) heightRes;

            float y0 = v0 * 2f - 1f;
            float y1 = v1 * 2f - 1f;

            for (int i = 0; i < radialRes; i++) {
                float a0 = (float)(i * Math.PI * 2 / radialRes);
                float a1 = (float)((i + 1) * Math.PI * 2 / radialRes);

                Vector3 p0 = new Vector3((float)Math.cos(a0), y0, (float)Math.sin(a0));
                Vector3 p1 = new Vector3((float)Math.cos(a1), y0, (float)Math.sin(a1));
                Vector3 p2 = new Vector3((float)Math.cos(a1), y1, (float)Math.sin(a1));
                Vector3 p3 = new Vector3((float)Math.cos(a0), y1, (float)Math.sin(a0));

                faces.add(new Face(new Vector3[]{p0, p1, p2, p3}, color));
            }
        }

        if (!capped) {
            return faces.toArray(new Face[0]);
        }

        // ===== TOP CAP =====
        buildCap(faces, color, radialRes, 1f);

        // ===== BOTTOM CAP =====
        buildCap(faces, color, radialRes, -1f);

        return faces.toArray(new Face[0]);
    }

    private static void buildCap(ArrayList<Face> faces, Color3 color, int radialRes, float y) {
        Vector3 center = new Vector3(0, y, 0);

        for (int i = 0; i < radialRes; i++) {
            float a0 = (float)(i * Math.PI * 2 / radialRes);
            float a1 = (float)((i + 1) * Math.PI * 2 / radialRes);

            Vector3 p0 = new Vector3((float)Math.cos(a0), y, (float)Math.sin(a0));
            Vector3 p1 = new Vector3((float)Math.cos(a1), y, (float)Math.sin(a1));

            // quad fan (degenerates cleanly into a triangle)
            if (y > 0) {
                faces.add(new Face(
                    new Vector3[]{center, p0, p1, center},
                    color
                ));
            } else {
                faces.add(new Face(
                    new Vector3[]{center, p1, p0, center},
                    color
                ));
            }
        }
    }
}