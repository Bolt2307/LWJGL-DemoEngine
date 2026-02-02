package testproj;

public class BoxCollider {
    public Vector3 min;
    public Vector3 max;

    public Vector3 position;

    public BoxCollider (Vector3 min, Vector3 max) {
        this.min = min;
        this.max = max;
        this.position = Vector3.ZERO.copy();
    }

    public BoxCollider (Vector3 min, Vector3 max, Vector3 position) {
        this.min = min;
        this.max = max;
        this.position = position.copy();
    }

    public BoxCollider scale (Vector3 scale) {
        this.min = this.min.multiply(scale);
        this.max = this.max.multiply(scale);
        return this;
    }

    public boolean colliding(BoxCollider other) {
        Vector3 aMin = this.min.add(this.position);
        Vector3 aMax = this.max.add(this.position);

        Vector3 bMin = other.min.add(other.position);
        Vector3 bMax = other.max.add(other.position);

        return (aMin.x <= bMax.x && aMax.x >= bMin.x) &&
            (aMin.y <= bMax.y && aMax.y >= bMin.y) &&
            (aMin.z <= bMax.z && aMax.z >= bMin.z);
    }

}