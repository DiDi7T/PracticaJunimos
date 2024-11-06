package org.icesi.junimosvalley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.icesi.junimosvalley.model.Player;
import org.icesi.junimosvalley.model.Position;
import org.icesi.junimosvalley.util.IDistance;
import org.icesi.junimosvalley.model.HelloController;

public class ScreenB extends BaseScreen{

    private Player player;

    public ScreenB(Canvas canvas){
        super(canvas);

        this.player = new Player(super.canvas);

    }

    @Override
    public void paint() {


        super.gc.setFill(Color.GRAY);
        super.gc.fillRect(0,0, super.canvas.getWidth(), super.canvas.getHeight());
        player.paint();

        IDistance iDistance = (from, to) -> {
            return Math.sqrt(
                    Math.abs(
                            Math.pow(from.getX() - to.getX(), 2) +
                                    Math.pow(from.getY() - to.getY(), 2)
                    )
            );
        };

        double distance = iDistance.distance(player.getPosition(),
                new Position(0, 0));

        System.out.println(distance);

        if (distance < 40){
            player.resetStates();
            player.setPosition(new Position(100, 100));
            HelloController.SCREEN = 0;
        }

    }


    @Override
    public void onKeyPressed(KeyEvent event){

        player.setOnKeyPressed(event);

    }

    @Override
    public void onKeyReleased(KeyEvent event){
        player.onKeyReleased(event);
    }
}
