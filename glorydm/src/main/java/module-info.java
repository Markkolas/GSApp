module org.gloryseekers {
    requires javafx.controls;
    requires xstream;
    exports org.gloryseekers;
    exports org.gloryseekers.infra to javafx.graphics;
    opens org.gloryseekers.domain.model to xstream;
}