module UI_module {
    requires java.desktop;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.web;

    opens frontend to javafx.graphics, javafx.base;
}