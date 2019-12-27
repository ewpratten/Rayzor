package ca.retrylife.ics4u.rayzor.objects;

import ca.retrylife.ics4u.rayzor.lighting.Ray;
import ca.retrylife.ics4u.rayzor.textures.Material;

import javax.vecmath.Point2f;

import ca.retrylife.ics4u.rayzor.geometry.Intersection;
import ca.retrylife.ics4u.rayzor.geometry.Vector3;

/**
 * A sphere object
 */
public class Sphere extends SceneObject {

    // Sphere attributes
    public Vector3 centre;
    public double radius;

    /**
     * Create a Sphere
     * 
     * @param centre Centre point of sphere
     * @param radius Sphere radius
     * @param mat    Shape material
     */
    public Sphere(Vector3 centre, double radius, Material mat) {
        this.centre = centre;
        this.radius = radius;
        this.material = mat;
    }

    @Override
    public Intersection getIntersection(Ray ray) {
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

        // Return the smallest distance between the ray and sphere
        if (t0 < 0.0 && t1 < 0.0) {
            return null;
        } else if (t0 < 0.0) {
            return new Intersection(t1, this);
        } else if (t1 < 0.0) {
            return new Intersection(t0, this);
        } else {
            double min_distance = (t0 < t1) ? t0 : t1;
            return new Intersection(min_distance, this);
        }
    }

    @Override
    public Vector3 getSurfaceNormal(Vector3 hitPoint) {

        return Vector3.sub(hitPoint, centre).normalize();
    }

    @Override
    public Point2f getTextureCoords(Vector3 hitPoint) {
        
        // Compute spherical coordinates
        double phi = Math.atan2(hitPoint.z, hitPoint.x);
        double theta = Math.acos(hitPoint.y / radius);

        // Adjust to map to [0-1]
        phi = (1 + phi / Math.PI) * 0.5;
        theta = theta / Math.PI;

        return new Point2f((float)phi, (float)theta);
    }
}