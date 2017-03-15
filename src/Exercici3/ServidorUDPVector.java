package Exercici3;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Matias
 */
public class ServidorUDPVector {

    public static void main(String[] args) throws ClassNotFoundException {

        final int PUERTO = 54552;

        try {
            System.out.println("Servidor Vector:");
            DatagramSocket socketUDP = new DatagramSocket(PUERTO, InetAddress.getLocalHost());
            byte[] buffer = new byte[1000];
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            socketUDP.receive(dp);

            //Escribimos el objeto que enviamos
            ByteArrayInputStream bais = new ByteArrayInputStream(dp.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            VectorString vs = (VectorString) ois.readObject();

            for (String string : vs.getArrayStrings()) {
                System.out.println(string);
            }

            String estat = "Tot correcte";
            byte[] arrayBytes = estat.getBytes();

            DatagramPacket dpRebut = new DatagramPacket(arrayBytes, arrayBytes.length, dp.getAddress(), dp.getPort());
            socketUDP.send(dpRebut);

            socketUDP.close();
        } catch (SocketException ex) {
            System.out.println(ex);
        } catch (UnknownHostException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
