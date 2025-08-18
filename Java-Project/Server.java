import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket(10007);
        }
    catch(IOException e)
    {
        System.err.println("could not listen on port: 10007 ");
        System.exit(1);
    }    
    Socket clientSocket = null;
    System.out.println("waiting for connection.....");

    try{
        clientSocket = serverSocket.accept();
    }
    catch(IOException e){
        System.err.println("accept failed");
        System.exit(1);
    }
    System.out.println("Connectin Successful");
    System.out.println("Waiting for connection");
    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    String Inputline;
    while((Inputline = in.readLine())!=null){
        System.out.println("server."+Inputline);
        out.println(Inputline);

    if(Inputline.equals("Bye"))
        break;    
    }
    out.close();
    in.close();
    clientSocket.close();
    serverSocket.close();
    }
}
