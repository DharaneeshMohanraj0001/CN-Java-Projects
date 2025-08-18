import java.net.*;
import java.io.*;

public class chatclient {
    public static void main(String[] args) throws IOException {
        Socket client = null;
        BufferedReader toclient = null;
        DataOutputStream firmClient = null;
        BufferedReader kybd = new BufferedReader(new InputStreamReader(System.in));

        try{
            client = new Socket(InetAddress.getLocalHost(),90);
            toclient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            firmClient = new DataOutputStream((client.getOutputStream()));
        }
        catch(UnknownHostException unknownEx){
            System.out.println("Server not found");
            System.exit(1);
        }
        System.out.println("Start conversation!");
        boolean end = false;
        String clientMsg,serverMsg;
        do{
            clientMsg = kybd.readLine();
            firmClient.writeBytes(clientMsg);
            firmClient.write(13);
            firmClient.write(10);
            firmClient.flush();
            System.out.println("you");
            serverMsg = toclient.readLine();
            System.out.println("from server "+serverMsg);
            if(clientMsg.equals("exit"))
            end = true;



            
        }
        while(!end);
        toclient.close();
        firmClient.close();
        client.close();


    
    }
}
