
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
        
        int cantHorm = 3;
        int ciclos = 3;
        
        int ciudOrg = 1;
        int ciudDes = 7;
        
        int xCity = 10;
        
        if(xCity < 4) {
            xCity = 4;
        }
        else if(xCity > 20) {
            xCity = 20;
        }
        
        
        
        ListaArista a = new ListaArista("a");
        
        a.InsertLast(2, 4, 34.9);
        a.InsertLast(1, 9, 37.3);
        a.InsertLast(1, 7, 115.6);
        a.InsertLast(2, 3, 38.2);
        a.InsertLast(4, 8, 95.1);
        a.InsertLast(0, 2, 78.0);
        a.InsertLast(7, 3, 29.9);
        a.InsertLast(4, 0, 62.1);
        a.InsertLast(6, 2, 43.5);
        a.InsertLast(6, 1, 55.7);
        a.InsertLast(5, 1, 51.9);
        a.InsertLast(9, 0, 92.1);
        a.InsertLast(8, 3, 23.5);
        a.InsertLast(5, 6, 23.5);
        a.InsertLast(8, 7, 56.5);
        
        a.ReadAll();
        System.out.println(a.Size());
        
        
        Grafo graph = new Grafo(xCity, ciudOrg, ciudDes);
        
        for(int i = 0; i < a.Size() + 1; i++) {
            graph.añadirArista(a.getArista(i).src, a.getArista(i).dst, a.getArista(i).valor);
        }
        
        graph.print();
        
        
        
        SistemaHormiga sysh = new SistemaHormiga(graph, a, cantHorm, ciclos, 0, 0, 0, 0);
        sysh.llenarArregloH(ciudOrg, graph);
        
        //sysh.recorrerGrafo(sysh.getHormigas(0));
        
        sysh.iniciarSimulacion();
        
    }//Cierre del programa
    
}
