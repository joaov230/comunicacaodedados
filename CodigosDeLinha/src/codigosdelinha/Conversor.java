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
    /////////////
    // As 2 primeiras posições do vetor devem ser ignoradas
    ////////////////////////////
    
    // NRZ-L
    // 0 é sinal alto, 1 é sinal baixo
    public void nrzlCodificado (String str) {
        sinal.clear();
        char c;
        sinal.add(ALTO);
        sinal.add(ALTO);
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c == '1') {
                sinal.add(BAIXO);
                sinal.add(BAIXO);
            } else if (c == '0') {
                sinal.add(ALTO);
                sinal.add(ALTO);
            }
        }
    }
    
    // NRZ-I
    // 1 inverte o sinal, 0 mantém o sinal anterior
    public void nrziCodificado (String str) {
        sinal.clear();
        char c;
        sinal.add(ALTO);
        sinal.add(ALTO);
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c == '1') {
                Integer aux = inverteUltimoSinal();
                sinal.add(aux);
                sinal.add(aux);
            } else if (c == '0') {
                Integer aux = getUltimoSinal();
                sinal.add(aux);
                sinal.add(aux);
            }
        }
    }
    
    // Bipolar-AMI
    // 0 é sinal médio, 1 é alto ou baixo (inverso do último bit 1)
    public void bamiCodificado (String str) {
        sinal.clear();
        char c;
        boolean alto = true;
        sinal.add(ALTO);
        sinal.add(ALTO);
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c == '1') {
                if (alto == true) { // Se o sinal anterior foi alto, o próximo será baixo
                    sinal.add(BAIXO);
                    sinal.add(BAIXO);
                    alto = false;
                } else { // Se o sinal anterior foi baixo, o próximo será alto
                    sinal.add(ALTO);
                    sinal.add(ALTO);   
                }
            } else if (c == '0') {
                sinal.add(MEDIO);
                sinal.add(MEDIO);
            }
        }
    }
    
    // Bipolar Pseudoternário
    // 1 é sinal médio, 0 é alto ou baixo (inverso do último bit 1)
    public void pseudoternarioCodificado (String str) {
        sinal.clear();
        char c;
        boolean alto = true;
        sinal.add(ALTO);
        sinal.add(ALTO);
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c == '0') {
                if (alto == true) { // Se o sinal anterior foi alto, o próximo será baixo
                    sinal.add(BAIXO);
                    sinal.add(BAIXO);
                    alto = false;
                } else { // Se o sinal anterior foi baixo, o próximo será alto
                    sinal.add(ALTO);
                    sinal.add(ALTO);   
                }
            } else if (c == '1') {
                sinal.add(MEDIO);
                sinal.add(MEDIO);
            }
        }
    }
    
    // 0 é sinal alto e baixo, 1 é sinal baixo e alto
    public void manchesterCodificado (String str) {
        sinal.clear();
        char c;
        sinal.add(ALTO);
        sinal.add(ALTO);
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c == '0') {
                sinal.add(ALTO);
                sinal.add(BAIXO);
            } else if (c == '1') {
                sinal.add(BAIXO);
                sinal.add(ALTO);
            }
        }
    }
    
    // 0 inverte e inverte, 1 mantém e inverte
    public void manchesterDiferencialCodificado (String str) {
        sinal.clear();
        char c;
        sinal.add(ALTO);
        sinal.add(ALTO);
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c == '0') {
                sinal.add(inverteUltimoSinal());
                sinal.add(inverteUltimoSinal());
            } else if (c == '1') {
                sinal.add(getUltimoSinal());
                sinal.add(inverteUltimoSinal());
            }
        }
    }
    
    
    
    
    // Se o último sinal for alto, o próximo será baixo e vice versa
    // Útil para codificações como a Manchester, que o mesmo bit assume 2 valores na ArrayList sinal
    private Integer inverteUltimoSinal () {
        return inverte(getUltimoSinal());
    }
    
    // Retorna o sinal invertido ao sinal enviado
    private Integer inverte (Integer temp) {
        if (temp == ALTO) { // Se o último sinal for alto, o próximo será baixo
            return BAIXO;
        } else if (temp == BAIXO) { // Se o último sinal for baixo, o próximo será alto
            return ALTO;
        } else { // Não há o que inverter
            return MEDIO;
        }        
    }
    
    // Retorna o ultimo sinal da ArrayList de sinais
    private Integer getUltimoSinal() {
        return sinal.get(sinal.size()-1);
    }
}
