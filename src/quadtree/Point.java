package quadtree;

import com.revivedstandards.model.StandardGameObject;
import com.revivedstandards.model.StandardID;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * This class represents a simple 2D point in the QuadTree plane.
 *
 * @author Joshua Crotts
 * @date 4/5/2020
 */
public class Point extends StandardGameObject {

    private final int SIZE = 3;
    private Color color;

    public Point(int x, int y) {
        super(x, y, StandardID.Object);
        this.setWidth(SIZE);
        this.setHeight(SIZE);
        this.color = Color.WHITE;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.fillOval((int) this.getX(), (int) this.getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }

    public void setColor(Color c) {
        this.color = c;
    }
}
