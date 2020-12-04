package wsu.edu;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;



public class SnakePane {
  int numCircles;
  Color snakeBodyColor;
  Color snakeHeadColor;
  Color fruitColor;
  int numFruits;
  Circle fruit;
  AnchorPane pane;
  int origNumFruits;
  Stage mainStage;
  private List<Circle> createSnake() {
    List<Circle> snake = new LinkedList<>();
    Circle head = new Circle(300, 200, 10, snakeHeadColor);
    snake.add(head);

    for (int i = 1; i <= numCircles; i++){
      Circle bodySecment = new Circle(300 - i * 20, 200, 10, snakeBodyColor);
      snake.add(bodySecment);
    }
    return snake;
  }
  private void drawSnake(List<Circle> snake) throws IOException {
    pane.getChildren().addAll(snake);
    addFruit();
  }

  private void addFruit() throws IOException {
      if (numFruits >= 0) {
        System.out.print(pane.getMaxWidth() + ", " + pane.getMaxHeight());
        fruit = new Circle((Math.random() * 600) + 10, (Math.random() * 400) + 10, 10, fruitColor);
        pane.getChildren().add(fruit);
        numFruits--;
      }
      else {
        GameOver a = new GameOver();
        a.gameOver(mainStage);
      }

  }
  private void moveSnakeRight(List<Circle> snake, Timeline s) throws IOException {
    Circle oldHead = snake.get(0);
    Circle newHead = snake.remove(snake.size() - 1);
    oldHead.setFill(snakeBodyColor);
    newHead.setFill(snakeHeadColor);
    newHead.setCenterX((oldHead.getCenterX() + 21) % 600);
    newHead.setCenterY(oldHead.getCenterY());
    snake.add(0, newHead);
    if(checkColision(snake)) s.stop();
  }
  private void moveSnakeLeft(List<Circle> snake, Timeline s) throws IOException {
    Circle oldHead = snake.get(0);
    Circle newHead = snake.remove(snake.size() - 1);
    oldHead.setFill(snakeBodyColor);
    newHead.setFill(snakeHeadColor);
    newHead.setCenterX((oldHead.getCenterX() - 20 + 599) % 600);
    newHead.setCenterY(oldHead.getCenterY());
    snake.add(0, newHead);
    if(checkColision(snake)) s.stop();
  }
  private void moveSnakeDown(List<Circle> snake, Timeline s) throws IOException {
    Circle oldHead = snake.get(0);
    Circle newHead = snake.remove(snake.size() - 1);
    oldHead.setFill(snakeBodyColor);
    newHead.setFill(snakeHeadColor);
    newHead.setCenterY((oldHead.getCenterY() + 21) % 400);
    newHead.setCenterX(oldHead.getCenterX());
    snake.add(0, newHead);
    if(checkColision(snake)) s.stop();
  }
  private void moveSnakeUp(List<Circle> snake, Timeline s) throws IOException {
    Circle oldHead = snake.get(0);
    Circle newHead = snake.remove(snake.size() - 1);
    oldHead.setFill(snakeBodyColor);
    newHead.setFill(snakeHeadColor);
    newHead.setCenterY((oldHead.getCenterY() - 20 + 399) % 400);
    newHead.setCenterX(oldHead.getCenterX());
    snake.add(0, newHead);
    if(checkColision(snake)) s.stop();
  }
  private boolean checkColision(List<Circle> snake) throws IOException {
    for(int x = 1; x < snake.size(); x ++){
      if(snake.get(0).getLayoutBounds().intersects(snake.get(x).getLayoutBounds())){
        GameOver a = new GameOver();

        a.gameOver(mainStage);
        return true;
      }
      else if (snake.get(0).getCenterX() < 10 && snake.get(0).getCenterX() > 630 && snake.get(0).getCenterY() < 10 && snake.get(0).getCenterY() > 470){
        GameOver a = new GameOver();
        a.gameOver(mainStage);
        return true;
      }
      else if(snake.get(0).getLayoutBounds().intersects(fruit.getLayoutBounds())){
        pane.getChildren().remove(fruit);
        addFruit();
        Circle bodySecment = new Circle(300 - snake.size() * 20, 200, 10, snakeBodyColor);
        snake.add(bodySecment);
        pane.getChildren().add(bodySecment);
      }
    }
    return false;
  }


  public void start(Stage stage, int snakeLen, ColorPicker bodyColor, ColorPicker headColor, ColorPicker fruitColor, int fruitNumber)
      throws IOException {
    numCircles = snakeLen;
    snakeBodyColor = bodyColor.getValue();
    snakeHeadColor = headColor.getValue();
    numFruits =  fruitNumber;
    origNumFruits = fruitNumber;
    this.fruitColor = fruitColor.getValue();
    mainStage = stage;

    pane = new AnchorPane();
    pane.setPrefSize(600,400);
    List<Circle> snake = createSnake();
    drawSnake(snake);


    Timeline timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event1 -> {
      try {
        moveSnakeRight(snake, timeline);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }));
    timeline.play();



    Scene scene = new Scene(pane);
    stage.setScene(scene);
    scene.setOnKeyPressed(event -> {
      switch (event.getCode()){
        case UP:
          timeline.stop();
          timeline.getKeyFrames().clear();
          timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event1 -> {
            try {
              moveSnakeUp(snake, timeline);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }));
          timeline.getKeyFrames().add(new KeyFrame(Duration.INDEFINITE, event2 -> {
            try {
              checkColision(snake);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }));
          timeline.play();
          break;
        case DOWN:
          timeline.stop();
          timeline.getKeyFrames().clear();
          timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event1 -> {
            try {
              moveSnakeDown(snake, timeline);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }));
          timeline.getKeyFrames().add(new KeyFrame(Duration.INDEFINITE, event2 -> {
            try {
              checkColision(snake);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }));
          timeline.play();
          break;
        case RIGHT:
          timeline.stop();
          timeline.getKeyFrames().clear();
          timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event1 -> {
            try {
              moveSnakeRight(snake, timeline);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }));
          timeline.getKeyFrames().add(new KeyFrame(Duration.INDEFINITE, event2 -> {
            try {
              checkColision(snake);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }));
          timeline.play();
          break;
        case LEFT:
          timeline.stop();
          timeline.getKeyFrames().clear();
          timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), event1 -> {
            try {
              moveSnakeLeft(snake, timeline);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }));
          timeline.getKeyFrames().add(new KeyFrame(Duration.INDEFINITE, event2 -> {
            try {
              checkColision(snake);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }));
          timeline.play();
          break;
        default:
          break;
      }
    });

    pane.requestFocus();
    stage.show();
  }
}
