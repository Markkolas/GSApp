module org.gloryseekers {
    requires javafx.controls;
    requires javafx.fxml;
    opens org.gloryseekers to javafx.fxml;
    exports org.gloryseekers;
    exports org.gloryseekers.infra to javafx.graphics;
}