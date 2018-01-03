package Application;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

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
