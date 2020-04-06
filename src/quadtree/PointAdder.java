package quadtree;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * This class is the MouseMotionListener and MouseListener companion class that
 * allows for dragging the mouse across the screen to add points to the current
 * QuadTree.
 *
 * @author Joshua Crotts
 * @date 4/5/2020
 */
public class PointAdder implements MouseListener, MouseMotionListener {

    private final QuadTree tree;

    public PointAdder(QuadTree tree) {
        this.tree = tree;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.tree.insert(new Point(e.getX(), e.getY()));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mousePressed(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
