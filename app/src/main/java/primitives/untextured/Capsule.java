package primitives.untextured;

import java.util.ArrayList;

import testproj.Color3;
import testproj.Face;
import testproj.Vector3;

public class Capsule extends testproj.Object3D {

    public Capsule(String name, Vector3 position, Vector3 rotation, Vector3 scale, Color3 color, int radialRes, int heightRes, float halfHeight, boolean invertedMesh) {
        super( name, position, rotation, scale, generateFaces(color, radialRes, heightRes, halfHeight), invertedMesh);
    }

    private static Face[] generateFaces(Color3 color, int radialRes, int heightRes, float halfHeight) {
        ArrayList<Face> faces = new ArrayList<>();

        // ===== CYLINDER =====
        for (int y = 0; y < heightRes; y++) {
            float v0 = y / (float) heightRes;
            float v1 = (y + 1) / (float) heightRes;

            float y0 = -halfHeight + v0 * (halfHeight * 2);
            float y1 = -halfHeight + v1 * (halfHeight * 2);

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

        // ===== TOP HEMISPHERE =====
        buildHemisphere(faces, color, radialRes, heightRes, halfHeight, true);

        // ===== BOTTOM HEMISPHERE =====
        buildHemisphere(faces, color, radialRes, heightRes, halfHeight, false);

        return faces.toArray(new Face[0]);
    }

    private static void buildHemisphere(ArrayList<Face> faces, Color3 color, int radialRes, int verticalRes, float halfHeight, boolean top) {
        for (int y = 0; y < verticalRes; y++) {
            float v0 = y / (float) verticalRes;
            float v1 = (y + 1) / (float) verticalRes;

            float phi0 = (float)(v0 * Math.PI / 2);
            float phi1 = (float)(v1 * Math.PI / 2);

            if (!top) {
                phi0 = (float)Math.PI - phi0;
                phi1 = (float)Math.PI - phi1;
            }

            for (int i = 0; i < radialRes; i++) {
                float a0 = (float)(i * Math.PI * 2 / radialRes);
                float a1 = (float)((i + 1) * Math.PI * 2 / radialRes);

                Vector3 p0 = spherical(a0, phi0, halfHeight, top);
                Vector3 p1 = spherical(a1, phi0, halfHeight, top);
                Vector3 p2 = spherical(a1, phi1, halfHeight, top);
                Vector3 p3 = spherical(a0, phi1, halfHeight, top);

                if (top) {
                    faces.add(new Face(new Vector3[]{p3, p2, p1, p0}, color));
                } else {
                    faces.add(new Face(new Vector3[]{p0, p1, p2, p3}, color));
                }

                
            }
        }
    }

    private static Vector3 spherical(float theta, float phi, float halfHeight, boolean top) {
        float x = (float)(Math.sin(phi) * Math.cos(theta));
        float y = (float)Math.cos(phi);
        float z = (float)(Math.sin(phi) * Math.sin(theta));

        y += top ? halfHeight : -halfHeight;

        return new Vector3(x, y, z);
    }
}