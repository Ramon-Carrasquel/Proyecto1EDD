
package proyecto_1_edd;

/**
 * Esta clase define la creación y el manejo del contenido de los grafos
 * @author sebas
 * @version 18/02/2024/A
 */

public class Grafo {
    
    //Campos de la clase
    int ciudadOrigen;
    int ciudadDestino;
    private int maxNodos;
    private double[][] matriz = new double[20][20];
    
    /**
     * Constructor para el grafo
       * @param n Define el tamaño que va a poseer el grafo
       * @param cOrg Define la ciudad de origen para el recorrido
       * @param cDes Define la ciudad de destino para el recorrido
     */
    
    Grafo(int n, int cOrg, int cDes) {
        
        int y = n;
        
        if(y < 4) {
            y = 4;
        }
        else if(y > 20) {
            y = 20;
        }
        
        this.maxNodos = y;
        
        
        if(cOrg < 1 || cOrg > maxNodos) {
            cOrg = 1;
        }
        else {
            ciudadOrigen = cOrg;
        }
        if(cDes < 1 || cDes > maxNodos) {
            cDes = y;
        }
        else {
            ciudadDestino = cDes;
        }
        if(cOrg == cDes) {
            ciudadOrigen = 1;
            ciudadDestino = maxNodos;
        }
        
        
        for (int i = 0; i < y; ++i) {
            for (int j = 0; j < y; ++j) {
                matriz[i][j] = 0;
            }
        }
        
    }//Cierre del constructor
    
    public void añadirCiudad() {
        
        /**
         * Método que añade una ciudad al grafo existente
         */
        
        if(maxNodos < 20) {
            maxNodos++;

            for(int i = 0; i < maxNodos; i++) {
                matriz[i][maxNodos - 1] = 0;
                matriz[maxNodos - 1][i] = 0;
            }
        }
    }//Cierre del método
    
    /**
     * Método que añade una arista al grafo existente
     * @param src Establece la ciudad "1" de la arista a crear
     * @param dst Establece la ciudad "2" de la arista a crear
     * @param valor Define el tamaño de la arista o distancia entre las dos ciudades
     */
    
    public void añadirArista(int src, int dst, double valor) {
        
        if(src != dst) {
            matriz[src][dst] = valor;
            matriz[dst][src] = valor;
        }
    }//Cierre del método
    
    /**
     * Método que elimina una ciudad del grafo existente
     * @param x Define la ciudad a ser eliminada (Ej.: Si se desea eliminar la ciudad 1, se inserta "1")
     */
    
    public void eliminarCiudad(int x) {
        
        if(x > maxNodos) {
            
        }
        else {
            if(maxNodos > 4) {
                while(x < maxNodos) {
                    
                    for(int i = 0; i < maxNodos; i++) {
                        matriz[i][x] = matriz[i][x + 1];
                    }
                    
                    for(int i = 0; i < maxNodos; i++) {
                        matriz[x][i] = matriz[x + 1][i];
                    }
                    
                    x++;
                }
                
                maxNodos--;
            }
        }
    }//Cierre del método
    
    /**
     * Método que elimina una arista del grafo existente
     * @param src Establece la ciudad "1" de la arista a eliminar
     * @param dst Establece la ciudad "2" de la arista a eliminar
     */
    
    public void eliminarArista(int src, int dst) {
        
        if(src != dst) {
            matriz[src][dst] = 0;
            matriz[dst][src] = 0;
        }
    }//Cierre del método
    
    public void borrarAristas() {
        
        /**
         * Método que elimina todas las aristas del grafo existente
         */
        
            for(int i = 0; i < maxNodos; i++) {
                for(int j = 0; j < maxNodos; j++) {
                    matriz[i][j] = 0;
                }
            }
        }//Cierre del método
    
    //---------------------------------//
    
    /**
     * Método que devuelve el tamaño horizontal de una fila (ciudad) en concreto (útil para encontrar las aristas de ésta)
     * @param src Define el índice de la fila en cuestión (Ej.: Si se desea la fila 2, se inserta "1")
     * @return El tamaño de una fila concreta
     */
    
    public int getTamañoHorizontal(int src) {
        return matriz[src].length;
    }//Cierre del método
    
    /**
     * Método que devuelve el valor (tamaño) de una arista en concreto
     * @param src Establece la ciudad "1" conectada a la arista
     * @param dst Establece la ciudad "2" conectada a la arista
     * @return El valor (tamaño) de una arista expecífica
     */
    
    public double getValorArista(int src, int dst) {
        return matriz[src][dst];
    }//Cierre del método
    
    /**
     * Método que devuelve la cantidad de aristas adheridas a una ciudad en específico
     * @param src Define el índice de la fila en la cual buscar las aristas (Ej.: Si se desea buscar aristas en la fila 2, se inserta "1")
     * @return El número de aristas conectadas a una ciudad en específico
     */
    
    public int getCantAristasAdy(int src) {
        int cantidadA = 0;
        for(int i = 0; i < matriz[src].length; i++) {
            if(matriz[src][i] != 0) {
                cantidadA += 1;
            }
        }
        return cantidadA;
    }//Cierre del método
    
    /**
     * Método que devuelve el tamaño del grafo
     * @return El tamaño del grafo
     */
    
    public int tamaño() {
        return maxNodos;
    }//Cierre del método
    
    //---------------------------------//
    
    public void printDestino() {
        System.out.println(ciudadDestino);
    }
    
    public void print() {
        System.out.println();
        for(int i = 0; i < maxNodos; i++) {
            for(int j = 0; j < maxNodos; j++) {
                System.out.print(matriz[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
}
