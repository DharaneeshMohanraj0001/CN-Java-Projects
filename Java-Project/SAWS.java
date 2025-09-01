import java.io.*;
import java.net.*;
import java.util.*;
public class SAWS {
public static void main(String[] args) throws Exception {
DatagramSocket serverSocket = new DatagramSocket(9876);
byte[] receiveData = new byte[1024];
byte[] sendData;
System.out.println("Server is running...");
while (true) {
DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
serverSocket.receive(receivePacket);
String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
InetAddress clientIP = receivePacket.getAddress();
int clientPort = receivePacket.getPort();
System.out.println("Received Frame: " + sentence);
if (new Random().nextInt(5) == 0) { 
sendData = "NAK".getBytes(); 
System.out.println("Sending NAK for: " + sentence);
} else {
sendData = "ACK".getBytes(); 
System.out.println("Sending ACK for: " + sentence);
}
DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, 
clientPort);
serverSocket.send(sendPacket);
}
}
}
