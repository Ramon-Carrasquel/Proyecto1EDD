
package proyecto_1_edd;



public class SistemaHormiga {
    
    //int m;                          //cantidad de nodos/ciudades
    //int T;                          //cantidad inicial de feromonas
    int a;                          //grado de importancia de la feromona
    int b;                          //grado de visibilidad
    double evaporacion;             //factor de evaporacion
    int q;                          //aprendizaje
    int h;                          //cantidad de hormigas
    Hormiga[] hormigas;             //cantidad de hormigas (lista)
    Grafo grafoSpec;
    ListaArista aristas;
    
    
    SistemaHormiga(Grafo grafoSpec, ListaArista aristas, int h) {
        
        this.grafoSpec = grafoSpec;
        this.aristas = aristas;
        this.h = h;
        this.hormigas = new Hormiga[h];
        a = 1;
        b = 2;
        q = 1;
        evaporacion = 0.5;
        
    }
    
    SistemaHormiga(Grafo grafoSpec, ListaArista aristas, int h, int a_, int b_, int q_, double evaporacion_) {
        
        this.grafoSpec = grafoSpec;
        this.aristas = aristas;
        this.h = h;
        this.hormigas = new Hormiga[h];
        a = a_;
        b = b_;
        q = q_;
        if(evaporacion_ > 1.0) {
            evaporacion = 1.0;
        }
        else if (evaporacion_ < 0.0) {
            evaporacion = 0.1;
        }
        else {
            evaporacion = evaporacion_;
        }
        
    }
    
    //---------------------------------//
    /*
    public double probabilidad() {
        double prob;
        
        
        return prob;
    }
    */
    //---------------------------------//
    
    public double potenciaD(double base, int power) {
        double resultado = 0;
        if(base == 1) {
            return base;
        }
        for(int i = 0; i < power; i++) {
            resultado = base * base;
        }
        return resultado;
    }
    
    public double factorVisibilidad(double dist) {
        double distancia = q / dist;
        return distancia;
    }
    
}
