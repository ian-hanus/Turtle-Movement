package frontend;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.File;

import static frontend.Language.ENGLISH;

/**
 * Stores the features of our simulation that should be able to be changed from a series of dropdowns and menus, allowing
 * user to change background color, pen color, pen size, and turtle image
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

    /**
     * The configuration constructor establishes all of the defaults of the new window. These can later be changed through
     * interacting with the box or running terminal commands
     */
    public Configuration(){
        myBackgroundColor = Color.WHITE;
        File turtleFile = new File(defaultPath);
        myTurtleImage = new Image(turtleFile.toURI().toString());
        myPenColor = Color.BLACK;
        myLanguage = ENGLISH;
        myPenWidth = DEFAULT_PEN_WIDTH;
    }

    /**
     * Draws each of the components in the configuration into the box that will be passed to the View
     * @param pane
     * @param main
     * @param canvas
     */
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
        Label penLabel = new Label("Pen Width | Pen Up");
        penLabel.getStyleClass().add("slider-label");
        CheckBox penUp = new CheckBox();
        penUp.setTranslateX(22.5);
        penUp.setOnAction(e -> penState(penUp));
        sliderRow.getChildren().addAll(penSlider, penLabel, penUp);
        configBox.getChildren().add(sliderRow);
    }

    private void penState(CheckBox penUp){
        if(penUp.isSelected()){

        }
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
        backgroundColorPicker.setOnAction(e -> canvas.setBackgroundColor(backgroundColorPicker.getValue()));
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
        languageBox.setPromptText("english");
        languageBox.getStyleClass().add("load-button");
        configBox.getChildren().add(languageBox);
        languageBox.setOnAction(e -> this.setLanguage((Language)languageBox.getValue()));
    }

//    public Image getTurtleImage(){
//        return myTurtleImage;
//    }
//
//    public Color getBackgroundColor(){
//        return myBackgroundColor;
//    }

    /**
     * Sets the turtle image equal to the new image chosen in the main view
     * @param turtleImage
     */
    public void setTurtleImage(Image turtleImage){
        myTurtleImage = turtleImage;
    }

//    public void setBackgroundColor(Color color){
//        myBackgroundColor = color;
//    }
//
//    public void setPenColor(Color color){
//        myPenColor = color;
//    }

    /**
     * Changes the language of the user input so that the backend knows what to translate the instructions to
     * @param language is the Language enumeration corresponding to the desired user input language
     */
    public void setLanguage(Language language){
        myLanguage = language;
    }

    /**
     * Gets the language of the user input so that the back end can decode each of the instructions
     * @return the Language enumeration corresponding to the user input language
     */
    public Language getLanguage(){
        return myLanguage;
    }

//    public Color getPenColor(){
//        return myPenColor;
//    }

    /**
     * Sets the width of the pen, controlled by the slider in the configuration box or by command through terminal
     * @param penWidth is the desired width of the pen in pixels
     */
    public void setPenWidth(double penWidth){
        myPenWidth = penWidth;
    }

    /**
     * Gets the width of the pen, which is changed by changing the line width on the canvas
     * @return the width of the pen in pixels
     */
    public double getPenWidth(){
        return myPenWidth;
    }
}
