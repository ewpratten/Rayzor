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
    public abstract Vector3 getColorVectorForRay(Scene scene, Intersection intersection, Vector3 hitPoint,
            Vector3 surfaceNormal);

}