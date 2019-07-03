/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comdados;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author João Vitor, Gustavo Fantinel, Bernardo
 */
public class Client {
    
    private final int port;
    String servidor = "localhost";
    
    // Construtor
    public Client(int port, String servidor) {
        this.port = port;
        this.servidor = servidor;
    }
    
    
    // Roda o cliente, que ENVIA mensagens
    public void run() throws Exception {

        Scanner inFromUser = new Scanner(System.in);
        DatagramSocket clientSocket = new DatagramSocket();
 
	InetAddress IPAddress = InetAddress.getByName(servidor);
 
	byte[] sendData = new byte[1024];
 
        
        do {
            System.out.println("Digite o texto a ser enviado ao servidor: ");
            String sentence = inFromUser.nextLine();
            sendData = sentence.getBytes();

            System.out.println("Texto em bytes: " + sendData);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

            System.out.println("Enviando pacote UDP para " + servidor + ":" + port);
            clientSocket.send(sendPacket); 
        } while (true);
   }
}
