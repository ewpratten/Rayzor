package ca.retrylife.ics4u.rayzor.lighting;

import ca.retrylife.ics4u.rayzor.Scene;
import ca.retrylife.ics4u.rayzor.geometry.Intersection;
import ca.retrylife.libvec.Vector3;
import ca.retrylife.libvec.Color3;

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
    public DirectionalLight(Vector3 direction, Color3 color, double intensity) {
        this.direction = direction;
        this.color = color;
        this.intensity = intensity;
    }

    @Override
    public Vector3 getColorVectorForRay(Scene scene, Ray ray, Intersection intersection, int depth) {

        // Calculate the hit point
        Vector3 hitPoint = Vector3.add(ray.origin, Vector3.mul(ray.direction, intersection.distance));

        // Find the surface's normal
        Vector3 surfaceNormal = intersection.object.getSurfaceNormal(hitPoint);

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
        double reflected = intersection.object.material.albedo / Math.PI;

        // Calculate the diffuse color
        Vector3 diffuseColor = Vector3
                .mul(Vector3.mul(Vector3.mul(intersection.object.material.color, color), lightPower), reflected);

        // If there is no reflection, there is no reflectivity
        if (intersection.object.material.isReflective) {

            // Create a reflected ray
            Ray reflectedRay = Ray.createReflection(surfaceNormal, ray.direction, hitPoint, scene.SHADOW_BIAS);

            // Handle color reflectivity
            diffuseColor = Vector3.mul(diffuseColor, (1.0 - reflected));
            diffuseColor = Vector3.add(diffuseColor, Vector3.mul(castRay(scene, reflectedRay, depth + 1), reflected));
        }

        return diffuseColor;
    }

}