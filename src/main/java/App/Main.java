package App;

import javafx.application.Application;
import javafx.stage.Stage;
import services.PasswordHasher;
import services.SceneManager;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.setStage(stage);
        stage.setScene(sceneManager.getScene());
        stage.show();

        String salt = PasswordHasher.generateSalt();
        String passhash = PasswordHasher.generateSaltedHash("admin123", salt);
        System.out.println(salt);
        System.out.println(passhash);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
