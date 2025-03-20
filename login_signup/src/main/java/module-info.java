module com.example.login_signup {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens com.example.login_signup to javafx.fxml;
    exports com.example.login_signup;
    exports com.example.login_signup.controller;
    opens com.example.login_signup.controller to javafx.fxml;
    exports com.example.login_signup.database;
    opens com.example.login_signup.database to javafx.fxml;
    exports com.example.login_signup.session;
    opens com.example.login_signup.session to javafx.fxml;
}