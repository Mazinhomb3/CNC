package br.com.igpaz.telas;

import java.sql.*;
import br.com.igpaz.dal.ModuloConexao;
import java.awt.Toolkit;
import java.text.DateFormat;
import javax.swing.JOptionPane;

public class TelaPrincipal extends javax.swing.JFrame {

    Connection conexao = null;

    public TelaPrincipal() {
        initComponents();
        setIcon();
        conexao = ModuloConexao.conector();
        
        TelaConvetidos.lblUsuarioFinal = lblUsuario;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Desktop = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        menCad = new javax.swing.JMenu();
        menCadConv = new javax.swing.JMenuItem();
        menCadPastor = new javax.swing.JMenuItem();
        menCadUsuario = new javax.swing.JMenuItem();
        MenCadLidCel = new javax.swing.JMenuItem();
        menCadDadosCel = new javax.swing.JMenuItem();
        menRel = new javax.swing.JMenu();
        menRelImp = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menSob = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de N. Convertidos");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        Desktop.setPreferredSize(new java.awt.Dimension(640, 480));

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/logopazchurch120.png"))); // NOI18N

        lblData.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblData.setText("Data");

        lblUsuario.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblUsuario.setText("USUÁRIO");

        menCad.setText("Cadastro");
        menCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadActionPerformed(evt);
            }
        });

        menCadConv.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menCadConv.setText("Converções");
        menCadConv.setEnabled(false);
        menCadConv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadConvActionPerformed(evt);
            }
        });
        menCad.add(menCadConv);

        menCadPastor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        menCadPastor.setText("Pastor");
        menCadPastor.setEnabled(false);
        menCadPastor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadPastorActionPerformed(evt);
            }
        });
        menCad.add(menCadPastor);

        menCadUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        menCadUsuario.setText("Usuário");
        menCadUsuario.setEnabled(false);
        menCadUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadUsuarioActionPerformed(evt);
            }
        });
        menCad.add(menCadUsuario);

        MenCadLidCel.setText("Cad. Lider Célula");
        MenCadLidCel.setEnabled(false);
        MenCadLidCel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenCadLidCelActionPerformed(evt);
            }
        });
        menCad.add(MenCadLidCel);

        menCadDadosCel.setText("Cad.  Dados Célula");
        menCadDadosCel.setEnabled(false);
        menCadDadosCel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadDadosCelActionPerformed(evt);
            }
        });
        menCad.add(menCadDadosCel);

        Menu.add(menCad);
        menCad.getAccessibleContext().setAccessibleName("&Cadastro");

        menRel.setText("Relatório");
        menRel.setEnabled(false);
        menRel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelActionPerformed(evt);
            }
        });

        menRelImp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        menRelImp.setText("Imprimir");
        menRelImp.setEnabled(false);
        menRelImp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelImpActionPerformed(evt);
            }
        });
        menRel.add(menRelImp);

        Menu.add(menRel);

        jMenu4.setText("Ajuda");

        menSob.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_MASK));
        menSob.setText("Sobre");
        menSob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menSobActionPerformed(evt);
            }
        });
        jMenu4.add(menSob);

        Menu.add(jMenu4);

        jMenu3.setText("Opções");

        menSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menSair.setText("Sair");
        menSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menSairActionPerformed(evt);
            }
        });
        jMenu3.add(menSair);

        Menu.add(jMenu3);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addComponent(lblData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(220, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(806, 544));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // As linhas Abaixo Substituem a lbldata pela data do sistem
        java.util.Date data = new java.util.Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.MEDIUM);
        lblData.setText(formatador.format(data));

    }//GEN-LAST:event_formWindowActivated

    private void menSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menSairActionPerformed
        // Exibe uma Caixa de Dialogo
        int sair = JOptionPane.showConfirmDialog(null, "Tem Ceteza de Sair?", " Atenção ", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_menSairActionPerformed

    private void menSobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menSobActionPerformed
        // Chama a tela sobre
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true);
    }//GEN-LAST:event_menSobActionPerformed

    private void menCadConvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadConvActionPerformed
        // Chama a tela Convertidos
        TelaConvetidos convertidos = new TelaConvetidos();
        convertidos.setVisible(true);
        Desktop.add(convertidos);


    }//GEN-LAST:event_menCadConvActionPerformed

    private void menCadPastorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadPastorActionPerformed
        // Chama a tela Cadastro pastor
        TelaCadastroPastor cadastropastor = new TelaCadastroPastor();
        cadastropastor.setVisible(true);
        Desktop.add(cadastropastor);
    }//GEN-LAST:event_menCadPastorActionPerformed

    private void menCadUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadUsuarioActionPerformed
        // Chama a tela usuarios
        TelaUsuario usuario = new TelaUsuario();
        usuario.setVisible(true);
        Desktop.add(usuario);
    }//GEN-LAST:event_menCadUsuarioActionPerformed

    private void menRelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelActionPerformed
        // Chama a tela usuarios
        //TelaRelatorioPastor relatoriopastor = new TelaRelatorioPastor();
        //relatoriopastor.setVisible(true);
        //Desktop.add(relatoriopastor);
    }//GEN-LAST:event_menRelActionPerformed

    private void menRelImpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelImpActionPerformed
        // Chama a telaRelatorioPastor
        TelaRelatorioPastor relatoriopastor = new TelaRelatorioPastor();
        relatoriopastor.setVisible(true);
        Desktop.add(relatoriopastor);
        
    }//GEN-LAST:event_menRelImpActionPerformed

    private void menCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_menCadActionPerformed

    private void MenCadLidCelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenCadLidCelActionPerformed
        // TODO add your handling code here:
        TelaCadastroLiderCelula cadastrolidercelula = new TelaCadastroLiderCelula();
        cadastrolidercelula.setVisible(true);
        Desktop.add(cadastrolidercelula);
    }//GEN-LAST:event_MenCadLidCelActionPerformed

    private void menCadDadosCelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadDadosCelActionPerformed
        // TODO add your handling code here:
     TelaCadastroDados telacadastrodados = new TelaCadastroDados();
     telacadastrodados.setVisible(true);
     Desktop.add(telacadastrodados);
    }//GEN-LAST:event_menCadDadosCelActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    public static javax.swing.JMenuItem MenCadLidCel;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    public static javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JMenu menCad;
    public static javax.swing.JMenuItem menCadConv;
    public static javax.swing.JMenuItem menCadDadosCel;
    public static javax.swing.JMenuItem menCadPastor;
    public static javax.swing.JMenuItem menCadUsuario;
    public static javax.swing.JMenu menRel;
    public static javax.swing.JMenuItem menRelImp;
    private javax.swing.JMenuItem menSair;
    private javax.swing.JMenuItem menSob;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/br/com/igpaz/icones/logopazchurch120.png")));

    }

}
