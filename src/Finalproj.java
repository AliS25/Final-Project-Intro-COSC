// Ali Sbeih 12/2/2021
// This is an interactive game. Click the mouse to shoot and try to hit as many targets as you can

import java.awt.*;

/**
 * 6/13/23
 * Added text to the canvas
 * changed background
 * changed player plane
 * changed plane speed
 * made the color of opponent planes random
 * made canvas size bigger
 * made the game infinite
 * added lives to the game
 * added sound
 */
public class Finalproj {
    public static void main(String[] args) {
        StdAudio.playInBackground("BGMusic.au");
        StdDraw.setCanvasSize(650,650);
        StdDraw.setXscale(-10, 10);
        StdDraw.setYscale(-10, 10);

        int score = 0;
        StdDraw.enableDoubleBuffering();



        int hearts = 3;
        while(hearts!=0) {
            double x = 10 * Math.random();
            if (x < 5) {
                x = x + 5;
            }

            int speed =3+(int)(Math.random()*8);
            if(oppPlane(x, speed,hearts)==1) {
                score++ ;

            }
            else {
                StdAudio.playInBackground("heart.wav");
                hearts--;
            }
        }
//        }

        for (double location = -8; location <= 12; location++) {
            StdDraw.picture(0,0,"cloudsBG.png",20,20);
            playerPlane(location);
            StdDraw.show();
            StdDraw.pause(80);
            StdDraw.clear();
        }
        StdAudio.stopInBackground();
        StdAudio.playInBackground("over.wav");
        StdDraw.picture(0,0,"cloudsBG.png",20,20);
        StdDraw.setPenColor(StdDraw.RED);
        Font f = new Font("Arial",Font.BOLD,30);
        StdDraw.setFont(f);
        StdDraw.text(0,5,"You destroyed "+score+" airplanes");

        if(score==0) StdDraw.text(0,0,"You need more practice");
        else if(score>=10)StdDraw.text(0,0,"Perfect!!!");
        else if(score>5)StdDraw.text(0,0,"Well done!");
        else StdDraw.text(0,0,"You are almost there");

        StdDraw.show();

    }

    static int oppPlane(double p, double s, int hearts) {
        int hit=0;
        int colValOne = rgbVAl();
        int colValTwo = rgbVAl();
        int colValThree = rgbVAl();
        int colValFour = rgbVAl();
        int colValFive = rgbVAl();
        int colValSix = rgbVAl();


        for (int xpos = -10; xpos < 13; xpos++) {
            StdDraw.clear();
            StdDraw.picture(0,0,"cloudsBG.png",20,20);

            StdDraw.setPenColor(colValOne, colValTwo, colValThree);
            StdDraw.filledRectangle(xpos, p, 0.4, 1.8);
            StdDraw.setPenColor(colValFour, colValFive, colValSix);
            StdDraw.filledRectangle(xpos, p, 1.2, 0.4);
            StdDraw.filledCircle(xpos + 1.2, p, 0.4);

            heart(hearts);


            StdDraw.show();

            playerPlane(-8);
            StdDraw.show();

            StdDraw.pause((int)(300/s));

            if (StdDraw.isMousePressed()) {
                bullet();
                StdDraw.show();
                StdDraw.pause(300);
                if (xpos <= 1.8 & xpos >= -1.8) {
                    explosion(p);
                    StdDraw.show();
                    StdDraw.pause(500);
                    hit=1;
                }
                break;
            }
        }
        return hit;
    }

    static void heart(double h){
            StdDraw.setPenColor(Color.red);
            StdDraw.filledEllipse(-9, -8, 0.15, 0.3);
            StdDraw.filledEllipse(-8.8, -8, 0.15, 0.3);
            double[] xcor = {-9.15, -8.9, -8.65};
            double[] ycor = {-8, -8.6, -8};
            StdDraw.filledPolygon(xcor, ycor);

        if(h>=2){
            StdDraw.filledPolygon(xcor, ycor);
            StdDraw.filledEllipse(-8.5,-8,0.15,0.3);
            StdDraw.filledEllipse(-8.3,-8,0.15,0.3);
            double[] xcorTwo = {-8.65,-8.4,-8.15};
            double[] ycorTwo = {-8,-8.6,-8};
            StdDraw.filledPolygon(xcorTwo,ycorTwo);
        }
        if(h==3){
            StdDraw.filledEllipse(-8,-8,0.15,0.3);
            StdDraw.filledEllipse(-7.8,-8,0.15,0.3);
            double[] xcorThree = {-8.15,-7.9,-7.65};
            double[] ycorThree = {-8,-8.6,-8};
            StdDraw.filledPolygon(xcorThree,ycorThree);
        }

    }
    static void playerPlane(double p) {
        StdDraw.picture(0,p,"shooterPlane.png",6,6);
    }

    static void explosion(double p) {
        StdAudio.playInBackground("explo.wav");
        StdDraw.setPenColor(204, 0, 0);
        StdDraw.filledCircle(0, p, 2);
        StdDraw.setPenColor(204, 204, 0);
        StdDraw.filledCircle(1.5, p + 1.5, 1.1);
        StdDraw.filledCircle(1.5, p - 1.5, 1.4);
        StdDraw.filledCircle(-1.5, p + 1.5, 1.3);
        StdDraw.filledCircle(-1.5, p - 1.5, 1.2);
        StdDraw.setPenColor(64, 64, 64);
        StdDraw.filledCircle(0, p, 0.5);
    }

    static void bullet() {
        StdAudio.playInBackground("laser.wav");
        StdDraw.setPenColor(153, 0, 0);
        StdDraw.filledRectangle(0, 1.9, 0.1, 8.1);
    }

    static int rgbVAl(){
        return (int)(Math.random()*256);
    }
}



