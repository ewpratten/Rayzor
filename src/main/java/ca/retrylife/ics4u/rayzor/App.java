package ca.retrylife.ics4u.rayzor;

import javax.imageio.ImageIO;
import javax.vecmath.Color3f;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import ca.retrylife.ics4u.rayzor.geometry.Vector3;
import ca.retrylife.ics4u.rayzor.objects.Sphere;

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
        scene = new Scene(new Dimension(800, 600), 90.0);

        // Add a sphere to the scene to be rendered
        Sphere s = new Sphere(new Vector3(0.0, 0.0, -5.0), 1.0, new Color3f(.4f, 1.f, .4f));
        scene.addObject(s);

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
