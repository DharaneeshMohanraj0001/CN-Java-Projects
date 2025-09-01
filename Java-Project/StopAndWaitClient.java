import java.io.*;
import java.net.*;
import java.util.*;
public class StopAndWaitClient {
public static void main(String[] args) throws Exception {
DatagramSocket clientSocket = new DatagramSocket();
InetAddress serverIP = InetAddress.getByName("localhost");
BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
int frameNo = 0;
while (true) {
System.out.print("Enter message (type 'exit' to quit): ");
String message = inFromUser.readLine();
if (message.equalsIgnoreCase("exit")) {
break;
}
String frame = "Frame-" + frameNo + ": " + message;
byte[] sendData = frame.getBytes();
DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, 
serverIP, 9876);
clientSocket.send(sendPacket);
System.out.println("Sent: " + frame);
byte[] receiveData = new byte[1024];
DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
clientSocket.receive(receivePacket);
String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
System.out.println("Received: " + response);
if (response.equals("ACK")) {
frameNo++; // send next frame
} else if (response.equals("NAP")) {
System.out.println("Resending frame: " + frame);
clientSocket.send(sendPacket); // resend same frame
}
}
clientSocket.close();
}
}


