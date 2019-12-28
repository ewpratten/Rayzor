package ca.retrylife.ics4u.rayzor;

import javax.imageio.ImageIO;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import ca.retrylife.ics4u.rayzor.scenes.Balls;
import ca.retrylife.ics4u.rayzor.scenes.BoxTest;
import ca.retrylife.ics4u.rayzor.scenes.Cornell;
import ca.retrylife.ics4u.rayzor.scenes.IScene;
import ca.retrylife.ics4u.rayzor.scenes.Rubik;

public class App implements Runnable {

    Scene scene;

    // Balls world
    IScene world = new Balls();

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
        scene = new Scene(new Dimension(800, 600), 95.0);

        // Build the scene
        world.build(scene);
        

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
