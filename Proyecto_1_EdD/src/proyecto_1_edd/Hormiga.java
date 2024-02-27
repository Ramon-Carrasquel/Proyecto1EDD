
package proyecto_1_edd;

/**
 * Esta clase define los parámetros y funciones de las hormigas
 * @author sebas
 * @version 20/02/2024/A
 */

public class Hormiga {
    
    //Campos de la clase
    int posicion;
    int posicionOriginal;
    boolean[] visitados;
    double trayecto = 0;
    int[] ciudadesVisitadas;
    Grafo grafoSpec;
    ListaArista aristasVisitadas = new ListaArista("aristasVisitadas");
    
    /**
     * Constructor para la hormiga
     * @param posicion Define la posición actual de la hormiga
     * @param grafoSpec Define el grafo a utilizar
     */
    
    Hormiga(int posicion, Grafo grafoSpec) {
        
        this.posicion = posicion;
        this.posicionOriginal = posicion;
        this.ciudadesVisitadas = new int[grafoSpec.tamaño()];
        this.grafoSpec = grafoSpec;
        visitados = new boolean[grafoSpec.tamaño()];
        visitados[posicion - 1] = true;
        ciudadesVisitadas[0] = posicion;
        
    }//Cierre del constructor
    
    //---------------------------------//
    
    /**
     * Método que añade distancia recorrida al trayecto de la hormiga
     * @param x Distancia a añadir
     */
    
    public void añadirDistanciaTrayecto(double x) {
        trayecto += x;
    }//Cierre del método
    
    /**
     * Método que añade la ciudad en la que actualmente está la hormiga a una lista buleana
     */
    
    public void setCiudadActualVisitada() {
        visitados[posicion - 1] = true;
    }//Cierre del método
    
    /**
     * Método que añade una ciudad visitada a una lista buleana
     * @param x Establece la ciudad en cuestión
     */
    
    public void setSpecCiudadVisitada(int x) {
        visitados[x - 1] = true;
    }//Cierre del método
    
    /**
     * Método que cambia la posición de la hormiga
     * @param x Establece la posicción de la hormiga mediante el número de la ciudad
     */
    
    public void setPosicion(int x) {
        this.posicion = x;
    }//Cierre del método
    
    /**
     * Método que inserta la ciudad a la que va a transitar la hormiga en una lista que contiene el recorrido de la hormiga
     * @param x Establece la ciudad a añadir en cuestión
     */
    
    public void setCiudadVisitada(int x) {
        for(int i = 0; i < ciudadesVisitadas.length; i++) {
            if(ciudadesVisitadas[i] == 0) {
                ciudadesVisitadas[i] = x;
                break;
            }
        }
    }//Cierre del método
    
    /**
     * Método que inserta aristas a una lista enlazada que las contiene
     * @param arista Establece la arista a añadir en cuestión
     */
    
    public void insertarAristaVisitada(Arista arista) {
        aristasVisitadas.InsertLast(arista.src, arista.dst, arista.valor);
    }//Cierre del método
    
    //---------------------------------//
    
    /**
     * Método que reinicia las ciudades visitadas y las vuelve "false"
     */
    
    public void resetVisitados() {
        for(int i = 0; i < visitados.length; i++) {
            visitados[i] = false;
        }
        visitados[posicion - 1] = true;
    }//Cierre del método
    
    /**
     * Método que reestablece la posición original
     */
    
    public void resetPosicion() {
        posicion = posicionOriginal;
    }//Cierre del método
    
    /**
     * Método que reestablece el recorrido de las ciudades visitadas
     */
    
    public void resetCiudadesVisitadas() {
        for(int i = 0; i < ciudadesVisitadas.length; i++) {
            ciudadesVisitadas[i] = 0;
        }
        ciudadesVisitadas[0] = posicion;
    }//Cierre del método
    
    /**
     * Método que reestablece el trayecto realizado
     */
    
    public void resetTrayecto() {
        trayecto = 0;
    }//Cierre del método
    
    /**
     * Método que reestablece la lista enlazada de las listas enlazadas
     */
    
    public void resetAristasVisitadas() {
        int tamaño = aristasVisitadas.Size();
        for(int i = 0; i < tamaño; i++) {
            aristasVisitadas.DeleteLast();
        }
    }//Cierre del método
    
    //---------------------------------//
    
    /**
     * Método que devuelve el recorrido realizado por la hormiga
     * @return Lista entero
     */
    
    public int[] getCiudadesVisitadas() {
        return ciudadesVisitadas;
    }//Cierre del método
    
    /**
     * Método que devuelve el trayecto realizado por la hormiga
     * @return Trayecto
     */
    
    public double getTrayecto() {
        return trayecto;
    }//Cierre del método
    
    //---------------------------------//
    
    /**
     * Método que imprime la posición actual de la hormiga
     */
    
    public void printHormiga() {
        System.out.println();
        System.out.println("Posicion actual: " + posicion);
    }//Cierre del método
    
    /**
     * Método que imprime la lista buleana de las ciudades visitadas
     */
    
    public void printVisitados() {
        for(int i = 0; i < visitados.length; i++) {
            System.out.println(visitados[i]);
        }
    }//Cierre del método
    
    /**
     * Método que imprime el trayecto de la hormiga
     */
    
    public void printTrayecto() {
        System.out.println();
        System.out.println("Trayecto del recorrido " + trayecto);
    }//Cierre del método
    
    /**
     * Método que imprime la lista entero de las ciudades visitadas
     */
    
    public void printCiudadesVisitadas() {
        for(int i = 0; i < ciudadesVisitadas.length; i++) {
            if(ciudadesVisitadas[i] == 0) {
                System.out.print("Fin");
                break;
            }
            System.out.print(ciudadesVisitadas[i] + " ");
        }
        System.out.println();
    }//Cierre del método
    
}//Cierre de la clase
