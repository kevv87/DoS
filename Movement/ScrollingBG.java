package Movement;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class ScrollingBG{

    Image BGImage = new Image(getClass().getResourceAsStream("SS10.png"));
    private ImageView backgroundIV;
    private Background BG1;
    private Background BG2;
    private Background BG3;
    private Background leftMost;
    private Background rightMost;
    int posX;

    public class Background extends Pane{

        private int position;

        public Background(int x){
            backgroundIV = new ImageView(BGImage);
            backgroundIV.setFitWidth(978);
            backgroundIV.setFitHeight(671);
            getChildren().add(backgroundIV);
            setPosition(x);
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
            setTranslateX(position);
        }
    }

    public void move(int x){
        if(x>0){
            if(posX==-960){
                if(rightMost==BG3){
                    BG1.setPosition(BG3.getPosition()+960);
                    rightMost=BG1;
                    leftMost=BG2;
                }
                else if(rightMost==BG1){
                    BG2.setPosition(BG1.getPosition()+960);
                    rightMost=BG2;
                    leftMost=BG3;
                }
                else if(rightMost==BG2){
                    BG3.setPosition(BG2.getPosition()+960);
                    rightMost=BG3;
                    leftMost=BG1;
                }
                posX=0;
            }
            else{
                BG1.setPosition(BG1.getPosition()-2);
                BG2.setPosition(BG2.getPosition()-2);
                BG3.setPosition(BG3.getPosition()-2);
                posX-=2;
            }
        }
        else{
            if(posX==960){
                if(leftMost==BG1){
                    BG3.setPosition(BG1.getPosition()-960);
                    leftMost=BG3;
                    rightMost=BG2;
                }
                else if(leftMost==BG3){
                    BG2.setPosition(BG3.getPosition()-960);
                    leftMost=BG2;
                    rightMost=BG1;
                }
                else if(leftMost==BG2){
                    BG1.setPosition(BG2.getPosition()-960);
                    leftMost=BG1;
                    rightMost=BG3;
                }
                posX=0;
            }
            else{
                BG1.setPosition(BG1.getPosition()+2);
                BG2.setPosition(BG2.getPosition()+2);
                BG3.setPosition(BG3.getPosition()+2);
                posX+=2;
            }
        }
    }

    public ScrollingBG(){
        BG1 = new Background(-960);
        BG2 = new Background(0);
        BG3 = new Background(960);
        leftMost=BG1;
        rightMost=BG3;
        posX=0;
    }

    public Background getBG1() {
        return BG1;
    }

    public Background getBG2() {
        return BG2;
    }

    public Background getBG3() {
        return BG3;
    }
}
