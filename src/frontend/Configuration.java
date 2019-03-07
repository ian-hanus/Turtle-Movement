package frontend;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;

import static frontend.Language.ENGLISH;

/**
 * Stores the features of our simulation that should be able to be changed from a series of dropdowns and menus
 */
public class Configuration {
    private Color myBackgroundColor;
    private Image myTurtleImage;
    private String defaultPath = "./src/GUIResources/turtle.png";
    private Color myPenColor;
    private Language myLanguage;
    private double myPenWidth;
    private VBox myConfigurationBox;

    private static final int DEFAULT_PEN_WIDTH = 10;
    private static final int MAX_PEN_WIDTH = 20;
    private static final int MIN_PEN_WIDTH = 1;


    public Configuration(){
        myBackgroundColor = Color.WHITE;
        File turtleFile = new File(defaultPath);
        myTurtleImage = new Image(turtleFile.toURI().toString());
        myPenColor = Color.BLACK;
        myLanguage = ENGLISH;
        myPenWidth = DEFAULT_PEN_WIDTH;
    }

    public void drawConfig(GridPane pane, SLogoMain main, Canvas canvas){
        myConfigurationBox = new RightBox("Configuration").getBox();
        createLoadButton(myConfigurationBox, main, canvas);
        createBackgroundPicker(myConfigurationBox, canvas);
        createPenPicker(myConfigurationBox, canvas);
        createLanguageDropdown(myConfigurationBox);
        createSlider(myConfigurationBox);
        pane.add(myConfigurationBox, 1, 0);
    }

    private void createSlider(VBox configBox){
        HBox sliderRow = new HBox();
        sliderRow.getStyleClass().add("color-picker-row");
        Slider penSlider = new Slider(MIN_PEN_WIDTH, MAX_PEN_WIDTH, 1);
        penSlider.getStyleClass().add("slider");
        penSlider.setOnDragDetected(e -> this.setPenWidth(penSlider.getValue()));
        Label penLabel = new Label("Pen Width");
        penLabel.getStyleClass().add("slider-label");
        sliderRow.getChildren().addAll(penSlider, penLabel);
        configBox.getChildren().add(sliderRow);
    }

    private void createLoadButton(VBox configBox, SLogoMain main, Canvas canvas){
        Button loadImageButton = new Button("");
        loadImageButton.setText("Load New Turtle Image");
        loadImageButton.getStyleClass().add("load-button");
        configBox.getChildren().add(loadImageButton);
        loadImageButton.setOnAction(e -> loadImage(main, canvas));
    }

    private void loadImage(SLogoMain main, Canvas canvas){
        try {
            myTurtleImage = main.loadTurtleImage();
        }
        catch(NullPointerException x){
            ErrorDisplay invalidFile = new ErrorDisplay("Image Loader", "Invalid file");
            invalidFile.display();
        }
        this.setTurtleImage(myTurtleImage);
        canvas.setTurtleImage(myTurtleImage);
    }

    private void createBackgroundPicker(VBox configBox, Canvas canvas){
        ColorPicker backgroundColorPicker = new ColorPicker();
        backgroundColorPicker.setOnAction(e -> canvas.setBackground(new Background(new BackgroundFill(backgroundColorPicker.getValue(), CornerRadii.EMPTY, Insets.EMPTY))));
        HBox colorRow = createColorRow(backgroundColorPicker, "Background Color");
        configBox.getChildren().add(colorRow);
    }

    private void createPenPicker(VBox configBox, Canvas canvas){
        ColorPicker penColorPicker = new ColorPicker();
        HBox colorRow = createColorRow(penColorPicker, "Pen Color");
        configBox.getChildren().add(colorRow);
        penColorPicker.setOnAction(e -> canvas.setPenColor(penColorPicker.getValue()));
    }

    private HBox createColorRow(ColorPicker colorPicker, String labelString){
        HBox colorRow = new HBox();
        colorRow.getStyleClass().add("color-picker-row");
        colorPicker.getStyleClass().add("color-picker");
        Label label= new Label(labelString);
        label.getStyleClass().add("color-picker-label");
        colorRow.getChildren().addAll(colorPicker, label);
        return colorRow;
    }

    private void createLanguageDropdown(VBox configBox){
        ComboBox languageBox = new ComboBox();
        languageBox.setItems(FXCollections.observableArrayList(Language.values()));
        languageBox.setPromptText("Language");
        languageBox.getStyleClass().add("load-button");
        configBox.getChildren().add(languageBox);
        languageBox.setOnAction(e -> this.setLanguage((Language)languageBox.getValue()));
    }

    public Image getTurtleImage(){
        return myTurtleImage;
    }

    public Color getBackgroundColor(){
        return myBackgroundColor;
    }

    public void setTurtleImage(Image turtleImage){
        myTurtleImage = turtleImage;
    }

    public void setBackgroundColor(Color color){
        myBackgroundColor = color;
    }

    public void setPenColor(Color color){
        myPenColor = color;
    }

    public void setLanguage(Language language){
        myLanguage = language;
    }

    public Language getLanguage(){
        return myLanguage;
    }

    public Color getPenColor(){
        return myPenColor;
    }

    public void setPenWidth(double penWidth){
        myPenWidth = penWidth;
    }

    public double getPenWidth(){
        return myPenWidth;
    }
}
