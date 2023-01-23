open module com.poo2.poo2_l {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    exports com.poo2.poo2_l;
    exports com.poo2.poo2_l.models;
    exports com.poo2.poo2_l.controllers.view;
    exports com.poo2.poo2_l.Interfaces;

}