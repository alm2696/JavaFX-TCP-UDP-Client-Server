package mod03_02;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Application demonstrating a UDP client.
 * This class sets up the primary stage and initializes the UDP client.
 * 
 * @author angel
 */
public class AppUDPClient extends Application {

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
     * Initializes the JavaFX stage and sets up the UDP client scene.
     * @param mainStage The primary stage for this application.
     */
    @Override
    public void start(Stage mainStage) {
        // Create an instance of UDPClient
        UDPClient client = new UDPClient();

        // Set the scene for the primary stage using SceneClient
        mainStage.setScene(SceneClient.create(client));
        
        // Set the title of the main stage
        mainStage.setTitle("UDP Client");
        
        // Display the main stage
        mainStage.show();
    }
}
