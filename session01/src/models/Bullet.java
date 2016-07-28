package models;

import java.awt.*;

/**
 * Created by Nam Hai on 7/27/2016.
 */
public class Bullet{
    int x;
    int y;
    Image img;
    Thread thread;
    public Bullet(int x, int y, Image img){
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

}
