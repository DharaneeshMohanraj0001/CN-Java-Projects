import java.io.*;
import java.net.*;

public class TCPServer {
public static void main(String[] args) throws Exception {
ServerSocket serverSocket = new ServerSocket(5000);
System.out.println("Server started. Waiting for client...");
Socket socket = serverSocket.accept();
System.out.println("Client connected!");

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
DataInputStream dis = new DataInputStream(socket.getInputStream());
DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

System.out.print("Enter total number of frames to send: ");

int totalFrames = Integer.parseInt(br.readLine());

System.out.print("Enter window size: ");
int windowSize = Integer.parseInt(br.readLine());

int frame = 0;
while (frame < totalFrames) {
int lastFrame = Math.min(frame + windowSize, totalFrames);
for (int i = frame; i < lastFrame; i++) {
System.out.println("Sending frame " + i);
dos.writeInt(i); 
dos.flush();
}

int ack = dis.readInt();

if (ack == -1) {
System.out.println("Negative ACK received! Some frame was lost.");
System.out.println("Retransmitting from frame " + frame + "...");
} else {
System.out.println("ACK received for frame " + ack);
frame = ack + 1;
}
}
dos.close();
dis.close();
socket.close();
serverSocket.close();
}
}
