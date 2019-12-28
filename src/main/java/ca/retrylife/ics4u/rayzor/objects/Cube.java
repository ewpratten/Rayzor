package ca.retrylife.ics4u.rayzor.objects;

import javax.vecmath.Point2f;

import ca.retrylife.ics4u.rayzor.geometry.Intersection;
import ca.retrylife.ics4u.rayzor.geometry.Vector3;
import ca.retrylife.ics4u.rayzor.lighting.Ray;
import ca.retrylife.ics4u.rayzor.textures.Material;

/**
 * A cube object
 */
public class Cube extends SceneObject {

    public Vector3 centre;
    public double width;
    public Vector3 size;

    public Vector3 min;
    public Vector3 max;

    /**
     * Create a cube
     * 
     * @param centre Cube centre
     * @param width  Cube width (diagonal)
     * @param mat    Cube material
     */
    public Cube(Vector3 centre, double width, Material mat) {
        this.centre = centre;
        this.width = width;
        this.material = mat;

        // Find half diag width
        Vector3 halfWidth = Vector3.fromOne(width / 2);

        // Set cube coords
        this.min = Vector3.sub(centre, halfWidth);
        this.max = Vector3.add(centre, halfWidth);

        // Set cubs size
        this.size = Vector3.sub(max, min);

    }

    @Override
    public Intersection getIntersection(Ray ray) {
        double tmin = (min.x - ray.origin.x) / ray.direction.x;
        double tmax = (max.x - ray.origin.x) / ray.direction.x;

        if (tmin > tmax) {
            // swap(tmin, tmax);

            double t = tmin;
            tmin = tmax;
            tmax = t;
        }

        double tymin = (min.y - ray.origin.y) / ray.direction.y;
        double tymax = (max.y - ray.origin.y) / ray.direction.y;

        if (tymin > tymax) {
            // swap(tymin, tymax);

            double t = tymin;
            tymin = tymax;
            tymax = t;
        }

        if ((tmin > tymax) || (tymin > tmax)) {
            return null;
        }

        if (tymin > tmin) {
            tmin = tymin;
        }

        if (tymax < tmax) {
            tmax = tymax;
        }

        double tzmin = (min.z - ray.origin.z) / ray.direction.z;
        double tzmax = (max.z - ray.origin.z) / ray.direction.z;

        if (tzmin > tzmax) {
            // swap(tzmin, tzmax);

            double t = tzmin;
            tzmin = tzmax;
            tzmax = t;
        }

        if ((tmin > tzmax) || (tzmin > tmax)) {
            return null;
        }

        if (tzmin > tmin) {
            tmin = tzmin;
        }

        if (tzmax < tmax) {
            tmax = tzmax;
        }

        return new Intersection(tmin, this);
    }

    @Override
    public Point2f getTextureCoords(Vector3 hitPoint) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector3 getSurfaceNormal(Vector3 hitPoint) {

        // Vector3 c = Vector3.mul(Vector3.add(min, max), 1.0);
        // Vector3 p = Vector3.sub(hitPoint, c);
        // Vector3 d = Vector3.mul(Vector3.sub(min, max), 1.0);
        // double bias = 0.00001;

        // return new Vector3((double)(p.x / Math.abs(d.x) * bias), (double)(p.y /
        // Math.abs(d.y) * bias), (double)(p.z / Math.abs(d.z) * bias)).normalize();

        Vector3 normal = Vector3.zero();
        Vector3 localPoint = Vector3.sub(hitPoint, centre);
        double min = Double.POSITIVE_INFINITY;
        double distance = Math.abs(size.x - Math.abs(localPoint.x));

        if (distance < min) {
            min = distance;
            normal = new Vector3(1.0, 0.0, 0.0);
            normal = Vector3.mul(normal, Math.copySign(1, localPoint.x));
        }
        distance = Math.abs(size.y - Math.abs(localPoint.y));
        if (distance < min) {
            min = distance;
            normal = new Vector3(0.0, 1.0, 0.0);
            normal = Vector3.mul(normal, Math.copySign(1, localPoint.y));
        }
        distance = Math.abs(size.z - Math.abs(localPoint.z));
        if (distance < min) {
            min = distance;
            normal = new Vector3(0.0, 0.0, 1.0);
            normal = Vector3.mul(normal, Math.copySign(1, localPoint.z));
        }
        return normal.normalize();
    }

}