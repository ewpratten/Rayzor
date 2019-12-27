package ca.retrylife.ics4u.rayzor;

import ca.retrylife.ics4u.rayzor.geometry.Vector3;

/**
 * A castable ray
 */
public class Ray {
    public Vector3 origin;
    public Vector3 direction;

    /**
     * Create a Ray
     * 
     * @param origin    Ray origin
     * @param direction Ray direction
     */
    public Ray(Vector3 origin, Vector3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    /**
     * Create a prime ray
     * 
     * Camera is aligned along the negative z-axis, with positive x towards the
     * right and positive y being up. We are essentially creating a pixel grid just
     * in front of the sensor, and casting from it.
     * 
     * @param x     Sensor X pos
     * @param y     Sensor Y pos
     * @param scene Scene
     * @return Prime ray
     */
    public static Ray prime(int x, int y, Scene scene) {

        // Scene must by wider than it is tall
        assert scene.size.getWidth() > scene.size.getHeight();

        // Determine aspect ratio
        double aspectRatio = (double) scene.size.getWidth() / (double) scene.size.getHeight();

        // Determine FOV adjustment factor
        double fovAdjustment = Math.tan(Math.toRadians(scene.fov) / 2.0);

        // Add .5 to our pixel position, and cast to a double. This will cause the ray
        // to pass through the center of the pixel, rather than it's edge
        double pixelCentreX = (double) x + 0.5;
        double pixelCentreY = (double) x + 0.5;

        // Convert the scene width from pixels [0-800] to percentage [0.0-1.0]
        double sensorX = pixelCentreX / (double) scene.size.getWidth();
        double sensorY = pixelCentreY / (double) scene.size.getHeight();

        // Adjust the sensor
        // Y is now flipped, so positive is up
        sensorX = ((sensorX * 2.0) - 1.0) * aspectRatio;
        sensorY = 1.0 - (sensorY * 2.0);

        // Adjust X and Y by FOV
        sensorX *= fovAdjustment;
        sensorY *= fovAdjustment;

        // Create a direction vector
        Vector3 directionVector = new Vector3(sensorX, sensorY, -1.0);

        // Return the newly generated ray
        return new Ray(new Vector3(0, 0, 0), directionVector.normalize());
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", origin.toString(), direction.toString());
    }
}