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
    
    public Checksum(final String data){
        this.data = new byte[data.length()];
        
        byte[] bytes = data.getBytes();
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

        return pacote;
    }

    public static char[] toCharArray(final byte[] data) {
        StringBuilder strBldr = new StringBuilder();
        
        for (byte b : data) {
            strBldr.append(b);
        }

        return strBldr.toString().toCharArray();
    }
}
