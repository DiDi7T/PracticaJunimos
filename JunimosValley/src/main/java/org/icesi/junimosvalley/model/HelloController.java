package org.icesi.junimosvalley.model;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import org.icesi.junimosvalley.screens.BaseScreen;
import org.icesi.junimosvalley.screens.ScreenA;
import org.icesi.junimosvalley.screens.ScreenB;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ArrayList<BaseScreen> screens;
    public static int SCREEN = 0;
    private boolean isRunning;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.graphicsContext = canvas.getGraphicsContext2D();
        screens = new ArrayList<>(2);
        screens.add(new ScreenA(canvas));
        screens.add(new ScreenB(canvas));

        initActions();
        isRunning = true;
        // Hilo
        new Thread(() -> { // Runable -> lambda
            while (isRunning){
                Platform.runLater( () -> {
                    screens.get(SCREEN).paint();
                });
                try {
                    Thread.sleep(200);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void setRunning(){
        isRunning = false;
    }

    public void initActions(){
        canvas.setOnKeyPressed(event -> {
            screens.get(SCREEN).onKeyPressed(event);
        });

        canvas.setOnKeyReleased(event -> {
            screens.get(SCREEN).onKeyReleased(event);
        });
    }
}