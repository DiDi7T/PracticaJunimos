package org.icesi.junimosvalley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.icesi.junimosvalley.model.*;
import org.icesi.junimosvalley.util.IDistance;

import java.util.ArrayList;

public class ScreenA extends BaseScreen {

    private Player player;
    private Box box1;
    private ArrayList<Objects> objetos;
    private ArrayList<Position> existingPositions;



    public ScreenA(Canvas canvas) {
        super(canvas);
        this.player = new Player(super.canvas);
        this.box1 = new Box(super.canvas);
        this.objetos = new ArrayList<>();
        this.existingPositions = new ArrayList<>();

        // Crear objetos sin superposición
        for (int i = 0; i < 5; i++) { // o la cantidad que prefieras
            Objects obj = new Objects(canvas, existingPositions);
            objetos.add(obj); // Agregar el objeto a la lista
            obj.paint(); // Pintar inmediatamente (opcional, podría hacerse en el método paint)
        }
    }

    @Override
    public void paint(){

        super.gc.setFill(Color.WHITE);
        super.gc.fillRect(0, 0, super.canvas.getWidth(), super.canvas.getHeight());
        player.paint();
        box1.paint();

        IDistance iDistance = (from, to) -> {
            return Math.sqrt(
                    Math.pow(from.getX() - to.getX(), 2) +
                            Math.pow(from.getY() - to.getY(), 2)
            );
        };


        for (Objects objeto : objetos) {
            objeto.paint();
            double distanceObj = iDistance.distance(objeto.getPosition(), player.getPosition());
            System.out.println("obj distance: "+objeto  + " |  " +distanceObj);
        }


        double distance = iDistance.distance(player.getPosition(), box1.getPosition());

        System.out.println(distance);

        System.out.println(player.getPosition().getX());

        if (player.getPosition().getX() < 12){
            player.getPosition().setX(12);
        }

        if (player.getPosition().getY() <5){
            player.getPosition().setY(5);
        }
        if (player.getPosition().getY() > 520){
            player.getPosition().setY(520);
        }

        if (distance < 160){

            // se calcula la diferencia de posición entre los dos elementos
            double posX = player.getPosition().getX() - box1.getPosition().getX();
            double posY = player.getPosition().getY() - box1.getPosition().getY();

            // se define un vector de diferencia
            Position diff = new Position(posX, posY);

            // se normaliza el vector
            diff.normalize();
            // y se multiplica por un scalar para que el cambio de
            // posición no sea de 1 en 1 si no de el valor del scalar
            diff.setSpeed(6);

            // a la posición actual del elemento que queremo mover, hacemos
            // que varie, en la diferencia de elementos
            box1.getPosition().setX(box1.getPosition().getX() + diff.getX());
            box1.getPosition().setY(box1.getPosition().getY() + diff.getY());

            /*
             *     diff
             *  A ------> B
             * */
        }

    }

    @Override
    public void onKeyPressed(KeyEvent event){
        player.setOnKeyPressed(event);


        if (event.getCode() == KeyCode.SPACE){
            player.resetStates();
            HelloController.SCREEN = 1;
        }
    }

    @Override
    public void onKeyReleased(KeyEvent event){
        player.onKeyReleased(event);
    }
}
