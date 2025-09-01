import java.io.*;
import java.net.*;
public class slidingwindowserver {
public static void main(String[] args) throws Exception {
ServerSocket serverSocket = new ServerSocket(5000);
Socket socket = serverSocket.accept();
DataInputStream in = new DataInputStream(socket.getInputStream());
DataOutputStream out = new DataOutputStream(socket.getOutputStream());
int windowSize = in.readInt();
int totalFrames = in.readInt();
System.out.println("Sliding window size: " + windowSize);
System.out.println("Expecting " + totalFrames + " frames.");
int expectedFrame = 0;
while (expectedFrame < totalFrames) {
for (int i = 0; i < windowSize && expectedFrame < totalFrames; i++) {
int frame = in.readInt();
System.out.println("Received Frame: " + frame);
if (frame == expectedFrame) {
expectedFrame++;
}
}
out.writeInt(expectedFrame);
System.out.println("Sent ACK: " + expectedFrame);
}
System.out.println("All frames received successfully.");
socket.close();
serverSocket.close();
}
}

