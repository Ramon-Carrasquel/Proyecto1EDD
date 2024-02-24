
package proyecto_1_edd;

import java.lang.Math;



public class SistemaHormiga {
    
    int a;                          //grado de importancia de la feromona
    int b;                          //grado de visibilidad
    int C;                          //cantidad de ciclos
    double evaporacion;             //factor de evaporacion
    int q;                          //aprendizaje
    int h;                          //cantidad de hormigas
    Hormiga[] hormigas;             //cantidad de hormigas (lista)
    Grafo grafoSpec;
    ListaArista aristas;
    
    
    
    SistemaHormiga(Grafo grafoSpec, ListaArista aristas, int h_, int C_, int a_, int b_, int q_, double evaporacion_) {
        
        this.grafoSpec = grafoSpec;
        this.aristas = aristas;
        if(h_ <= 0) {
            h = 1;
        }
        else {
            h = h_;
        }
        this.hormigas = new Hormiga[h];
        
        if(a_ <= 0) {
            a = 1;
        }
        else {
            a = a_;
        }
        
        if(b_ <= 1) {
            b = 2;
        }
        else {
            b = b_;
        }
        
        if(q_ <= 0) {
            q = 1;
        }
        else {
            q = q_;
        }
        
        if(C_ <= 0) {
            C = 1;
        }
        else {
            C = C_;
        }
        
        if(evaporacion_ >= 1.0) {
            evaporacion = 0.5;
        }
        else if (evaporacion_ <= 0.0) {
            evaporacion = 0.5;
        }
        else {
            evaporacion = evaporacion_;
        }
        
    }
    
    //---------------------------------//
    
    public void llenarArregloH(int posicion_, Grafo grafoSpec_) {
        for(int i = 0; i < h; i++) {
            Hormiga ant = new Hormiga(posicion_, grafoSpec.tamaño(), grafoSpec_);
            hormigas[i] = ant;
        }
    }
    
    //---------------------------------//
    
    public Arista getAristas(int inX) {
        return aristas.getArista(inX);
    }
    
    public Hormiga getHormigas(int inX) {
        return hormigas[inX];
    }
    
    public void printHormigas() {
        for(int i = 0; i < h; i++) {
            System.out.println();
            System.out.println("Hormiga " + (i + 1));
            hormigas[i].printHormiga();
        }
        System.out.println();
    }
    
    //---------------------------------//
    
