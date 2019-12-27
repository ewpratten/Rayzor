package ca.retrylife.ics4u.rayzor.geometry;

import javax.vecmath.Color3f;

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
     * Create a Vector3 from a Color3f
     * @param c Color3f
     * @return Vector3
     */
    public static Vector3 fromColor3f(Color3f c) {
        return new Vector3(c.x, c.y, c.z);
    }

    /**
     * Convert the vector to a Color3f object
     * @return Color3f
     */
    public Color3f toColor3f() {
        return new Color3f((float)x, (float)y, (float)z);
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

    /**
     * Add two vectors
     * 
     * @param a First vector
     * @param b Second vector
     * @return Sum
     */
    public static Vector3 add(Vector3 a, Vector3 b) {
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    /**
     * Get the inverse of a vector
     * 
     * @param x Vector
     * @return Inverse
     */
    public static Vector3 negate(Vector3 x) {
        return new Vector3(-x.x, -x.y, -x.z);
    }

    /**
     * Multiply two vectors
     * 
     * @param a First vector
     * @param b Second vector
     * @return Product
     */
    public static Vector3 mul(Vector3 a, Vector3 b) {
        return new Vector3(a.x * b.x, a.y * b.y, a.z * b.z);
    }

    /**
     * Multiply a vector and a scalar
     * 
     * @param a Vector
     * @param b Scalar
     * @return Product
     */
    public static Vector3 mul(Vector3 a, double b) {
        return new Vector3(a.x * b, a.y * b, a.z * b);
    }

}