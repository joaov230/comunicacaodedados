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

            DataInputStream entrada;
            
            // Tenta conectar o cliente com o servidor
            try (Socket socket = servidor.accept()) {
                
                // Recebe a mensagem
                entrada = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                String str = "";  // Str começa vazia
                byte[] bytes = new byte[254];
                int i = 0;
                
                // Lê enquanto não receber "Cambio"
                do {
                    byte tam = entrada.readByte();
                    for (i = 0; i < tam; i++) {
                        bytes[i] = entrada.readByte();
                        //System.out.println("Leu um byte");
                    }
                    // Nesse momento ele recebeu tudo e "i" é o tamanho do dado enviado (com checksum incluso)
                    
                    byte[] bytesComChecksum = new byte[i];
                    for (int j = 0; j < i; j++) {
                        bytesComChecksum[j] = bytes[j];
                    }
                    byte[] bytesSemChecksum = new byte[i-1];
                    for (int j = 0; j < (i-1); j++) {
                        bytesSemChecksum[j] = bytes[j];
                    }
                     
                    boolean ok = Checksum.testChecksum(bytesSemChecksum, bytesComChecksum[i-1]);
                    
                    System.out.println("Checksum: " + ok);
                    
                    str = new String(bytesSemChecksum, "UTF-8");
                    
                    System.out.println("Mensagem recebida: " + str);
                } while (!str.equalsIgnoreCase("Cambio"));
            }
            
            // Fecha todas as conexões
            entrada.close();
            servidor.close();
            System.out.println("Servidor fechado!");
            
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
