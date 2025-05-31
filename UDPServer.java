package mod03_02;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 * Implementation of a basic UDP server that receives UDP packets.
 * 
 * @author angel
 */
public class UDPServer implements NetServer {

    // DatagramSocket for receiving UDP packets
    private DatagramSocket socket;
    
    // Server running state
    private boolean running = false;
    
    // TextArea for logging messages to the UI
    private TextArea output = null;
    
    /**
     * Start the UDP server.
     */
    @Override
    public void start() {
        try {
            // Create the DatagramSocket for communication on port 5000
            this.socket = new DatagramSocket(5000);
            
            // Set the timeout for communications to allow UI updates
            socket.setSoTimeout(300);
            
            // Set the server state to running
            this.running = true;
            
            // Log that the server has started
            this.log("UDP Server started\n");
            
            // Start checking for incoming packets
            this.checkForPacket();

        } catch (Exception exception) {
            // Log any exceptions and print the stack trace
            this.log("EXCEPTION: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
    
    /**
     * Check for incoming UDP packets.
     */
    private void checkForPacket() {
        // Create a buffer to store the incoming packet data
        byte[] buffer = new byte[65536];
        
        // Create a DatagramPacket to hold the incoming packet
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        
        try {
            // Receive a packet from the network
            // This will block until a packet is received or a timeout occurs
            this.socket.receive(packet);
            
            // Log information about the received packet
            this.log("Packet received");
            this.log("   Address: " + packet.getAddress());
            this.log("      Port: " + packet.getPort());
            this.log("      Data: " + new String(packet.getData()));
            this.log("");
            
        } catch (SocketTimeoutException exception) {
            // Handle timeout exceptions; no action needed here
        } catch (Exception exception) {
            // Log any other exceptions and print the stack trace
            this.log("EXCEPTION: " + exception.getMessage());
            exception.printStackTrace();
            
            // If an error occurs, stop checking for packets
            return;
        }
        
        // Check if the server is still running
        if (this.running) {
            // Schedule the next packet check on the JavaFX event loop
            Platform.runLater(() -> UDPServer.this.checkForPacket());
        }
        
        // If the server is not running, close the socket
        if (!this.running) {
            this.socket.close();
        }
    }
    
    /**
     * Stop the UDP server.
     */
    @Override
    public void end() {
        // Log that the server is stopping
        if (this.running) {
            this.log("Stopping the server");
        }
        
        // Set the server state to not running
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
