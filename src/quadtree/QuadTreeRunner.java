package quadtree;

import com.revivedstandards.main.StandardDraw;
import com.revivedstandards.main.StandardGame;
import java.awt.Rectangle;

/**
 * This class is the driver class for initializing the first QuadTree so its
 * children are recursively constructed as points are added.
 *
 * @author Joshua Crotts
 * @date 4/5/2020
 */
public class QuadTreeRunner extends StandardGame {

    private final QuadTree quadTree;
    private final PointAdder pointAdder;

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 800;

    public QuadTreeRunner() {
        super(WINDOW_WIDTH, WINDOW_HEIGHT, "QuadTree");
        this.quadTree = new QuadTree(1, new Rectangle(0, 0, this.getGameWidth(), this.getGameHeight()));
        this.pointAdder = new PointAdder(quadTree);
        super.addMouseListener(this.pointAdder);
        super.addMouseMotionListener(this.pointAdder);
        this.startGame();
    }

    @Override
    public void tick() {
        this.quadTree.tick();
    }

    @Override
    public void render() {
        this.quadTree.render(StandardDraw.Renderer);
    }

    public static void main(String[] args) {
        QuadTreeRunner tree = new QuadTreeRunner();
    }
}
