
package proyecto_1_edd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class ListaArista {
    
    private Arista nfirst;
    private Arista nlast;
    private String name;
    private int iN;
    
    Lista lista1;
    ListaArista lista2;    
    
    ListaArista(String n) {
        
        nfirst = null;
        iN = 0;
        name = n;
        
    }
    
    public ListaArista(){
        this.lista1 = null;
        this.lista2 = null;
    }      
    
    //---------------------------------//
    
    public Lista getLista1(){
        return lista1;
    }
    
    public void setLista1(Lista lista1){
        this.lista1 = lista1;
    }
    
    public ListaArista getLista2(){
        return lista2;
    }
    
    public void setLista2(ListaArista lista2){
        this.lista2 = lista2;
    }       
    
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
            if((getArista(i + 1).src == pSrc && getArista(i+1).dst == pDst) || (getArista(i + 1).dst == pSrc && getArista(i+1).src == pDst)) {
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
            if((getArista(i + 1).src == pSrc && getArista(i+1).dst == pDst) || (getArista(i + 1).dst == pSrc && getArista(i+1).src == pDst)) {
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
        for (int i = 1; i < inX; i++) {
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

    public static void escribir_texto(Lista ciudades, ListaArista aristas){
        String contenido_actual = "";
        String path = "test\\archivo.txt";
        contenido_actual += "ciudades\n";
        if (!ciudades.isEmpty()){
            Nodo temp = ciudades.First();
            for (int i = 0; i < ciudades.Size(); i++){
                String contenidociudad =  Integer.toString(temp.getvalor());
                contenido_actual += contenidociudad;
                temp = temp.getnext();
            }
        }
        contenido_actual += "aristas\n";
        if (!aristas.isEmpty()){
            Arista temp = aristas.First();
            for (int i = 0; i < aristas.Size(); i++){
                String contenidoaristas = temp.getsrc() + "," + temp.getdst() + "," + temp.getvalor() + "\n";
                contenido_actual += contenidoaristas;
                temp = temp.getnext();
            }
        }
        try{
            File file = new File(path);
            PrintWriter pw = new PrintWriter(file);
            pw.print(contenido_actual);
            pw.close();
        }catch (Exception e){
          JOptionPane.showMessageDialog(null, "Error al escribir el archivo"); 
        }
    }
 
    public static ListaArista leer_texto(){
        Lista ciudades = new Lista("Ciudades");
        ListaArista aristas = new ListaArista("Aristas");
        String linea;
        String path = "test\\archivo.txt";
        File file = new File(path);
        try {
            if(!file.exists()){
                file.createNewFile();
            }else{
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                        int readingOp = 0;
                while ((linea = br.readLine())!= null){
                    if (!linea.isEmpty()){
                        if (linea.trim().equals("ciudad")){
                            readingOp = 0;
                        }else if(linea.trim().equals("aristas")){
                            readingOp = 1;
                        }else if (readingOp ==0){
                            ciudades.InsertLast(Integer.parseInt(linea.trim()));
                        }else if (readingOp == 1){
                            String[] contenido = linea.split(",");
                            int src = Integer.parseInt(contenido[0]);
                            int dst = Integer.parseInt(contenido[1]);
                            double valor = Double.parseDouble(contenido[2]);
                            aristas.InsertLast(src, dst, valor);
                        }
                    }
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERROR");
        }ListaArista result = new ListaArista();
    result.setLista1(ciudades);
    result.setLista2(aristas);
    return result;
        
    }   
}
