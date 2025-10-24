package Main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.*;
import javafx.scene.*;

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new
        FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
        Parent root = loader.load();
        Scene myScene = new Scene(root,400,400);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
