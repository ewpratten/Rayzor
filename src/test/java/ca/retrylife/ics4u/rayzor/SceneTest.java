package ca.retrylife.ics4u.rayzor;

import org.junit.Test;

import ca.retrylife.ics4u.rayzor.geometry.Vector3;
import ca.retrylife.ics4u.rayzor.objects.Sphere;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.vecmath.Color3f;

/**
 * Tests for Scene
 */
public class SceneTest {

    @Test
    public void testCanRender() {
        // Build a scene to render
        Scene scene = new Scene(new Dimension(800, 600), 90.0);

        // Add a sphere to the scene to be rendered
        Sphere s = new Sphere(new Vector3(0.0, 0.0, -5.0), 1.0, new Color3f(.4f, 1.f, .4f), 0.18);
        scene.addObject(s);

        // Render the scene to an image
        BufferedImage img = scene.render();

        // Assert outputs
        assert scene.size.getWidth() == img.getWidth();
        assert scene.size.getHeight() == img.getHeight();

    }
}