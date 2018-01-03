package Application;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.sql.*;

public class Login extends Application {

    private App app;
    private Label lbRes;
    private TextField tfUser;
    //TextField tfPass;
    private PasswordField pfPass;
    private Button btnLogin;

    //Variables for DB users
    private String urlUsers = "jbdc:postgresql://localhost:5432/users";
    private String login = "postgres";
    private String pass = "postgres";

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
        //rootNode.setOrientation(Orientation.VERTICAL);

        //create Scene
        Scene myScene = new Scene(rootNode, 180, 160);

        //set scene to stage
        myStage.setScene(myScene);

        Label lbStart = new Label("Input your credentials");
        tfUser = new TextField();
        tfUser.setPromptText("Username");
        pfPass = new PasswordField();
        pfPass.setPromptText("Password");

        btnLogin = new Button("Login");
        lbRes = new Label("                                       ");

        //set action for button
        btnLogin.setOnAction((ae) -> {
            if (tfUser.getText().equals("admin") && pfPass.getText().equals("admin")) {
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

    boolean checkUser(String username, String password) {
        boolean check = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(urlUsers, login, pass);
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }




        return check;
    }

}
