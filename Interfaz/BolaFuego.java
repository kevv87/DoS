package Interfaz;

public class BolaFuego {

    private double posX;
    private double posY;
    private String image_Url;

    // GETTERS
    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public String getImage_Url() {
        return image_Url;
    }

    //SETTERS

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void setImage_Url(String image_Url) {
        this.image_Url = image_Url;
    }
}
