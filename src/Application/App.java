package Application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Stage myStage = new Stage();
        myStage.setTitle("Application");

        FlowPane rootNode = new FlowPane(10, 10);

        rootNode.setAlignment(Pos.TOP_LEFT);
        Scene myScene = new Scene(rootNode, 500, 500);

        myStage.setScene(myScene);

        myStage.show();
    }
}
