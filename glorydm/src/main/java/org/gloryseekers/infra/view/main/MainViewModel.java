package org.gloryseekers.infra.view.main;

import javafx.beans.property.SimpleStringProperty;

public class MainViewModel {

    private SimpleStringProperty currentGameDate = new SimpleStringProperty();

    public MainViewModel() {
        this.currentGameDate.set(getCurrentGameDate());
    }

    private String getCurrentGameDate() {
        return "Dia 6 del mes 5 de la 2 estación";
    }

    public SimpleStringProperty getCurrentGameDateProperty() {
        return this.currentGameDate;
    }
}
