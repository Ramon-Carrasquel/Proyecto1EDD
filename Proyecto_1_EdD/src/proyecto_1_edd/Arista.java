
package proyecto_1_edd;



public class Arista {
    
    public double valor;
    public double feromona;
    public int src;
    public int dst;
    Arista next;
    
    
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
    }
    
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
    }
    
    //---------------------------------//
    
    public int getSrc() {
        return src;
    }
    
    public int getDst() {
        return dst;
    }
    
    public double getValor() {
        return valor;
    }
    
    public double getFeromona() {
        return feromona;
    }
    
    //---------------------------------//
    
    public void cantFeroInic(int cantCiudades) {
        this.feromona = feromona / (double) cantCiudades;
    }
    
    public void setFero(double valor) {
        this.feromona = valor;
    }
    
    //---------------------------------//
    
    public void printFeromona() {
        System.out.println(feromona);
    }
    
    public void printSrcDst() {
        System.out.println(src + " - " + dst);
    }
    
}
