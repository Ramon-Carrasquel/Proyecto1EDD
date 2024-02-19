
package proyecto_1_edd;



public class Proyecto_1_EdD {
    
    public static void main(String[] args) {
        
        ListaArista a = new ListaArista("a");
        
        int x = 8;
        
        a.InsertLast(234.94, 1, 4);
        a.InsertLast(37.33, 3, 5);
        a.InsertLast(115.67, 3, 7);
        a.InsertLast(38.24, 1, 3);
        a.InsertLast(95.14, 4, 0);
        a.InsertLast(78.01, 1, 5);
        
        a.ReadAll();
                
        
        Grafo graph = new Grafo(x);
        
        for(int i = 0; i < a.Size(); i++) {
            graph.añadirArista(a.getArista(i).valor, a.getArista(i).src, a.getArista(i).dst);
        }
        
        graph.print();
        
        //graph.dFS(2);
        
    }
    
}
