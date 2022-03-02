import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class EdgeGUI {
    private Line line;
    private Text text;
    private int v1;
    private int v2;

    public EdgeGUI(Line line, Text text) {
        this.line = line;
        this.text = text;
    }

    public EdgeGUI(Line line, Text text, int v1, int v2) {
        this.line = line;
        this.text = text;
    }

    public Line getLine() { return line; }

    public Text getText() { return text; }

    public int getV1() { return v1; }

    public int getV2() { return v2; }

    public void setV1(int v1) { this.v1 = v1; }

    public void setV2(int v2) { this.v2 = v2; }
}
