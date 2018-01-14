package Application;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
import java.sql.*;

public class Login extends Application {

    private App app;
    private Label lbRes;
    private TextField tfUser;
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
        GridPane rootNode = new GridPane();
        rootNode.setAlignment(Pos.TOP_CENTER);
        rootNode.setHgap(10);
        rootNode.setVgap(10);

        //create Scene
        Scene myScene = new Scene(rootNode, 300, 160);
        myStage.setResizable(false);

        //set scene to stage
        myStage.setScene(myScene);

        //Label lbStart = new Label("Input your credentials");
        Text lbStart = new Text("Input your credentials");
        Label un = new Label("User Name");
        Label pw = new Label("Password");
        rootNode.add(un, 0, 1);
        rootNode.add(pw, 0, 2);
        rootNode.add(lbStart, 0, 0, 2, 1);
        tfUser = new TextField();
        rootNode.add(tfUser, 1, 1);
        tfUser.setPromptText("Username");
        pfPass = new PasswordField();
        rootNode.add(pfPass, 1,2);
        pfPass.setPromptText("Password");

        btnLogin = new Button("Sign in");
        rootNode.add(btnLogin, 1,3);
        lbRes = new Label("");

        rootNode.add(lbRes, 0, 4, 2, 1);

        //set action for button
        btnLogin.setOnAction((ae) -> {
            if (checkUser(tfUser.getText(), pfPass.getText())) {
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

        //show stage
        myStage.show();
    }


    //Connect to DB for verification of users
    private boolean checkUser(String username, String password) {
        boolean check = false;
        ResultSet u = App.request("SELECT * FROM users WHERE username like '" + username + "'");
        try {
        while (u.next()) {
            if (u.getString("username").equals(username) && u.getString("password").equals(password)) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
