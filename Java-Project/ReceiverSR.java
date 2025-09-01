import java.io.*;
import java.net.*;
import java.util.*;

public class ReceiverSR {
public static void main(String[] args) {
try (Socket socket = new Socket("127.0.0.1", 5001)) {
BufferedReader in = new BufferedReader(new
InputStreamReader(socket.getInputStream()));
PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

Scanner sc = new Scanner(System.in);

while (true) {
String msg = in.readLine();
if (msg == null) break;
String[] parts = msg.split(" ");
if (!parts[0].equals("FRAME")) continue;

int frameId = Integer.parseInt(parts[1]);
boolean isRetx = (parts.length >= 3 && parts[2].equals("RETX"));

if (!isRetx) {
System.out.print("Did you receive frame " + frameId + " successfully? (y/n): ");
String ans = sc.nextLine().trim().toLowerCase();
if (ans.equals("y")) {
System.out.println("Frame " + frameId + " received successfully.");
System.out.println("Sending ACK for frame " + frameId);
out.println("ACK " + frameId);
System.out.println("ACK\n");
} else {
System.out.println("Frame " + frameId + " lost/corrupted!");
System.out.println("Sending NACK for frame " + frameId);
out.println("NACK " + frameId);
System.out.println("NACK\n");
}

} else {
System.out.print("Did you receive frame " + frameId + " successfully? (y/n): ");
String ans = sc.nextLine().trim().toLowerCase();
if (ans.equals("y")) {
System.out.println("Frame " + frameId + " received successfully(retransmitted).");
System.out.println("Sending ACK for frame " + frameId);
out.println("ACK " + frameId);
System.out.println("ACK\n");
} else {
System.out.println("Frame " + frameId + " still not okay. Sending NACK for frame " + frameId);
out.println("NACK " + frameId);
System.out.println("NACK\n");
}
}
}
socket.close();
} catch (Exception e) {
e.printStackTrace();
}
}
}
