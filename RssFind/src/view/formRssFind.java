/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author CCE User
 */
public class formRssFind extends javax.swing.JFrame {

    /**
     * Creates new form formRssFind
     */
    public formRssFind() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        mmCadastrar = new javax.swing.JMenu();
        mmCadastroSites = new javax.swing.JMenuItem();
        mmCadastrarTermos = new javax.swing.JMenuItem();
        mmCadastroHorario = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mmEditarSites = new javax.swing.JMenuItem();
        mmEditarTemros = new javax.swing.JMenuItem();
        mmEditarHorario = new javax.swing.JMenuItem();
        mmEditarNoticias = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        mmCadastrar.setText("Cadastrar");

        mmCadastroSites.setText("Sites");
        mmCadastrar.add(mmCadastroSites);

        mmCadastrarTermos.setText("Termos");
        mmCadastrar.add(mmCadastrarTermos);

        mmCadastroHorario.setText("Horários");
        mmCadastrar.add(mmCadastroHorario);

        jMenuBar1.add(mmCadastrar);

        jMenu1.setText("Editar");

        mmEditarSites.setText("Sites");
        jMenu1.add(mmEditarSites);

        mmEditarTemros.setText("Termos");
        jMenu1.add(mmEditarTemros);

        mmEditarHorario.setText("Horário");
        jMenu1.add(mmEditarHorario);

        mmEditarNoticias.setText("Notícias");
        jMenu1.add(mmEditarNoticias);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 697, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 441, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenu mmCadastrar;
    public javax.swing.JMenuItem mmCadastrarTermos;
    public javax.swing.JMenuItem mmCadastroHorario;
    public javax.swing.JMenuItem mmCadastroSites;
    public javax.swing.JMenuItem mmEditarHorario;
    public javax.swing.JMenuItem mmEditarNoticias;
    public javax.swing.JMenuItem mmEditarSites;
    public javax.swing.JMenuItem mmEditarTemros;
    // End of variables declaration//GEN-END:variables
}
