//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Laba_6;

import com.sun.java.accessibility.util.AWTEventMonitor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class BouncingBall implements Runnable {
    private static final int MAX_RADIUS = 20;
    private static final int MIN_RADIUS = 15;
    private static final int MAX_SPEED = 30;
    private Field field;
    private int radius;
    private Color color;
    private boolean superflag = false;
    private double x;
    private double y;
    private int speed;
    private double speedX;
    private double speedY;

    public BouncingBall(Field field) {
        this.field = field;
        this.radius = (new Double(Math.random() * 5.0D)).intValue() + 15;
        this.speed = (new Double((double)Math.round((float)(150 / this.radius)))).intValue();
        if (this.speed > 30) {
            this.speed = 30;
        }
        AWTEventMonitor.addMouseListener(new MouseHandler());
        double angle = Math.random() * 2.0D * 3.141592653589793D;
        this.speedX = 3.0D * Math.cos(angle);
        this.speedY = 3.0D * Math.sin(angle);
        this.color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
        this.x = Math.random() * (field.getSize().getWidth() - (double)(2 * this.radius)) + (double)this.radius;
        this.y = Math.random() * (field.getSize().getHeight() - (double)(2 * this.radius)) + (double)this.radius;
        Thread thisThread = new Thread(this);
        thisThread.start();
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public boolean isSuperflag() {
        return this.superflag;
    }

    public double getSpeedX() {
        return this.speedX;
    }

    public double getSpeedY() {
        return this.speedY;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getRadius() {
        return this.radius;
    }

    public BouncingBall superball() {
        this.superflag = true;
        this.radius *= 2;
        return this;
    }

    public void run() {
        try {
            while(true) {
                this.field.canMove(this);
                int i = 0;

                while(true) {
                    Field var10001 = this.field;

                        if (this.x + this.speedX <= (double)this.radius) {
                            this.speedX = -this.speedX;
                            this.x = (double)this.radius;
                        } else if (this.x + this.speedX >= (double)(this.field.getWidth() - this.radius)) {
                            this.speedX = -this.speedX;
                            this.x = (double)(new Double((double)(this.field.getWidth() - this.radius))).intValue();
                        } else if (this.y + this.speedY <= (double)this.radius) {
                            this.speedY = -this.speedY;
                            this.y = (double)this.radius;
                        } else if (this.y + this.speedY >= (double)(this.field.getHeight() - this.radius)) {
                            this.speedY = -this.speedY;
                            this.y = (double)(new Double((double)(this.field.getHeight() - this.radius))).intValue();
                        } else {
                            this.x += this.speedX;
                            this.y += this.speedY;
                        }

                        Thread.sleep((long)(16 - this.speed));int j = 0;
                        break;





                }
            }
        } catch (InterruptedException var3) {
        }
    }

    public void paint(Graphics2D canvas) {
        if (!this.superflag) {
            canvas.setColor(this.color);
            canvas.setPaint(this.color);
        } else {
            canvas.setColor(this.color);
            canvas.setPaint(Color.RED);
        }

        java.awt.geom.Ellipse2D.Double ball = new java.awt.geom.Ellipse2D.Double(this.x - (double)this.radius, this.y - (double)this.radius, (double)(2 * this.radius), (double)(2 * this.radius));
        canvas.draw(ball);
        canvas.fill(ball);
    }

    public class MouseHandler extends MouseAdapter {
        public MouseHandler() {
        }
        public void mouseClicked(MouseEvent ev) {

setSpeedX(0);setSpeedY(0);
            //thisThread.sleep();

            }
        }



}
