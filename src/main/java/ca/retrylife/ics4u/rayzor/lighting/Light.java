package ca.retrylife.ics4u.rayzor.lighting;

import javax.vecmath.Color3f;

import ca.retrylife.ics4u.rayzor.Scene;
import ca.retrylife.ics4u.rayzor.geometry.Intersection;
import ca.retrylife.ics4u.rayzor.geometry.Vector3;

/**
 * Base class for all scene lights
 */
public abstract class Light {

    /**
     * Light color
     */
    public Color3f color;

    /**
     * Light intensity
     */
    public double intensity;

    /**
     * Get the color for an intersecting ray
     * 
     * @param scene         Scene
     * @param intersection  Ray intersection
     * @param hitPoint      Ray hitpoint
     * @param surfaceNormal Intersecting surface normal
     * @return Calculated color vector
     */
    public abstract Vector3 getColorVectorForRay(Scene scene, Ray ray, Intersection intersection, int depth);

    /**
     * Recursively cast a ray to handle reflections
     * 
     * @param scene Scene
     * @param ray   Origin ray
     * @param depth Current depth
     * @return Computed color
     */
    public Vector3 castRay(Scene scene, Ray ray, int depth) {

        // Handle max depth
        if (depth >= scene.MAX_DEPTH) {
            // System.out.println("MAX");
            return new Vector3(0.0, 0.0, 0.0);
        }

        // Find next ray intersection
        Intersection intersection = scene.follow(ray);

        // Handle no intersection
        if (intersection == null) {
            return new Vector3(0.0, 0.0, 0.0);
        }

        // Recurse to next point
        return getColorVectorForRay(scene, ray, intersection, depth);
    }

}