package ca.retrylife.ics4u.rayzor.geometry;

import ca.retrylife.ics4u.rayzor.objects.SceneObject;

/**
 * Ray intersection
 */
public class Intersection {
    public double distance;
    public SceneObject object;

    public Intersection(double distance, SceneObject object){
        this.distance = distance;
        this.object = object;
    }

}