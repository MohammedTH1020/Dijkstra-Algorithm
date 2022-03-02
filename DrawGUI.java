import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DrawGUI {

    public final static int CIRCLE_RADIUS = 10;

    public static StackPane getNode(String nodeName, double x, double y) {
        Text text = new Text(nodeName);
        text.setFill(Color.WHITE);
        text.setFont(Font.font(20));

        Circle circle = new Circle();
        circle.setRadius(CIRCLE_RADIUS);
        circle.setStroke(Color.BLACK);

        StackPane stack = new StackPane();
        stack.setLayoutX(x);
        stack.setLayoutY(y);
        stack.getChildren().addAll(circle, text);
        return stack;
    }

    public static EdgeGUI getLine(StackPane node1, StackPane node2, int weight) {
        Line line12 = new Line();
        line12.setStartX(node1.getLayoutX() + CIRCLE_RADIUS);
        line12.setStartY(node1.getLayoutY() + CIRCLE_RADIUS);
        line12.setEndX(node2.getLayoutX() + CIRCLE_RADIUS);
        line12.setEndY(node2.getLayoutY() + CIRCLE_RADIUS);

        Text text = new Text(String.valueOf(weight));
        text.setX(( line12.getStartX() + line12.getEndX()) / 2);
        text.setY(( line12.getStartY() + line12.getEndY()) / 2);
        text.setFont(Font.font(15));

        return new EdgeGUI(line12, text);
    }

}
