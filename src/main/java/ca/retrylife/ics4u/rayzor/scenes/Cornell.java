package ca.retrylife.ics4u.rayzor.scenes;

import java.awt.Color;


import ca.retrylife.ics4u.rayzor.Scene;
import ca.retrylife.ics4u.rayzor.lighting.DirectionalLight;
import ca.retrylife.ics4u.rayzor.lighting.SphericalLight;
import ca.retrylife.ics4u.rayzor.objects.Plane;
import ca.retrylife.ics4u.rayzor.objects.Sphere;
import ca.retrylife.ics4u.rayzor.textures.Material;
import ca.retrylife.libvec.Color3;
import ca.retrylife.libvec.Vector3;

public class Cornell implements IScene {

    private void buildBox(Scene scene) {

        // Top and bottom
        scene.addObject(new Plane(new Vector3(0.0, -10.0, 0.0), new Vector3(0.0, -1.0, 0.0),
                new Material(new Color3(0.2f, 0.2f, 0.2f), 3.0)));
        // scene.addObject(new Plane(new Vector3(0.0, 10.0, 0.0), new Vector3(0.0, 1.0, 0.0),
        //         new Material(new Color3(0.2f, 0.2f, 0.2f), 3.0)));

        // Left and right
        // scene.addObject(new Plane(new Vector3(-10.0, 0.0, 0.0), new Vector3(-1.0, 0.0, 0.0),
        //         new Material(new Color3(1.0f, 0.2f, 0.2f), 0.8)));
        // scene.addObject(new Plane(new Vector3(10.0, 0.0, 0.0), new Vector3(1.0, 0.0, 0.0),
        //         new Material(new Color3(0.2f, 1.0f, 0.2f), 0.8)));

        // // Back and front
        // scene.addObject(new Plane(new Vector3(0.0, 0.0, -20.0), new Vector3(0.0, 0.0, -1.0),
        //         new Material(new Color3(0.2f, 0.2f, 0.2f), 1.0)));
        // scene.addObject(new Plane(new Vector3(0.0, 0.0, 1.0), new Vector3(0.0, 0.0, 1.0),
        //         new Material(new Color3(0.2f, 0.2f, 1.0f), 1.0)));

    }

    private void addObjects(Scene scene) {

        scene.addObject(
                new Sphere(new Vector3(-3.0, -4.0, -12.0), 3.0, new Material(new Color3(Color.WHITE), 1.0, true)));
        scene.addObject(
                new Sphere(new Vector3(1.0, -2.0, -10.0), 1.0, new Material(new Color3(0.8f, 0.1f, 0.1f), 0.8, false)));

    }

    private void addLighting(Scene scene) {
        // scene.addLight(new SphericalLight(new Vector3(0.0, 8.0, -8.0), new Color3(0.6f, 0.6f, 0.6f), 20000));

        scene.addLight(new DirectionalLight(new Vector3(-0.25, -1.0, -1.0), new Color3(1.0f, 1.0f, 1.0f), 1.0));
        // scene.addLight(new SphericalLight(new Vector3(0.25, 0.0, 0.0), new
        // Color3(0.8f, 0.2f, 0.3f), 3000.0));
        // scene.addLight(new SphericalLight(new Vector3(-2.0, 10.0, -3.0), new
        // Color3(1.0f, 1.0f, 1.0f), 30000.0));

    }

    @Override
    public void build(Scene scene) {

        buildBox(scene);

        addObjects(scene);

        addLighting(scene);

    }

}