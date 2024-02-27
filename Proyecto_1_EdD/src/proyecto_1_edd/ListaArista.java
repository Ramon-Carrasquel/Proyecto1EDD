
package proyecto_1_edd;

/**
 * Esta clase define la función de lista enlazada para las aristas
 * @author sebas
 * @version 18/02/2024/A
 */

public class ListaArista {
    
    //Campos de la clase
    private Arista nfirst;
    private Arista nlast;
    private String name;
    private int iN;
    
    /**
     * Constructor para la lista de aristas
     * @param n Establece el nombre de la lista (para la función de ciertos métodos)
     */
    
    public ListaArista(String n) {
        
        nfirst = null;
        iN = 0;
        name = n;
        
    }//Cierre del constructor
    
    //---------------------------------//
    
    /**
     * Método que sirve como comprobante si la lista está vacía o no
     * @return Valor buleano
     */
    
    public boolean isEmpty() {
        return nfirst == null;
    }//Cierre del método
    
    /**
     * Método que devuelve la primera arista de la lista
     * @return Primera arista de la lista
     */
    
    public Arista First() {
        return nfirst;
    }//Cierre del método
    
    /**
     * Método que devuelve la última lista de la arista
     * @return Última arista de la lista
     */
    
    public Arista Last() {
        return nlast;
    }//Cierre del método
    
    /**
     * Método que devuelve el tamaño de la lista
     * @return Tamaño de la lista
     */
    
    public int Size() {
        return iN;
    }//Cierre del método
    
    /**
     * Método que lee toda la lista
     */
    
    public void ReadAll() {
        Arista pAux = nfirst;
        for (int i = 0; i < iN; i++) {
            System.out.println(pAux.src + "," + pAux.dst + "," + pAux.valor);
            pAux = pAux.next;
        }
        System.out.println("FIN");
    }//Cierre del método
    
    /**
     * Método que devuelve el valor/tamaño de una arista específica de la lista mediante los índices de las dos ciudades
     * @param pSrc Establece la primera ciudad
     * @param pDst Establece la segunda ciudad
     * @return El valor/tamaño de la arista encontrada en la lista
     */
    
    public double SearchValue(int pSrc, int pDst) {
        Arista pAux = nfirst;
        for (int i = 0; i < iN; i++) {
            if((pAux.src == pSrc && pAux.dst == pDst) || (pAux.src == pDst && pAux.dst == pSrc)) {
                return pAux.valor;
            }
            else {
                pAux = pAux.next;
            }
        }
        return 0;
    }//Cierre del método
    
    /**
     * Método que devuelve el índice de una arista específica de la lista mediante los índices de las dos ciudades
     * @param pSrc Establece la primera ciudad
     * @param pDst Establece la segunda ciudad
     * @return El índice de la arista encontrada en la lista
     */
    
    public int SearchIndex(int pSrc, int pDst) {
        Arista pAux = nfirst;
        for (int i = 0; i < iN; i++) {
            if((pAux.src == pSrc && pAux.dst == pDst) || (pAux.src == pDst && pAux.dst == pSrc)) {
                return i + 1;
            }
            else {
                pAux = pAux.next;
            }
        }
        return 0;
    }//Cierre del método
    
    //---------------------------------//
    
    /**
     * Método que crea e inserta una arista en la primera posición de la lista
     * @param pSrc Establece la primera ciudad
     * @param pDst Establece la segunda ciudad
     * @param pValor Establece el valor/tamaño de la arista
     */
    
    public void InsertFirst(int pSrc, int pDst, double pValor) {
        for(int i = 0; i < iN; i++) {
            if((getArista(i + 1).src == pSrc && getArista(i+1).dst == pDst) || (getArista(i + 1).dst == pSrc && getArista(i+1).src == pDst)) {
                return;
            }
            else if(pSrc == pDst) {
                return;
            }
        }
        
        Arista pNew = new Arista(pSrc, pDst, pValor);
        pNew.next = nfirst;
        nfirst = pNew;
        if (nlast == null) {
            nlast = nfirst;
        }
        iN++;
    }//Cierre del método
    
