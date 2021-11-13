module org.gloryseekers {
    requires javafx.controls;
    requires xstream;
    requires transitive log4j.api; 
    requires javafx.fxml;
    exports org.gloryseekers;
    exports org.gloryseekers.infra to javafx.graphics;
    exports org.gloryseekers.infra.view to javafx.graphics, javafx.fxml;
    exports org.gloryseekers.infra.material to javafx.fxml;
    opens org.gloryseekers.domain.model to xstream;
    opens org.gloryseekers to javafx.fxml;
    opens org.gloryseekers.infra.material to javafx.fxml;
    opens org.gloryseekers.infra.view.main to javafx.fxml;
    opens org.gloryseekers.infra.view to javafx.fxml;
}