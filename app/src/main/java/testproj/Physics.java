package testproj;

public class Physics {
    public static Vector3 gravity = new Vector3(0.0f, -29.4f, 0.0f);
    public static boolean paused = false;

    public static void update (float delta) {
        if (paused) return;
        
        findColliding();
        applyForces();

        for (RigidBody body : RigidBody.rigidBodies) {
            if (!body.active) continue;

            body.acceleration = gravity.multiply(body.gravityScale);
            body.velocity = body.velocity.add(body.acceleration.multiply(delta));

            if (body.anchored) {
                body.velocity = Vector3.ZERO;
            } else {
                body.position = body.position.add(body.velocity.multiply(delta));
            }
            
            body.object.position = body.position;
            body.collider.position = body.position;
        }
    }

    public static void findColliding () {
        for (RigidBody body : RigidBody.rigidBodies) {
            body.checkCollisions(RigidBody.rigidBodies);
        }
    }

    public static void applyForces () {
        for (RigidBody body : RigidBody.rigidBodies) {
            if (!body.anchored) {
                for (RigidBody other : body.collidingBodies) {
                    Vector3 normal = body.position.subtract(other.position).normalize();
                    body.acceleration = body.acceleration.add(normal.multiply(body.acceleration.negate()));
                }
            }
        }
    }
}