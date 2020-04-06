/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

import com.revivedstandards.main.StandardDraw;
import com.revivedstandards.model.StandardGameObject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * This is the base QuadTree class. As points are added to the ArrayList of
 * points, the QT will recursively create four children.
 *
 * @author Joshua Crotts
 * @date 4/5/2020
 */
public class QuadTree extends StandardGameObject {

    private final ArrayList<Point> points;

    private QuadTree topLeft = null;
    private QuadTree topRight = null;
    private QuadTree bottomLeft = null;
    private QuadTree bottomRight = null;

    private final Rectangle bounds;

    private final int capacity;

    private boolean subdivided = false;

    public QuadTree(int capacity, Rectangle bounds) {
        this.capacity = capacity;
        this.points = new ArrayList<>();
        this.bounds = bounds;
    }

    @Override
    public void tick() {
    }

    /**
     * Creates four rectangular children in the top-left, top-right,
     * bottom-left, and bottom-right of the current quadtree.
     *
     * The x and y coordinates represent the center of the current QT's bounds.
     */
    public void subdivide() {
        int x = (int) this.bounds.getCenterX();
        int y = (int) this.bounds.getCenterY();
        int w = this.bounds.width;
        int h = this.bounds.height;

        this.topLeft = new QuadTree(this.capacity, new Rectangle(x - w / 2, y - h / 2, w / 2, h / 2));
        this.topRight = new QuadTree(this.capacity, new Rectangle(x, y - h / 2, w / 2, h / 2));
        this.bottomLeft = new QuadTree(this.capacity, new Rectangle(x - w / 2, y, w / 2, h / 2));
        this.bottomRight = new QuadTree(this.capacity, new Rectangle(x, y, w / 2, h / 2));

    }

    /**
     * Attempts to insert a Point P into the QuadTree.
     *
     * If the two do not intersect, we don't try to insert it (as that's not the
     * correct quadrant). If there is no point in the quadrant where we want to
     * insert, we go ahead and place it there. However, if one is already there,
     * we subdivide that quadrant, then recursively place the point there (in
     * each of the four quadrants).
     *
     * @param p Point
     */
    public void insert(Point p) {
        if (!this.bounds.contains(p.getBounds())) {
            return;
        }

        if (this.points.size() < this.capacity) {
            this.points.add(p);
        } else {
            if (!this.subdivided) {
                this.subdivide();
                this.subdivided = true;
            }

            this.topLeft.insert(p);
            this.topRight.insert(p);
            this.bottomLeft.insert(p);
            this.bottomRight.insert(p);
        }
    }

    @Override
    public void render(Graphics2D g2) {
        // If we haven't subdivided, don't bother rendering the 
        // other quads as they don't exist.
        if (this.subdivided) {
            this.topLeft.render(g2);
            this.topRight.render(g2);
            this.bottomLeft.render(g2);
            this.bottomRight.render(g2);
        }

        g2.setColor(Color.RED);
        g2.draw(this.bounds);

        //  Iterate through the points and draw them.
        for (int i = 0; i < this.points.size(); i++) {
            this.points.get(i).render(StandardDraw.Renderer);
        }
    }
}
