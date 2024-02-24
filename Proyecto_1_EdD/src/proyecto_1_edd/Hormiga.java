
package proyecto_1_edd;



public class Hormiga {
    
    int nC;                                                                     //cantidad de ciudades
    int posicion;                                                               //posicion actual de la hormiga
    int posicionOriginal;                                                       //posicion original de la hormiga
    boolean[] visitados;                                                        //ciudades visitadas
    double trayecto = 0;                                                        //suma total de la distancia recorrida
    int[] ciudadesVisitadas;                                                    //ciudades visitadas en orden
    Grafo grafoSpec;                                                            //grafo en cuestion
    ListaArista aristasVisitadas = new ListaArista("aristasVisitadas");       //aristas visitadas por la hormiga
    
    
    Hormiga(int posicion, int nC, Grafo grafoSpec) {
        
        this.nC = nC;
        this.posicion = posicion;
        this.posicionOriginal = posicion;
        this.ciudadesVisitadas = new int[grafoSpec.tamaño()];
        this.grafoSpec = grafoSpec;
        visitados = new boolean[grafoSpec.tamaño()];
        visitados[posicion - 1] = true;
        ciudadesVisitadas[0] = posicion;
        
    }
    
    public void dFS() {
        int posicion_ = posicion - 1;
        dFSH(posicion_, visitados);
    }
    
    public void dFSH(int posicion_, boolean[] visitado) {
        if(visitado[posicion_]) {
            return;
        }
        else {
            visitado[posicion_] = true;
            System.out.println(posicion_ + " = Visitado");
        }
        
        for(int i = 0; i < grafoSpec.getTamañoHorizontal(posicion_); i++) {
            if(grafoSpec.getValorArista(posicion_, i) != 0) {
                dFSH(i, visitado);
            }
        }
        return;
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
    
    /*
    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    RECORDATORIO PERSONAL: Preferiblemente arreglar el "display" de la función de abajo
    (no muestra correctamente las ciudades recorridas en orden)
    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    */
    
    public void setCiudadVisitada(int x) {
        for(int i = 0; i < ciudadesVisitadas.length; i++) {
            if(ciudadesVisitadas[i] != 0) {
                ciudadesVisitadas[i] = x;
                break;
            }
        }
    }
    
    public void resetVisitados() {
        for(int i = 0; i < visitados.length; i++) {
            visitados[i] = false;
        }
        visitados[posicion - 1] = true;
    }
    
    public void resetPosicion() {
        posicion = posicionOriginal;
    }
    
    public void resetTrayecto() {
        for(int i = 0; i < ciudadesVisitadas.length; i++) {
            ciudadesVisitadas[i] = 0;
        }
        ciudadesVisitadas[0] = posicion;
    }
    
    public void resetCiudadesVisitadas() {
        trayecto = 0;
    }
    
    public void resetAristasVisitadas() {
        int tamaño = aristasVisitadas.Size();
        for(int i = 0; i < tamaño; i++) {
            aristasVisitadas.DeleteLast();
        }
    }
    
    public void insertarAristaVisitada(Arista arista) {
        aristasVisitadas.InsertLast(arista.src, arista.dst, arista.valor);
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
                break;
            }
            System.out.println(ciudadesVisitadas[i]);
        }
    }
    
}
