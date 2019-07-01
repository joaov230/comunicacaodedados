/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comdados;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author João Vitor, Gustavo Fantinel, Bernardo
 */
public class Client {
    
    private final int port;
    
    // Inicializador
    public Client(int port) {
        this.port = port;
    }
    
    // Roda o cliente, que ENVIA mensagens
    public void run() {
        try {
            Socket socket = new Socket ("127.0.0.1", port);
            System.out.println("Conectado com sucesso!");
            
            Scanner entrada = new Scanner(System.in);
            String str = "";
            
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            
            do {
                str = entrada.nextLine();
                
                saida.writeUTF(str);
                System.out.println("Mensagem: " + str);
            } while (!str.equals("Cambio"));

            
            // Depois de tudo, termina a conexão
            entrada.close();
            saida.close();
            socket.close();
            
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
