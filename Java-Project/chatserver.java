import java.net.*;
import java.io.*;

public class chatserver {
public static void main(String[] args) throws IOException {
ServerSocket server = null;
try{
    server = new ServerSocket(90);

}
catch(IOException ioEx){
    System.out.println("error in port 90");
    System.exit(1);
}
Socket serverSocket = null;
try{
    System.out.println("Waiting for client");
    serverSocket = server.accept();
    System.out.println("Connection accepted at"+serverSocket);
}
catch(IOException ioEX){
    System.out.println("failed to connect");
    System.exit(1);
}
DataOutputStream firmServer = new DataOutputStream(serverSocket.getOutputStream());
BufferedReader toServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
BufferedReader kybd = new BufferedReader(new InputStreamReader(System.in));
String clientMsg,serverMsg;
System.out.println("Start Chatting Type exit to terminate!");
boolean end = false;
do{
    clientMsg = toServer.readLine();
    System.out.println("From Client :"+clientMsg);
    serverMsg = kybd.readLine();
    firmServer.writeBytes(serverMsg);
    firmServer.write(13);
    firmServer.write(10);
    firmServer.flush();
    if(serverMsg.equals("exit"))
    end = true;

}while(!end);
firmServer.close();
server.close();


}
}
