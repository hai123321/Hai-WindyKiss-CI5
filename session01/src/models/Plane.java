package models;

import java.awt.*;

/**
 * Created by Nam Hai on 7/24/2016.
 * Them vao 1 may bay moi dieu khien bang chuot.
 */

public class Plane {
    Image img;
    int x;
    int y;

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
        }
        if (x > 700) {
            x = 700;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > 950) {
            y = 950;
        }
    }

    public Plane() {
    }

    public Plane(Image img, int x, int y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }

    public void moveto(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
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
}
