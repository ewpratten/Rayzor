package ca.retrylife.ics4u.rayzor.objects;

import javax.vecmath.Color3f;

import ca.retrylife.ics4u.rayzor.lighting.Ray;
import ca.retrylife.ics4u.rayzor.geometry.Intersection;
import ca.retrylife.ics4u.rayzor.geometry.Vector3;

public class Plane extends SceneObject {

    // Positioning
    public Vector3 origin;
    public Vector3 normal;

    /**
     * Create a Plane from a point and normal
     * 
     * @param origin Point
     * @param normal Normal
     * @param color  Object color
     * @param albedo Reflectivity
     */
    public Plane(Vector3 origin, Vector3 normal, Color3f color, double albedo) {
        this.origin = origin;
        this.normal = normal;
        this.color = color;
        this.albedo = albedo;

    }

    @Override
    public Intersection getIntersection(Ray ray) {

        // Find denominator between the normal and the ray's direction
        double denominator = normal.dot(ray.direction);

        // Since planes are infinite, if the plane and ray are not parallel, they will
        // intersect
        if (denominator > 1e-6) {

            // Here, the point of intersection is calculated. The method for this is
            // described over on scratchapixel.com:
            // https://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-plane-and-ray-disk-intersection

            // Find the difference between the plane, and ray origins
            Vector3 v = Vector3.sub(origin, ray.origin);

            // Determine the distance from the ray to the intersection
            double distance = v.dot(normal) / denominator;

            // If there is a distance, return it
            if (distance >= 0.0) {
                return new Intersection(distance, this);
            } else {
                return null;
            }

        } else {
            return null;
        }
    }

    @Override
    public Vector3 getSurfaceNormal(Vector3 hitPoint) {
        return Vector3.negate(normal);
    }

}