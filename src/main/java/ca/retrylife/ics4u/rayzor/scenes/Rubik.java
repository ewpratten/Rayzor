package ca.retrylife.ics4u.rayzor.scenes;

import java.awt.Color;


import ca.retrylife.ics4u.rayzor.Scene;
import ca.retrylife.ics4u.rayzor.lighting.DirectionalLight;
import ca.retrylife.ics4u.rayzor.lighting.SphericalLight;
import ca.retrylife.ics4u.rayzor.objects.Cube;
import ca.retrylife.ics4u.rayzor.objects.Plane;
import ca.retrylife.ics4u.rayzor.objects.Sphere;
import ca.retrylife.ics4u.rayzor.textures.Material;
import ca.retrylife.libvec.Color3;
import ca.retrylife.libvec.Vector3;

public class Rubik implements IScene {

    @Override
    public void build(Scene scene) {

        /* Add scene objects */

        // BG
        scene.addObject(new Plane(new Vector3(0.0, 0.0, -20.0), new Vector3(0.0, 0.0, -1.0),
                new Material(new Color3(0.6f, 0.8f, 1.0f), 0.18)));
        scene.addObject(new Plane(new Vector3(0.0, -8.0, 0.0), new Vector3(0.0, -1.0, 0.0),
                new Material(new Color3(0.2f, 0.2f, 0.2f), 0.2, true)));

        // Top front
        scene.addObject(
                new Cube(new Vector3(-2.5, -1.0, -10.0), 2.0, new Material(new Color3(1.0f, 0.0f, 0.0f), 0.5, false)));
        scene.addObject(
                new Cube(new Vector3(0.0, -1.0, -10.0), 2.0, new Material(new Color3(1.0f, 0.0f, 0.0f), 0.5, false)));
        scene.addObject(
                new Cube(new Vector3( 2.5, -1.0, -10.0), 2.0, new Material(new Color3(1.0f, 0.0f, 0.0f), 0.5, false)));
        
        // Mid front
        scene.addObject(
                new Cube(new Vector3(-2.5, -3.5, -10.0), 2.0, new Material(new Color3(1.0f, 0.0f, 0.0f), 0.5, false)));
        scene.addObject(
                new Cube(new Vector3(0.0, -3.5, -10.0), 2.0, new Material(new Color3(1.0f, 0.0f, 0.0f), 0.5, false)));
        scene.addObject(
                new Cube(new Vector3( 2.5, -3.5, -10.0), 2.0, new Material(new Color3(1.0f, 0.0f, 0.0f), 0.5, false)));

        // Bottom front
        scene.addObject(
                new Cube(new Vector3(-2.5, -6.0, -10.0), 2.0, new Material(new Color3(1.0f, 0.0f, 0.0f), 0.5, false)));
        scene.addObject(
                new Cube(new Vector3(0.0, -6.0, -10.0), 2.0, new Material(new Color3(1.0f, 0.0f, 0.0f), 0.5, false)));
        scene.addObject(
                new Cube(new Vector3( 2.5, -6.0, -10.0), 2.0, new Material(new Color3(1.0f, 0.0f, 0.0f), 0.5, false)));

        /* Add scene lighting */
        // scene.addLight(new DirectionalLight(new Vector3(0.0, 0.0, -0.0), new Color3(Color.WHITE), 100));
        // scene.addLight(new DirectionalLight(new Vector3(-1.0, 0.0, 0.0), new Color3(Color.WHITE), 10));
        // scene.addLight(new SphericalLight(new Vector3(0.0, 0.0, -0.5), new Color3(Color.WHITE), 100));

        scene.addLight(new DirectionalLight(new Vector3(-0.25, -1.0, -1.0), new Color3(1.0f, 1.0f, 1.0f), 80));
        scene.addLight(new SphericalLight(new Vector3(0.25, 0.0, 0.0), new Color3(0.8f, 0.2f, 0.3f), 3000.0));
        scene.addLight(new SphericalLight(new Vector3(-2.0, 10.0, -3.0), new Color3(1.0f, 1.0f, 1.0f), 30000.0));

        scene.addLight(new SphericalLight(new Vector3(1.0, 5.0, -3.0), new Color3(1.0f, 1.0f, 1.0f), 30000.0));

    }

}