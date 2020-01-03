package ca.retrylife.libvec2;

import ca.retrylife.libvec.VecUtil;

public class Vector3JNI {
    
    // Include CPP lib
    static {
        System.loadLibrary("Vector3");
    }

    public double x, y, z;

    /**
     * Create a vector from components
     * 
     * @param x X component
     * @param y Y component
     * @param z Z component
     */
    public Vector3JNI(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    /**
     * Copy constructor
     * 
     * @param other Copy source
     */
    public Vector3JNI(Vector3JNI other) {
        this(other.x, other.y, other.z);
    }

    /**
     * Create a vector from one component
     * 
     * @param x X component
     */
    public Vector3JNI(double x) {
        this(x, x, x);
    }

    /**
     * Create a vector of 0
     */
    public Vector3JNI() {
        this(0.0);
    }

    /**
     * Get the vector length
     * 
     * @return Length
     */
    public native double length();

    /**
     * Get the vector norm
     * 
     * @return Norm
     */
    public native double norm();

    /**
     * Get the vector normal
     * 
     * @return Normalized vector
     */
    public Vector3JNI normalize() {

        // Calculate the inverse length
        double inverseLength = (1.0 / length());

        // Build a new vector
        return new Vector3JNI(x * inverseLength, y * inverseLength, z * inverseLength);
    }

    /**
     * Clamp all components
     * 
     * @param min Lowest value
     * @param max Highest value
     */
    public void clamp(double min, double max) {

        x = VecUtil.clamp(x, min, max);
        y = VecUtil.clamp(y, min, max);
        z = VecUtil.clamp(z, min, max);
    }

    /**
     * Check equality with another Vector3JNI
     * 
     * @param other Other vector
     * @return Is equal?
     */
    public boolean equals(Vector3JNI other) {
        return other.x == x && other.y == y && other.z == z;
    }

    /* Operators */

    /**
     * Get the dot product of the other Vector3JNI
     * 
     * @param other Other Vector3JNI
     * @return Dot product
     */
    public native double dot(Vector3JNI other);

    /**
     * Get the cross product of the other Vector3JNI
     * 
     * @param other Other Vector3JNI
     * @return Cross product
     */
    public Vector3JNI cross(Vector3JNI other) {
        return new Vector3JNI(this.y * other.z - this.z * other.y, this.z * other.x - this.x * other.z,
                this.x * other.y - this.y * other.x);
    }

    /**
     * Subtract two Vector3JNIs
     * 
     * @param a First vector
     * @param b Second vector
     * @return Difference
     */
    public static Vector3JNI sub(Vector3JNI a, Vector3JNI b) {
        return new Vector3JNI(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    /**
     * Add two vectors
     * 
     * @param a First vector
     * @param b Second vector
     * @return Sum
     */
    public static Vector3JNI add(Vector3JNI a, Vector3JNI b) {
        return new Vector3JNI(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    /**
     * Get the inverse of a vector
     * 
     * @param x Vector
     * @return Inverse
     */
    public static Vector3JNI negate(Vector3JNI x) {
        return new Vector3JNI(-x.x, -x.y, -x.z);
    }

    /**
     * Multiply two vectors
     * 
     * @param a First vector
     * @param b Second vector
     * @return Product
     */
    public static Vector3JNI mul(Vector3JNI a, Vector3JNI b) {
        return new Vector3JNI(a.x * b.x, a.y * b.y, a.z * b.z);
    }

    /**
     * Multiply a vector and a scalar
     * 
     * @param a Vector
     * @param b Scalar
     * @return Product
     */
    public static Vector3JNI mul(Vector3JNI a, double b) {
        return new Vector3JNI(a.x * b, a.y * b, a.z * b);
    }
}