package ca.retrylife.ics4u.rayzor.scenes;


import ca.retrylife.ics4u.rayzor.Scene;
import ca.retrylife.ics4u.rayzor.lighting.DirectionalLight;
import ca.retrylife.ics4u.rayzor.lighting.SphericalLight;
import ca.retrylife.ics4u.rayzor.objects.Cube;
import ca.retrylife.ics4u.rayzor.objects.Plane;
import ca.retrylife.ics4u.rayzor.objects.Sphere;
import ca.retrylife.ics4u.rayzor.textures.Material;
import ca.retrylife.libvec.Color3;
import ca.retrylife.libvec.Vector3;

/**
 * "Balls" is a demonstration of reflectivity with spheres and planes
 */
public class BoxTest implements IScene {

	@Override
	public void build(Scene scene) {

		/* Add objects to scene */

		// World "box"
		scene.addObject(new Plane(new Vector3(-9.0, 0.0, 0.0), new Vector3(-0.5, 0.0, 0.0),
				new Material(new Color3(0.3f, 0.2f, 0.4f), 0.5, true)));
		scene.addObject(new Plane(new Vector3(0.0, 0.0, -20.0), new Vector3(0.0, 0.0, -1.0),
				new Material(new Color3(0.6f, 0.8f, 1.0f), 0.18)));
		scene.addObject(new Plane(new Vector3(0.0, -2.0, 0.0), new Vector3(0.0, -1.0, 0.0),
				new Material(new Color3(0.2f, 0.2f, 0.2f), 0.2, true)));

		// Balls
		scene.addObject(
				new Sphere(new Vector3(2.0, 2.0, -4.0), 2.25, new Material(new Color3(1.0f, 0.2f, 0.2f), 0.08, true)));
		scene.addObject(
				new Sphere(new Vector3(-3.0, 1.0, -6.0), 2.0, new Material(new Color3(0.2f, 0.2f, 1.0f), 3.0, true)));
		scene.addObject(
				new Sphere(new Vector3(0.0, 0.0, -5.0), 1.0, new Material(new Color3(0.2f, 1.0f, .2f), 0.2, true)));
		scene.addObject(
				new Sphere(new Vector3(-1.0, 0.0, -4.0), 0.5, new Material(new Color3(0.2f, 0.4f, .2f), 1, true)));

		// Boxes
		scene.addObject(new Cube(new Vector3(-1.0, -1.0, -3.0), 0.5, new Material(new Color3(1.0f, 1.0f, 1.0f), 0.01, false)));

		/* Add lights to scene */
		scene.addLight(new DirectionalLight(new Vector3(-0.25, -1.0, -1.0), new Color3(1.0f, 1.0f, 1.0f), 0.3));
		scene.addLight(new SphericalLight(new Vector3(0.25, 0.0, 0.0), new Color3(0.8f, 0.2f, 0.3f), 3000.0));
		scene.addLight(new SphericalLight(new Vector3(-2.0, 10.0, -3.0), new Color3(1.0f, 1.0f, 1.0f), 30000.0));

	}

}