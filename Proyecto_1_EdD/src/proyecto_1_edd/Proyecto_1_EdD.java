
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
        
        //System.setProperty("org.graphstream.ui", "swing");
        
        int cantHorm = 3;
        int ciclos = 3;
        
        int ciudOrg = 1;
        int ciudDes = 6;
        
        int xCity = 6;
        
        
        
        ListaArista a = new ListaArista("a");
        
        
        a.InsertLast(1, 3, 34);
        a.InsertLast(0, 3, 24);
        a.InsertLast(0, 2, 72);
        a.InsertLast(0, 5, 39);
        a.InsertLast(5, 3, 66);
        a.InsertLast(4, 5, 88);
        a.InsertLast(1, 4, 74);
        a.InsertLast(1, 2, 17);
        a.InsertLast(4, 3, 78);

        
        
        a.ReadAll();
        System.out.println(a.Size());
        
        
        Grafo graph = new Grafo(xCity, ciudOrg, ciudDes);
        
        for(int i = 0; i < a.Size() + 1; i++) {
            graph.añadirArista(a.getArista(i).src, a.getArista(i).dst, a.getArista(i).valor);
        }
        
        graph.print();
        
        
        
        SistemaHormiga sysh = new SistemaHormiga(graph, a, cantHorm, ciclos, 0, 0, 0, 0);
        sysh.llenarArregloH(ciudOrg);
        
        
        sysh.iniciarSimulacion();
        
        //sysh.caminoOptimo(graph, graph.getCiudadOrigen());
        
    }//Cierre del programa
    
}
