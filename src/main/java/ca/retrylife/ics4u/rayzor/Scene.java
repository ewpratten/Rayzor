package ca.retrylife.ics4u.rayzor;

import java.awt.image.BufferedImage;
import java.awt.Dimension;

import java.util.ArrayList;
import java.util.Arrays;

import ca.retrylife.ics4u.rayzor.objects.Sphere;

/**
 * A renderable scene
 */
public class Scene {

    // Scene sizing
    public Dimension size;
    public double fov;

    // Scene objects
    ArrayList<Sphere> objects = new ArrayList<>();

    /**
     * Create a scene of a specific size with pre-defined objects
     * 
     * @param size    Scene size
     * @param fov     Scene FOV
     * @param objects Renderable objects for scene
     */
    public Scene(Dimension size, double fov, Sphere... objects) {
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
    public void addObject(Sphere obj) {
        objects.add(obj);
    }

    public BufferedImage render() {
        return new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
    }

}