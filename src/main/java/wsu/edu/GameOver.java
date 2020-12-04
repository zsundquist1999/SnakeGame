package wsu.edu;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameOver {
  @FXML
  Label Berries;

  int berries;

  private static Scene scene;

//  public GameOver(int berries){
//    this.berries = berries;
//  }

  public void initialize(){
    Berries.setText("You ate " +  berries + " berries!");
  }
  public void gameOver(Stage stage) throws IOException {
    scene = new Scene(loadFXML("GameOver"), 640, 480);
    stage.setTitle("Snake Game");
    stage.setScene(scene);
    stage.show();
  }
  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }
}