    public void iniciarSimulacion() {
        for(int i = 0; i < C; i++) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("             Ciclo " + (i + 1));
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            iniciarCiclo();
            for(int j = 0; j < aristas.Size(); j++) {
                actuFeromona(aristas.getArista(j + 1));
                System.out.println("Feromona de la arista " + (aristas.getArista(j + 1).src + 1) + "," + (aristas.getArista(j + 1).dst + 1));
                aristas.getArista(j + 1).printFeromona();
            }
        }
    }
    
    public void iniciarCiclo() {
        
        for(int i = 0; i < h; i++) {
            
            hormigas[i].resetAristasVisitadas();
            hormigas[i].resetPosicion();
            hormigas[i].resetTrayecto();
            hormigas[i].resetVisitados();
            hormigas[i].resetCiudadesVisitadas();
            
            System.out.println("------------------------------");
            System.out.println("         Hormiga " + (i + 1));
            System.out.println("------------------------------");
            recorrerGrafo(hormigas[i]);
            hormigas[i].printCiudadesVisitadas();
            hormigas[i].aristasVisitadas.ReadAll();
        }
        
    }
    
    public int ruletaAleatoria(double[] listaProb) {
        double numAlt = Math.random();
        double probAcumulada = 0;
        
        
        for(int i = 0; i < listaProb.length; i++) {
            probAcumulada += listaProb[i];
            System.out.println(i);

            if (numAlt < probAcumulada) {
                return i;
            }
        }
        
        
        throw new RuntimeException("Arreglo de probabilidades inválido.");
    }
    
    //---------------------------------//
    
    public void recorrerGrafo(Hormiga hormiga) {
        hormiga.printHormiga();
        for(int i = 0; i < grafoSpec.tamaño(); i++) {
            if(grafoSpec.ciudadDestino == hormiga.posicion) {
                return;
            }
            try {
                Arista seleccionArista = selectCamino(hormiga);
                hormiga.insertarAristaVisitada(seleccionArista);
                if(!hormiga.visitados[seleccionArista.dst]) {
                    hormiga.setPosicion(seleccionArista.dst + 1);
                    hormiga.setCiudadActualVisitada();
                    hormiga.insertarAristaVisitada(seleccionArista);
                    hormiga.añadirDistanciaTrayecto(seleccionArista.valor);
                    hormiga.printCiudadesVisitadas();
                    
                    hormiga.setCiudadVisitada(seleccionArista.dst + 1);
                    if(hormiga.aristasVisitadas.Last().feromona == 1) {
                        hormiga.aristasVisitadas.Last().cantFeroInic(grafoSpec.tamaño());
                    }
                    
                    
                    hormiga.printHormiga();
                    System.out.println("Siguiente ciudad ---> " + (seleccionArista.dst + 1));
                    hormiga.printVisitados();
                    System.out.println();
                    
                    grafoSpec.printDestino();
                    hormiga.printHormiga();
                    System.out.println();
                }
                else if(!hormiga.visitados[seleccionArista.src]){
                    hormiga.setPosicion(seleccionArista.src + 1);
                    hormiga.setCiudadActualVisitada();
                    hormiga.insertarAristaVisitada(seleccionArista);
                    hormiga.añadirDistanciaTrayecto(seleccionArista.valor);
                    hormiga.printCiudadesVisitadas();
                    
                    hormiga.setCiudadVisitada(seleccionArista.src + 1);
                    if(hormiga.aristasVisitadas.Last().feromona == 1) {
                        hormiga.aristasVisitadas.Last().cantFeroInic(grafoSpec.tamaño());
                    }
                    
                    
                    hormiga.printHormiga();
                    System.out.println("Siguiente ciudad ---> " + (seleccionArista.src + 1));
                    hormiga.printVisitados();
                    System.out.println();
                    
                    grafoSpec.printDestino();
                    hormiga.printHormiga();
                    System.out.println();
                }
            }
            catch(RuntimeException e) {
                System.out.println("WALKTHROUGH END");
                hormiga.printTrayecto();
                System.out.println();
                break;
            }
            
        }
        
    }
    
    public Arista selectCamino(Hormiga hormiga) {
        int posicion = hormiga.posicion - 1;
        int tamañoProb = 0;
        ListaArista aristas_ = new ListaArista("aristas_");
        
        for(int i = 0; i < grafoSpec.getTamañoHorizontal(posicion); i++) {
            if(grafoSpec.getValorArista(posicion, i) != 0) {
                if(!hormiga.visitados[i]) {
                    tamañoProb += 1;
                    aristas_.InsertLast(posicion, i, grafoSpec.getValorArista(posicion, i));
                }
            }
        }
        
        double[] listaRuleta = probabilidades(hormiga, posicion, tamañoProb);
        
        System.out.println();
        for(int i = 0; i < listaRuleta.length; i++) {
            System.out.println(listaRuleta[i]);
        }
        
        int inXCaminoElegido = ruletaAleatoria(listaRuleta);
        Arista caminoDestino = aristas_.getArista(inXCaminoElegido + 1);
        
        return caminoDestino;
        
    }
    
    public double[] probabilidades(Hormiga hormiga, int posicion_, int cantAristas) {
        int posicion = posicion_;
        double prob = 0;
        double[] listaProbabilidad = new double[cantAristas];
        
        for(int i = 0; i < grafoSpec.getTamañoHorizontal(posicion); i++) {
            if(grafoSpec.getValorArista(posicion, i) != 0) {
                if(!hormiga.visitados[i]) {
                    int feromonaInX = aristas.SearchIndex(posicion, i);
                    Arista ari = aristas.getArista(feromonaInX);
                    
                    
                    if(ari.feromona == 1) {
                        ari.cantFeroInic(grafoSpec.tamaño());
                    }
                    
                    
                    double feromona_ = ari.feromona;
                    double distancia = ari.valor;

                    System.out.println(distancia);

                    prob = (potenciaD(feromona_, a) * potenciaD(factorVisibilidad(distancia), b))
                            / sumatoria(hormiga);
                    
                    for(int j = 0; j < listaProbabilidad.length; j++) {
                        if(listaProbabilidad[j] == 0) {
                            listaProbabilidad[j] = prob;
                            break;
                        }
                    }

                    System.out.println(prob);
                }
            }
        }
        
        return listaProbabilidad;
    }
    
    public double sumatoria(Hormiga hormiga) {
        int posicion = hormiga.posicion - 1;
        double suma = 0;
        
        for(int i = 0; i < grafoSpec.getTamañoHorizontal(posicion); i++) {
            if(grafoSpec.getValorArista(posicion, i) != 0) {
                if(!hormiga.visitados[i]) {
                    int feromonaInX = aristas.SearchIndex(posicion, i);
                    Arista ari = aristas.getArista(feromonaInX);
                    
                    if(ari.feromona == 1) {
                        ari.cantFeroInic(grafoSpec.tamaño());
                    }
                
                    double feromona_ = ari.feromona;
                    double distancia = ari.valor;

                    suma += potenciaD(feromona_, a) * potenciaD(factorVisibilidad(distancia), b);
                }
            }
        }
        
        return suma;
    }
    
    //---------------------------------//
    
    /*
    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    RECORDATORIO PERSONAL: Resolver el problema de la generación de las feromonas
    (dan resultados muy sospechosos y poco fiables)
    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    */
    
    public void actuFeromona(Arista arista) {
        double newFeromona = 0;
        
        newFeromona = evapFeromona(arista) + aumeFeromona(arista);
        
        arista.setFero(newFeromona);
    }
    
    public double aumeFeromona(Arista arista) {
        Hormiga[] hormigasAristaL = new Hormiga[h];
        for(int i = 0; i < h; i++) {
            int inXArista = hormigas[i].aristasVisitadas.SearchIndex(arista.src, arista.dst);
            Arista specArista = hormigas[i].aristasVisitadas.getArista(inXArista);
            if((specArista.src == arista.src && specArista.dst == arista.dst) || (specArista.src == arista.dst && specArista.dst == arista.src)) {
                hormigasAristaL[i] = hormigas[i];
            }
        }
        
        double deltaFeromona = 0;
        for(int i = 0; i < hormigasAristaL.length; i++) {
            if(hormigasAristaL[i] != null) {
                System.out.println(i + "----------------------------");
                deltaFeromona += q / hormigasAristaL[i].trayecto;
            }
        }
        
        return deltaFeromona;
    }
    
    public double evapFeromona(Arista arista) {
        double facEvaporacion = 0;
        
        facEvaporacion = (1 - evaporacion) * arista.feromona;
        
        return facEvaporacion;
    }
    
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
    
    //---------------------------------//
    
    /*
    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    RECORDATORIO PERSONAL: Crear las funciones que devuelvan la información necesaria
    y propuesta por el profesor (cantidad de hormigas) *(camino óptimo para cada hormiga,
    recorrido realizado y distancia del recorrido)* *(valor de feromona de cada arista)*
    *(camino óptimo entre la ciudad de origen y la de destino)*
    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    */
    
    public int mostrarCantidadHormigas() {
        return h;
    }
    
}
