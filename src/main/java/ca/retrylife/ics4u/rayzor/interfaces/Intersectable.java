package ca.retrylife.ics4u.rayzor.interfaces;

import ca.retrylife.ics4u.rayzor.lighting.Ray;
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
}