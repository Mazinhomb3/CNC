package br.com.igpaz.telas;

import br.com.igpaz.dal.ModuloConexao;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JOptionPane;

public class Teste extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Teste() throws ParseException {
        initComponents();
        conexao = ModuloConexao.conector();

        //Verifica data do sistema
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lblData.setText(formatador.format(data));
        lblData.setForeground(Color.red);

       
    }

    public void pesquisar() {

        String sql = "select * from tbl_redes where cod_lider_rede like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdLider.getText() + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                txtSupRede.setText(rs.getString("superv_rede"));
                txtCorRede.setText(rs.getString("cor_rede"));
                txtPrRede.setText(rs.getString("pr_rede"));
                txtDistrito.setText(rs.getString("distrito_rede"));
                txtArea.setText(rs.getString("area_rede"));
                txtSetor.setText(rs.getString("setor_rede"));
                txtLider.setText(rs.getString("lider_cel_rede"));
                txtId.setText(rs.getString("id_rede"));

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Limpar() {
        txtId.setText(null);
        txtSupRede.setText(null);
        txtCorRede.setText(null);
        txtPrRede.setText(null);
        txtDistrito.setText(null);
        txtArea.setText(null);
        txtSetor.setText(null);
        txtLider.setText(null);
        txtIdLider.setText(null);

    }

    private void adicionar() {
        String sql = "insert into tbl_dados ( cod_lider, nome_lider, supervisor_rede_lider, rede_lider, cor_rede_lider, distrito_lider, area_lider,"
                + "setor_lider, data_lider, membros_celula, membroscomp_celula, convidadospres_celula, criancas_celula, mda_celula, ge_celula, "
                + "oferta_celula,id_rede) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        try {

            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtIdLider.getText());
            pst.setString(2, txtLider.getText());
            pst.setString(3, txtSupRede.getText());
            pst.setString(4, txtPrRede.getText());
            pst.setString(5, txtCorRede.getText());
            pst.setString(6, txtDistrito.getText());
            pst.setString(7, txtArea.getText());
            pst.setString(8, txtSetor.getText());
            pst.setString(9, txtDataBr.getText());
            pst.setString(10, cmbMtc.getSelectedItem().toString());
            pst.setString(11, cmbMcp.getSelectedItem().toString());
            pst.setString(12, cmbConvPres.getSelectedItem().toString());
            pst.setString(13, cmbCrianca.getSelectedItem().toString());
            pst.setString(14, cmbMda.getSelectedItem().toString());
            pst.setString(15, cmbGes.getSelectedItem().toString());
            pst.setString(16, jFormattedTextField1.getText());
            pst.setString(17, txtId.getText());
            
            
            if ((txtSupRede.getText().isEmpty() || txtCorRede.getText().isEmpty() || txtPrRede.getText().isEmpty() || txtDistrito.getText().isEmpty()
                    || txtArea.getText().isEmpty() || txtSetor.getText().isEmpty() || txtLider.getText().isEmpty() || txtIdLider.getText().isEmpty())) {

                JOptionPane.showMessageDialog(null, "Campos Obrigatórios.");

            } else {

//esse codigo atualiza o banco de dados
                int adicionado = pst.executeUpdate();
//A llinha abaixo serve de apoio ao codigo           
// System.out.println(adicionado);
                if (adicionado > 0) {

                    JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso.");
                    txtId.setText(null);
                    txtSupRede.setText(null);
                    txtCorRede.setText(null);
                    txtPrRede.setText(null);
                    txtDistrito.setText(null);
                    txtArea.setText(null);
                    txtSetor.setText(null);
                    txtLider.setText(null);
                    txtIdLider.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    private void auterar() {
        String sql = "update  tbl_redes set superv_rede=?, cor_rede=? , pr_rede=?, distrito_rede=?, area_rede=?, setor_rede=?,lider_cel_rede=?, cod_lider_rede=? where id_rede=?";
        try {

            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtSupRede.getText());
            pst.setString(2, txtCorRede.getText());
            pst.setString(3, txtPrRede.getText());
            pst.setString(4, txtDistrito.getText());
            pst.setString(5, txtArea.getText());
            pst.setString(6, txtSetor.getText());
            pst.setString(7, txtLider.getText());
            pst.setString(8, txtIdLider.getText());
            pst.setString(8, txtId.getText());

            if ((txtSupRede.getText().isEmpty() || txtCorRede.getText().isEmpty() || txtPrRede.getText().isEmpty() || txtDistrito.getText().isEmpty()
                    || txtArea.getText().isEmpty() || txtSetor.getText().isEmpty() || txtLider.getText().isEmpty() || txtIdLider.getText().isEmpty())) {

                JOptionPane.showMessageDialog(null, "Campos Obrigatórios.");

            } else {

//esse codigo atualiza o banco de dados
                int adicionado = pst.executeUpdate();
//A llinha abaixo serve de apoio ao codigo           
// System.out.println(adicionado);
                if (adicionado > 0) {

                    JOptionPane.showMessageDialog(null, "Dados auterados com sucessos.");

                    txtId.setText(null);
                    txtSupRede.setText(null);
                    txtCorRede.setText(null);
                    txtPrRede.setText(null);
                    txtDistrito.setText(null);
                    txtArea.setText(null);
                    txtSetor.setText(null);
                    txtLider.setText(null);
                    txtIdLider.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    private void deletar() {
        if ((txtSupRede.getText().isEmpty() || txtCorRede.getText().isEmpty() || txtPrRede.getText().isEmpty() || txtDistrito.getText().isEmpty()
                || txtArea.getText().isEmpty() || txtSetor.getText().isEmpty() || txtLider.getText().isEmpty() || txtIdLider.getText().isEmpty())) {

            JOptionPane.showMessageDialog(null, "Você não pode deletar um campo vazio. Click na tabela.");
            txtIdLider.requestFocus();
        } else {

            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza da remoção desse Cadastro.", "Atenção", JOptionPane.YES_NO_OPTION);
            if (JOptionPane.YES_OPTION == confirma) {
                String sql = "delete from tbl_redes where id_rede=?";
                try {

                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtId.getText());
                    int apagado = pst.executeUpdate();
                    if (apagado > 0) {
                        JOptionPane.showMessageDialog(null, "Cadastro removido com sucesso.");

                        txtId.setText(null);
                        txtSupRede.setText(null);
                        txtCorRede.setText(null);
                        txtPrRede.setText(null);
                        txtDistrito.setText(null);
                        txtArea.setText(null);
                        txtSetor.setText(null);
                        txtLider.setText(null);
                        txtIdLider.setText(null);

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
        txtId = new javax.swing.JTextField();
        txtDataBr = new javax.swing.JTextField();
        btnLimpar = new javax.swing.JButton();
        btnAuterar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        lblPrenchaCampos = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblDistrito4 = new javax.swing.JLabel();
        txtIdLider = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSupRede = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPrRede = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDistrito = new javax.swing.JTextField();
        txtCorRede = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSetor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtLider = new javax.swing.JTextField();
        lblDistrito5 = new javax.swing.JLabel();
        txtDataBrasil = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cmbGes = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        cmbMtc = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        cmbMcp = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbConvPres = new javax.swing.JComboBox<>();
        jSeparator4 = new javax.swing.JSeparator();
        cmbCrianca = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cmcTotPresent = new javax.swing.JComboBox<>();
        cmbMda = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();

        txtCodPastor.setEditable(false);

        jLabel1.setText("COD.");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro Dados Células");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/pincel.gif"))); // NOI18N
        btnLimpar.setToolTipText("Adicionar");
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 50, 40));

        btnAuterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/update.png"))); // NOI18N
        btnAuterar.setToolTipText("Atualizar");
        btnAuterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAuterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuterarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAuterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 50, 40));

        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/delete.png"))); // NOI18N
        btnDeletar.setToolTipText("Deletar");
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });
        getContentPane().add(btnDeletar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 50, 40));

        lblPrenchaCampos.setText("* Campos Obrigatórios");
        getContentPane().add(lblPrenchaCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 6, -1, -1));

        lblData.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblData.setText("jLabel1");
        getContentPane().add(lblData, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 7, 84, -1));

        lblDistrito4.setText("Cod. Célula:");
        getContentPane().add(lblDistrito4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));
        getContentPane().add(txtIdLider, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 28, 65, -1));

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/add.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 50, 40));

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/read.png"))); // NOI18N
        btnPesquisar.setToolTipText("Adicionar");
        btnPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 50, 40));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel2.setText("Sup. Rede:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 62, -1, -1));

        txtSupRede.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        getContentPane().add(txtSupRede, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 58, 200, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel3.setText("Cor. Rede:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 90, -1, -1));

        txtPrRede.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        getContentPane().add(txtPrRede, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 114, 200, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel4.setText("Pr. Rede:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 118, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel5.setText("Distrito:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 146, -1, -1));

        txtDistrito.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        getContentPane().add(txtDistrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 142, 200, -1));

        txtCorRede.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        getContentPane().add(txtCorRede, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 86, 200, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel6.setText("Área:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        txtSetor.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        getContentPane().add(txtSetor, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 200, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel7.setText("Setor:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        txtArea.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        getContentPane().add(txtArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 200, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel8.setText("Líder:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        txtLider.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        getContentPane().add(txtLider, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 200, -1));

        lblDistrito5.setText("DIA DA CÉLULA:");
        getContentPane().add(lblDistrito5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        try {
            txtDataBrasil.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txtDataBrasil, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 26, 81, -1));

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setForeground(new java.awt.Color(255, 153, 51));
        jPanel1.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Membros total da célula");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(50, 0, 118, 14);

        cmbGes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40" }));
        jPanel1.add(cmbGes);
        cmbGes.setBounds(170, 320, 50, 26);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("TOTAL DE PRESENTES");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(50, 230, 190, 14);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(40, 230, 200, 10);
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(40, 50, 200, 20);

        cmbMtc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40" }));
        jPanel1.add(cmbMtc);
        cmbMtc.setBounds(50, 20, 44, 26);
        jPanel1.add(jSeparator3);
        jSeparator3.setBounds(40, 110, 200, 10);

        cmbMcp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40" }));
        jPanel1.add(cmbMcp);
        cmbMcp.setBounds(50, 80, 44, 26);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Membros compromissados presentes");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(50, 60, 190, 14);

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Convidados Presentes");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(50, 120, 190, 14);

        cmbConvPres.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40" }));
        jPanel1.add(cmbConvPres);
        cmbConvPres.setBounds(50, 140, 44, 26);
        jPanel1.add(jSeparator4);
        jSeparator4.setBounds(40, 170, 200, 10);

        cmbCrianca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40" }));
        jPanel1.add(cmbCrianca);
        cmbCrianca.setBounds(50, 200, 44, 26);

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Crianças");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(50, 180, 190, 14);

        cmcTotPresent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40" }));
        jPanel1.add(cmcTotPresent);
        cmcTotPresent.setBounds(50, 250, 44, 26);

        cmbMda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40" }));
        jPanel1.add(cmbMda);
        cmbMda.setBounds(100, 320, 50, 26);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("MDA'S ");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(110, 300, 40, 16);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("GE'S");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(180, 300, 48, 16);

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jPanel1.add(jFormattedTextField1);
        jFormattedTextField1.setBounds(150, 270, 90, 30);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("OFERTA");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(171, 250, 50, 16);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 280, 370));

        setBounds(0, 0, 640, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAuterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuterarActionPerformed
        // Auterar
        auterar();
    }//GEN-LAST:event_btnAuterarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        // delete
        deletar();
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // limpar
        Limpar();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_btnPesquisarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAuterar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JComboBox<String> cmbConvPres;
    private javax.swing.JComboBox<String> cmbCrianca;
    private javax.swing.JComboBox<String> cmbGes;
    private javax.swing.JComboBox<String> cmbMcp;
    private javax.swing.JComboBox<String> cmbMda;
    private javax.swing.JComboBox<String> cmbMtc;
    private javax.swing.JComboBox<String> cmcTotPresent;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblDistrito4;
    private javax.swing.JLabel lblDistrito5;
    private javax.swing.JLabel lblPrenchaCampos;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtCodPastor;
    private javax.swing.JTextField txtCorRede;
    private javax.swing.JTextField txtDataBr;
    private javax.swing.JFormattedTextField txtDataBrasil;
    private javax.swing.JTextField txtDistrito;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdLider;
    private javax.swing.JTextField txtIdPastor;
    private javax.swing.JTextField txtLider;
    private javax.swing.JTextField txtPrRede;
    private javax.swing.JTextField txtSetor;
    private javax.swing.JTextField txtSupRede;
    // End of variables declaration//GEN-END:variables
}
