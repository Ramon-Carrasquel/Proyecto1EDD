
package proyecto_1_edd;



public class Nodo {
    
    double value;
    Nodo next;
    
    Nodo(double value_) {
        if(value < 0) {
            value = 0;
        }
        else {
            value = value_;
        }
    }
    
    Nodo(double value_, Nodo next) {
        if(value < 0) {
            value = 0;
        }
        else {
            value = value_;
        }
        
        this.next = next;
    }
    
}
