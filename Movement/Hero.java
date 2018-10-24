package Movement;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Hero extends Pane{

    //Hero configuration
    private Image Hero = new Image(getClass().getResourceAsStream("Player.gif"));
    private ImageView HeroIV = new ImageView(Hero);
    private int width = 148; //size of sprite
    private int height = 198;    //size of sprite
    private int PosX = 0;  //Player position X
    private int PosY = 0;  //Player position Y
    private int resistencia = 3;

    public Hero(){
        HeroIV.setViewport(new Rectangle2D(0,0, width, height));
        getChildren().addAll(HeroIV);
    }

    public void moveX(int x){
        boolean right = x>0;
        for(int i = 0; i < Math.abs(x); i++){
            if(right) this.setTranslateX(this.getTranslateX() +1);
                else this.setTranslateX(this.getTranslateX() -1);
        }
    }

    public void moveY(int y){
        boolean right = y>0;
        for(int i = 0; i < Math.abs(y); i++){
            if(right) this.setTranslateY(this.getTranslateY() +1);
            else this.setTranslateY(this.getTranslateY() -1);
        }
    }

    public int getPosX() {
        return PosX;
    }

    public int getPosY() {
        return PosY;
    }

    public void setPosX(int posX) {
        PosX = posX;
    }

    public void setPosY(int posY)
    {
        PosY = posY;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }
}
