
package proyecto_1_edd;

/**
 * Esta clase comprende la inicialización de todo el programa; desde las interfaces hasta el funcionamiento interno del programa
 * @author sebas
 * @version 18/02/2024/A
 */

public class Proyecto_1_EdD {
    
    /**
     * Método que inicia el programa principal
     * @param args 
     */
    
    public static void main(String[] args) {
        
        System.setProperty("org.graphstream.ui", "swing");
        
        int cantHorm = 120;
        int ciclos = 7;
        
        int ciudOrg = 4;
        int ciudDes = 11;
        
        int xCity = 12;
        
        
        
        ListaArista a = new ListaArista("a");
        
        
        a.InsertLast(1, 3, 34);
        a.InsertLast(0, 3, 24);
        a.InsertLast(0, 2, 72);
        a.InsertLast(0, 9, 39);
        a.InsertLast(6, 3, 66);
        a.InsertLast(4, 6, 88);
        a.InsertLast(1, 7, 74);
        a.InsertLast(1, 2, 17);
        a.InsertLast(0, 3, 78);
        a.InsertLast(6, 5, 59);
        a.InsertLast(0, 8, 18);
        a.InsertLast(8, 9, 49);
        a.InsertLast(8, 1, 27);
        a.InsertLast(2, 11, 43);
        a.InsertLast(7, 11, 67);
        a.InsertLast(6, 10, 39);
        a.InsertLast(3, 10, 33);
        a.InsertLast(4, 9, 32);
        a.InsertLast(5, 8, 53);

        
        
        a.ReadAll();
        System.out.println(a.Size());
        
        
        Grafo graph = new Grafo(xCity, ciudOrg, ciudDes);
        
        for(int i = 0; i < a.Size() + 1; i++) {
            graph.añadirArista(a.getArista(i).src, a.getArista(i).dst, a.getArista(i).valor);
        }
        
        graph.print();
        
        
        
        SistemaHormiga sysh = new SistemaHormiga(graph, a, cantHorm, ciclos, 0, 0, 0, 0);
        sysh.llenarArregloH(ciudOrg, graph);
        
        
        // sysh.iniciarSimulacion(); //
        
        
        
    }//Cierre del programa
    
}
