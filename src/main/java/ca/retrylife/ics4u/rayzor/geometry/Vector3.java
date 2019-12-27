package ca.retrylife.ics4u.rayzor.geometry;

/**
 * Vector3 provides a small java 3D vector implementation.
 */
public class Vector3 {

    public double x, y, z;

    /**
     * Create a Vector3 from components
     * 
     * @param x X component
     * @param y Y component
     * @param z Z component
     */
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    /**
     * Copy constructor
     * 
     * @param other Copy source
     */
    public Vector3(Vector3 other) {
        this(other.x, other.y, other.z);
    }

    /**
     * Get a Vector3 of 0
     * 
     * @return Vector3
     */
    public static Vector3 zero() {
        return fromOne(0.0);
    }

    /**
     * Get a Vector3 of v
     * 
     * @param v Value
     * @return Vector3
     */
    public static Vector3 fromOne(double v) {
        return new Vector3(v, v, v);
    }

    /**
     * Get the vector length
     * 
     * @return Length
     */
    public double length() {
        return Math.sqrt(norm());
    }

    /**
     * Get the vector norm
     * 
     * @return Norm
     */
    public double norm() {
        return ((x * x) + (y * y) + (z * z));
    }

    /**
     * Get the normalized version of the Vector3
     * 
     * @return Normalized Vector3
     */
    public Vector3 normalize() {

        // Calculate the inverse length
        double inverseLength = (1.0 / length());

        // Build a new vector
        return new Vector3(x * inverseLength, y * inverseLength, z * inverseLength);
    }

    /**
     * Get the dot product of the other Vector3
     * 
     * @param other Other Vector3
     * @return Dot product
     */
    public double dot(Vector3 other) {
        return (this.x * other.x) + (this.y * other.y) + (this.z * other.z);
    }

    /**
     * Get the cross product of the other Vector3
     * 
     * @param other Other Vector3
     * @return Cross product
     */
    public Vector3 cross(Vector3 other) {
        return new Vector3(this.y * other.z - this.z * other.y, this.z * other.x - this.x * other.z,
                this.x * other.y - this.y * other.x);
    }

    /**
     * Subtract two Vector3s
     * 
     * @param a First vector
     * @param b Second vector
     * @return Difference
     */
    public static Vector3 sub(Vector3 a, Vector3 b) {
        return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
    }

}