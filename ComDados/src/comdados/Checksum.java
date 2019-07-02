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
    
    // Retorna uma String em decimal da String em binário passada por parâmetro
    public static String fromBinaryToDecimal (String binario) {
        int binary = Integer.parseInt(binario);

        int decimal = 0;  
        int n = 0;  
        while(true){  
            if(binary == 0){  
                break;  
            } else {  
                int temp = binary%10;  
                decimal += temp*Math.pow(2, n);  
                binary = binary/10;  
                n++;  
            }  
        }
        Integer decimalInteger = decimal;
        return (decimalInteger.toString()); 
    }
    
    // Retorna uma String em binário do valor decimal passado por parâmetro
    public static String toBinary(int decimal){    
        ArrayList<Integer> binary = new ArrayList();    
        int index = 0;    
        while(decimal > 0){
            binary.add((decimal%2));
            decimal = decimal/2;
        }
        return binary.toString();
    }
    
    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    // Método usado no Cliente
    // Recebe uma String em binário, faz a checksum e retorna uma String em binário
    // O checksum recebe uma String em binário qualquer
    // Faz a soma em decimal, transforma a soma para binário e retorna
    public static String fazChecksumBinary (String valorBinario) {
        
        String valorDecimal = Checksum.fromBinaryToDecimal(valorBinario);
        
        Integer soma = 0;
        for (int i = 0; i < valorDecimal.length(); i++) {
            Integer valorInteiro = valorDecimal.charAt(i) - '0';
            soma = soma + valorInteiro;
        }
        
        String somaString = Checksum.toBinary(soma);
        
        return somaString;
    }
    
    
    // Método usado no Servidor
    // Recebe o valor binário, faz o checksum desse valor (só chamar o método fazChecksumBinary
    // Recebe o valor do checksum enviado e compara os dois
    // Se tá errado, o pacote foi incompleto
    public static boolean trataChecksumBinary (String valorBinario , String checksumBinario) {
        String check = Checksum.fazChecksumBinary(valorBinario);
        
        if (checksumBinario.equals(check)) {
            return true;
        } else {
            return false;
        }
    }
}
