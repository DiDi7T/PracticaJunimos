package org.icesi.junimosvalley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.icesi.junimosvalley.model.Player;

public class ScreenA {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Player player;

    public ScreenA(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.player = new Player(this.canvas);
    }

    public void paint(){
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        player.paint();
    }

    public void onKeyPressed(KeyEvent event){
        player.setOnKeyPressed(event);
    }

    public void onKeyReleased(KeyEvent event){
        player.onKeyReleased(event);
    }
}
