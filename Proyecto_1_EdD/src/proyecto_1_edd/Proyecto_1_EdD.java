
package proyecto_1_edd;



public class Proyecto_1_EdD {
    
    public static void main(String[] args) {
        
        Grafo graph = new Grafo(5);
        
        
        graph.añadirArista(3, 1, 82);
        graph.añadirArista(0, 2, 54);
        graph.añadirArista(2, 4, 35);
        graph.añadirArista(3, 4, 26);
        
        graph.print();
        
        graph.dFS(2);
        
    }
    
}
