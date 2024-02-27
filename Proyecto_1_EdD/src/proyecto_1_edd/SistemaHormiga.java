
package proyecto_1_edd;

import java.lang.Math;

/**
 * Esta clase define el funcionamiento de los recorridos del grafo
 * @author sebas
 * @version 20/02/2024/A
 */

public class SistemaHormiga {
    
    //Campos de la clase
    int a;
    int b;
    int C;
    double evaporacion;
    int q;
    int h;
    int[] caminoOptimoCic;
    Hormiga[] hormigas;
    public Grafo grafoSpec;
    public ListaArista aristas;
    
    /**
     * 
     * @param grafoSpec Define el grafo a utilizar
     * @param aristas Define la lista enlazada que contiene las aristas del grafo
     * @param h_ Define la cantidad de hormigas en la simulación
     * @param C_ Define la cantidad de ciclos a ejecutar en la simulación
     * @param a_ Define el grado de importancia de la feromona (generalmente siendo 1)
     * @param b_ Define el grado de visibilidad (generalmente siendo 2)
     * @param q_ Define el aprendizaje (generalmente siendo 1)
     * @param evaporacion_ Define el factor de evaporacion
     */
    
    public SistemaHormiga(Grafo grafoSpec, ListaArista aristas, int h_, int C_, int a_, int b_, int q_, double evaporacion_) {
        
        this.grafoSpec = grafoSpec;
        this.aristas = aristas;
        caminoOptimoCic = new int[grafoSpec.tamaño()];
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
        
    }//Cierre del constructor
    
    //---------------------------------//
    
    /**
     * Método que llena un arreglo con la cantidad de hormigas en la simulación
     * @param posicion_ Establece la ciudad en la cual se encuentra la hormiga
     */
    
    public void llenarArregloH(int posicion_) {
        for(int i = 0; i < h; i++) {
            Hormiga ant = new Hormiga(posicion_, grafoSpec);
            hormigas[i] = ant;
        }
    }//Cierre del método
    
    //---------------------------------//
    
    /**
     * Método que devuelve una arista de la lista enlazada "aristas" de la clase
     * @param inX Define el índice para buscar la arista
     * @return La arista en el índice "inX"
     */
    
    public Arista getAristas(int inX) {
        return aristas.getArista(inX);
    }//Cierre del método
    
    /**
     * Método que devuelve una hormiga de la lista "hormigas" de la clase
     * @param inXDefine el índice para buscar la hormiga
     * @return La hormiga en el índice "inX"
     */
    
    public Hormiga getHormigas(int inX) {
        return hormigas[inX];
    }//Cierre del método
    
    //---------------------------------//
    
    /**
     * Método que inicia el ciclo
     */
    
    public void iniciarCiclo() {
        
        for(int i = 0; i < h; i++) {
            
            hormigas[i].resetAristasVisitadas();
            hormigas[i].resetPosicion();
            hormigas[i].resetTrayecto();
            hormigas[i].resetVisitados();
            hormigas[i].resetCiudadesVisitadas();
            
            
            recorrerGrafo(hormigas[i]);
        }
        
    }//Cierre del método
    
    /**
     * Método que genera un número aleatorio para escoger una de entre todas las probabilidades de los caminos disponibles para la hormiga
     * @param listaProb Establece la lista con las probabilidades de los caminos
     * @return El índice ---------------------------------------------------------------------------------------------------------
     */
    
    public int ruletaAleatoria(double[] listaProb) {
        double numAlt = Math.random();
        double probAcumulada = 0;
        
        
        for(int i = 0; i < listaProb.length; i++) {
            probAcumulada += listaProb[i];

            if (numAlt < probAcumulada) {
                return i;
            }
        }
        
        
        throw new RuntimeException("Arreglo de probabilidades inválido.");
    }//Cierre del método
    
    //---------------------------------//
    
    /**
     * Método que recorre el grafo mediante una hormiga en concreto
     * @param hormiga Establece la hormiga que ejecutará el recorrido
     */
    
