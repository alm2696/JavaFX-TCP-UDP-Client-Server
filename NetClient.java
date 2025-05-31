package mod03_02;

import javafx.scene.control.TextArea;

/**
 * Interface representing a network client.
 * Defines methods for sending messages and setting a log output.
 * 
 * @author angel
 */
public interface NetClient {

    /**
     * Sends a message through the network.
     * @param message The message to be sent.
     */
    void send(String message);
    
    /**
     * Sets the UI TextArea for logging output.
     * @param output The TextArea used for logging network activity.
     */
    void setLog(TextArea output);
}
