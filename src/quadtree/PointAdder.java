package quadtree;

import com.revivedstandards.input.Mouse;
import com.revivedstandards.main.StandardDraw;
import com.revivedstandards.util.StdOps;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * This class is the MouseMotionListener and MouseListener companion class that
 * allows for dragging the mouse across the screen to add points to the current
 * QuadTree.
 *
 * @author Joshua Crotts
 * @date 4/5/2020
 */
public class PointAdder extends Mouse {

    private final QuadTree tree;

    private boolean rightClicked = false;
    private int oldX;
    private int oldY;

    public PointAdder(QuadTree tree) {
        this.tree = tree;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON3) {
            if (!this.rightClicked) {
                this.rightClicked = true;
                this.oldX = e.getX();
                this.oldY = e.getY();
            } else {

                Rectangle queryRect = new Rectangle(oldX, oldY, Math.abs(e.getX() - oldX), Math.abs(e.getY() - oldY));
                ArrayList<Point> queryPoints = this.tree.fastQuery(queryRect, new ArrayList<>());
                //ArrayList<Point> queryPoints = this.tree.slowQuery(queryRect);

                Color c = StandardDraw.getRandomColor();
                
                for (Point p : queryPoints) {
                    p.setColor(c);
                }

                this.rightClicked = false;
            }
        } else {
            for (int i = 0; i < 100000; i++) {
                Point p = new Point(e.getX() + StdOps.rand(-5, 5), e.getY() + StdOps.rand(-5, 5));
                this.tree.insert(p);
                QuadTree.allPoints.add(p);
            }
        }

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
