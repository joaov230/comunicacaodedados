/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comdados;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author João Vitor, Gustavo Fantinel, Bernardo
 */
public class ComDados {

    private int Port = 55455;
    private Client cliente;
    private Server servidor;
    
    
    public ComDados () {
        cliente = new Client(Port, "localhost");
        servidor = new Server(Port);
    }
    
    public void run () {
        ///////////////////
        // Cria as threads
        Thread threadCliente = new Thread() {
            @Override
            public void run() {
                try {
                    cliente.run();
                } catch (Exception ex) {
                    Logger.getLogger(ComDados.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        Thread threadServidor = new Thread() {
            @Override
            public void run() {
                try {
                    servidor.run();
                } catch (Exception ex) {
                    Logger.getLogger(ComDados.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        ///////////////////
        // Inicia as threads
        threadServidor.start();
        threadCliente.start();
        
        ///////////////////
        // Finaliza as threads
        try {
            threadServidor.join();
            threadCliente.join();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        ComDados programa = new ComDados();
        programa.run();
    }
    
    
}
