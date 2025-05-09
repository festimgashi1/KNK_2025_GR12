package services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneManager {
    private static SceneManager sceneManager;
    private Scene scene;
    private String currentPath;
    private String centerPanePath;

    private SceneManager(){
        this.currentPath= "/Views/log_in.fxml";
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
        return FXMLLoader.load(getClass().getResource(path));    }
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
}
