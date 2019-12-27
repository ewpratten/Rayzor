package ca.retrylife.ics4u.rayzor.interfaces;


import ca.retrylife.ics4u.rayzor.lighting.Ray;

import javax.vecmath.Point2f;

import ca.retrylife.ics4u.rayzor.geometry.Intersection;
import ca.retrylife.ics4u.rayzor.geometry.Vector3;

/**
 * Common interface for objects that can intersect with rays
 */
public interface Intersectable {

    /**
     * Check intersection with ray
     * 
     * @param ray Ray to check
     * @return Intersection
     */
    public Intersection getIntersection(Ray ray);

    /**
     * Get the texture coordinates for a given hit point
     * @param hitPoint Hit point
     * @return Coordinates
     */
    public Point2f getTextureCoords(Vector3 hitPoint);
}