
package proyecto_1_edd;



public class ListaArista {
    
    private Arista nfirst;
    private Arista nlast;
    private String name;
    private int iN;
    
    
    public ListaArista(String n) {
        
        nfirst = null;
        iN = 0;
        name = n;
        
    }
    
    //---------------------------------//
    
    public boolean isEmpty() {
        return nfirst == null;
    }
    
    public Arista First() {
        return nfirst;
    }
    
    public Arista Last() {
        return nlast;
    }
    
    public int Size() {
        return iN;
    }
    
    public void Read(int inX) {
        Arista pAux = nfirst;
        for (int i = 0; i < inX; i++) {
            pAux = pAux.next;
        }
        System.out.println(pAux.src + "," + pAux.dst + "," + pAux.valor);
    }
    
    public void ReadAll() {
        Arista pAux = nfirst;
        for (int i = 0; i < iN; i++) {
            System.out.println(pAux.src + "," + pAux.dst + "," + pAux.valor);
            pAux = pAux.next;
        }
        System.out.println("FIN");
    }
    
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
    }
    
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
    }
    
    //---------------------------------//
    
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
    }
    
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
    }
    
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
    }
    
    //---------------------------------//
    
    public Arista getArista(int inX) {
        Arista pAux = nfirst;
        for (int i = 0; i < inX; i++) {
            pAux = pAux.next;
        }
        return pAux;
    }
    
    public double DeleteFirst() {
        double pValor = nfirst.valor;
        nfirst = nfirst.next;
        if (nfirst == null) {
            nlast = null;
        }
        
        iN--;
        return pValor;
    }
    
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
    }
    
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
    }
    
    public void Clear() {
        this.nfirst = null;
        this.iN = 0;
    }
    
}
