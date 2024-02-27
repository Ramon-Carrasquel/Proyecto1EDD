
package Interfaces;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.BorderLayout;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.swingViewer.*;
import org.graphstream.ui.view.Viewer;

import proyecto_1_edd.*;

/**
 *
 * @author sebas
 */
public class VentanaGrafica extends javax.swing.JFrame {

    /**
     * Creates new form VentanaGrafica
     */
    
    int nNodos;
    Node Nodes[];
    Edge Edges[];
    ListaArista arists;
    Grafo graph0;
    
    int numHormigas;
    
    int cOrigen;
    int cDestino;
    
    int C;
    int a;
    int b;
    int q;
    double evaporacion;
    
    
    int iterH = 0;
    int iterC = 0;
    
    
    SistemaHormiga sysh;
    
    String SSheet = "node { shape:circle; text-size: 18px; text-style: bold; text-alignment: center; size: 25px; fill-color: #d5cc67; } "
                + "graph { fill-color: #a38943, #b1834e; fill-mode: gradient-vertical; } "
                + "edge { text-size: 18px; text-style: bold; text-alignment: along; size: 3px; fill-color: #d5cc67; } ";
    
    //Graph graph = new MultiGraph("graph");
    
    
    public VentanaGrafica() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        addGraph(nNodos);
    }
    
    public VentanaGrafica(int numNodes, int numHormigas_, int cOrigen_, int cDestino_, int C_, int a_, int b_, int q_, double evaporacion_, ListaArista list) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        
        this.numHormigas = numHormigas_;
        
        this.cOrigen = cOrigen_;
        this.cDestino = cDestino_;
        
        this.C = C_;
        this.a = a_;
        this.b = b_;
        this.q = q_;
        this.evaporacion = evaporacion_;
        
        this.nNodos = numNodes;
        this.arists = list;
        this.graph0 = new Grafo(nNodos, cOrigen, cDestino);
        this.Nodes = new Node[nNodos];
        this.Edges = new Edge[arists.Size()];
        
        
        for(int i = 0; i < arists.Size() + 1; i++) {
            graph0.añadirArista(arists.getArista(i - 1).src, arists.getArista(i - 1).dst, arists.getArista(i - 1).valor);
        }
        
        
        sysh = new SistemaHormiga(graph0, arists, numHormigas, C, a, b, q, evaporacion);
        sysh.llenarArregloH(cOrigen_);
        
        
        
        sysh.iniciarCiclo();
        for(int j = 0; j < sysh.aristas.Size(); j++) {
            sysh.actuFeromona(sysh.aristas.getArista(j));
        }
        
        
        addGraph(nNodos);
    }
    
    public void startCycle() {
        
    }
    
    public void addGraph(int numNodos) {
        
        
        
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        
        Graph graph = new MultiGraph("graph");
        
        graph.setAttribute("ui.stylesheet", SSheet);
        
        
        for(int i = 1; i <= numNodos; i++) {
            Node name = graph.addNode(Integer.toString(i));
            Nodes[i - 1] = name;
        }
        
        for(int i = 0; i < Nodes.length; i++) {
            if(i == cOrigen - 1) {
                Nodes[i].setAttribute("ui.class", "origin");
            }
            else if(i == cDestino - 1) {
                Nodes[i].setAttribute("ui.class", "destiny");
            }
            Nodes[i].setAttribute("ui.label", Integer.toString(i + 1));
            Nodes[i].setAttribute("ui.label.align", "center");
        }
        
        for(int i = 0; i < arists.Size(); i++) {
            String name = Integer.toString(arists.getArista(i).getSrc()) + Integer.toString(arists.getArista(i).getDst());
            Edge name1 = graph.addEdge(name, arists.getArista(i).getDst(), arists.getArista(i).getSrc());
            name1.setAttribute("ui.label", Double.toString(arists.getArista(i).getValor()));
            name1.setAttribute("ui.label.align", "along");
            Edges[i] = name1;
        }
        
        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        
        
        
        Hormiga wat = sysh.getHormigas(iterH);
        jTrayectoria1.setText(Double.toString(wat.getTrayecto()));
        
        int[] walk = wat.getCiudadesVisitadas();
        
        for(int i = 0; i < walk.length - 1; i++) {
            if(walk[i + 1] != 0) {
                int inX = arists.SearchIndex(walk[i] - 1, walk[i + 1] - 1) - 1;
                Edges[inX].setAttribute("ui.class", "antPath");
            }
            else {
                ;
            }
        }
        
        String travel = "";
        
        for(int i = 0; i < walk.length; i++) {
            if(walk[i] != 0) {
                travel += walk[i] + " ";
            }
        }
        
        jRecorrido.setText(travel);
        
        
        inXHormiga.setText(Integer.toString(iterH + 1));
        inXCiclo.setText(Integer.toString(iterC + 1));
        
        graph.addAttribute("ui.stylesheet", "edge.antPath { fill-color: #53FF14; } ");
        graph.addAttribute("ui.stylesheet", "node.origin { fill-color: #53FF14; } ");
        graph.addAttribute("ui.stylesheet", "node.destiny { fill-color: #14FFD1; } ");
        
        
        
        ViewPanel view = viewer.addDefaultView(false);
        view.setSize(450, 450);
        
        PanelContenidoGraph.removeAll();
        PanelContenidoGraph.add(view, BorderLayout.CENTER);
        PanelContenidoGraph.revalidate();
        PanelContenidoGraph.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        PanelFondo = new javax.swing.JPanel();
        PanelContenidoGraph = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jRecorrido = new javax.swing.JLabel();
        CloseButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        Titulo2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        inXCiclo = new javax.swing.JLabel();
        Titulo4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        inXHormiga = new javax.swing.JLabel();
        Titulo6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        Titulo3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTrayectoria1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(750, 470));

        PanelFondo.setBackground(new java.awt.Color(255, 153, 51));
        PanelFondo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PanelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelContenidoGraph.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        PanelContenidoGraph.setPreferredSize(new java.awt.Dimension(450, 450));

        javax.swing.GroupLayout PanelContenidoGraphLayout = new javax.swing.GroupLayout(PanelContenidoGraph);
        PanelContenidoGraph.setLayout(PanelContenidoGraphLayout);
        PanelContenidoGraphLayout.setHorizontalGroup(
            PanelContenidoGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
        );
        PanelContenidoGraphLayout.setVerticalGroup(
            PanelContenidoGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
        );

        PanelFondo.add(PanelContenidoGraph, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 450, 450));

        jPanel3.setBackground(new java.awt.Color(204, 153, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jRecorrido.setBackground(new java.awt.Color(102, 102, 102));
        jRecorrido.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jRecorrido.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        PanelFondo.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, 140, 60));

        CloseButton.setBackground(new java.awt.Color(0, 153, 153));
        CloseButton.setText("X");
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });
        PanelFondo.add(CloseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 50, -1));

        nextButton.setBackground(new java.awt.Color(0, 153, 153));
        nextButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nextButton.setForeground(new java.awt.Color(255, 255, 255));
        nextButton.setText("Siguiente");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        PanelFondo.add(nextButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 420, 90, -1));

        jPanel6.setBackground(new java.awt.Color(204, 153, 0));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Titulo2.setFont(new java.awt.Font("Franklin Gothic Heavy", 1, 20)); // NOI18N
        Titulo2.setForeground(new java.awt.Color(0, 153, 153));
        Titulo2.setText("Recorrido");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(Titulo2)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(Titulo2)
                .addGap(15, 15, 15))
        );

        PanelFondo.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 150, 60));

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        inXCiclo.setFont(new java.awt.Font("Franklin Gothic Heavy", 1, 20)); // NOI18N
        inXCiclo.setForeground(new java.awt.Color(0, 153, 153));
        inXCiclo.setText("0");

        Titulo4.setFont(new java.awt.Font("Franklin Gothic Heavy", 1, 20)); // NOI18N
        Titulo4.setForeground(new java.awt.Color(0, 153, 153));
        Titulo4.setText("Ciclo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(Titulo4)
                .addGap(35, 35, 35)
                .addComponent(inXCiclo)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inXCiclo)
                    .addComponent(Titulo4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelFondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 190, 40));

        jPanel2.setBackground(new java.awt.Color(255, 153, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        inXHormiga.setFont(new java.awt.Font("Franklin Gothic Heavy", 1, 20)); // NOI18N
        inXHormiga.setForeground(new java.awt.Color(0, 153, 153));
        inXHormiga.setText("0");

        Titulo6.setFont(new java.awt.Font("Franklin Gothic Heavy", 1, 20)); // NOI18N
        Titulo6.setForeground(new java.awt.Color(0, 153, 153));
        Titulo6.setText("Hormiga");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(Titulo6)
                .addGap(18, 18, 18)
                .addComponent(inXHormiga)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inXHormiga)
                    .addComponent(Titulo6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelFondo.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 190, 40));

        jPanel7.setBackground(new java.awt.Color(204, 153, 0));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Titulo3.setFont(new java.awt.Font("Franklin Gothic Heavy", 1, 20)); // NOI18N
        Titulo3.setForeground(new java.awt.Color(0, 153, 153));
        Titulo3.setText("Trayecto");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(Titulo3)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(Titulo3)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        PanelFondo.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, 150, 60));

        jPanel5.setBackground(new java.awt.Color(204, 153, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTrayectoria1.setBackground(new java.awt.Color(102, 102, 102));
        jTrayectoria1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jTrayectoria1.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jTrayectoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jTrayectoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        PanelFondo.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 140, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        
        this.dispose();//Este boton cierra la ventana del Gráfico
    }//GEN-LAST:event_CloseButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        //la idea es que este boton limpie el contenido de PanelCotenidoGraph y que con cada iteracion del grafico se muestre un rspectivo grafico
        
        //RECORDATORIO: Arreglar los ciclos y las hormigas.
        
        iterH++;
        
        if(iterH > numHormigas - 1) {
            iterH = 0;
            iterC++;
            sysh.iniciarCiclo();
            for(int j = 0; j < sysh.aristas.Size(); j++) {
                sysh.actuFeromona(sysh.aristas.getArista(j));
            }
        }
        
        
        if(iterC > C - 1) {
            this.dispose();
        }
        else {
            PanelContenidoGraph.removeAll();
            addGraph(nNodos);
        }
    }//GEN-LAST:event_nextButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaGrafica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButton;
    private javax.swing.JPanel PanelContenidoGraph;
    private javax.swing.JPanel PanelFondo;
    private javax.swing.JLabel Titulo2;
    private javax.swing.JLabel Titulo3;
    private javax.swing.JLabel Titulo4;
    private javax.swing.JLabel Titulo6;
    private javax.swing.JLabel inXCiclo;
    private javax.swing.JLabel inXHormiga;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel jRecorrido;
    private javax.swing.JLabel jTrayectoria1;
    private javax.swing.JButton nextButton;
    // End of variables declaration//GEN-END:variables
}
