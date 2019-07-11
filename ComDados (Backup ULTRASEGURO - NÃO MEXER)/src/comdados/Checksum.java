/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comdados;

import java.util.ArrayList;

/**
 *
 * @author joaob
 */
public class Checksum {
    
    private byte[] data;
    
    public Checksum(final char[] data){
        
        String str = String.valueOf(data);
        
        this.data = new byte[str.length()];
        
        byte[] bytes = str.getBytes();
        System.arraycopy(bytes, 0, this.data, 0, this.data.length);
    }
    
    public byte checksum() {
        short soma = 0;
        
        for(int i = 0; i < data.length; i++){
            soma += data[i];
        }
        
        byte somaEmBytes = (byte)soma;

        return somaEmBytes;
    }
    
    public byte[] getBytes() {
        byte[] pacote = new byte[data.length + 1];
        System.arraycopy(data, 0, pacote, 0, data.length);

        pacote[data.length] = checksum();
        //pacote[data.length] += 1;

        return pacote;
    }
    
    public static boolean testChecksum (byte[] verificador, byte checksum) {
        short soma = 0;
        
        for(int i = 0; i < verificador.length; i++){
            soma += verificador[i];
        }
        
        byte somaEmBytes = (byte)soma;
        
        if (somaEmBytes == checksum) {
            return true;
        } else {
            return false;
        }
    }
}
