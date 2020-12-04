package wsu.edu;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PrimaryController {




  @FXML
  TextField playerName;

  @FXML
  ComboBox<Integer> snakeLength;

  @FXML
  ColorPicker snakeHeadColor;

  @FXML
  ColorPicker snakeBodyColor;

  @FXML
  ColorPicker fruitColor;

  @FXML
  ComboBox<Integer> numFruits;




  public void initialize(){
    System.out.println("Primary Controller initalized");
    for(int x = 1; x <= 10; x ++){
      snakeLength.getItems().add(x);
    }
    for (int x = 1; x <= 20; x++){
      numFruits.getItems().add(x);
    }
    numFruits.setValue(10);
    snakeLength.setValue(4);
    snakeHeadColor.setValue(Color.RED);
    snakeBodyColor.setValue(Color.LIMEGREEN);
    fruitColor.setValue(Color.LIGHTBLUE);
  }

  @FXML
  public void startGame(ActionEvent actionEvent) throws IOException {
    Node source = (Node) actionEvent.getSource();
    Window theStage = source.getScene().getWindow();

    SnakePane snakePane = new SnakePane();
    snakePane.start((Stage) theStage, snakeLength.getValue(), snakeBodyColor, snakeHeadColor, fruitColor, numFruits.getValue());
  }
}
