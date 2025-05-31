package mod03_02;

import javafx.scene.control.TextArea;

/**
 * Interface representing a network server.
 * Defines methods for starting, stopping the server, and setting a log output.
 * 
 * @author angel
 */
public interface NetServer {

    /**
     * Starts the server and begins listening for connections.
     */
    void start();

    /**
     * Stops the server and closes any open connections.
     */
    void end();

    /**
     * Sets the UI TextArea for logging output.
     * @param output The TextArea used for displaying server activity and logs.
     */
    void setLog(TextArea output);
}
