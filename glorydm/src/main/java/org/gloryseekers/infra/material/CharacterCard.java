package org.gloryseekers.infra.material;

import java.io.IOException;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

public class CharacterCard extends AnchorPane {
    @FXML 
    private Label nameText;

    @FXML 
    private Label loadText;

    @FXML 
    private ImageView image;


    public CharacterCard () {
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

    private ImageView getImageView() { 
        return this.image;
    }

    public void  setImage(Image image) {
        getImageView().setImage(image);
    }

}
