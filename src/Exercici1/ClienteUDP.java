package Exercici1;

import java.io.IOException;
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
public class ClienteUDP {

    public static void main(String[] args) {

        final int PUERTO = 54152;
        try {
            System.out.println("UDP Client:");
            DatagramSocket socketUDP = new DatagramSocket();
            String mensajeEscrito = JOptionPane.showInputDialog(null, "Mensaje: ", "Introduce el mensaje", 1);
            DatagramPacket dp = new DatagramPacket(mensajeEscrito.getBytes(), mensajeEscrito.getBytes().length, InetAddress.getLocalHost(), PUERTO);
            socketUDP.send(dp);
            byte[] buffer = new byte[1000];
            System.out.println("Enviado: " + new String(dp.getData()));
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            socketUDP.receive(peticion);
            System.out.println("Recibido: " + new String(peticion.getData()));
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