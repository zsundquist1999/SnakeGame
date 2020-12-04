package wsu.edu;

import java.util.LinkedList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SnakePane {
  private List<Circle> createSnake() {
    List<Circle> snake = new LinkedList<>();
    Circle head = new Circle(300, 200, 10, Color.RED);
    snake.add(head);

    int numCircles = 10;
    for (int i = 1; i <= numCircles; i++){
      Circle bodySecment = new Circle(300 - i * 20, 200, 10, Color.LIMEGREEN);
      snake.add(bodySecment);
    }
    return snake;
  }
  private void drawSnake(List<Circle> snake, Pane pane ){
    pane.getChildren().addAll(snake);
  }
  private void moveSnakeRight(List<Circle> snake){
    Circle oldHead = snake.get(0);
    Circle newHead = snake.remove(snake.size() - 1);
    oldHead.setFill(Color.LIMEGREEN);
    newHead.setFill(Color.RED);
    newHead.setCenterX((oldHead.getCenterX() + 20) % 600);
    newHead.setCenterY(oldHead.getCenterY());
    snake.add(0, newHead);
  }
  private void moveSnakeLeft(List<Circle> snake){
    Circle oldHead = snake.get(0);
    Circle newHead = snake.remove(snake.size() - 1);
    oldHead.setFill(Color.LIMEGREEN);
    newHead.setFill(Color.RED);
    newHead.setCenterX((oldHead.getCenterX() - 20 + 600) % 600);
    newHead.setCenterY(oldHead.getCenterY());
    snake.add(0, newHead);
  }
  private void moveSnakeDown(List<Circle> snake){
    Circle oldHead = snake.get(0);
    Circle newHead = snake.remove(snake.size() - 1);
    oldHead.setFill(Color.LIMEGREEN);
    newHead.setFill(Color.RED);
    newHead.setCenterY((oldHead.getCenterY() + 20) % 400);
    newHead.setCenterX(oldHead.getCenterX());
    snake.add(0, newHead);
  }
  private void moveSnakeUp(List<Circle> snake){
    Circle oldHead = snake.get(0);
    Circle newHead = snake.remove(snake.size() - 1);
    oldHead.setFill(Color.LIMEGREEN);
    newHead.setFill(Color.RED);
    newHead.setCenterY((oldHead.getCenterY() - 20 + 400) % 400);
    newHead.setCenterX(oldHead.getCenterX());
    snake.add(0, newHead);
  }


  public void start(Stage stage) {
    AnchorPane anchorPane = new AnchorPane();
    anchorPane.setPrefSize(600,400);

    List<Circle> snake = createSnake();
    drawSnake(snake, anchorPane);

    Timeline timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event1 -> moveSnakeRight(snake)));
    timeline.play();

    Scene scene = new Scene(anchorPane);
    stage.setScene(scene);
    scene.setOnKeyPressed(event -> {
      switch (event.getCode()){
        case UP:
          timeline.stop();
          timeline.getKeyFrames().clear();
          timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event1 -> moveSnakeUp(snake)));
          timeline.play();
          break;
        case DOWN:
          timeline.stop();
          timeline.getKeyFrames().clear();
          timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event1 -> moveSnakeDown(snake)));
          timeline.play();
          break;
        case RIGHT:
          timeline.stop();
          timeline.getKeyFrames().clear();
          timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event1 -> moveSnakeRight(snake)));
          timeline.play();
          break;
        case LEFT:
          timeline.stop();
          timeline.getKeyFrames().clear();
          timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event1 -> moveSnakeLeft(snake)));
          timeline.play();
          break;
        default:
          break;
      }
    });
    anchorPane.requestFocus();
    stage.show();
  }
}
