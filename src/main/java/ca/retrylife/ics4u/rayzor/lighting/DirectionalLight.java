package ca.retrylife.ics4u.rayzor.lighting;

import javax.vecmath.Color3f;

import ca.retrylife.ics4u.rayzor.Scene;
import ca.retrylife.ics4u.rayzor.geometry.Intersection;
import ca.retrylife.ics4u.rayzor.geometry.Vector3;

/**
 * A distant directional light source
 */
public class DirectionalLight extends Light {

    // Light meta
    public Vector3 direction;

    /**
     * Create a directional light source
     * 
     * @param direction Light direction
     * @param color     Light color
     * @param intensity Light intensity
     */
    public DirectionalLight(Vector3 direction, Color3f color, double intensity) {
        this.direction = direction;
        this.color = color;
        this.intensity = intensity;
    }

    @Override
    public Vector3 getColorVectorForRay(Scene scene, Intersection intersection, Vector3 hitPoint,
            Vector3 surfaceNormal) {

        // Get the direction to the light
        Vector3 directionToLight = Vector3.negate(direction);

        // Get the shadow ray (with a little fudging to deal with shadow specks)
        Ray shadow = new Ray(Vector3.add(hitPoint, Vector3.mul(surfaceNormal, scene.SHADOW_BIAS)), directionToLight);

        // Determine if the shadow ray is in light or not
        boolean inLight = scene.follow(shadow) == null;

        // Calculate lighting intensity
        double intensity = (inLight) ? this.intensity : 0.0;

        // Find the amount of light hitting this point with Lambert’s Cosine Law
        double lightPower = ((double) surfaceNormal.dot(directionToLight)) * intensity;

        // Calculate the amount of light reflected
        double reflected = intersection.object.albedo / Math.PI;

        return Vector3.mul(
                Vector3.mul(Vector3.mul(Vector3.fromColor3f(intersection.object.color), Vector3.fromColor3f(color)),
                        lightPower),
                reflected);
    }

}