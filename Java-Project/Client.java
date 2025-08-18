import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        String serverHostname = new String("127.0.0.1");
    if(args.length>0)
    serverHostname = args[0];
    System.out.println("Attempting to connection to host"+serverHostname+"on port 10007, ");
    Socket echoSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    try{
        echoSocket = new Socket(serverHostname, 10007);
        out = new PrintWriter(echoSocket.getOutputStream(),true);
        in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));


    }
    catch(UnknownHostException e)
    {
        System.err.println("Don't know abot host:"+serverHostname);
        System.exit(1);

    }
    catch(IOException e)
    {
        System.err.println("could not get I/O for"+"the connection to"+serverHostname);
        System.exit(1);
    }
    BufferedReader stdln = new BufferedReader(new InputStreamReader(System.in));
    String userInput;
    System.out.println("Input");
    while((userInput = stdln.readLine())!=null)
{
    out.println(userInput);
    System.out.println("echo:"+in.readLine());
    System.out.println("Input");
}
    out.close();
    in.close();
    stdln.close();
    echoSocket.close();
        
    }
    
}
