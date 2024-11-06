package org.icesi.junimosvalley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Objects {

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Position position;
    private Image image;
    private static final double OBJETO_SIZE = 30; // Tamaño aproximado de la imagen

    public Objects(Canvas canvas, ArrayList<Position> existingPositions) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();

        // Cargar imagen aleatoria (roca o rama)
        if (Math.random() < 0.5) {
            this.image = new Image(getClass().getResourceAsStream("/sprites/objects/roca1.png"), OBJETO_SIZE, OBJETO_SIZE, false, false);
        } else {
            this.image = new Image(getClass().getResourceAsStream("/sprites/objects/palo.png"), OBJETO_SIZE, OBJETO_SIZE, false, false);
        }

        // Generar una posición aleatoria que no se solape con otras posiciones
        do {
            double x = Math.random() * (canvas.getWidth() - OBJETO_SIZE);
            System.out.println("x cor: " + x );
            double y = Math.random() * (canvas.getHeight() - OBJETO_SIZE);
            this.position = new Position(x, y);
        } while (isOverlapping(position, existingPositions));

        // Agregar la posición del nuevo objeto a la lista de posiciones existentes
        existingPositions.add(this.position);
    }

    private boolean isOverlapping(Position newPosition, ArrayList<Position> existingPositions) {
        for (Position pos : existingPositions) {
            double dx = pos.getX() - newPosition.getX();
            double dy = pos.getY() - newPosition.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);
            if (distance < OBJETO_SIZE) {
                return true; // Hay superposición
            }
        }
        return false; // No hay superposición
    }

    public void paint() {
        graphicsContext.drawImage(image, position.getX(), position.getY());
    }

    public Position getPosition() {
        return position;
    }
    public boolean collidesWith(Player player) {
        double dx = player.getPosition().getX() - this.position.getX();
        double dy = player.getPosition().getY() - this.position.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < (50 / 2 + 50 / 2); // Ajusta 50 a la medida de tus objetos y el player
    }


}
