/*
 * This class creates a UDP client to listen for messages from the transmittor.
 */

package Receiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class udpClient implements Runnable {

	//Set port to listen on
	private final int port;

    public udpClient(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        /**
         * Bind the client socket to the port on which you expect to
         * read incoming messages
         */
        try (DatagramSocket clientSocket = new DatagramSocket(port)) {
            /**
             * Create a byte array buffer to store incoming data. If the message length
             * exceeds the length of your buffer, then the message will be truncated. To avoid this,
             * you can simply instantiate the buffer with the maximum UDP packet size, which
             * is 65506
             */

            byte[] buffer = new byte[65507];

            // Set a timeout of 3000 ms for the client.
            //clientSocket.setSoTimeout(3000);
            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);


                clientSocket.receive(datagramPacket);

                String receivedMessage = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                //System.out.println(receivedMessage);
                
                //Send to packetManager
                packetManager.unpackMessage(receivedMessage);
                
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Timeout. Client is closing.");
        }
    }
	

}
