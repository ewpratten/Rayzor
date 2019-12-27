package ca.retrylife.ics4u.rayzor;

import javax.imageio.ImageIO;
import javax.vecmath.Color3f;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import ca.retrylife.ics4u.rayzor.geometry.Vector3;
import ca.retrylife.ics4u.rayzor.lighting.DirectionalLight;
import ca.retrylife.ics4u.rayzor.lighting.SphericalLight;
import ca.retrylife.ics4u.rayzor.objects.Plane;
import ca.retrylife.ics4u.rayzor.objects.Sphere;
import ca.retrylife.ics4u.rayzor.textures.Material;

public class App implements Runnable {

    Scene scene;

    public static void main(String[] args) {

        // Build an app
        App app = new App();

        // Run the app
        app.run();
    }

    private App() {

        // Create a scene

        // Default: 800 x 600
        // 1920 x 1080
        // 4K
        // 16K: 15360 x 8640
        scene = new Scene(new Dimension(15360, 8640), 95.0);

        // Add objects to scene
        scene.addObject(new Plane(new Vector3(-9.0, 0.0, 0.0), new Vector3(-0.5, 0.0, 0.0),
                new Material(new Color3f(0.3f, 0.2f, 0.4f), 0.5, true)));
        scene.addObject(new Plane(new Vector3(0.0, 0.0, -20.0), new Vector3(0.0, 0.0, -1.0),
                new Material(new Color3f(0.6f, 0.8f, 1.0f), 0.18)));
        
        scene.addObject(new Plane(new Vector3(0.0, -2.0, 0.0), new Vector3(0.0, -1.0, 0.0),
                new Material(new Color3f(0.2f, 0.2f, 0.2f), 0.2, true)));
        scene.addObject(
                new Sphere(new Vector3(2.0, 2.0, -4.0), 2.25, new Material(new Color3f(1.0f, 0.2f, 0.2f), 0.08, true)));
        scene.addObject(
                new Sphere(new Vector3(-3.0, 1.0, -6.0), 2.0, new Material(new Color3f(0.2f, 0.2f, 1.0f), 3.0, true)));
        scene.addObject(
                new Sphere(new Vector3(0.0, 0.0, -5.0), 1.0, new Material(new Color3f(0.2f, 1.0f, .2f), 0.2, true)));

        // Add lights to scene
        scene.addLight(new DirectionalLight(new Vector3(-0.25, -1.0, -1.0), new Color3f(1.0f, 1.0f, 1.0f), 0.3));
        scene.addLight(new SphericalLight(new Vector3(0.25, 0.0, 0.0), new Color3f(0.8f, 0.2f, 0.3f), 3000.0));
        scene.addLight(new SphericalLight(new Vector3(-2.0, 10.0, -3.0), new Color3f(1.0f, 1.0f, 1.0f), 30000.0));

    }

    @Override
    public void run() {

        // Render the scene
        BufferedImage frame = scene.render();

        // Save the frame

        try {
            ImageIO.write(frame, "png", new File("output.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
