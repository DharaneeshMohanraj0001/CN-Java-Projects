
    import java.io.*;
import java.net.*;
import java.util.Scanner;
public class SlidingWindowClient {
public static void main(String[] args) throws Exception {
Socket socket = new Socket("localhost", 5000);
DataOutputStream out = new DataOutputStream(socket.getOutputStream());
DataInputStream in = new DataInputStream(socket.getInputStream());
Scanner sc = new Scanner(System.in);
System.out.print("Enter the sliding window size: ");
int windowSize = sc.nextInt();
out.writeInt(windowSize);
System.out.print("Enter the number of frames to send: ");
int totalFrames = sc.nextInt();
out.writeInt(totalFrames);
int frame = 0;
while (frame < totalFrames) {
int sendUpto = Math.min(frame + windowSize, totalFrames);
System.out.println("\nSending frames: " + frame + " to " + (sendUpto - 1));
for (int i = frame; i < sendUpto; i++) {
System.out.println("Sent Frame: " + i);
out.writeInt(i); 
}
int ack = in.readInt();
System.out.println("Received ACK: " + ack);
frame = ack; // move window forward
}
System.out.println("All frames sent successfully.");
socket.close();
sc.close();
}
}

