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
    public boolean doesIntersect(Ray ray) {

        // Find distance from centre to origin
        Vector3 l = Vector3.sub(centre, ray.origin);

        // Calculate triangle sides
        double adj = l.dot(ray.direction);
        double opp = l.dot(l) - (adj * adj);

        // Squared radius
        double radius2 = radius * radius;

        // If opp is less than the radius, it is in the sphere
        return opp <= radius2;
    }

    @Override
    public Double getIntersection(Ray ray) {
        // Find distance from centre to origin
        Vector3 l = Vector3.sub(centre, ray.origin);

        // Calculate triangle sides
        double adj = l.dot(ray.direction);
        double opp = l.dot(l) - (adj * adj);

        // Squared radius
        double radius2 = radius * radius;

        // If opp is greater than the radius, it is not intersecting
        if (opp > radius2) {
            return null;
        }

        // Calc and return intersection
        double thc = Math.sqrt(radius2 - opp);
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