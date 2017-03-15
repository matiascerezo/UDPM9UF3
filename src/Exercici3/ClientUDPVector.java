package Exercici3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**
 *
 * @author Matias
 */
public class ClientUDPVector {

    public static void main(String[] args) throws IOException {

        final int PUERTO = 54552;
        String[] mensajesEscrito = new String[5];
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            System.out.println("Client Vector:");
            DatagramSocket socketUDP = new DatagramSocket();

            //Recogemos 5 palabras
            for (int i = 0; i < 5; i++) {
                mensajesEscrito[i] = JOptionPane.showInputDialog(null, "Mensaje nº: " + (i+1), "Introduce el mensaje", 1);
            }
            //Escribimos el objeto que enviamos
            oos.writeObject(new VectorString(mensajesEscrito));
            oos.flush();

            byte[] arrayBytes = baos.toByteArray();

            DatagramPacket dp = new DatagramPacket(arrayBytes, arrayBytes.length, InetAddress.getLocalHost(), PUERTO);
            socketUDP.send(dp);
            byte[] buffer = new byte[1000];

            DatagramPacket dpRebut = new DatagramPacket(buffer, buffer.length);
            socketUDP.receive(dpRebut);

            System.out.println("Recibido: " + new String(dpRebut.getData()));
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
