/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comdados;


/**
 *
 * @author João Vitor, Gustavo Fantinel, Bernardo
 */
public class ComDados {

    private Client cliente;
    private Server servidor;
    
    public ComDados () {
        cliente = new Client();
        servidor = new Server();
    }
    public static void main(String[] args) {
        ComDados programa = new ComDados();
        
        // Cria as threads
        Thread threadCliente = new Thread() {
            @Override
            public void run() {
                //cliente.run();
            }
        };
        
        Thread threadServidor = new Thread() {
            @Override
            public void run() {
                //servidor.run();
            }
        };
        
        // Inicia as threads
        threadServidor.start();
        threadCliente.start();
        
        // Finaliza as threads
        try {
            threadServidor.join();
            threadCliente.join();
        } catch (Exception e) {
            System.exit(0);
        }
        
    }
    
    
}
