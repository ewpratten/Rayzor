package ca.retrylife.ics4u.rayzor.objects;

import javax.vecmath.Color3f;

import ca.retrylife.ics4u.rayzor.Ray;
import ca.retrylife.ics4u.rayzor.geometry.Vector3;
import ca.retrylife.ics4u.rayzor.interfaces.Intersectable;

/**
 * A sphere object
 */
public class Sphere implements Intersectable {

    // Sphere attributes
    public Vector3 centre;
    public double radius;
    public Color3f color;

    /**
     * Create a Sphere
     * 
     * @param centre Centre point of sphere
     * @param radius Sphere radius
     * @param color  Sphere color
     */
    public Sphere(Vector3 centre, double radius, Color3f color) {
        this.centre = centre;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public Double intersects(Ray ray) {
        // Create a line segment from the ray's origin to the sphere's centre
        // Vector3 segment = Vector3.sub(centre, ray.origin);
        // double adj = segment.length();
        // // Vector3d segment = new Vector3d((centre.x - ray.origin.x), (centre.y -
        // // ray.origin.y),
        // // (centre.z - ray.origin.z));

        // // Vector3d segment = centre.sub(ray.origin);
        // // Vector3 segment = new Vector3(centre);
        // // segment.sub(ray.origin);

        // // Using the segment as the hypot, find the adg side
        // double hyp = segment.dot(ray.direction);

        // // Find the length-squared of the opposite side
        // double d2 = segment.dot(segment) - (adj2 * adj2);

        // // If length-squared is less than radius squared, the ray intersects the
        // // sphere
        // return d2 < (radius * radius);

        // float delta = 0;
        // Vector3d omc = Vector3d.sub(centre, ray.origin);
        // float b = Vector3d.Dot(ray, omc);
        // delta = b * b - Vector3d.Dot(omc, omc) + r * r;
        // return delta >= 0;

        Vector3 l = Vector3.sub(centre, ray.origin);

        double adj = l.dot(ray.direction);
        double d2 = l.dot(l) - (adj * adj);

        double radius2 = radius * radius;

        // If d2 is outside the sphere, there is no intersection. Therefore, the
        // intersection is null
        if (d2 > radius2) {
            return null;
        }

        double thc = Math.sqrt(radius2 - d2);
        double t0 = adj - thc;
        double t1 = adj + thc;

        if (t0 < 0.0 && t1 < 0.0) {
            return null;
        } else if (t0 < 0.0) {
            return t1;
        } else if (t1 < 0.0) {
            return t0;
        } else {
            double distance = (t0 < t1) ? t0 : t1;
            return distance;
        }
    }
}