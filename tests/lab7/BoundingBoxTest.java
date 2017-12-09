package lab7;

import org.junit.jupiter.api.Test;

import static app.Helpers.tap;
import static org.junit.jupiter.api.Assertions.*;

class BoundingBoxTest {
    private BoundingBox boundingBox(Double xmin, Double ymin, Double xmax, Double ymax) {
        return tap(new BoundingBox(), box -> {
            box.xmin = xmin;
            box.ymin = ymin;
            box.xmax = xmax;
            box.ymax = ymax;
        });
    }

    @Test
    void test_intersects_works_on_intersecting_boxes() {
        assertTrue(boundingBox(-4D, -3D, -2D, -1D).intersects(boundingBox(-3D, -2D, -1D, 0D))); // top-right
        assertTrue(boundingBox(-4D, -3D, -2D, -1D).intersects(boundingBox(-3D, -4D, -1D, -2D))); // bottom-right
        assertTrue(boundingBox(-4D, -3D, -2D, -1D).intersects(boundingBox(-5D, -4D, -3D, -2D))); // bottom-left
        assertTrue(boundingBox(-4D, -3D, -2D, -1D).intersects(boundingBox(-5D, 0D, -3D, -2D))); // top-left

        assertTrue(boundingBox(-4D, -4D, -1D, -1D).intersects(boundingBox(-3D, -1D, -2D, 1D))); // top
        assertTrue(boundingBox(-4D, -4D, -1D, -1D).intersects(boundingBox(-2D, -3D, 1D, -2D))); // right
        assertTrue(boundingBox(-4D, -4D, -1D, -1D).intersects(boundingBox(-3D, -5D, -2D, -3D))); // bottom
        assertTrue(boundingBox(-4D, -4D, -1D, -1D).intersects(boundingBox(-6D, -3D, -3D, -2D))); // left
    }

    @Test
    void test_intersects_works_on_corner_touching_boxes() {
        assertTrue(boundingBox(-3D, -2D, -2D, 3D).intersects(boundingBox(-2D, 3D, -1D, 4D))); // top-right
        assertTrue(boundingBox(-3D, -2D, -2D, 3D).intersects(boundingBox(-2D, 1D, -1D, 2D))); // bottom-right
        assertTrue(boundingBox(-3D, -2D, -2D, 3D).intersects(boundingBox(-4D, 1D, -3D, 2D))); // bottom-left
        assertTrue(boundingBox(-3D, -2D, -2D, 3D).intersects(boundingBox(-4D, 3D, -3D, 4D))); // top-left
    }

    @Test
    void test_intersects_does_not_work_on_not_intersecting_boxes() {
        assertFalse(boundingBox(-4D, -3D, -2D, -1D).intersects(boundingBox(10D, 10D, 18D, 18D))); // top-right
        assertFalse(boundingBox(-4D, -3D, -2D, -1D).intersects(boundingBox(10D, -10D, 18D, -8D))); // bottom-right
        assertFalse(boundingBox(-4D, -3D, -2D, -1D).intersects(boundingBox(-10D, -10D, -8D, -8D))); // bottom-left
        assertFalse(boundingBox(-4D, -3D, -2D, -1D).intersects(boundingBox(-10D, 10D, -8D, 18D))); // top-left
    }
}
