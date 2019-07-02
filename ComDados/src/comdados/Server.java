/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comdados;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author João Vitor, Gustavo Fantinel, Bernardo
 */
public class Server {
    
    private final int port;
    
    public Server(int port) {
        this.port = port;
    }
    
    public void run() {
        try {
            // Inicia o servidor seguro
            ServerSocket servidor = new ServerSocket(port);
            System.out.println("Servidor iniciado!");

            String str = "";  // Str começa vazia
            
            // Tenta conectar o cliente com o servidor
            Socket socket = servidor.accept();
                
            // Recebe a mensagem
            DataInputStream entrada = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                
            // Lê enquanto não receber "Cambio"
            do {
                str = entrada.readUTF();
                System.out.println("Mensagem recebida: " + str);                    
            } while (!str.equalsIgnoreCase("Cambio"));
            
            
            // Fecha todas as conexões
            entrada.close();
            servidor.close();
            System.out.println("Conexão fechada!");

        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
