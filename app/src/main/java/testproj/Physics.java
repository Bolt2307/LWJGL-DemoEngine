package testproj;

public class Physics {
    public static Vector3 gravity = new Vector3(0.0f, -98f, 0.0f);
    public static boolean paused = false;

    public static void update (float delta) {
        if (paused) return;

        checkForCollisions();
        applyForces();

        for (RigidBody body : RigidBody.rigidBodies) {
            if (!body.active) continue;

            body.acceleration = gravity.multiply(body.gravityScale);
            body.velocity = body.velocity.add(body.acceleration.multiply(delta));

            if (body.anchored) {
                body.velocity = Vector3.ZERO.copy();
            } else {
                body.position = body.position.add(body.velocity.multiply(delta));
            }

            if (body.player == null) { 
                body.collider.position = body.position;
                body.object.position = body.position;
            } else {
                body.player.setPosition(body.position);
            }
        }
    }

    public static void checkForCollisions () {
        for (RigidBody body : RigidBody.rigidBodies) {
            body.checkCollisions(RigidBody.rigidBodies);
        }
    }

    public static void applyForces () {
        for (RigidBody body : RigidBody.rigidBodies) {
            if ((!body.active) || (body.anchored)) continue;

            for (RigidBody collidingBody : body.collidingBodies) {
                body.velocity = body.velocity.subtract(body.velocity.add(collidingBody.velocity).multiply((body.damping + 1.0f) * (collidingBody.mass / body.mass)));
            }
        }
    }
}