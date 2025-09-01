import java.io.*;
import java.net.*;
import java.util.*;

public class SenderSR {
public static void main(String[] args) {
try (ServerSocket serverSocket = new ServerSocket(5001)) {
System.out.println("Server started. Waiting for client...");
Socket socket = serverSocket.accept();
System.out.println("Client connected!");

BufferedReader in = new BufferedReader(new
InputStreamReader(socket.getInputStream()));
PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

Scanner sc = new Scanner(System.in);
System.out.print("Enter total number of frames to send: ");
int totalFrames = sc.nextInt();
System.out.print("Enter window size: ");
int win = sc.nextInt();
System.out.println("\nEnter window size: " + win + "\n");

boolean[] acked = new boolean[totalFrames];
int base = 0, nextSeq = 0;

while (base < totalFrames) {
List<Integer> sentNow = new ArrayList<>();
while (nextSeq < totalFrames && nextSeq < base + win) {
System.out.println("Sending frame " + nextSeq);
out.println("FRAME " + nextSeq);
sentNow.add(nextSeq);
nextSeq++;
}
List<Integer> retransmit = new ArrayList<>();
for (int i = 0; i < sentNow.size(); i++) {
String msg = in.readLine();
if (msg == null) break;
String[] parts = msg.split(" ");
String type = parts[0];

int id = Integer.parseInt(parts[1]);

if (type.equals("ACK")) {
System.out.println("ACK received for frame " + id);
acked[id] = true;
} else if (type.equals("NACK")) {
System.out.println("NACK received for frame " + id + ". Will retransmit later.");
retransmit.add(id);
}
}
for (int id : retransmit) {
System.out.println("\nSending frame " + id + " <-- Retransmission");
out.println("FRAME " + id + " RETX");
// Wait until ACK is received
while (true) {
String msg = in.readLine();
String[] parts = msg.split(" ");
if (parts[0].equals("ACK") && Integer.parseInt(parts[1]) == id) {
System.out.println("ACK received for frame " + id);
acked[id] = true;
break;
}
}
}

// 4) Slide window
while (base < totalFrames && acked[base]) base++;
}
System.out.println("\nAll frames sent successfully!");
socket.close();
} catch (Exception e) {
e.printStackTrace();
}
}
}