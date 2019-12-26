package ca.retrylife.ics4u.rayzor.objects;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

/**
 * A sphere object
 */
public class Sphere {

    // Sphere attributes
    public Vector3d centre;
    public double raduis;
    public Color3f color;

    /**
     * Create a Sphere
     * 
     * @param centre Centre point of sphere
     * @param radius Sphere radius
     * @param color  Sphere color
     */
    public Sphere(Vector3d centre, double radius, Color3f color) {
        this.centre = centre;
        this.raduis = radius;
        this.color = color;
    }
}