
package proyecto_1_edd;



public class Hormiga {
    
    int nC;                          //cantidad de ciudades
    int posicion;                   //posicion actual de la hormiga
    //boolean[] visitados;            //ciudades visitadas
    double trayecto;                //suma total de la distancia recorrida
    int[] ciudadesVisitadas;        //ciudades visitadas en orden
    Grafo grafoSpec;                //grafo en cuestion
    
    
    Hormiga(int posicion, int nC, Grafo grafoSpec) {
        
        this.nC = nC;
        this.posicion = posicion;
        //visitados = new boolean[nC];
        this.ciudadesVisitadas = new int[nC];
        this.grafoSpec = grafoSpec;
        
    }
    
    public void dFS() {
        int posicion_ = posicion - 1;
        boolean[] visitado = new boolean[grafoSpec.tamaño()];
        dFSH(posicion_, visitado);
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
    
}
