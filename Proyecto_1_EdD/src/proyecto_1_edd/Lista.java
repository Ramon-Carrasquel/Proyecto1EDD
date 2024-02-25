
package proyecto_1_edd;



public class Lista {
    
    private Nodo nfirst;
    private Nodo nlast;
    private String name;
    private int iN;
    
    Lista(String n) {
        nfirst = null;
        iN = 0;
        name = n;
    }
    
    //---------------------------------//
    
    public boolean isEmpty() {
        return nfirst == null;
    }
    
    public Nodo First() {
        return nfirst;
    }
    
    public Nodo Last() {
        return nlast;
    }
    
    public int Size() {
        return iN;
    }
    
    public void Read(int inX) {
        Nodo pAux = nfirst;
        for (int i = 0; i < inX; i++) {
            pAux = pAux.next;
        }
        System.out.println(pAux.value);
    }
    
    public void ReadAll() {
        Nodo pAux = nfirst;
        try {
            for (int i = 0; i < iN; i++) {
                System.out.print(pAux.value + " -> ");
                pAux = pAux.next;
            }
        }
        catch (NullPointerException e) {
            System.out.println("FIN - INTERRUPIDO");
        }
        System.out.println("FIN");
    }
    
    public int SearchValue(double pValue) {
        Nodo pAux = nfirst;
        for (int i = 0; i < iN; i++) {
            if(pAux.value == pValue) {
                return i;
            }
            else {
                pAux = pAux.next;
            }
        }
        return 0;
    }
    
    //---------------------------------//
    
    public Nodo Next(Nodo pValue) {
        if (pValue != null){
            pValue = pValue.next;
            return pValue;
        } else {
            return null;
        }
    }
    
    //---------------------------------//
    
    public void InsertFirst(double pValue) {
        Nodo pNew = new Nodo(pValue);
        pNew.next = nfirst;
        nfirst = pNew;
        if (nlast == null) {
            nlast = nfirst;
        }
        iN++;
    }
    
    public void Insert(int inX, double pValue) {
        if (inX == 0) {
            InsertFirst(pValue);
            return;
        }
        if (inX == iN - 1) {
            InsertLast(pValue);
            return;
        }
        Nodo pAux = nfirst;
        for (int i = 1; i < inX; i++) {
            pAux = pAux.next;
        }
        Nodo pNew = new Nodo(pValue, pAux.next);
        pAux.next = pNew;
        iN++;
    }
    
    public void InsertLast(double pValue) {
        if (nlast == null) {
            InsertFirst(pValue);
            return;
        }
        Nodo pNew = new Nodo(pValue);
        nlast.next = pNew;
        nlast = pNew;
        iN++;
    }
    
    //---------------------------------//
    
    public Nodo getNode(int inX) {
        Nodo pAux = nfirst;
        for (int i = 1; i < inX; i++) {
            pAux = pAux.next;
        }
        return pAux;
    }
    
    public double DeleteFirst() {
        double pValue = nfirst.value;
        nfirst = nfirst.next;
        if (nfirst == null) {
            nlast = null;
        }
        
        iN--;
        return pValue;
    }
    
    public double Delete(int inX) {
        if (inX == 0) {
            return DeleteFirst();
        }
        if (inX == iN - 1) {
            return DeleteLast();
        }
        
        Nodo pPrev = getNode(inX - 1);
        double pValue = pPrev.next.value;
        pPrev.next = pPrev.next.next;
        
        iN--;
        
        return pValue;
    }
    
    public double DeleteLast() {
        if (iN <= 1) {
            return DeleteFirst();
        }
        
        Nodo pSecondLast = getNode(iN - 2);
        double pValue = nlast.value;
        nlast = pSecondLast;
        nlast.next = null;
        
        iN--;
        return pValue;
    }
    
    public void Clear() {
        this.nfirst = null;
        this.iN = 0;
    }
    
}
