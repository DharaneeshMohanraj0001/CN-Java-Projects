import java.io.*;
import java.net.*;

public class TCPClient {
public static void main(String[] args) throws Exception {
Socket socket = new Socket("localhost", 5000);
DataInputStream dis = new DataInputStream(socket.getInputStream());
DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

int expectedFrame = 0;
int lastAck = -1;

while (true) {
try {
int frame = dis.readInt();

System.out.print("Did you receive frame " + frame + " successfully? (y/n): ");
String response = br.readLine();

if (response.equalsIgnoreCase("y")) {
if (frame == expectedFrame) {
System.out.println("Frame " + frame + " received successfully.");
lastAck = frame;
expectedFrame++;
} else {
System.out.println("Out-of-order frame " + frame + " discarded!");
}
} else {
System.out.println("Frame " + frame + " lost/corrupted!");
dos.writeInt(-1); 
dos.flush();
continue;
}

dos.writeInt(lastAck);
dos.flush();

} catch (EOFException e) {
break;
}

}

dos.close();
dis.close();
socket.close();
}
}
