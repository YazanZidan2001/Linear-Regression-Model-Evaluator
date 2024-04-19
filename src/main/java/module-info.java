module com.example.ai_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires weka;



    opens com.example.ai_project to javafx.fxml;
    exports com.example.ai_project;
}