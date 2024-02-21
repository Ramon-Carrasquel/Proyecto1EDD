
package proyecto_1_edd;



public class Proyecto_1_EdD {
    
    public static void main(String[] args) {
        
        int xCity = 8;
        if(xCity < 4) {
            xCity = 4;
        }
        else if(xCity > 20) {
            xCity = 20;
        }
        
        
        ListaArista a = new ListaArista("a");
        
        a.InsertLast(234.94, 1, 4);
        a.InsertLast(37.33, 2, 5);
        a.InsertLast(115.67, 1, 7);
        a.InsertLast(38.24, 1, 3);
        a.InsertLast(95.14, 4, 0);
        a.InsertLast(78.01, 1, 2);
        a.InsertLast(29.92, 7, 3);
        a.InsertLast(62.11, 5, 0);
        
        a.ReadAll();
        System.out.println(a.Size());
        
        
        Grafo graph = new Grafo(xCity);
        
        for(int i = 0; i < a.Size() + 1; i++) {
            graph.añadirArista(a.getArista(i).valor, a.getArista(i).src, a.getArista(i).dst);
        }
        
        graph.print();
        
        
        /*
        graph.borrarAristas();
        a.Clear();
        
        graph.print();
        a.ReadAll();
        */
        
        
        int x = a.SearchIndex(1, 2);
        a.getArista(x).cantFeroInic(xCity);
        System.out.println(a.getArista(x).feromona);
        
        Hormiga ant1 = new Hormiga(1,xCity, graph);
        ant1.dFS();
        
        
    }
    
}
