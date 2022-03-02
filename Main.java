import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
        /*int nodes = 5;

        Graph a = new Graph(nodes);

        a.addEdge(0, 1, 5);
        a.addEdge(0, 3, 8);
        a.addEdge(1, 2, 6);
        a.addEdge(1, 3, 9);
        a.addEdge(2, 4, 4);
        a.addEdge(3, 2, 2);
        a.addEdge(4, 1, 5);

        System.out.println(a.minimumDistanceBetweenTwoNodes(0, 1));*/
    }

    @Override
    public void start(Stage stage) throws Exception {

        Pane pane = new Pane();

        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\test_file.txt"));
        String line = reader.readLine();

        int nodes = Integer.parseInt(line);
        int countOfInputLines = 1;
        line = reader.readLine();

        Graph graph = new Graph(nodes);
        ArrayList<StackPane> graphNodes = new ArrayList<>();
        ArrayList<EdgeGUI> allUIEdges = new ArrayList<>();

        while (line != null) {
            if(countOfInputLines <= 5) {
                String currentLine = line;
                String[] spittedLine = line.split(",");

                StackPane node = DrawGUI.getNode(String.valueOf(countOfInputLines-1), Integer.parseInt(spittedLine[0])*20
                        , Integer.parseInt(spittedLine[1])*20);
                graphNodes.add(node);
                pane.getChildren().add(node);

            } else {
                break;
            }
            countOfInputLines++;
            line = reader.readLine();
        }

        while (line != null) {
            String currentLine = line;
            String[] splittedLine = line.split(",");

            graph.addEdge(Integer.parseInt(splittedLine[0]), Integer.parseInt(splittedLine[1])
                  , Integer.parseInt(splittedLine[2]));

            StackPane node1 = graphNodes.get(Integer.parseInt(splittedLine[0]));
            StackPane node2 = graphNodes.get(Integer.parseInt(splittedLine[1]));
            EdgeGUI edgeGUI = DrawGUI.getLine(node1, node2, Integer.parseInt(splittedLine[2]));
            edgeGUI.setV1(Integer.parseInt(splittedLine[0]));
            edgeGUI.setV2(Integer.parseInt(splittedLine[1]));
            allUIEdges.add(edgeGUI);

            pane.getChildren().addAll(edgeGUI.getLine(), edgeGUI.getText());
            line = reader.readLine();
        }

        System.out.println("Enter the two vertices numbers separated by a space:");
        try {
            String[] vertices = new Scanner(System.in).nextLine().split(" ");

            if(vertices[0].equals(vertices[1])) {
                StackPane node = graphNodes.get(Integer.parseInt(vertices[0]));
                Circle nodeCircle = (Circle) node.getChildren().get(0);
                nodeCircle.setFill(Color.RED);
            } else {
                ArrayList<ArrayList<Integer>> minPaths =
                        graph.minimumDistanceBetweenTwoNodes(Integer.parseInt(vertices[0])
                                , Integer.parseInt(vertices[1]));

                ArrayList<Integer> pathsToDest = minPaths.get(Integer.parseInt(vertices[1]));
                for (int i = 0; i < pathsToDest.size() - 1; i++) {
                    StackPane node = graphNodes.get(pathsToDest.get(i));
                    Circle nodeCircle = (Circle) node.getChildren().get(0);
                    nodeCircle.setFill(Color.RED);

                    int v1 = pathsToDest.get(i);
                    int v2 = pathsToDest.get(i + 1);

                    for (EdgeGUI edge : allUIEdges) {
                        if (edge.getV1() == v1 && edge.getV2() == v2
                                || edge.getV1() == v2 && edge.getV2() == v1)
                            edge.getLine().setStroke(Color.RED);
                    }
                }
                StackPane node = graphNodes.get(pathsToDest.get(pathsToDest.size() - 1));
                Circle nodeCircle = (Circle) node.getChildren().get(0);
                nodeCircle.setFill(Color.RED);
            }

            Scene scene = new Scene(pane, 500, 500);
            stage.setScene(scene);
            stage.setTitle("Simple program");
            stage.show();
        } catch (Exception ex) {
            System.out.println("Please enter 2 valid numbers separated by a space!");
        }

    }

}
