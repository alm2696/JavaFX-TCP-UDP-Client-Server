package mod03_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 * Implementation of a TCP client that communicates with a TCP server.
 * This class handles sending a message to the server and waiting for a response.
 * 
 * @author angel
 */
public class TCPClient implements NetClient {

    // Instance variable to hold the socket connection
    private Socket socket;
    
    // TextArea for logging messages
    private TextArea output = null;

    /**
     * Sends a message to the TCP server.
     * @param message The message to be sent to the server.
     */
    @Override
    public void send(String message) {
        try {
            // Create an InetAddress object for localhost
            InetAddress address = InetAddress.getByName("localhost");
            
            // Initialize a socket connection to the server on port 5000
            this.socket = new Socket(address, 5000);
            
            // Set a timeout for socket operations
            socket.setSoTimeout(300);
            
            // Create a PrintWriter to send data through the socket
            PrintWriter netOut = new PrintWriter(this.socket.getOutputStream(), true);
            
            // Send a message to the server
            netOut.println("TCP Message");
            
            // Wait for a response from the server
            this.waitForResponse();

        } catch (Exception exception) {
            // Log any exceptions that occur
            this.log("EXCEPTION: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * Waits for a response from the server and logs it.
     */
    public void waitForResponse() {
        try {
            // Create a BufferedReader to read the server response
            BufferedReader netIn = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    
            // Wait until data is available from the input stream
            while (this.socket.getInputStream().available() != 0) {
                String line;
                while ((line = netIn.readLine()) != null) {
                    // Log the response from the server
                    this.log("RESPONSE: " + line);
                    // Close the socket connection
                    this.socket.close();
                    return;
                }
            }
            
            // Use Platform.runLater to handle waiting for response in the JavaFX Application Thread
            Platform.runLater(() -> TCPClient.this.waitForResponse());
            
        } catch (SocketTimeoutException exception) {
            // Handle socket timeout exception and retry waiting for a response
            Platform.runLater(() -> TCPClient.this.waitForResponse());
        } catch (Exception exception) {
            // Log any other exceptions that occur
            this.log("EXCEPTION: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * Closes the socket connection.
     */
    public void close() {
        try {
            // Close the socket connection if it is open
            if (this.socket != null && !this.socket.isClosed()) {
                this.socket.close();
            }
        } catch (Exception exception) {
            // Log any exceptions that occur during closing
            this.log("EXCEPTION: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * Sets the UI TextArea for logging output.
     * @param output The TextArea to use for logging messages.
     */
    @Override
    public void setLog(TextArea output) {
        this.output = output;
    }

    /**
     * Logs messages to the UI TextArea.
     * @param message The message to log.
     */
    private void log(String message) {
        // Append the message to the TextArea and add a new line
        this.output.appendText(message + "\n");
    }
}
