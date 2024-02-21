
package proyecto_1_edd;



public class Arista {
    
    double valor;
    double feromona;
    int src;
    int dst;
    Arista next;
    
    
    Arista(double value, int src_, int dst_) {
        
        valor = value;
        feromona = 1;
        src = src_;
        dst = dst_;
        
    }
    
    Arista(double value, int src_, int dst_, Arista next) {
        
        valor = value;
        feromona = 1;
        src = src_;
        dst = dst_;
        this.next = next;
        
    }
    
    public void cantFeroInic(int cantCiudades) {
        this.feromona = feromona / (double) cantCiudades;
    }
    
}
