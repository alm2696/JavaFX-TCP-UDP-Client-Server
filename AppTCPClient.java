package mod03_02;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Application demonstrating a TCP client.
 * This class sets up the primary stage and initializes the TCP client.
 * 
 * @author angel
 */
public class AppTCPClient extends Application {

    /**
     * Main entry point for the application.
     * Launches the JavaFX application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Initializes the JavaFX stage and sets up the TCP client scene.
     * @param mainStage The primary stage for this application.
     */
    @Override
    public void start(Stage mainStage) {
        // Create an instance of TCPClient
        TCPClient client = new TCPClient();

        // Set the scene for the primary stage using SceneClient
        mainStage.setScene(SceneClient.create(client));
        
        // Set the title of the main stage
        mainStage.setTitle("TCP Client");
        
        // Define the action to take when the application window is closed
        mainStage.setOnCloseRequest(event -> {
            client.close(); // Ensure the client is properly closed
        });

        // Display the main stage
        mainStage.show();
    }
}