    public void recorrerGrafo(Hormiga hormiga) {
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
                    
                    hormiga.setCiudadVisitada(seleccionArista.dst + 1);
                    if(hormiga.aristasVisitadas.Last().feromona == 1) {
                        hormiga.aristasVisitadas.Last().cantFeroInic(grafoSpec.tamaño());
                    }
                    
                }
                else if(!hormiga.visitados[seleccionArista.src]){
                    hormiga.setPosicion(seleccionArista.src + 1);
                    hormiga.setCiudadActualVisitada();
                    hormiga.insertarAristaVisitada(seleccionArista);
                    hormiga.añadirDistanciaTrayecto(seleccionArista.valor);
                    
                    hormiga.setCiudadVisitada(seleccionArista.src + 1);
                    if(hormiga.aristasVisitadas.Last().feromona == 1) {
                        hormiga.aristasVisitadas.Last().cantFeroInic(grafoSpec.tamaño());
                    }
                    
                }
            }
            catch(RuntimeException e) {
                break;
            }
            
        }
        
    }//Cierre del método
    
    /**
     * Método que selecciona uno de los distintos caminos disponibles para la hormiga
     * @param hormiga Establece la hormiga que ejecutará la selección del camino
     * @return La arista (el camino) escogido por la hormiga
     */
    
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
        
        int inXCaminoElegido = ruletaAleatoria(listaRuleta);
        Arista caminoDestino = aristas_.getArista(inXCaminoElegido);
        
        return caminoDestino;
        
    }//Cierre del método
    
    /**
     * Método que sirve como la fórmula del cálculo de probabilidades para los caminos disponibles de la hormiga
     * @param hormiga Establece la hormiga que se usará para los cálculos
     * @param posicion_ Establece la ciudad en la que se encuentra la hormiga como un índice
     * @param cantAristas Establece la cantidad de aristas o caminos disponibles para la hormiga
     * @return Lista con las probabilidades de los caminos
     */
    
    public double[] probabilidades(Hormiga hormiga, int posicion_, int cantAristas) {
        int posicion = posicion_;
        double prob = 0;
        double[] listaProbabilidad = new double[cantAristas];
        
        for(int i = 0; i < grafoSpec.getTamañoHorizontal(posicion); i++) {
            if(grafoSpec.getValorArista(posicion, i) != 0) {
                if(!hormiga.visitados[i]) {
                    int feromonaInX = aristas.SearchIndex(posicion, i);
                    Arista ari = aristas.getArista(feromonaInX - 1);
                    
                    
                    if(ari.feromona == 1) {
                        ari.cantFeroInic(grafoSpec.tamaño());
                    }
                    
                    
                    double feromona_ = ari.feromona;
                    double distancia = ari.valor;
                    

                    prob = (potenciaD(feromona_, a) * potenciaD(factorVisibilidad(distancia), b))
                            / sumatoria(hormiga);
                    
                    for(int j = 0; j < listaProbabilidad.length; j++) {
                        if(listaProbabilidad[j] == 0) {
                            listaProbabilidad[j] = prob;
                            break;
                        }
                    }
                }
            }
        }
        
        return listaProbabilidad;
    }//Cierre del método
    
    /**
     * Método que sirve como fórmula del cálculo de la sumatoria, la cual es un complemento de la fórmula del método "probabilidades"
     * @param hormiga Establece la hormiga que se usará para los cálculos
     * @return El resultado de la sumatoria
     */
    
    public double sumatoria(Hormiga hormiga) {
        int posicion = hormiga.posicion - 1;
        double suma = 0;
        
        for(int i = 0; i < grafoSpec.getTamañoHorizontal(posicion); i++) {
            if(grafoSpec.getValorArista(posicion, i) != 0) {
                if(!hormiga.visitados[i]) {
                    int feromonaInX = aristas.SearchIndex(posicion, i);
                    Arista ari = aristas.getArista(feromonaInX - 1);
                    
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
    }//Cierre del método
    
    //---------------------------------//
    
    /**
     * Método que actualiza los niveles de feromona de las aristas mediante ciertos parámetros
     * @param arista Establece la arista a la cual se le va a actualizar el nivel de feromona
     */
    
    public void actuFeromona(Arista arista) {
        double newFeromona = 0;
        
        newFeromona = evapFeromona(arista) * arista.feromona + aumeFeromona(arista);
        
        arista.setFero(newFeromona);
    }//Cierre del método
    
    /**
     * Método que genera el aumento de la feromona de la arista
     * @param arista Establece la arista a la cual se le va a aumentar la feromona
     * @return El delta-feromona (el aumento de la feromona)
     */
    
    public double aumeFeromona(Arista arista) {
        Hormiga[] hormigasArista = new Hormiga[h];
        for(int i = 0; i < h; i++) {
            int inXArista = hormigas[i].aristasVisitadas.SearchIndex(arista.src, arista.dst);
            Arista specArista = hormigas[i].aristasVisitadas.getArista(inXArista - 1);
            if((specArista.src == arista.src && specArista.dst == arista.dst) || (specArista.src == arista.dst && specArista.dst == arista.src)) {
                hormigasArista[i] = hormigas[i];
            }
        }
        
        double deltaFeromona = 0;
        for(int i = 0; i < hormigasArista.length; i++) {
            if(hormigasArista.length == 0) {
                deltaFeromona = 0;
                break;
            }
            if(hormigasArista[i] != null) {
                deltaFeromona += q / arista.valor; //hormigasArista[i].trayecto;
            }
        }
        
        //deltaFeromona += arista.feromona;
        
        return deltaFeromona;
    }//Cierre del método
    
    /**
     * Método que genera el factor de evaporación de la feromona de la arista
     * @return El factor de evaporación
     */
    
    public double evapFeromona(Arista arista) {
        double facEvaporacion = 0;
        
        facEvaporacion = (1.0 - evaporacion);
        
        return facEvaporacion;
    }//Cierre del método
    
    //---------------------------------//
    
    /**
     * Método que sirve como una fórmula de potenciación
     * @param base Establece la base
     * @param power Establece el exponente
     * @return El resultado de la potenciación
     */
    
    public double potenciaD(double base, int power) {
        double resultado = 0;
        if(base == 1) {
            return base;
        }
        for(int i = 0; i < power; i++) {
            resultado = base * base;
        }
        return resultado;
    }//Cierre del método
    
    /**
     * Método que calcula el factor de visibilidad de una ciudad
     * @param dist define la distancia de la arista que conecta a una ciudad
     * @return El factor de visibilidad
     */
    
    public double factorVisibilidad(double dist) {
        double distancia = q / dist;
        return distancia;
    }//Cierre del método
    
    //---------------------------------//
    
    /**
     * Método que devuelve el índice de la ciudad adyacente a la posición actual
     * @param distance Define una lista con las distancias de las ciudades
     * @param visited Define una lista con buleanos que representan si una ciudad fue visitada o no
     * @return El índice de la ciudad más cercana
     */
    
    public static int findMinDistVertex(double[] distance, boolean [] visited) {
		
	int minVertex = -1;
        
	for(int i = 0; i < distance.length; i++) {
		if(visited[i] == false && (minVertex == -1 || distance[i] < distance[minVertex])) {
			minVertex = i;
		}
	}
	return minVertex;
    }//Cierre del método
    
    /**
     * Método que encuentra el camino más óptimo del grafo en cuestión entre dos ciudades
     * @param graph Establece el grafo a utilizar
     * @param src Establece la posición inicial del recorrido
     */
    
    public int[] caminoOptimo(Grafo graph, int src) {
        double [] distance = new double[graph.tamaño()];
	int source = src - 1;
	boolean [] visited = new boolean[graph.tamaño()];
		
	distance[source] = 0;
        
        for (int i = 0; i < graph.tamaño(); i++) {
            if(i == source) continue;
            distance[i] = Integer.MAX_VALUE;
	}
        
        int[] path = new int[graph.tamaño()];
        
        for(int i = 0; i < graph.tamaño(); i++) {
            int minDistVertex = findMinDistVertex(distance, visited);
            
            
            for(int l = 0; l < path.length; l++) {
                if(path[l] == 0) {
                    path[l] = minDistVertex;
                    break;
                }
            }
            
            visited[minDistVertex] = true;

            
            if(graph.getCiudadDestino() - 1 == minDistVertex) {
                break;
            }
            
            
            for(int j = 0; j < graph.tamaño(); j++) {
                if(graph.getValorArista(minDistVertex, j) != 0 && visited[j] == false && distance[minDistVertex] <= Integer.MAX_VALUE) {
                    double newDist = distance[minDistVertex] + graph.getValorArista(minDistVertex, j);
                            
                    if(newDist < distance[j]) {
                        distance[j] = newDist;
                    }
                }
            }
        }
        
        return path;
    }//Cierre del método
    
    //---------------------------------//
    
    /**
     * Método que retorna la cantidad de hormigas en la simulación
     * @return Cantidad de hormigas ("h")
     */
    
    public int cantidadHormigas() {
        return h;
    }//Cierre del método
    
    /**
     * Método que retorna la lista del recorrido realizado por una hormiga en concreto
     * @param hormiga Establece la hormiga en concreto
     * @return Lista del recorrido hecho por la hormiga
     */
    
    public int[] recorridoRealizado(Hormiga hormiga) {
        return hormiga.getCiudadesVisitadas();
    }//Cierre del método
    
    /**
     * Método que retorna el trayecto realizado por una hormiga en concreto
     * @param hormiga Establece la hormiga en concreto
     * @return El trayecto hecho por la hormiga
     */
    
    public double distanciaRecorrida(Hormiga hormiga) {
        return hormiga.getTrayecto();
    }//Cierre del método
    
    /**
     * Método que devuelve el valor de feromona de una arista en concreto
     * @param arista Establece la arista en concreto
     * @return La feromona de la arista
     */
    
    public double valorFeromona(Arista arista) {
        return arista.getFeromona();
    }//Cierre del método
    
}//Cierre de la clase
