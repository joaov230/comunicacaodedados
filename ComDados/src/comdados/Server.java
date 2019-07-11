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
        // Cria um server UDP
        DatagramSocket serverSocket = new DatagramSocket(port);
 
        // Cria o vetor de bytes receptor
	byte[] receiveData = new byte[1024];
        String sentence = "";
        
        do {
            // Cria o pacote de dados que será recebido
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            System.out.println("Servidor esperando por mensagem na porta " + port + ".");
            
            // Espera receber um pacote e salva no recievePacket
            serverSocket.receive(receivePacket);
            sentence = new String(receivePacket.getData());  // Salva os dados (.getData()) numa String
            
            System.out.println("Mensagem recebida: " + sentence);
            // InetAddress e porta usados para enviar um "ok" para o cliente indicando que a mensagem foi recebida
            // Ainda não implementado
            InetAddress IPAddress = receivePacket.getAddress();
            int porta = receivePacket.getPort();
            receiveData = new byte[1024];
        } while (true);
    }
}
