package ca.retrylife.ics4u.rayzor.objects;

import javax.vecmath.Color3f;

import ca.retrylife.ics4u.rayzor.geometry.Vector3;
import ca.retrylife.ics4u.rayzor.interfaces.Intersectable;

/**
 * A scene object
 */
public abstract class SceneObject implements Intersectable {

    /**
     * Object color vector
     */
    public Color3f color;

    /**
     * Reflectivity of the object
     */
    public double albedo;

    /**
     * Get the surface normal from a hit point
     * 
     * @param hitPoint Point of intersection
     * @return Normal
     */
    public abstract Vector3 getSurfaceNormal(Vector3 hitPoint);

}