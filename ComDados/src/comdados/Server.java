/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comdados;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author João Vitor, Gustavo Fantinel, Bernardo
 */
public class Server {
    
    private final int port;
    
    public Server(int port) {
        this.port = port;
    }
    
    public void run() throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(port);
 
	byte[] receiveData = new byte[1024];
        String sentence = "";
        
        do {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            System.out.println("Servidor esperando por mensagem na porta " + port + ".");
            serverSocket.receive(receivePacket);
            sentence = new String(receivePacket.getData());
            System.out.println("Mensagem recebida: " + sentence);
            // InetAddress e porta usados para enviar um "ok" para o cliente indicando que a mensagem foi recebida 
            InetAddress IPAddress = receivePacket.getAddress();
            int porta = receivePacket.getPort();
        } while (true);
    }
}
