package ca.retrylife.ics4u.rayzor;

import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Color;

import java.util.ArrayList;
import java.util.Arrays;

import ca.retrylife.ics4u.rayzor.geometry.Intersection;
import ca.retrylife.ics4u.rayzor.objects.SceneObject;

/**
 * A renderable scene
 */
public class Scene {

    // Scene sizing
    public Dimension size;
    public double fov;

    // Scene objects
    ArrayList<SceneObject> objects = new ArrayList<>();

    /**
     * Create a scene of a specific size with pre-defined objects
     * 
     * @param size    Scene size
     * @param fov     Scene FOV
     * @param objects Renderable objects for scene
     */
    public Scene(Dimension size, double fov, SceneObject objects) {
        this(size, fov);

        // Add all objects
        this.objects.addAll(Arrays.asList(objects));

    }

    /**
     * Create a scene of a specific size
     * 
     * @param size Scene size
     * @param fov  Scene FOV
     */
    public Scene(Dimension size, double fov) {

        // Set locals
        this.size = size;
        this.fov = fov;

    }

    /**
     * Add an object to the scene
     * 
     * @param obj Object for scene
     */
    public void addObject(SceneObject obj) {
        objects.add(obj);
    }

    /**
     * Follow a ray to find the nearest intersection
     * 
     * @param ray Ray to follow
     * @return Nearest intersection
     */
    public Intersection follow(Ray ray) {

        // Create a null intersection in case of no intersections found
        Intersection smallest = null;

        // Check each object
        for (SceneObject object : objects) {

            // Get the intersect distance
            Intersection i = object.getIntersection(ray);

            // Ensure that there is actually an intersection
            if (i != null) {

                // Set if smallest
                if (smallest == null || i.distance < smallest.distance) {
                    smallest = i;
                }
            }
        }

        // Return the closest intersection
        return smallest;
    }

    /**
     * Render the scene to an image
     * 
     * @return Rendered image
     */
    public BufferedImage render() {

        // Create the render frame
        BufferedImage frame = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);

        // Draw all pixels to frame
        for (int x = 0; x < frame.getWidth(); x++) {
            for (int y = 0; y < frame.getHeight(); y++) {

                // Create ray for pixel
                Ray ray = Ray.prime(x, y, this);
                Intersection i = follow(ray);

                // Check each scene object
                // for (SceneObject object : objects) {

                // Check for intersection
                if (i != null) {

                    // Set the pixel value
                    frame.setRGB(x, y, new Color(Math.round(i.object.color.x * 254), Math.round(i.object.color.y * 254),
                            Math.round(i.object.color.z * 254)).getRGB());

                    // System.out.println("PX");
                } else {
                    frame.setRGB(x, y, Color.black.getRGB());
                }

                // }

            }

        }

        // Return the rendered frame
        return frame;
    }

}