package ca.retrylife.ics4u.rayzor.interfaces;

import ca.retrylife.ics4u.rayzor.Ray;

/**
 * Common interface for objects that can intersect with rays
 */
public interface Intersectable {

    /**
     * Check for intersection with a ray
     * 
     * @param rayray Ray to check
     * @return Dows intersect?
     */
    public boolean doesIntersect(Ray ray);

    /**
     * Check intersection with ray
     * 
     * @param ray Ray to check
     * @return Intersection
     */
    public Double getIntersection(Ray ray);
}