package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private static SceneManager sceneManager;
    private Scene scene;
    private String currentPath;
    private String centerPanePath;


    private final Map<String, Object> data = new HashMap<>();

    private SceneManager(){
        this.currentPath = "/Views/customer_flights.fxml";
        this.scene = this.initScene();
    }

    public static SceneManager getInstance(){
        if(sceneManager == null)
            sceneManager = new SceneManager();
        return sceneManager;
    }

    private Scene initScene(){
        try{
            return new Scene(this.getParent(currentPath));
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private Parent getParent(String path) throws IOException{
        return FXMLLoader.load(getClass().getResource(path));
    }

    public Scene getScene(){
        return scene;
    }

    public void switchScene(String fxmlPath){
        try{
            this.currentPath = fxmlPath;
            Parent root = getParent(fxmlPath);
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCenterPanePath(String fxmlPath){
        this.centerPanePath = fxmlPath;
    }

    public void setData(String key, Object value) {
        data.put(key, value);
    }

    public Object getData(String key) {
        return data.get(key);
    }
}
