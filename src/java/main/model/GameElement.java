package main.model;

import main.assistant.Constants;
import main.assistant.Drawing;
import main.assistant.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public abstract class GameElement implements Serializable {
    protected ImageIcon image;
    protected Position position = new Position(0,0);
    protected boolean isPassable; /*Pode passar por cima?*/
    protected boolean isMortal;       /*Se encostar, o Bomberman morre?*/
    protected boolean isCollectable;
    protected boolean isPushable;

    protected GameElement(int posX, int posY,String imgNamePNG) {
        this.isPassable = false;
        this.isMortal = false;
        this.isCollectable = false;
        this.isPushable = false;
        setPosition(posX, posY);
        try {
            image = new ImageIcon(new java.io.File(".").getCanonicalPath() + Constants.PATH + imgNamePNG);
            Image img = image.getImage();
            BufferedImage bufferedImg = new BufferedImage(Constants.CELL_SIDE, Constants.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics graphics = bufferedImg.createGraphics();
            graphics.drawImage(img, 0, 0, Constants.CELL_SIDE, Constants.CELL_SIDE, null);
            image = new ImageIcon(bufferedImg);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Position getPosition() {
        /*TODO: Retirar este método para que objetos externos nao possam operar
        diretamente sobre a posição do Personagem*/
        return position;
    }

    public boolean isPassable() {
        return isPassable;
    }

    public void setIsPassable(boolean isPassable) {
        this.isPassable = isPassable;
    }

    public boolean isMortal() {
        return isMortal;
    }

    public void setIsMortal(boolean isMortal) {
        this.isMortal = isMortal;
    }

    public boolean isCollectable() {
        return isCollectable;
    }

    public void setCollectable(boolean collectable) {
        isCollectable = collectable;
    }

    public boolean isPushable(){return isPushable;}

    public void setIsPushable(boolean pushable){
        this.isPushable = pushable;
    }

    public void autoDraw() {
        Drawing.draw(this.image, position.getPosX(), position.getPosY());
    }

    public boolean setPosition(int posX, int posY) {
        return position.setPosition(posX, posY);
    }
}
