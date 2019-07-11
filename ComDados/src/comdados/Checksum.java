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
    }
    
    public byte checksum() {
        short sum = 0;
        
        for(int i= 0; i < data.length; i++){
            sum += data[i];
        }
        byte sumInBytes = (byte) sum;

        return sumInBytes;
    }
}
