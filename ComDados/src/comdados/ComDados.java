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

    private int Port = 55455;
    private Client cliente;
    private Server servidor;
    
    
    public ComDados () {
        cliente = new Client(Port);
        servidor = new Server(Port);
    }
    
    
    public static void main(String[] args) {
        ComDados programa = new ComDados();
        
        ///////////////////
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
    
    
}
