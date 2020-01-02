package ca.retrylife.ics4u.rayzor;

import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Color;

import java.util.ArrayList;
import java.util.Arrays;

import ca.retrylife.ics4u.rayzor.geometry.Intersection;
import ca.retrylife.ics4u.rayzor.lighting.Light;
import ca.retrylife.ics4u.rayzor.lighting.Ray;
import ca.retrylife.ics4u.rayzor.objects.SceneObject;
import ca.retrylife.libvec.Vector3;
import ca.retrylife.libvec.Color3;

/**
 * A renderable scene
 */
public class Scene {

    // Constants
    public final double SHADOW_BIAS = 1e-12;
    public final int MAX_DEPTH = 10;

    // Scene sizing
    public Dimension size;

    // Rendering camera
    public Camera camera;

    // Scene objects
    ArrayList<SceneObject> objects = new ArrayList<>();
    ArrayList<Light> lights = new ArrayList<>();

    /**
     * Create a scene of a specific size with pre-defined objects
     * 
     * @param size    Scene size
     * @param cam     Scene Camera
     * @param lights  Scene light sources
     * @param objects Renderable objects for scene
     */
    public Scene(Dimension size, Camera cam, Light[] lights, SceneObject... objects) {
        this(size, cam);

        // Add all objects
        this.objects.addAll(Arrays.asList(objects));
        this.lights.addAll(Arrays.asList(lights));

    }

    /**
     * Create a scene of a specific size with pre-defined objects
     * 
     * @param size    Scene size
     * @param cam     Scene Camera
     * @param objects Renderable objects for scene
     */
    public Scene(Dimension size, Camera cam, SceneObject... objects) {
        this(size, cam);

        // Add all objects
        this.objects.addAll(Arrays.asList(objects));

    }

    /**
     * Create a scene of a specific size
     * 
     * @param size Scene size
     * @param cam  Scene Camera
     */
    public Scene(Dimension size, Camera cam) {

        // Set locals
        this.size = size;
        this.camera = cam;

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
     * Add a light to the scene
     * 
     * @param light Scene light
     */
    public void addLight(Light light) {
        lights.add(light);
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
     * Calculate the color for a ray intersection
     * 
     * @param ray          Ray
     * @param intersection Ray intersection
     * @return Calculated color
     */
    public Color3 getColor(Ray ray, Intersection intersection) {

        Vector3 output = new Vector3(0.0f, 0.0f, 0.0f);

        // Calculate each scene light
        for (Light light : lights) {

            // Calculate the light's color
            Vector3 lightColor = light.castRay(this, ray, 0);

            // Accumulate the color
            output = Color3.add(output, lightColor);

        }

        // Clamp the color
        output.clamp(0.0, 1.0);

        return new Color3((float) output.x, (float) output.y, (float) output.z);
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
                Intersection intersection = follow(ray);

                // Check for intersection
                if (intersection != null) {

                    // Calculate the color for the ray
                    Color color = getColor(ray, intersection).toColor();

                    // Set the pixel value
                    frame.setRGB(x, y, color.getRGB());

                } else {
                    frame.setRGB(x, y, Color.black.getRGB());
                }

            }

        }

        // Return the rendered frame
        return frame;
    }

}