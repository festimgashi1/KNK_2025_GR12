package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private static SceneManager sceneManager;
    private Scene scene;
    private Stage primaryStage;
    private String currentPath;
    private final Map<String, Object> data = new HashMap<>();
    private FXMLLoader lastLoader;

    private SceneManager() {
        this.currentPath = "/Views/customer_flights.fxml";
        this.scene = this.initScene();
    }

    public static SceneManager getInstance() {
        if (sceneManager == null)
            sceneManager = new SceneManager();
        return sceneManager;
    }

    private Scene initScene() {
        try {
            return new Scene(getParent(currentPath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Parent getParent(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
        lastLoader = loader;
        return root;
    }

    public Scene getScene() {
        return scene;
    }

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void switchScene(String fxmlPath) {
        try {
            this.currentPath = fxmlPath;
            Parent root = getParent(fxmlPath);

            if (scene == null) {
                scene = new Scene(root);
            } else {
                scene.setRoot(root);
            }

            if (primaryStage != null) {
                primaryStage.setScene(scene);
                primaryStage.show();
            } else {
                System.out.println("Warning: primaryStage is null in SceneManager.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setData(String key, Object value) {
        data.put(key, value);
    }

    public Object getData(String key) {
        return data.get(key);
    }

    public <T> T getLastController() {
        return lastLoader != null ? lastLoader.getController() : null;
    }
}
