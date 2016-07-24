//import javax.swing.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Collections;

/**
 * Created by Nam Hai on 7/24/2016.
    Them vao 1 may bay moi dieu khien bang chuot.
 */

class Plane {
    Image img;
    int x;
    int y;

    public Plane() {
    }

    public Plane(Image img, int x, int y) {
        this.img = img;
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



public class GameWindow extends Frame implements Runnable {
    Image backgroundnd;
    //    Image myPlane;
    Plane[] plane = new Plane[10];
    BufferedImage bufferedImage;
    Graphics bufferdImageGraphics;
//    int planeX = 350;
//    int planeY = 900;

    Thread thread;



    public GameWindow() {
        plane[0] = new Plane(null, 350, 900);
        plane[1] = new Plane(null, 350, 100);
        System.out.println("Game window constructor");
        this.setVisible(true);
        this.setSize(768, 1024);
        this.setLocation(0, 0);
        try {
            backgroundnd = ImageIO.read(new File("resources/background.png"));
        } catch (IOException e) {
            System.out.println("Can not open this file.");
        }
        try {
            plane[0].setImg(ImageIO.read(new File("resources/plane2.png")));
            plane[1].setImg(ImageIO.read(new File("resources/plane1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("windowClosed");

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        plane[0].setX(plane[0].getX() - 10);
                        if (plane[0].getX() < 0) {
                            plane[0].setX(0);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        plane[0].setX(plane[0].getX() + 10);
                        if (plane[0].getX() > 700) {
                            plane[0].setX(700);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        plane[0].setY(plane[0].getY() + 10);
                        if (plane[0].getY() > 950) {
                            plane[0].setY(950);
                        }
                        break;
                    case KeyEvent.VK_UP:
                        plane[0].setY(plane[0].getY() - 10);
                        if (plane[0].getY() < 0) {
                            plane[0].setY(0);
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                plane[1].setX(e.getX());
                plane[1].setY(e.getY());
            }
        });
//        thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
        this.bufferedImage = new BufferedImage(768, 1024, BufferedImage.TYPE_INT_ARGB);
        this.bufferdImageGraphics = bufferedImage.getGraphics();
        thread = new Thread(this);
        thread.start();
    }

//    @Override
//    public void paint(Graphics g) {
//        g.drawImage(backgroundnd, 0, 0, null);
////            g.drawImage(myPlane, backgroundnd.getWidth(null)/2 - myPlane.getWidth(null)/2 , 900, null);
//        g.drawImage(myPlane, planeX , planeY, null);
//        System.out.println("PAINT");
//    }


    @Override
    public void update(Graphics g) {
        bufferdImageGraphics.drawImage(backgroundnd, 0, 0, null);
//            g.drawImage(myPlane, backgroundnd.getWidth(null)/2 - myPlane.getWidth(null)/2 , 900, null);
        bufferdImageGraphics.drawImage(plane[0].getImg(), plane[0].getX(), plane[0].getY(), null);
        bufferdImageGraphics.drawImage(plane[1].getImg(), plane[1].getX(), plane[1].getY(), null);
        g.drawImage(bufferedImage, 0, 0, null);
//        System.out.println("PAINT");
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(27);
                repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


