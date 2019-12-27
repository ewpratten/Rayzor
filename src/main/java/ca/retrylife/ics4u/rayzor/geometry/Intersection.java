package ca.retrylife.ics4u.rayzor.geometry;

import ca.retrylife.ics4u.rayzor.objects.Sphere;

/**
 * Ray intersection
 */
public class Intersection {
    public double distance;
    public Sphere object;

    public Intersection(double distance, Sphere object){
        this.distance = distance;
        this.object = object;
    }

}