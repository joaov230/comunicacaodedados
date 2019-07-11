/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comdados;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Vitor, Gustavo Fantinel, Bernardo
 */
public class Client {
    
    private final int port;
    byte verificaAck;
    
    // Inicializador
    public Client(int port) {
        this.port = port;
    }
    
    // Roda o cliente, que ENVIA mensagens
    public void run() {
        try {
            // Inicia a conexão
            Socket socket = new Socket ("127.0.0.1", port);
            System.out.println("Conectado com sucesso!");
            
            // Receptor
            DataInputStream ack = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            
            Scanner entrada = new Scanner(System.in);
            String str = "";
            
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            
            do {
                str = entrada.nextLine();
                int cont = 0;
                char[] strBuffer = new char[8];
                
                do {
                    for (int j = 0; j < 8; j++) {
                        strBuffer[j] = ' ';
                    }
                    for (int j = 0; j < 8 && cont < str.length(); j++) {
                        strBuffer[j] = str.charAt(cont);
                        cont++;
                    }
                    
                    Checksum frame = new Checksum(strBuffer);
                
                    byte[] sendData = frame.getBytes();

                    byte[] sendDataAux = new byte[sendData.length+1];
                    sendDataAux[0] = (byte)sendData.length;

                    int i = 1;
                    for (int j = 0; j < sendData.length; j++) {
                        sendDataAux[i] = sendData[j];
                        i++;
                    }

                    saida.write(sendDataAux);
                    
                    // Inicia o timer e espera receber ack
                    do {
                        ExecutorService executor = Executors.newSingleThreadExecutor();
                        Future<String> future = executor.submit(new Callable() {
                            public String call() throws Exception {
                                verificaAck = ack.readByte();
                                return "OK";
                            }
                        });
                        
                        future.get(3, TimeUnit.SECONDS);
                        
                        if (verificaAck != 75) {
                            saida.write(sendDataAux);
                        }
                        executor.shutdownNow();
                    } while (verificaAck != 75);
                    
                    verificaAck = 0;
                } while (cont < str.length());
                System.out.println();
                System.out.println("Mensagem enviada: " + str);
            } while (!str.equalsIgnoreCase("Sair"));
            
            // Depois de tudo, termina a conexão
            entrada.close();
            saida.close();
            socket.close();

            System.out.println("Client Fechado!");
            
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
