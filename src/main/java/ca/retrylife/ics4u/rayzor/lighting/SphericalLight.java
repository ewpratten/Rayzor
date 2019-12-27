package ca.retrylife.ics4u.rayzor.lighting;

import javax.vecmath.Color3f;

import ca.retrylife.ics4u.rayzor.Scene;
import ca.retrylife.ics4u.rayzor.geometry.Intersection;
import ca.retrylife.ics4u.rayzor.geometry.Vector3;

public class SphericalLight extends Light {

    public Vector3 position;

    public SphericalLight(Vector3 position, Color3f color, double intensity) {
        this.position = position;
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
        Vector3 directionToLight = Vector3.sub(position, hitPoint).normalize();

        // Get the shadow ray (with a little fudging to deal with shadow specks)
        Ray shadow = new Ray(Vector3.add(hitPoint, Vector3.mul(surfaceNormal, scene.SHADOW_BIAS)), directionToLight);

        // Determine if the shadow ray is in light or not
        Intersection shadowIntersection = scene.follow(shadow);

        // check if the nearest intersection is closer than the light is
        boolean inLight = shadowIntersection == null
                || shadowIntersection.distance > Vector3.sub(position, hitPoint).length();

        // Calculate lighting intensity with the Inverse Square Law
        double r2 = Vector3.sub(position, hitPoint).norm();

        double intensity = (inLight) ? (this.intensity / (4.0 * Math.PI * r2)) : 0.0;

        // Find the amount of light hitting this point with Lambert’s Cosine Law
        double lightPower = ((double) surfaceNormal.dot(directionToLight)) * intensity;

        // Calculate the amount of light reflected
        double reflected = intersection.object.material.albedo / Math.PI;

        // Calculate the diffuse color
        Vector3 diffuseColor = Vector3.mul(Vector3.mul(
                Vector3.mul(Vector3.fromColor3f(intersection.object.material.color), Vector3.fromColor3f(color)),
                lightPower), reflected);
            
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