package ca.retrylife.ics4u.rayzor;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import ca.quarkphysics.hsa2.GraphicsConsole;
import ca.retrylife.ics4u.rayzor.scenes.Balls;
import ca.retrylife.ics4u.rayzor.scenes.IScene;

public class RTApp implements Runnable {

    /* Constants */
    private static final int WINW = 300;
    private static final int WINH = 200;

    // World scene
    Scene scene;

    // Graphics
    GraphicsConsole gc;
    double lastTime = 0;

    // World setting
    // This can be switched out with any class that implements IScene.
    IScene world = new Balls();

    public static void main(String[] args) {

        // Build an app
        RTApp app = new RTApp();

        // Run the app
        app.run();
    }

    private RTApp() {

        // Create a scene
        scene = new Scene(new Dimension(WINW, WINH), new Camera(75.0));

        // Build the scene
        world.build(scene);

        // Create a graphics console
        gc = new GraphicsConsole("Rayzor");

        // Configure the console
        gc.setSize(WINW, WINH);
        gc.setLocationRelativeTo(null);
        gc.setResizable(false);

    }

    @Override
    public void run() {

        while (true) {
            // Render the scene
            BufferedImage frame = scene.render();

            // Draw the frame to the screen
            gc.drawImage(frame, 0, 0);

            // Calculate FPS
            double fps = 1000000.0 / (lastTime - (lastTime = System.nanoTime()));

            gc.drawString(String.format("%.6f", fps), 10, 10);

            // Some basic camera movement
            char key = gc.getKeyChar();

            switch (key) {
            case 'w':
                scene.camera.z -= 0.1;
                break;
            case 'a':
                scene.camera.x -= 0.1;
                break;
            case 'd':
                scene.camera.x += 0.1;
                break;
            case 's':
                scene.camera.z += 0.1;
                break;
            case 'e':
                scene.camera.y += 0.1;
                break;
            case 'q':
                scene.camera.y -= 0.1;
                break;
            }
        }

    }

}
