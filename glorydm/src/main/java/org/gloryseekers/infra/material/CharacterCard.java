package org.gloryseekers.infra.material;

import java.io.IOException;
import java.io.InputStream;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

/**
 * The CharacterCard is a Node used for showing character images, it's player name and it's current and max load. 
 */
public class CharacterCard extends AnchorPane {

    private final static int IMG_WIDTH = 800;
    private final static int IMG_HEIGHT = 800;

    @FXML 
    private Label nameText;

    @FXML 
    private Label loadText;

    @FXML 
    private ImageView image;
    
    /**
     * Instances a new CharacterCard.
     */
    private CharacterCard() { // This is so f** slow, idk if I can just make an static loader or something.
        FXMLLoader fxmlLoader = new FXMLLoader(CharacterCard.class.getResource("CharacterCard.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try{
            fxmlLoader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public CharacterCard(String nameText, String loadText) {
        this(nameText,loadText,CharacterCard.class.getResourceAsStream("default.png"));   
    }

    public CharacterCard(String nameText, String loadText, String imageUrl) {
        this();
        setNameText(nameText);
        setLoadText(loadText);
        setImage(new Image(imageUrl, IMG_WIDTH, IMG_HEIGHT, false, false));
    }

    public CharacterCard(String nameText, String loadText, InputStream inputStream) {
        this();
        setNameText(nameText);
        setLoadText(loadText);
        setImage(new Image(inputStream, IMG_WIDTH, IMG_HEIGHT, false, false));
    }

    public StringProperty nameTextProperty() {
        return nameText.textProperty();
    }

    public StringProperty loadTextProperty() {
        return loadText.textProperty();
    }

    public void setNameText(String value) {
        nameTextProperty().set(value);
    }

    public void setLoadText(String value) {
        loadTextProperty().set(value);
    }

    public ObjectProperty<Image> imageProperty() {
        return this.image.imageProperty();
    } 

    public void setImage(Image image) {
        this.image.setImage(image);
    }

}
