
package proyecto_1_edd;



public class Nodo {
    
    int value;
    Nodo next;
    
    Nodo(int value) {
        if(value < 0) {
            value = 0;
        }
        else {
            value = value;
        }
    }
    
    Nodo(int value, Nodo next) {
        if(value < 0) {
            value = 0;
        }
        else {
            value = value;
        }
        
        this.next = next;
    }
    
}
