module post.post3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires json.simple;
    requires com.google.gson;
    requires commons.io;
     requires org.apache.commons.lang3;

    requires org.json;
    opens post.post3 to javafx.fxml;
    exports post.post3;
}