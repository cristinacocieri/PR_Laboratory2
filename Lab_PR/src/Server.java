import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    DatagramSocket socket = null;
    int SequenceNumber = 0;
    int port = 8888;

    public Server(int portinput)
    {
        port = portinput;
    }
    public void CreateConnection() {
        System.out.println("Establishing Connection");
        try {
            socket = new DatagramSocket(port);
            byte[] incomingData = new byte[1024];
            System.out.println("Waiting for client message...");

            while (true) {
                DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                socket.receive(incomingPacket);
                String message = new String(incomingPacket.getData());
                System.out.print("Received message from client: " + message);

                int clientSequenceNum = Integer.parseInt(message.substring(message.indexOf(" ") + 1, message.lastIndexOf(" ")));

                if(clientSequenceNum == SequenceNumber){
                    System.out.println("Sequence numbers match, sending ACK");
                }
                else{
                    System.out.println("Sequence numbers do no match, sending duplicate ACK");
                    SequenceNumber--;
                }

                InetAddress IPAddress = incomingPacket.getAddress();
                int port = incomingPacket.getPort();
                String reply = "ACK " + SequenceNumber;
                byte[] data = reply.getBytes();
                DatagramPacket replyPacket =
                        new DatagramPacket(data, data.length, IPAddress, port);
                socket.send(replyPacket);
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                SequenceNumber++;
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please enter the command: java Server port\n Ex. java Server 8888");
        } else {
            Server server = new Server(Integer.parseInt(args[0])); //specify port
            server.CreateConnection();
        }
    }
}
