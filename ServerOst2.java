package sample;

import java.net.*;
import java.io.*;


public class ServerOst2 {

    static int num[] = {0, 0, 0};
    private boolean running;

    private static byte[] buf = new byte[256];

    public static String recieveString(DatagramSocket socket) throws IOException {
        DatagramPacket numberString = new DatagramPacket(buf, buf.length);
        socket.receive(numberString);
        String string = new String(buf, 0, numberString.getLength());
        ;
        buf = null;
        return string;
    }
    public static String removeNumber(String string){
        if(string.length() % 4 == 0){
            string = string.replaceAll("[48]","");
        }
        return string;
    }
    public static DatagramSocket startServer() throws SocketException {
        DatagramSocket socket = new DatagramSocket(1234);
        return socket;
    }


    public static void sendPacket(DatagramSocket socket, String string) throws IOException {
        buf = string.getBytes();
        InetAddress ip = InetAddress.getLocalHost();
        DatagramPacket result = new DatagramPacket(buf, buf.length,ip,1235);
        socket.send(result);
    }




    public static void main(String[] args) throws IOException, InterruptedException {
        DatagramSocket socket = startServer();
        sendPacket(socket,removeNumber(recieveString(socket)));
    }
}
