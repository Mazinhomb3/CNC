package br.com.igpaz.telas;

import br.com.igpaz.dal.ModuloConexao;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class TelaCadastroDados extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaCadastroDados() {
        initComponents();
        conexao = ModuloConexao.conector();

        //Verifica data do sistema
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lblData.setText(formatador.format(data));
        lblData.setForeground(Color.red);

    }

    public void pesquisar() {

        String sql = "select * from tbl_dados where cod_lider like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdLider.getText() + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                txtSupRede.setText(rs.getString("supervisor_rede_lider"));
                txtCorRede.setText(rs.getString("cor_rede_lider"));
                txtPrRede.setText(rs.getString("rede_lider"));
                txtDistrito.setText(rs.getString("distrito_lider"));
                txtArea.setText(rs.getString("area_lider"));
                txtSetor.setText(rs.getString("setor_lider"));
                txtLider.setText(rs.getString("nome_lider"));
                txtId.setText(rs.getString("id_lider"));
                txtDataBrasil.setText(rs.getString("data_lider"));
                cmbMtc.addItem(rs.getString("membros_celula"));
                cmbMcp.addItem(rs.getString("membroscomp_celula"));
                cmbConvPres.addItem(rs.getString("convidadospres_celula"));
                cmbCrianca.addItem(rs.getString("criancas_celula"));
                cmbMda.addItem(rs.getString("mda_celula"));
                cmbGes.addItem(rs.getString("ge_celula"));

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
        txtIdLider.setText(null);

    }

    private void somar() {

        String mcp = cmbMcp.getSelectedItem().toString();
        int mcp1 = Integer.parseInt(mcp);
        String convPres = cmbConvPres.getSelectedItem().toString();
        int convPres1 = Integer.parseInt(convPres);
        String criancas = cmbCrianca.getSelectedItem().toString();
        int criancas1 = Integer.parseInt(criancas);
        int total = mcp1 + convPres1 + criancas1;
        String total1 = String.valueOf(total);
        lblTotalPres.setText(total1);

    }

    private void adicionar() {

        String sql = "insert into tbl_dados ( cod_lider, nome_lider, supervisor_rede_lider, rede_lider, cor_rede_lider, distrito_lider, area_lider,"
                + "setor_lider, data_lider, membros_celula, membroscomp_celula, convidadospres_celula, criancas_celula, totalpres_celula, mda_celula, ge_celula, "
                + "oferta_celula, id_rede, digitador_lider) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        try {

            String databrasil = txtDataBrasil.getText();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = format.parse(databrasil);
            DateFormat formatBR = new SimpleDateFormat("yyyy-MM-dd");
            String dateFormated = formatBR.format(date1);
            txtDateFormated.setText(dateFormated);

            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtIdLider.getText());
            pst.setString(2, txtLider.getText());
            pst.setString(3, txtSupRede.getText());
            pst.setString(4, txtPrRede.getText());
            pst.setString(5, txtCorRede.getText());
            pst.setString(6, txtDistrito.getText());
            pst.setString(7, txtArea.getText());
            pst.setString(8, txtSetor.getText());
            pst.setString(9, txtDateFormated.getText());
            pst.setString(10, cmbMtc.getSelectedItem().toString());
            pst.setString(11, cmbMcp.getSelectedItem().toString());
            pst.setString(12, cmbConvPres.getSelectedItem().toString());
            pst.setString(13, cmbCrianca.getSelectedItem().toString());
            pst.setString(14, lblTotalPres.getText());
            pst.setString(15, cmbMda.getSelectedItem().toString());
            pst.setString(16, cmbGes.getSelectedItem().toString());
            pst.setString(17, txtOferta.getText());
            pst.setString(18, txtId.getText());
            pst.setString(19, TelaPrincipal.lblUsuario.getText());

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
                    lblTotalPres.setText(null);
                    txtOferta.setText(null);
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

            JOptionPane.showMessageDialog(null, "Você não pode deletar um campo vazio.");
            txtIdLider.requestFocus();
        } else {

            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza da remoção desses dados.", "Atenção", JOptionPane.YES_NO_OPTION);
            if (JOptionPane.YES_OPTION == confirma) {
                String sql = "delete from tbl_dados where id_lider=?";
                try {

                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtId.getText());
                    int apagado = pst.executeUpdate();
                    if (apagado > 0) {
                        JOptionPane.showMessageDialog(null, "dados removido com sucesso.");

                        txtId.setText(null);
                        txtSupRede.setText(null);
                        txtCorRede.setText(null);
                        txtPrRede.setText(null);
                        txtDistrito.setText(null);
                        txtArea.setText(null);
                        txtSetor.setText(null);
                        txtLider.setText(null);
                        txtIdLider.setText(null);
                        txtOferta.setText(null);

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
        txtDateFormated = new javax.swing.JTextField();
        btnLimpar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        lblPrenchaCampos = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        btnAdicionar = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        lblDistrito4 = new javax.swing.JLabel();
        txtIdLider = new javax.swing.JTextField();
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
        cmbMda = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        txtOferta = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblTotalPres = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        txtCodPastor.setEditable(false);

        jLabel1.setText("COD.");

        txtDateFormated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateFormatedActionPerformed(evt);
            }
        });

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro Pastor");
        getContentPane().setLayout(null);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/pincel.gif"))); // NOI18N
        btnLimpar.setToolTipText("Limpar");
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(170, 290, 50, 48);

        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/delete.png"))); // NOI18N
        btnDeletar.setToolTipText("Deletar");
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });
        getContentPane().add(btnDeletar);
        btnDeletar.setBounds(220, 290, 62, 48);

        lblPrenchaCampos.setText("* Campos Obrigatórios");
        getContentPane().add(lblPrenchaCampos);
        lblPrenchaCampos.setBounds(430, 10, 129, 16);

        lblData.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblData.setText("jLabel1");
        getContentPane().add(lblData);
        lblData.setBounds(21, 7, 84, 15);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/add.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar);
        btnAdicionar.setBounds(60, 290, 62, 48);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/read.png"))); // NOI18N
        btnPesquisar.setToolTipText("Pesquisar");
        btnPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesquisar);
        btnPesquisar.setBounds(120, 290, 54, 48);

        lblDistrito4.setText("Cod. Célula:");
        getContentPane().add(lblDistrito4);
        lblDistrito4.setBounds(30, 30, 66, 16);

        txtIdLider.setForeground(new java.awt.Color(255, 0, 0));
        txtIdLider.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdLiderKeyPressed(evt);
            }
        });
        getContentPane().add(txtIdLider);
        txtIdLider.setBounds(105, 28, 65, 24);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel2.setText("Sup. Rede:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(21, 62, 53, 14);

        txtSupRede.setEditable(false);
        txtSupRede.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        getContentPane().add(txtSupRede);
        txtSupRede.setBounds(80, 58, 200, 22);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel3.setText("Cor. Rede:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(23, 90, 51, 14);

        txtPrRede.setEditable(false);
        txtPrRede.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        getContentPane().add(txtPrRede);
        txtPrRede.setBounds(80, 114, 200, 22);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel4.setText("Pr. Rede:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(29, 118, 45, 14);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel5.setText("Distrito:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(36, 146, 38, 14);

        txtDistrito.setEditable(false);
        txtDistrito.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        getContentPane().add(txtDistrito);
        txtDistrito.setBounds(80, 142, 200, 22);

        txtCorRede.setEditable(false);
        txtCorRede.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        getContentPane().add(txtCorRede);
        txtCorRede.setBounds(80, 86, 200, 22);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel6.setText("Área:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(48, 174, 26, 14);

        txtSetor.setEditable(false);
        txtSetor.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        getContentPane().add(txtSetor);
        txtSetor.setBounds(80, 200, 200, 22);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel7.setText("Setor:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(45, 204, 29, 14);

        txtArea.setEditable(false);
        txtArea.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        getContentPane().add(txtArea);
        txtArea.setBounds(80, 170, 200, 22);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel8.setText("Líder:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(46, 234, 28, 14);

        txtLider.setEditable(false);
        txtLider.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        txtLider.setForeground(new java.awt.Color(255, 0, 51));
        getContentPane().add(txtLider);
        txtLider.setBounds(80, 230, 200, 22);

        lblDistrito5.setText("DIA DA CÉLULA:");
        getContentPane().add(lblDistrito5);
        lblDistrito5.setBounds(370, 40, 89, 16);

        try {
            txtDataBrasil.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txtDataBrasil);
        txtDataBrasil.setBounds(470, 30, 81, 30);

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setForeground(new java.awt.Color(255, 153, 51));
        jPanel1.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Membros total da célula");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(50, 10, 118, 14);

        cmbGes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cmbGes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbGesKeyPressed(evt);
            }
        });
        jPanel1.add(cmbGes);
        cmbGes.setBounds(170, 330, 50, 26);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("TOTAL DE PRESENTES");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(50, 250, 120, 14);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(40, 240, 200, 0);
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(40, 60, 200, 10);

        cmbMtc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40" }));
        jPanel1.add(cmbMtc);
        cmbMtc.setBounds(50, 30, 60, 26);
        jPanel1.add(jSeparator3);
        jSeparator3.setBounds(40, 120, 200, 10);

        cmbMcp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40" }));
        jPanel1.add(cmbMcp);
        cmbMcp.setBounds(50, 90, 60, 26);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Membros compromissados presentes");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(50, 70, 190, 14);

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Convidados Presentes");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(50, 130, 190, 14);

        cmbConvPres.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40" }));
        jPanel1.add(cmbConvPres);
        cmbConvPres.setBounds(50, 150, 60, 26);
        jPanel1.add(jSeparator4);
        jSeparator4.setBounds(40, 240, 200, 10);

        cmbCrianca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40" }));
        cmbCrianca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbCriancaKeyPressed(evt);
            }
        });
        jPanel1.add(cmbCrianca);
        cmbCrianca.setBounds(50, 210, 60, 26);

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Crianças");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(50, 190, 190, 14);

        cmbMda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", " " }));
        jPanel1.add(cmbMda);
        cmbMda.setBounds(70, 330, 50, 26);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("MDA'S ");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(80, 310, 40, 16);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("GE'S");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(180, 310, 48, 16);
        jPanel1.add(jSeparator5);
        jSeparator5.setBounds(40, 180, 200, 10);

        txtOferta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtOfertaKeyPressed(evt);
            }
        });
        jPanel1.add(txtOferta);
        txtOferta.setBounds(190, 270, 60, 30);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Oferta");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(200, 250, 36, 16);

        lblTotalPres.setText("Total");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotalPres)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTotalPres, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(60, 280, 40, 20);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("R$ ");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(170, 280, 20, 16);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(320, 70, 280, 370);

        setBounds(0, 0, 640, 480);
    }// </editor-fold>//GEN-END:initComponents

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

    private void txtDateFormatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateFormatedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateFormatedActionPerformed

    private void txtIdLiderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdLiderKeyPressed
        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_BACK_SPACE:
                pesquisar();
                break;
            case KeyEvent.VK_ENTER:
                pesquisar();
                break;
            default:
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        pesquisar();

                    }
                });
        }
    }//GEN-LAST:event_txtIdLiderKeyPressed

    private void cmbCriancaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCriancaKeyPressed
        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_TAB:
                break;
            case KeyEvent.VK_ENTER:
                somar();
                break;
            default:
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        somar();

                    }
                });
        }
    }//GEN-LAST:event_cmbCriancaKeyPressed

    private void cmbGesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbGesKeyPressed
        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_TAB:
                somar();
                adicionar();
                break;
            case KeyEvent.VK_ENTER:
                somar();
                adicionar();
                break;
            default:
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
        }
    }//GEN-LAST:event_cmbGesKeyPressed

    private void txtOfertaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOfertaKeyPressed
        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                cmbMda.requestFocus();
                break;
            case KeyEvent.VK_TAB:
                cmbMda.requestFocus();
                break;
            default:
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
        }
    }//GEN-LAST:event_txtOfertaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JComboBox<String> cmbConvPres;
    private javax.swing.JComboBox<String> cmbCrianca;
    private javax.swing.JComboBox<String> cmbGes;
    private javax.swing.JComboBox<String> cmbMcp;
    private javax.swing.JComboBox<String> cmbMda;
    private javax.swing.JComboBox<String> cmbMtc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblDistrito4;
    private javax.swing.JLabel lblDistrito5;
    private javax.swing.JLabel lblPrenchaCampos;
    private javax.swing.JLabel lblTotalPres;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtCodPastor;
    private javax.swing.JTextField txtCorRede;
    private javax.swing.JFormattedTextField txtDataBrasil;
    private javax.swing.JTextField txtDateFormated;
    private javax.swing.JTextField txtDistrito;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdLider;
    private javax.swing.JTextField txtIdPastor;
    private javax.swing.JTextField txtLider;
    private javax.swing.JFormattedTextField txtOferta;
    private javax.swing.JTextField txtPrRede;
    private javax.swing.JTextField txtSetor;
    private javax.swing.JTextField txtSupRede;
    // End of variables declaration//GEN-END:variables
}
