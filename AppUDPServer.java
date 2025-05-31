package mod03_02;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Application for demonstrating a UDP server.
 * This class sets up the primary stage and initializes the UDP server.
 * 
 * @author angel
 */
public class AppUDPServer extends Application {

    /**
     * Main entry point for the application.
     * Launches the JavaFX application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        Application.launch(args);
    }

    /**
     * Initializes the JavaFX stage and sets up the UDP server scene.
     * @param mainStage The primary stage for this application.
     */
    @Override
    public void start(Stage mainStage) {
        // Create an instance of UDPServer
        UDPServer server = new UDPServer();
        
        // Set the scene for the primary stage using SceneServer
        mainStage.setScene(SceneServer.create(server));
        
        // Define an action to stop the server when the application closes
        mainStage.setOnCloseRequest((event) -> {
            server.end();
        });
        
        // Set the title of the main stage
        mainStage.setTitle("UDP Server");
        
        // Display the main stage
        mainStage.show();
    }
}
