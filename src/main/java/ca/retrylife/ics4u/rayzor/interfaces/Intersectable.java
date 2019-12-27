package ca.retrylife.ics4u.rayzor.interfaces;

import ca.retrylife.ics4u.rayzor.Ray;

/**
 * Common interface for objects that can intersect with rays
 */
public interface Intersectable {

    /**
     * Check if intersects with a ray
     * 
     * @param ray Ray to check
     * @return Intersection
     */
    public Double intersects(Ray ray);
}