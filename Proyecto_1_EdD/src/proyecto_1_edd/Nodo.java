
package proyecto_1_edd;



public class Nodo {
    
    int value;
    Nodo next;
    
    Nodo(int value) {
        this.value = value;
    }
    
    Nodo(int value, Nodo next) {
        this.value = value;
        this.next = next;
    }
    public int getvalor(){
        return value;
    }
    
    public Nodo getnext(){
        return next;
    }     
}
