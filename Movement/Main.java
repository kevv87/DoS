package Movement;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.HashMap;

public class Main extends Application{
    //Screen
    private  HashMap<KeyCode, Boolean> keys = new HashMap<>();
    static Pane root = new Pane();
    private Scene scene;
    private Image BGImage = new Image(getClass().getResourceAsStream("background24.jpg"));
    private ImageView Background = new ImageView(BGImage);
    //Player
    private Hero player = new Hero();
    //Enemies
    private DragonHorde Enemies;

    //       _______________
    //______/New game screen
    public void newGame(){
        root.setPrefSize(1366, 768);
        Background.setFitWidth(1366);
        Background.setFitHeight(768);
        root.getChildren().add(Background);
        player.setTranslateY(250);
        root.getChildren().add(player);
        Enemies = new DragonHorde(root,3,3,3,3,Background.getFitHeight());
        enemyMovement.start();
        scene = new Scene(root);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
        });
    }


    //       _____________
    //______/Hero control
    public boolean isPressed(KeyCode key){
        return keys.getOrDefault(key, false);
    }

    public void movePlayer(){
        if ((isPressed(KeyCode.W) || isPressed(KeyCode.UP)) && player.getPosY()>-250){
            player.moveY(-2);
            player.setPosY(player.getPosY()-2);
        }
        if ((isPressed(KeyCode.S) || isPressed(KeyCode.DOWN)) && player.getPosY()<294){
            player.moveY(2);
            player.setPosY(player.getPosY()+2);
        }
        if ((isPressed(KeyCode.D) || isPressed(KeyCode.RIGHT)) && player.getPosX()<1218){
            player.moveX(2);
            player.setPosX(player.getPosX()+2);
        }
        if ((isPressed(KeyCode.A) || isPressed(KeyCode.LEFT)) && player.getPosX()>0){
            player.moveX(-2);
            player.setPosX(player.getPosX()-2);
        }
    }

    Thread enemyMovement = new Thread(){
        @Override
        public void run() {
            while (Enemies.getPosX()>-76){
                try{
                    sleep(50);
                    Enemies.moveHorde();
                }
                catch (Exception E){

                }
            }
        }
    };

    @Override
    public void start(Stage primaryStage) throws  Exception{
        newGame();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                movePlayer();
            }
        };
        timer.start();
        primaryStage.setTitle("GAME OF SORTS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static  void  main(String[] args){
        launch(args);
    }
}
