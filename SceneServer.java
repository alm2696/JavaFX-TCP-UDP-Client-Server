package mod03_02;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Utility class to create a scene for a network server.
 * The scene includes buttons to start, stop the server, and a TextArea for logging output.
 * 
 * @author angel
 */
public class SceneServer {

    // Static variable to store the current scene to avoid creating multiple instances
    private static Scene currentScene = null;

    /**
     * Creates a JavaFX scene for the network server.
     * The scene includes "Start" and "End" buttons for server control and a TextArea for logging.
     * 
     * @param server The NetServer implementation to be used for controlling the server and logging.
     * @return The created Scene object.
     */
    public static Scene create(NetServer server) {
        
        // If the scene has already been created, return the existing instance
        if (SceneServer.currentScene != null)
            return SceneServer.currentScene;

        // Create layout containers
        HBox hBox = new HBox(2);  // Horizontal box to hold the VBox containers
        VBox leftVBox = new VBox(2);  // Left VBox for buttons
        VBox rightVBox = new VBox(2);  // Right VBox for output TextArea
        hBox.getChildren().addAll(leftVBox, rightVBox);

        // Create buttons for starting and stopping the server
        Button start = new Button("Start");
        Button end = new Button("End");
        leftVBox.getChildren().addAll(start, end);

        // Create the TextArea for logging output
        TextArea output = new TextArea();
        rightVBox.getChildren().add(output);

        // Set the TextArea for logging in the server
        server.setLog(output);

        // Define actions for the buttons
        start.setOnAction(event -> { 
            server.start();  // Start the server when the "Start" button is pressed
        });
        end.setOnAction(event -> {
            server.end();  // Stop the server when the "End" button is pressed
        });

        // Create and return the Scene with the configured layout
        return new Scene(hBox);
    }
}
