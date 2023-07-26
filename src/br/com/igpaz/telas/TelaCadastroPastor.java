
package br.com.igpaz.telas;

import br.com.igpaz.dal.ModuloConexao;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaCadastroPastor extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaCadastroPastor() {
        initComponents();
        conexao = ModuloConexao.conector();
        this.pesquisa_avancada();
        //Verifica data do sistema
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lblData.setText(formatador.format(data));
        lblData.setForeground(Color.red);

    }

    private void pesquisa_avancada() {
        String sql = "select  cod_pr as Cod, nome_pr as Nome , cor_rede as Cor_Rede , distrito as Distrito from pastor  where nome_pr like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText() + "%");
            rs = pst.executeQuery();
            tblPastor.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void setar_campos() {
        int setar = tblPastor.getSelectedRow();
        txtCodPastor.setText(tblPastor.getModel().getValueAt(setar, 0).toString());
        txtNome.setText(tblPastor.getModel().getValueAt(setar, 1).toString());
        txtCorRede.setText(tblPastor.getModel().getValueAt(setar, 2).toString());
        txtDistrito.setText(tblPastor.getModel().getValueAt(setar, 3).toString());

    }

    private void adicionar() {
        String sql = "insert into pastor ( nome_pr,cor_rede,distrito) values (?,?,?) ";
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtCorRede.getText());
            pst.setString(3, txtDistrito.getText());
            if ((txtNome.getText().isEmpty() || txtCorRede.getText().isEmpty() )) {

                JOptionPane.showMessageDialog(null, "Campos Obrigatórios.");

            } else {

//esse codigo atualiza o banco de dados
                int adicionado = pst.executeUpdate();
//A llinha abaixo serve de apoio ao codigo           
// System.out.println(adicionado);
                if (adicionado > 0) {

                    JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso.");
                    txtNome.setText(null);
                    txtCorRede.setText(null);
                    txtDistrito.setText(null);
                    this.pesquisa_avancada();

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    private void auterar() {
        String sql = "update  pastor set nome_pr=?,cor_rede=?,distrito=? where cod_pr=?";
        try {

            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtNome.getText());
            pst.setString(2, txtCorRede.getText());
            pst.setString(3, txtDistrito.getText());
            pst.setString(4, txtCodPastor.getText());

             if ((txtNome.getText().isEmpty() || txtCorRede.getText().isEmpty() )) {

                JOptionPane.showMessageDialog(null, "Campos Obrigatórios.");

            } else {

//esse codigo atualiza o banco de dados
                int adicionado = pst.executeUpdate();
//A llinha abaixo serve de apoio ao codigo           
// System.out.println(adicionado);
                if (adicionado > 0) {

                    JOptionPane.showMessageDialog(null, "Dados auterados com sucessos.");
                    txtNome.setText(null);
                    txtCorRede.setText(null);
                    txtDistrito.setText(null);
                    this.pesquisa_avancada();

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    private void deletar() {
        if (txtNome.getText().isEmpty() || txtNome.getText().length() < 4) {

            JOptionPane.showMessageDialog(null, "Você não pode deletar um campo vazio. Click na tabela.");
            txtNome.requestFocus();
        } else {

            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza da remoção desse Cadastro.", "Atenção", JOptionPane.YES_NO_OPTION);
            if (JOptionPane.YES_OPTION == confirma) {
                String sql = "delete from pastor where cod_pr=?";
                try {

                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtCodPastor.getText());
                    int apagado = pst.executeUpdate();
                    if (apagado > 0) {
                        JOptionPane.showMessageDialog(null, "Cadastro removido com sucesso.");

                        txtNome.setText(null);
                        txtCorRede.setText(null);
                        txtDistrito.setText(null);
                        this.pesquisa_avancada();

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtIdPastor = new javax.swing.JTextField();
        txtCodPastor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblCorRede = new javax.swing.JLabel();
        txtCorRede = new javax.swing.JTextField();
        lblDistrito = new javax.swing.JLabel();
        txtDistrito = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnAuterar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPastor = new javax.swing.JTable();
        lblPrenchaCampos = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();

        txtCodPastor.setEditable(false);

        jLabel1.setText("COD.");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro Pastor");

        lblNome.setText("*Nome");

        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeKeyReleased(evt);
            }
        });

        lblCorRede.setText("*Cor da Rede");

        lblDistrito.setText("*Distrito");

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/add.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnAuterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/update.png"))); // NOI18N
        btnAuterar.setToolTipText("Atualizar");
        btnAuterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAuterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuterarActionPerformed(evt);
            }
        });

        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/delete.png"))); // NOI18N
        btnDeletar.setToolTipText("Deletar");
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        tblPastor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPastor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPastorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPastor);

        lblPrenchaCampos.setText("*Preencha todos os Campos");

        lblData.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblData.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPrenchaCampos)
                .addGap(51, 51, 51))
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdicionar)
                        .addGap(101, 101, 101)
                        .addComponent(btnAuterar)
                        .addGap(106, 106, 106)
                        .addComponent(btnDeletar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCorRede)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCorRede))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDistrito)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(80, 124, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrenchaCampos)
                    .addComponent(lblData))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorRede)
                    .addComponent(txtCorRede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDistrito)
                    .addComponent(txtDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAdicionar)
                    .addComponent(btnAuterar)
                    .addComponent(btnDeletar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(0, 0, 640, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyReleased
        // Pesquisa Avançada
        pesquisa_avancada();
    }//GEN-LAST:event_txtNomeKeyReleased

    private void tblPastorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPastorMouseClicked
        // Setar Campos
        setar_campos();
    }//GEN-LAST:event_tblPastorMouseClicked

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // Adicionar
        adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnAuterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuterarActionPerformed
        // Auterar
        auterar();
    }//GEN-LAST:event_btnAuterarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        // delete
        deletar();
    }//GEN-LAST:event_btnDeletarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAuterar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCorRede;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblDistrito;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPrenchaCampos;
    private javax.swing.JTable tblPastor;
    private javax.swing.JTextField txtCodPastor;
    private javax.swing.JTextField txtCorRede;
    private javax.swing.JTextField txtDistrito;
    private javax.swing.JTextField txtIdPastor;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
