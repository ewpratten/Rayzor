package ca.retrylife.ics4u.rayzor;

import org.junit.Test;

import ca.retrylife.ics4u.rayzor.objects.Sphere;
import ca.retrylife.ics4u.rayzor.textures.Material;
import ca.retrylife.libvec.Color3;
import ca.retrylife.libvec.Vector3;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.image.BufferedImage;


/**
 * Tests for Scene
 */
public class SceneTest {

    @Test
    public void testCanRender() {
        // Build a scene to render
        Scene scene = new Scene(new Dimension(800, 600), new Camera(0.0, 0.0, 0.0, 90.0));

        // Add a sphere to the scene to be rendered
        Sphere s = new Sphere(new Vector3(0.0, 0.0, -5.0), 1.0, new Material(new Color3(.4f, 1.f, .4f), 0.18));
        scene.addObject(s);

        // Render the scene to an image
        BufferedImage img = scene.render();

        // Assert outputs
        assert scene.size.getWidth() == img.getWidth();
        assert scene.size.getHeight() == img.getHeight();

    }
}