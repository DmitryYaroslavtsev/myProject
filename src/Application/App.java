package Application;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.*;

public class App extends Application {

    /*public static void main(String[] args) {
        launch(args);
    }
    */
    @Override
    public void start(Stage primaryStage) {
        Stage myStage = new Stage();
        myStage.setTitle("Application");

        GridPane rootNode = new GridPane();

        rootNode.setAlignment(Pos.TOP_LEFT);
        Scene myScene = new Scene(rootNode, 500, 500);

        myStage.setScene(myScene);
        createBtnTest();

        rootNode.getChildren().add(createBtnTest());

        myStage.show();
    }

    int i = 0;
    Button createBtnTest() {
        Button btn = new Button("Test");
        btn.setOnAction((ae) -> {
            if (i < 10) i++;
            else {
                i = 0;
                btn.setText("Test");
            }
            btn.setText(Integer.toString(i));
        });
        return btn;
    }
}