    /**
     * Método que crea e inserta una arista en una posición en específico de la lista
     * @param inX Establece el índice de la posición
     * @param pSrc Establece la primera ciudad
     * @param pDst Establece la segunda ciudad
     * @param pValor Establece el valor/tamaño de la arista
     */
    
    public void Insert(int inX, int pSrc, int pDst, double pValor) {
        for(int i = 0; i < iN; i++) {
            if((getArista(i).src == pSrc && getArista(i).dst == pDst) || (getArista(i).dst == pSrc && getArista(i).src == pDst)) {
                return;
            }
            else if(pSrc == pDst) {
                return;
            }
        }
        
        if (inX == 0) {
            InsertFirst(pSrc, pDst, pValor);
            return;
        }
        if (inX == iN) {
            InsertLast(pSrc, pDst, pValor);
            return;
        }
        
        Arista pAux = nfirst;
        for (int i = 1; i < inX; i++) {
            pAux = pAux.next;
        }
        Arista pNew = new Arista(pSrc, pDst, pValor, pAux.next);
        pAux.next = pNew;
        iN++;
    }//Cierre del método
    
    /**
     * Método que crea e inserta una arista en la última posición de la lista
     * @param pSrc Establece la primera ciudad
     * @param pDst Establece la segunda ciudad
     * @param pValor Establece el valor/tamaño de la arista
     */
    
    public void InsertLast(int pSrc, int pDst, double pValor) {
        for(int i = 0; i < iN; i++) {
            if((getArista(i).src == pSrc && getArista(i).dst == pDst) || (getArista(i).dst == pSrc && getArista(i).src == pDst)) {
                return;
            }
            else if(pSrc == pDst) {
                return;
            }
        }
        
        if (nlast == null) {
            InsertFirst(pSrc, pDst, pValor);
            return;
        }
        Arista pNew = new Arista(pSrc, pDst, pValor);
        nlast.next = pNew;
        nlast = pNew;
        iN++;
    }//Cierre del método
    
    //---------------------------------//
    
    /**
     * Método que devuelve una arista en específico de la lista
     * @param inX Establece el índice de la arista a buscar
     * @return La arista específica
     */
    
    public Arista getArista(int inX) {
        Arista pAux = nfirst;
        for (int i = 0; i < inX; i++) {
            pAux = pAux.next;
        }
        return pAux;
    }//Cierre del método
    
    /**
     * Método que elimina la primera arista de la lista
     * @return Arista eliminada
     */
    
    public double DeleteFirst() {
        double pValor = nfirst.valor;
        nfirst = nfirst.next;
        if (nfirst == null) {
            nlast = null;
        }
        
        iN--;
        return pValor;
    }//Cierre del método
    
    /**
     * Método que elimina una arista en específico de la lista
     * @param inX Establece el índice de la arista a eliminar
     * @return Arista específica eliminada
     */
    
    public double Delete(int inX) {
        if (inX == 0) {
            return DeleteFirst();
        }
        if (inX == iN - 1) {
            return DeleteLast();
        }
        
        Arista pPrev = getArista(inX - 1);
        double pValor = pPrev.next.valor;
        pPrev.next = pPrev.next.next;
        
        iN--;
        return pValor;
    }//Cierre del método
    
    /**
     * Método que elimina la última arista de la lista
     * @return Arista eliminada
     */
    
    public double DeleteLast() {
        if (iN <= 1) {
            return DeleteFirst();
        }
        
        Arista pSecondLast = getArista(iN - 2);
        double pValor = nlast.valor;
        nlast = pSecondLast;
        nlast.next = null;
        
        iN--;
        return pValor;
    }//Cierre del método
    
    /**
     * Método que limpia la lista
     */
    
    public void Clear() {
        this.nfirst = null;
        this.iN = 0;
    }//Cierre del método
    
}//Cierre de la clase
