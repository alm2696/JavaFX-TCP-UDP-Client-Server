package mod03_02;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Utility class to create a scene for a network client.
 * The scene includes a button to send messages and a TextArea for logging output.
 * 
 * @author angel
 */
public class SceneClient {

    // Static variable to store the current scene to avoid creating multiple instances
    private static Scene currentScene = null;

    /**
     * Creates a JavaFX scene for the network client.
     * The scene includes a "Send" button and a TextArea for logging.
     * 
     * @param client The NetClient implementation to be used for sending messages and logging.
     * @return The created Scene object.
     */
    public static Scene create(NetClient client) {
        
        // If the scene has already been created, return the existing instance
        if (SceneClient.currentScene != null)
            return SceneClient.currentScene;

        // Create layout containers
        HBox hBox = new HBox(2);  // Horizontal box to hold the VBox containers
        VBox leftVBox = new VBox(2);  // Left VBox for buttons
        VBox rightVBox = new VBox(2);  // Right VBox for output TextArea
        hBox.getChildren().addAll(leftVBox, rightVBox);

        // Create the "Send" button
        Button send = new Button("Send");
        leftVBox.getChildren().add(send);

        // Create the TextArea for logging output
        TextArea output = new TextArea();
        rightVBox.getChildren().add(output);

        // Set the TextArea for logging in the client
        client.setLog(output);

        // Define the action for the "Send" button
        send.setOnAction(event -> { 
            client.send("It's a UDP Message");  // Send a predefined message
        });

        // Create and return the Scene with the configured layout
        return new Scene(hBox);
    }
}
