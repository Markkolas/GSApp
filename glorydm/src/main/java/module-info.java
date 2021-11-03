module org.gloryseekers {
    requires javafx.controls;
    requires com.fasterxml.jackson.dataformat.xml;
    exports org.gloryseekers;
    exports org.gloryseekers.infra to javafx.graphics;
    exports org.gloryseekers.domain.model to com.fasterxml.jackson.databind;
    requires javafx.fxml;
    opens org.gloryseekers to javafx.fxml;
    opens org.gloryseekers.infra.material to javafx.fxml;
    opens org.gloryseekers.infra.view.main to javafx.fxml;
    opens org.gloryseekers.infra.view to javafx.fxml;
    opens org.gloryseekers.domain.model to com.fasterxml.jackson.databind;
    exports org.gloryseekers.infra.view to javafx.graphics, javafx.fxml;
    exports org.gloryseekers.infra.material to javafx.fxml;
}