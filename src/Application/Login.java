package Application;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Login extends Application {

    private App app;
    private Label lbStart;
    private Label lbRes;
    private TextField tfUser;
    //TextField tfPass;
    private PasswordField pfPass;
    private Button btnLogin;

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
        pfPass = new PasswordField();
        pfPass.setPromptText("Password");

        btnLogin = new Button("Login");
        lbRes = new Label("                                       ");

        //set action for button
        btnLogin.setOnAction((ae) -> {
            if (tfUser.getText().equals("admin") && pfPass.getText().equals("admin")) {
                rootNode.getChildren().removeAll(lbStart,tfUser,pfPass,btnLogin,lbRes);
                //rootNode.setPrefHeight(500);
                myStage.close();
                app = new App();
                app.start(myStage);
            }
            else {
                lbRes.setText("       Invalid credentials       ");
            }
        });
        //set action for text fields
        tfUser.setOnAction((ae) -> btnLogin.fire());
        pfPass.setOnAction((ae) -> btnLogin.fire());

        rootNode.getChildren().addAll(lbStart, tfUser,pfPass,btnLogin,lbRes);
        //show stage
        myStage.show();
    }
}
