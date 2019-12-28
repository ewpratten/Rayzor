package ca.retrylife.ics4u.rayzor.interfaces;


import ca.retrylife.ics4u.rayzor.lighting.Ray;

import ca.retrylife.libvec.Vector3;
import ca.retrylife.libvec.Point2;

import ca.retrylife.ics4u.rayzor.geometry.Intersection;

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
    public Point2 getTextureCoords(Vector3 hitPoint);
}