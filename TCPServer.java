package mod03_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 * Implementation of a basic TCP server that listens for client connections,
 * processes incoming messages, and sends responses.
 * 
 * @author angel
 */
public class TCPServer implements NetServer {

    // Instance variable to hold the server socket
    private ServerSocket socket;
    
    // Flag to indicate whether the server is running
    private boolean running = false;
    
    // TextArea for logging messages
    private TextArea output = null;

    /**
     * Start the TCP server and begin accepting connections.
     */
    @Override
    public void start() {
        try {
            // Create a ServerSocket to listen for incoming connections on port 5000
            this.socket = new ServerSocket(5000, 10);
            
            // Set a timeout for socket operations to allow the JavaFX UI to update
            this.socket.setSoTimeout(300);
            
            // Set the server state to running
            this.running = true;
            
            // Update the UI to indicate the server has started
            this.log("TCP Server started");
            
            // Begin accepting incoming connections
            this.acceptConnections();

        } catch (Exception exception) {
            // Log any exceptions that occur during server startup
            this.log("EXCEPTION: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * Wait for and handle incoming client connections.
     */
    private void acceptConnections() {
        try {
            // Wait for an incoming connection
            // This call blocks until a connection is made or a timeout occurs
            Socket clientSocket = this.socket.accept();
            
            // Handle the connection with the client
            this.handleConnection(clientSocket);
            
            // If the server is not running, close the server socket
            if (!this.running) {
                this.socket.close();
            }

        } catch (SocketTimeoutException exception) {
            // Handle timeout exceptions; no action needed here
        } catch (Exception exception) {
            // Log any other exceptions that occur
            this.log("EXCEPTION: " + exception.getMessage());
            exception.printStackTrace();
            
            // Stop accepting new connections if an error occurs
            return;
        }
        
        // If the server is still running, schedule the method to be called again
        if (this.running) {
            Platform.runLater(() -> TCPServer.this.acceptConnections());
        }
    }

    /**
     * Handle communication with a connected client.
     * @param clientSocket The socket used to communicate with the client.
     */
    private void handleConnection(Socket clientSocket) {
        try {
            // Log connection details
            this.log("Connection accepted");
            this.log("   Address: " + clientSocket.getInetAddress());
            this.log("      Port: " + clientSocket.getPort());
            this.log("");
        
            // Create a BufferedReader to read messages from the client
            BufferedReader netIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            // Read and log the message from the client
            String line = netIn.readLine();
            this.log("MESSAGE: " + line);
            
            // Create a PrintWriter to send a response back to the client
            PrintWriter netOut = new PrintWriter(clientSocket.getOutputStream(), true);
            netOut.println("Message received.");
            
            // Close the client socket as communication is complete
            clientSocket.close();
            
        } catch (Exception exception) {
            // Log any exceptions that occur during communication with the client
            this.log("EXCEPTION: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * Stop the server and release resources.
     */
    @Override
    public void end() {
        if (this.running) {
            // Log the server stopping message
            this.log("Stopping the server");
        }
        
        // Set the running flag to false
        this.running = false;
    }

    /**
     * Set the UI TextArea for logging output.
     * @param output The TextArea to use for logging messages.
     */
    @Override
    public void setLog(TextArea output) {
        this.output = output;
    }

    /**
     * Log messages to the UI TextArea.
     * @param message The message to log.
     */
    private void log(String message) {
        // Append the message to the TextArea and add a new line
        this.output.appendText(message + "\n");
    }
}
