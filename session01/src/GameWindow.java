//import javax.swing.*;

import com.sun.java.accessibility.util.EventID;
import models.Bullet;
import models.Plane;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;


public class GameWindow extends Frame {
    Image backgroundnd;
    //    Image myPlane;
    Vector<Bullet> bulletVector1;
    Vector<Bullet> bulletVector2;
    Vector<Plane> enemyPlane;
    Plane[] plane = new Plane[10];
    BufferedImage bufferedImage;
    Graphics bufferdImageGraphics;

    bulletX thread;
    enemyX threadenemyX;

    Random in = new Random();

    public GameWindow() {
        plane[0] = new Plane(null, 350, 900);
//        plane[1] = new Plane(null, 200, 900);
        bulletVector1 = new Vector<>();
        bulletVector2 = new Vector<>();
        enemyPlane = new Vector<>();
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
                        plane[0].move(-10, 0);
                        break;
                    case KeyEvent.VK_RIGHT:
                        plane[0].move(+10, 0);
                        break;
                    case KeyEvent.VK_DOWN:
                        plane[0].move(0, 10);
                        break;
                    case KeyEvent.VK_UP:
                        plane[0].move(0, -10);
                        break;
                    case KeyEvent.VK_SPACE:
                        try {
                            Bullet bullet = new Bullet(plane[0].getX(), plane[0].getY() - 10, ImageIO.read(new File("resources/bullet.png")));
                            Bullet bullet2 = new Bullet(plane[0].getX() + 55, plane[0].getY() - 10, ImageIO.read(new File("resources/bullet.png")));
                            bulletVector1.add(bullet);
                            bulletVector1.add(bullet2);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        //
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor"));
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseMoved(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                plane[0].moveto(e.getX() - 35, e.getY() - 30);
            }
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    Bullet bullet = new Bullet(plane[0].getX() + 30, plane[0].getY() - 15, ImageIO.read(new File("resources/bullet.png")));
                    bulletVector2.add(bullet);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.bufferedImage = new BufferedImage(768, 1024, BufferedImage.TYPE_INT_ARGB);
        this.bufferdImageGraphics = bufferedImage.getGraphics();
        thread = new bulletX();
        thread.start();
        threadenemyX = new enemyX();
        threadenemyX.start();
    }


    @Override
    public void update(Graphics g) {
        bufferdImageGraphics.drawImage(backgroundnd, 0, 0, null);
        bufferdImageGraphics.drawImage(plane[0].getImg(), plane[0].getX(), plane[0].getY(), null);
        for (Bullet bullet : bulletVector1) {
            bufferdImageGraphics.drawImage(bullet.getImg(), bullet.getX(), bullet.getY(), null);
        }
        for (Bullet bullet : bulletVector2) {
            bufferdImageGraphics.drawImage(bullet.getImg(), bullet.getX(), bullet.getY(), null);
        }
        for (Plane plane : enemyPlane) {
            bufferdImageGraphics.drawImage(plane.getImg(), plane.getX(), plane.getY(), null);
        }
        g.drawImage(bufferedImage, 0, 0, null);
    }

    class bulletX extends Thread {
        @Override
        public synchronized void run() {
            try {
                while (true) {
                    Thread.sleep(27);
                    try{
                        for (Plane plane : enemyPlane) {
                            for (Bullet bullet : bulletVector1) {
                                if (plane.getY() + 32 > bullet.getY() && plane.getY() < bullet.getY() && plane.getX() < bullet.getX() && plane.getX() + 32 > bullet.getX()) {
                                    bulletVector1.remove(bullet);
                                    enemyPlane.remove(plane);
                                    break;
                                }
                            }
                            for (Bullet bullet : bulletVector2) {
                                if (plane.getY() + 32 > bullet.getY() && plane.getY() < bullet.getY() && plane.getX() < bullet.getX() && plane.getX() + 32 > bullet.getX()) {
                                    bulletVector2.remove(bullet);
                                    enemyPlane.remove(plane);
                                    break;
                                }
                            }
                        }
                    } catch(Exception e){

                    }


                    Iterator<Bullet> bulletIterator = bulletVector1.iterator();
                    while (bulletIterator.hasNext()) {
                        Bullet bullet = bulletIterator.next();
                        bullet.setY(bullet.getY() - 10);
                        if (bullet.getY() <= 0) {
                            bulletIterator.remove();
                        }
                    }

                    Iterator<Bullet> bulletIterator2 = bulletVector2.iterator();
                    while (bulletIterator2.hasNext()) {
                        Bullet bullet = bulletIterator2.next();
                        bullet.setY(bullet.getY() - 10);
                        if (bullet.getY() <= 0) {
                            bulletIterator2.remove();
                        }
                    }

                    Iterator<Plane> planeEIterator = enemyPlane.iterator();
                    while ((planeEIterator.hasNext())) {
                        Plane plane = planeEIterator.next();
                        plane.setY(plane.getY() + 5);
                        if (plane.getY() > 1024) {
                            planeEIterator.remove();
                        }
                    }

                    repaint();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class enemyX extends Thread {
        @Override
        public synchronized void run() {
            try {
                while (true) {
                    thread.sleep(500);
                    if (enemyPlane.size() < 10) {

                        Plane tmp = new Plane(ImageIO.read(new File("resources/enemy_plane_yellow_1.png")), in.nextInt(768), 0);
                        enemyPlane.add(tmp);
                    }
                }
            } catch (InterruptedException e) {

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


