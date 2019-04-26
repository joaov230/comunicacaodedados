/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigosdelinha;

import java.util.ArrayList;

/**
 *
 * @author João, Gustavo e Bernardo
 */
public class Conversor {
    // Defines:
    private static final int ALTO = 1;
    private static final int MEDIO = 0;
    private static final int BAIXO = -1;
    
    // ArrayList que vai armazenar o sinal (ALTO, MEDIO ou BAIXO)
    // Contem o dobro do tamanho da string de texto, pois cada bit é dividido pela metade
    // para facilitar a representação gráfica na tela
    public ArrayList<Integer> sinal = new ArrayList<Integer>();
    
    ////////////////////////////
    // Os métodos receberão uma string (texto com 0s e 1s), que será analisada
    // e transformada para forma de sinal
    ////////////////////////////
    
    // 0 é sinal alto, 1 é sinal baixo
    public void nrzlCodificado (String str) {
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c == 1) {
                sinal.add(BAIXO);
                sinal.add(BAIXO);
            } else if (c == 0) {
                sinal.add(ALTO);
                sinal.add(ALTO);
            }
        }
    }
    
    
    // Se o último sinal for alto, o próximo será baixo e vice versa
    // Útil para codificações como a Manchester, que o mesmo bit assume 2 valores na ArrayList sinal
    private Integer inverte (Integer ultimoSinal) {
        if (ultimoSinal == ALTO) { // Se o último sinal for alto, o próximo será baixo
            return BAIXO;
        } else if (ultimoSinal == BAIXO) { // Se o último sinal for baixo, o próximo será alto
            return ALTO;
        } else { // Não há o que inverter
            return MEDIO;
        }
    }
}
