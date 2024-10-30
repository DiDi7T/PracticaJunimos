package org.icesi.junimosvalley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Player {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Position position;

    // 0 = idle | 1 = run
    private int state;
    private int frame;

    private ArrayList<Image> idles;
    private ArrayList<Image> runR;
    private ArrayList<Image> runL;
    private ArrayList<Image> runU;
    private ArrayList<Image> runD;
    private ArrayList<Image> atack;
    private ArrayList<Image> Axe;
    private ArrayList<Image> beak;
    private ArrayList<Image> shovel;
    private ArrayList<Image> happy;


    // actions
    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;

    public Player(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        position = new Position(100,100);
        idles = new ArrayList<>();
        runR = new ArrayList<>();
        runL = new ArrayList<>();
        runU = new ArrayList<>();
        runD = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Image idle = new Image(getClass().getResourceAsStream("/sprites/player/idle/idle"+i+".png"),50,50,false,false);
            idles.add(idle);
        }
        for (int i = 0; i < 3; i++) {
            Image runRight = new Image(getClass().getResourceAsStream("/sprites/player/run/right/right"+i+".png"),50,50,false,false);
            runR.add(runRight);
        }
        for (int i = 0; i < 3; i++) {
            Image runLeft = new Image(getClass().getResourceAsStream("/sprites/player/run/left/left"+i+".png"),50,50,false,false);
            runL.add(runLeft);
        }
        for (int i = 0; i < 3; i++) {
            Image runUp = new Image(getClass().getResourceAsStream("/sprites/player/run/up/up"+i+".png"),50,50,false,false);
            runU.add(runUp);
        }
        for (int i = 0; i < 3; i++) {
            Image runDown = new Image(getClass().getResourceAsStream("/sprites/player/run/down/0"+i+".png"),50,50,false,false);
            runD.add(runDown);
        }
    }
    public void paint(){
        onMove();
        if(state == 0){
            graphicsContext.drawImage(idles.get(frame%3), position.getX(), position.getY());
            frame++;
        }
        else if(state == 1){
            graphicsContext.drawImage(runR.get(frame%3), position.getX(), position.getY());
            frame++;
        } else if (state==2) {
            graphicsContext.drawImage(runL.get(frame%3), position.getX(), position.getY());
            frame++;
        } else if (state==3) {
            graphicsContext.drawImage(runU.get(frame%2), position.getX(), position.getY());
            frame++;
        } else if (state==4) {
            graphicsContext.drawImage(runD.get(frame%2), position.getX(), position.getY());
            frame++;
        }
    }
    public void onMove(){
        if (up){
            position.setY(position.getY() - 30);
        }
        if (down){
            position.setY(position.getY() + 30);
        }
        if (right){
            position.setX(position.getX() + 20);
        }
        if (left){
            position.setX(position.getX() - 20);
        }
    }

    public void setOnKeyPressed(KeyEvent event){
        switch (event.getCode()){
            //case A -> System.out.println("Tecla A");
            case UP -> {state = 3; up = true; }
            case DOWN -> {state = 4; down = true;}
            case RIGHT -> {state= 1; right = true;}
            case LEFT -> {state = 2; left = true;}
        }
    }

    public void onKeyReleased(KeyEvent event){
        switch (event.getCode()){
            //case A -> System.out.println("Tecla A");
            case UP -> {state= 0; up = false;}
            case DOWN -> {state = 0; down = false;}
            case RIGHT -> {state = 0; right = false;}
            case LEFT -> {state= 0; left = false;}
        }
    }

}
