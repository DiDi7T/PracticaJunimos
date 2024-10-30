module org.icesi.junimosvalley {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.icesi.junimosvalley to javafx.fxml;
    exports org.icesi.junimosvalley;
    exports org.icesi.junimosvalley.model;
    opens org.icesi.junimosvalley.model to javafx.fxml;
}