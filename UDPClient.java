package mod03_02;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javafx.scene.control.TextArea;

/**
 * Implementation of a UDP client that sends messages to a UDP server.
 * 
 * @author angel
 */
public class UDPClient implements NetClient {

    // DatagramSocket for sending UDP packets
    private DatagramSocket socket;
    
    // TextArea for logging messages to the UI
    private TextArea output = null;

    /**
     * Send a message to the UDP server.
     * @param message The message to send.
     */
    @Override
    public void send(String message) {
        try {
            // Create a new DatagramSocket for sending the packet
            this.socket = new DatagramSocket();
            
            // Convert the message to bytes
            byte[] buffer = new String("UDP Message").getBytes();
            
            // Define the address and port of the UDP server
            InetAddress address = InetAddress.getByName("localhost");
            
            // Create a DatagramPacket with the message and target address
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
            
            // Log the action of sending a UDP packet
            this.log("Sending a UDP packet");
            
            // Send the UDP packet
            this.socket.send(packet);
            
            // Close the DatagramSocket
            this.socket.close();
            
        } catch (Exception exception) {
            // Log any exceptions that occur during the send operation
            this.log("EXCEPTION: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * Set the UI TextArea to use for logging output.
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
