module org.gloryseekers {
    requires javafx.controls;
    requires javafx.fxml;
    opens org.gloryseekers to javafx.fxml;
    opens org.gloryseekers.infra.material to javafx.fxml;
    exports org.gloryseekers;
    exports org.gloryseekers.infra.view to javafx.graphics;
    exports org.gloryseekers.infra.material to javafx.fxml;
}