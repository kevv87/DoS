package Movement;

import Actors.factories.dragons.Dragon;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.concurrent.TimeUnit;
import java.sql.Time;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Node;

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

    public void movePlayer() throws Exception{
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
        if(isPressed(KeyCode.F)){
            TimeUnit.MILLISECONDS.sleep(50);
            Thread fire = new Thread(){
                Fire fire; 
                @Override
                public void run(){
                    fire = new Fire(player.getPosX()+300, player.getPosY()+250);
                    addToPane(fire);
                    while(fire.getPosX()<Background.getFitWidth()){
                        fire.move();
                        for(Dragon enemy:Enemies.getHorde()){
                            boolean interseccion = fire.getBoundsInParent().intersects(enemy.getBoundsInParent());
                            if(interseccion){
                                Enemies.getHorde().remove(enemy);
                                remove(enemy);

                                return;
                            }
                        }
                        try {
                            sleep(10);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    try {
                        sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            };
            fire.start();
            System.out.println("Fire");
            Thread.sleep(1000);
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
                try {
                    movePlayer();
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    
    public void addToPane(Fire toad){
        
        Platform.runLater(new Runnable(){
           
            @Override public void run(){
                
                root.getChildren().addAll(toad);
            }
            
        });   
    }
    
    
    
    public void remove(Dragon dragon){
        Platform.runLater(new Runnable(){
           
            @Override public void run(){
                root.getChildren().remove(dragon);
            }
            
        });  
    }
    public void remove(Fire fire){
        Platform.runLater(new Runnable(){
           
            @Override public void run(){
                root.getChildren().remove(fire);
            }
            
        });  
    }
}
