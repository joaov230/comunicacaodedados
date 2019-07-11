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
        // Cria um cliente UDP
        DatagramSocket clientSocket = new DatagramSocket();
 
        // IP Address
	InetAddress IPAddress = InetAddress.getByName(servidor);
 
	byte[] sendData = new byte[1024];   // Vetor de bytes a serem enviados
        Scanner inFromUser = new Scanner(System.in);  // Leitor do usuário
        
        do {
            System.out.println("Digite o texto a ser enviado ao servidor: ");
            
            String sentence = inFromUser.nextLine(); // Lê do usuário
            Checksum frame = new Checksum(sentence);
            
            // Bytes do frame
            sendData = frame.getBytes();
            
            // Define o pacote de dados: dados a serem enviados, o tamanho dos dados, o IP address e a porta
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

            // Envia o pacote pelo DatagramSocket que foi criado
            System.out.println("Enviando pacote UDP para " + servidor + ":" + port);
            clientSocket.send(sendPacket);
        } while (true);
   }
}
