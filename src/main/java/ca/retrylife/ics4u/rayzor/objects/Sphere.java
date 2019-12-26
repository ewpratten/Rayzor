package ca.retrylife.ics4u.rayzor.objects;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import ca.retrylife.ics4u.rayzor.Ray;
import ca.retrylife.ics4u.rayzor.interfaces.Intersectable;

/**
 * A sphere object
 */
public class Sphere implements Intersectable {

    // Sphere attributes
    public Vector3d centre;
    public double radius;
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
        this.radius = radius;
        this.color = color;
    }

    @Override
    public boolean intersects(Ray ray) {
        // Create a line segment from the ray's origin to the sphere's centre
        // Vector3d segment = new Vector3d((centre.x - ray.origin.x), (centre.y -
        // ray.origin.y),
        // (centre.z - ray.origin.z));

        // Vector3d segment = centre.sub(ray.origin);
        Vector3d segment = new Vector3d(centre);
        segment.sub(ray.origin);

        // Using the segment as the hypot, find the adg side
        double adj2 = segment.dot(ray.direction);

        // Find the length-squared of the opposite side
        double d2 = segment.dot(segment) - (adj2 * adj2);

        // If length-squared is less than radius squared, the ray intersects the
        // sphere
        return d2 < (radius * radius);

        // float delta = 0;
        // Vector3d omc = Vector3d.sub(centre, ray.origin);
        // float b = Vector3d.Dot(ray, omc);
        // delta = b * b - Vector3d.Dot(omc, omc) + r * r;
        // return delta >= 0;
    }
}