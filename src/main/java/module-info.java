module com.poo2.poo2_l {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens com.poo2.poo2_l to javafx.fxml;
    exports com.poo2.poo2_l;
    exports com.poo2.poo2_l.models;
    opens com.poo2.poo2_l.models to javafx.fxml;
    exports com.poo2.poo2_l.controllers;
    opens com.poo2.poo2_l.controllers to javafx.fxml;
    exports com.poo2.poo2_l.controllers.ui;
    opens com.poo2.poo2_l.controllers.ui to javafx.fxml;
}