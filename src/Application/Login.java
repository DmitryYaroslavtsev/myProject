package Application;

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.*;

public class Login extends Application {

    App app;
    Label lbStart;
    Label lbRes;
    TextField tfUser;
    TextField tfPass;
    Button btnLogin;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //create Stage
        Stage myStage = new Stage();
        myStage.setTitle("Login");

        //create rootNode
        FlowPane rootNode = new FlowPane(10,10);
        rootNode.setAlignment(Pos.TOP_CENTER);

        //create Scene
        Scene myScene = new Scene(rootNode, 180, 160);

        //set scene to stage
        myStage.setScene(myScene);

        lbStart = new Label("Input your credentials");
        tfUser = new TextField();
        tfUser.setPromptText("Username");
        tfPass = new TextField();
        tfPass.setPromptText("Password");

        btnLogin = new Button("Login");
        lbRes = new Label("                                       ");

        btnLogin.setOnAction((ae) -> {
            if (tfUser.getText().equals("admin") && tfPass.getText().equals("admin")) {
                rootNode.getChildren().removeAll(lbStart,tfUser,tfPass,btnLogin,lbRes);
                //rootNode.setPrefHeight(500);
                myStage.close();
                app = new App();
                app.start(myStage);

            }
            else {
                lbRes.setText("       Invalid credentials       ");
            }
        });

        rootNode.getChildren().addAll(lbStart, tfUser,tfPass,btnLogin,lbRes);
        //show stage
        myStage.show();
    }
}
