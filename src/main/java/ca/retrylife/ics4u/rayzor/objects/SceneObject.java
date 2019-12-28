package ca.retrylife.ics4u.rayzor.objects;


import ca.retrylife.ics4u.rayzor.interfaces.Intersectable;
import ca.retrylife.ics4u.rayzor.textures.Material;
import ca.retrylife.libvec.Vector3;

/**
 * A scene object
 */
public abstract class SceneObject  implements Intersectable {

    /**
     * Object material
     */
    public Material material;

    /**
     * Get the surface normal from a hit point
     * 
     * @param hitPoint Point of intersection
     * @return Normal
     */
    public abstract Vector3 getSurfaceNormal(Vector3 hitPoint);

}