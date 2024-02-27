
package proyecto_1_edd;



public class Hormiga {
    
    int posicion;
    int posicionOriginal;
    boolean[] visitados;
    double trayecto = 0;
    int[] ciudadesVisitadas;
    Grafo grafoSpec;
    ListaArista aristasVisitadas = new ListaArista("aristasVisitadas");
    
    
    Hormiga(int posicion, Grafo grafoSpec) {
        
        this.posicion = posicion;
        this.posicionOriginal = posicion;
        this.ciudadesVisitadas = new int[grafoSpec.tamaño()];
        this.grafoSpec = grafoSpec;
        visitados = new boolean[grafoSpec.tamaño()];
        visitados[posicion - 1] = true;
        ciudadesVisitadas[0] = posicion;
        
    }
    
    //---------------------------------//
    
    public void añadirDistanciaTrayecto(double x) {
        trayecto += x;
    }
    
    public void setCiudadActualVisitada() {
        visitados[posicion - 1] = true;
    }
    
    public void setSpecCiudadVisitada(int x) {
        visitados[x - 1] = true;
    }
    
    
    public void setPosicion(int x) {
        this.posicion = x;
    }
    
    public void setCiudadVisitada(int x) {
        for(int i = 0; i < ciudadesVisitadas.length; i++) {
            if(ciudadesVisitadas[i] == 0) {
                ciudadesVisitadas[i] = x;
                break;
            }
        }
    }
    
    public void insertarAristaVisitada(Arista arista) {
        aristasVisitadas.InsertLast(arista.src, arista.dst, arista.valor);
    }
    
    //---------------------------------//
    
    public void resetVisitados() {
        for(int i = 0; i < visitados.length; i++) {
            visitados[i] = false;
        }
        visitados[posicion - 1] = true;
    }
    
    public void resetPosicion() {
        posicion = posicionOriginal;
    }
    
    public void resetCiudadesVisitadas() {
        for(int i = 0; i < ciudadesVisitadas.length; i++) {
            ciudadesVisitadas[i] = 0;
        }
        ciudadesVisitadas[0] = posicion;
    }
    
    public void resetTrayecto() {
        trayecto = 0;
    }
    
    public void resetAristasVisitadas() {
        int tamaño = aristasVisitadas.Size();
        for(int i = 0; i < tamaño; i++) {
            aristasVisitadas.DeleteLast();
        }
    }
    
    //---------------------------------//
    
    public int[] getCiudadesVisitadas() {
        return ciudadesVisitadas;
    }
    
    public double getTrayecto() {
        return trayecto;
    }
    
    //---------------------------------//
    
    public void printHormiga() {
        System.out.println();
        System.out.println("Posicion actual: " + posicion);
    }
    
    public void printVisitados() {
        for(int i = 0; i < visitados.length; i++) {
            System.out.println(visitados[i]);
        }
    }
    
    public void printTrayecto() {
        System.out.println();
        System.out.println("Trayecto del recorrido " + trayecto);
    }
    
    public void printCiudadesVisitadas() {
        for(int i = 0; i < ciudadesVisitadas.length; i++) {
            if(ciudadesVisitadas[i] == 0) {
                System.out.print("Fin");
                break;
            }
            System.out.print(ciudadesVisitadas[i] + " ");
        }
        System.out.println();
    }
    
}
