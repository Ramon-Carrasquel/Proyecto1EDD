
package proyecto_1_edd;



public class Grafo {
    
    //private Lista aristas;
    private int maxNodos;
    private int[][] matriz = new int[20][20];
    
    Grafo(int n) {
        int y = n;
        if(y < 4) {
            y = 4;
        }
        this.maxNodos = y;
        
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matriz[i][j] = 0;
            }
        }
    }
    
    public void añadirCiudad() {
        if(maxNodos < 20) {
            maxNodos++;

            for(int i = 0; i < maxNodos; i++) {
                matriz[i][maxNodos - 1] = 0;
                matriz[maxNodos - 1][i] = 0;
            }
        }
    }
    
    public void añadirArista(double valor, int src, int dst) {
        if(src != dst) {
            matriz[src][dst] = (int) valor;
            matriz[dst][src] = (int) valor;
        }
    }
    
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
    }
    
    public void eliminarArista(int src, int dst) {
        if(src != dst) {
            matriz[src][dst] = 0;
            matriz[dst][src] = 0;
        }
    }
    
    public void establCiudadPartida(int c) {
        
    }
    
    public void establCiudadLlegada(int c) {
        
    }
    
    public void dFS(int src) {
        boolean[] visitado = new boolean[matriz.length];
        dFSH(src, visitado);
    }
    
    public void dFSH(int src, boolean[] visitado) {
        if(visitado[src]) {
            return;
        }
        else {
            visitado[src] = true;
            System.out.println(src + " = Visitado");
        }
        for(int i = 0; i < matriz[src].length; i++) {
            if(matriz[src][i] != 0) {
                dFSH(i, visitado);
            }
        }
        return;
    }
    
    //---------------------------------//
    
    public void print() {
        for(int i = 0; i < maxNodos; i++) {
            for(int j = 0; j < maxNodos; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
}
