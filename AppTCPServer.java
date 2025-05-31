package mod03_02;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Application for demonstrating a TCP server.
 * This class sets up the primary stage and initializes the TCP server.
 * 
 * @author angel
 */
public class AppTCPServer extends Application {

    /**
     * Main entry point for the application.
     * Launches the JavaFX application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Launch the JavaFX application with the provided arguments
        Application.launch(args);
    }

    /**
     * Initializes the JavaFX stage and sets up the TCP server scene.
     * @param mainStage The primary stage for this application.
     */
    @Override
    public void start(Stage mainStage) {
        // Create an instance of TCPServer
        TCPServer server = new TCPServer();

        // Set the scene for the primary stage using SceneServer
        mainStage.setScene(SceneServer.create(server));
        
        // Define the action to take when the application window is closed
        mainStage.setOnCloseRequest(event -> {
            server.end(); // Ensure the server is properly stopped
        });

        // Set the title of the main stage
        mainStage.setTitle("TCP Server");
        
        // Display the main stage
        mainStage.show();
    }
}
