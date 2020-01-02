package ca.retrylife.ics4u.rayzor;

import ca.retrylife.libvec.Vector3;

public class Camera extends Vector3 {

    /**
     * Camera Field Of View
     */
    public double fov;

    /**
     * Create a camera at [0,0,0]
     * @param fov Camera FOV
     */
    public Camera(double fov) {
        this(0,0,0, fov);
    }

    /**
     * Create a camera at a specific position
     * 
     * @param x   X pos
     * @param y   Y pos
     * @param z   Z pos
     * @param fov Camera FOV
     */
    public Camera(double x, double y, double z, double fov) {
        super(x, y, z);

        this.fov = fov;
    }

}