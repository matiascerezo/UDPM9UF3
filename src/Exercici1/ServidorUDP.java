package Exercici1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author Matias
 */
public class ServidorUDP {

    public static void main(String[] args) throws IOException {
        byte[] buffer = new byte[1000];
        try {
            System.out.println("Run UDP Server...");
            DatagramSocket socketUDP = new DatagramSocket(54152);
            while (true) {
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                System.out.println("Recibido: " + new String(peticion.getData()));
                DatagramPacket respuesta = new DatagramPacket(peticion.getData(), peticion.getData().length, peticion.getAddress(), peticion.getPort());
                socketUDP.send(respuesta);
                System.out.println("Enviado: " + new String(respuesta.getData()));
                socketUDP.close();
            }
        } catch (SocketException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }
}
