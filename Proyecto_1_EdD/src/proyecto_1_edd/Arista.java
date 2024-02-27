
package proyecto_1_edd;

/**
 * Esta clase define los parámetros de las aristas
 * @author sebas
 * @version 18/02/2024/A
 */

public class Arista {
    
    //Campos de la clase
    public double valor;
    public double feromona;
    public int src;
    public int dst;
    Arista next;
    
    /**
     * Constructor 1 de la clase
     * @param src_ Define la primera ciudad
     * @param dst_ Define la segunda ciudad
     * @param value Define el tamaño de la arista
     */
    
    Arista(int src_, int dst_, double value) {
        int ciudad1 = src_;
        int ciudad2 = dst_;
        
        valor = value;
        feromona = 1;
        if(ciudad1 < 0) {
            ciudad1 *= -1;
        }
        if(ciudad2 < 0) {
            ciudad2 *= -1;
        }
                
        if(ciudad1 > ciudad2) {
            src = ciudad2;
            dst = ciudad1;
        }
        else {
            src = ciudad1;
            dst = ciudad2;
        }
    }//Cierre del constructor
    
    /**
     * Constructor 2 de la clase
     * @param src_ Define la primera ciudad
     * @param dst_ Define la segunda ciudad
     * @param value Define el tamaño de la arista
     * @param next Define la siguiente arista
     */
    
    Arista(int src_, int dst_, double value, Arista next) {
        int ciudad1 = src_;
        int ciudad2 = dst_;
        
        valor = value;
        feromona = 1;
        if(ciudad1 > ciudad2) {
            src = ciudad2;
            dst = ciudad1;
        }
        else {
            src = ciudad1;
            dst = ciudad2;
        }
        this.next = next;
    }//Cierre del constructor
    
    //---------------------------------//
    
    /**
     * Método que retorna la ciudad "src" que conforma la arista
     * @return Número de la ciudad como índice
     */
    
    public int getSrc() {
        return src;
    }//Cierre del método
    
    /**
     * Método que retorna la ciudad "dst" que conforma la arista
     * @return Número de la ciudad como índice
     */
    
    public int getDst() {
        return dst;
    }//Cierre del método
    
    /**
     * Método que retorna el valor/tamaño de la arista
     * @return Valor/tamaño de la arista
     */
    
    public double getValor() {
        return valor;
    }//Cierre del método
    
    /**
     * Método que retorna el valor de la feromona de la arista
     * @return Valor de la feromona
     */
    
    public double getFeromona() {
        return feromona;
    }//Cierre del método
    
    //---------------------------------//
    
    /**
     * Método que inicializa la cantidad inicial de feromona
     * @param cantCiudades Establece la cantidad
     */
    
    public void cantFeroInic(int cantCiudades) {
        this.feromona = feromona / (double) cantCiudades;
    }//Cierre del método
    
    /**
     * Método que establece un nivel de feromona específico
     * @param valor Establece el nivel de feromona deseado
     */
    
    public void setFero(double valor) {
        this.feromona = valor;
    }//Cierre del método
    
    //---------------------------------//
    
    /**
     * Método que imprime el nivel de feromona actual
     */
    
    public void printFeromona() {
        System.out.println(feromona);
    }//Cierre del método
    
    /**
     * Método que imprime las ciudades que une la arista
     */
    
    public void printSrcDst() {
        System.out.println(src + " - " + dst);
    }//Cierre del método
    
}//Cierre de la clase
