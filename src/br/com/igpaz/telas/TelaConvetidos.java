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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public final class TelaConvetidos extends javax.swing.JInternalFrame {

    static JLabel lblUsuarioFinal;

    ArrayList nome = new ArrayList();
    ArrayList numero = new ArrayList();
    ArrayList bairro = new ArrayList();
    ArrayList lider = new ArrayList();

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaConvetidos() {

        initComponents();
        conexao = ModuloConexao.conector();
        //Popula as Combobox
        this.pesquisa_avancada();
        this.populaComboBox();
        this.dataendereco();
        this.databairro();
        // this.datacontato();
        this.datalider();
        //Colocar cor nas Labels
        lblDataTela.setForeground(Color.red);
        lblUsuariofinal.setForeground(Color.red);
        lblUsuariofinal.setText(TelaPrincipal.lblUsuario.getText().toUpperCase());

        //formato da data
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.MEDIUM);
        lblDataTela.setText(formatador.format(data));
        
        Calendar calendarData = Calendar.getInstance();
        int numeroDiasParaSubtrair = 0;
        calendarData.add(Calendar.DATE, numeroDiasParaSubtrair);
        Date dataInicial = calendarData.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblData1.setText(format.format(dataInicial));

    }
    //Pesquisa Endereço no banco de dados

    public void dataendereco() {
        String sql = "select distinct endereco from endereco ";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String Nome = rs.getString("endereco");
                nome.add(Nome);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }
    //Auto Completa endereços.

    public void autoComplete(String txt) {
        String complete = "";
        int start = txt.length();
        int last = txt.length();
        int a;
        for (a = 0; a < nome.size(); a++) {
            if (nome.get(a).toString().startsWith(txt)) {
                complete = nome.get(a).toString();
                last = complete.length();
                break;

            }
        }
        if (last > start) {
            txtEndereco.setText(complete);
            txtEndereco.setCaretPosition(last);
            txtEndereco.moveCaretPosition(start);
        }
    }
    //Pesquisa no Banco de dados bairro.

    public void databairro() {
        String sql = "select distinct bairro from endereco ";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String Bairro = rs.getString("bairro");
                bairro.add(Bairro);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }
    //Autocompleta Bairro

    public void autoCompleteBairro(String txt) {
        String complete = "";
        int start = txt.length();
        int last = txt.length();
        int a;
        for (a = 0; a < bairro.size(); a++) {
            if (bairro.get(a).toString().startsWith(txt)) {
                complete = bairro.get(a).toString();
                last = complete.length();
                break;

            }
        }
        if (last > start) {
            txtBairro.setText(complete);
            txtBairro.setCaretPosition(last);
            txtBairro.moveCaretPosition(start);
        }
    }

    //Pesquisa dados Lider
    public void datalider() {
        String sql = "select distinct lider from pessoas ";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String Lider = rs.getString("lider");
                lider.add(Lider);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }
    //Audo Completa dados Lider

    public void autoCompleteLider(String txt) {
        String complete = "";
        int start = txt.length();
        int last = txt.length();
        int a;
        for (a = 0; a < lider.size(); a++) {
            if (lider.get(a).toString().startsWith(txt)) {
                complete = lider.get(a).toString();
                last = complete.length();
                break;

            }
        }
        if (last > start) {
            txtLider.setText(complete);
            txtLider.setCaretPosition(last);
            txtLider.moveCaretPosition(start);
        }
    }

    //Popula Combobox Cor_rede
    public void populaComboBox() {

        String sql = "select distinct cor_rede from pastor";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {

                cmbCorRede.addItem(rs.getString("cor_rede"));

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    //Popula combobox Distrito
    public void populaCombodistrito() {
        cmbDistrito.removeAllItems();
        String sql = "select distinct distrito from pastor where cor_rede like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cmbCorRede.getSelectedItem() + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                cmbDistrito.addItem(rs.getString("distrito"));

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void combobox_rede() {

        String sql = "select distinct nome_pr from pastor where cor_rede like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cmbCorRede.getSelectedItem() + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                txtRede.setText(rs.getString("nome_pr"));

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void adicionar() {
        String sql = "insert into pessoas (nome,idade,endereco,numero,bairro,contato,lider,data,nome_pr,cor_rede,distrito,cadastro,tipo) values (?,?,?,?,?,?,?,?,?,?,?,?,?) ";

        try {
            if (txtNome.getText().isEmpty() || txtRede.getText().isEmpty() || txtNome.getText().length() < 10) {

                JOptionPane.showMessageDialog(null, "Campo Obrigatório e maior que dez caracteres.");
                txtNome.requestFocus();

            } else {
                pst = conexao.prepareStatement(sql);

                pst.setString(1, txtNome.getText());
                pst.setString(2, cmbIdade.getSelectedItem().toString());
                pst.setString(3, txtEndereco.getText());
                pst.setString(4, (String) txtNumero.getText());
                pst.setString(5, txtBairro.getText());
                pst.setString(6, txtContato.getText());
                pst.setString(7, txtLider.getText());
                pst.setString(8, lblData1.getText());
                pst.setString(9, txtRede.getText());
                pst.setString(10, cmbCorRede.getSelectedItem().toString());
                pst.setString(11, cmbDistrito.getSelectedItem().toString());
                pst.setString(12, lblUsuariofinal.getText());
                pst.setString(13, cmbTipo.getSelectedItem().toString());

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    adicionartabelaendereco();
                    //  JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso.");
                }
            }

        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "ATENÇÃO RECONCOLIAÇÃO");
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void adicionartabelaendereco() {
        String sql = "insert into endereco (endereco,numero,bairro) values (?,?,?) ";

        try {

            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtEndereco.getText());
            pst.setString(2, (String) txtNumero.getText());
            pst.setString(3, txtBairro.getText());

            int adicionado = pst.executeUpdate();

//A llinha abaixo serve de apoio ao codigo           
            //System.out.println(adicionado);
            if (adicionado > 0) {

                JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso.");
                txtNome.setText(null);
                txtEndereco.setText(null);
                txtNumero.setText(null);
                txtBairro.setText(null);
                txtContato.setText(null);
                txtLider.setText(null);

                this.pesquisa_avancada();
                this.populaComboBox();
                this.dataendereco();
                this.databairro();
                this.datalider();

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void auterar() {
        String sql = "update pessoas set nome=?,idade=?,endereco=?,numero=?,bairro=?,contato=?,lider=?,data=?,nome_pr=?,cor_rede=?,distrito=?,cadastro=?,tipo=? where id_conv=?";
        try {

            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtNome.getText());
            pst.setString(2, cmbIdade.getSelectedItem().toString());
            pst.setString(3, txtEndereco.getText());
            pst.setString(4, txtNumero.getText());
            pst.setString(5, txtBairro.getText());
            pst.setString(6, txtContato.getText());
            pst.setString(7, txtLider.getText());
            pst.setString(8, lblData1.getText());
            pst.setString(9, txtRede.getText());
            pst.setString(10, cmbCorRede.getSelectedItem().toString());
            pst.setString(11, cmbDistrito.getSelectedItem().toString());
            pst.setString(12, lblUsuariofinal.getText());
            pst.setString(13, cmbTipo.getSelectedItem().toString());
            pst.setString(14, txtId_Convertidos.getText());

            if (txtNome.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Campos Obrigatorios.");

            } else {

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {

                    JOptionPane.showMessageDialog(null, "Dados auterados com sucesso.");
                    txtNome.setText(null);
                    txtEndereco.setText(null);
                    txtNumero.setText(null);
                    txtBairro.setText(null);
                    txtContato.setText(null);
                    txtLider.setText(null);
                    this.pesquisa_avancada();
                    this.populaComboBox();
                    this.dataendereco();
                    this.databairro();
                    this.datalider();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //JOptionPane.showMessageDialog(null, e);
        }

    }

    private void deletar() {
        if (txtNome.getText().isEmpty() || txtNome.getText().length() < 10) {

            JOptionPane.showMessageDialog(null, "Não pode deletar um campo vazio. Click na tabela para setar campos.");
            txtNome.requestFocus();

        } else {

            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza da remoção desse cadastro.", "Atenção", JOptionPane.YES_NO_OPTION);
            if (JOptionPane.YES_OPTION == confirma) {
                String sql = "delete from pessoas where id_conv=?";
                try {

                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtId_Convertidos.getText());
                    int apagado = pst.executeUpdate();
                    if (apagado > 0) {
                        JOptionPane.showMessageDialog(null, "Cadastro removido com sucesso.");
                        txtId_Convertidos.setText(null);
                        txtNome.setText(null);
                        txtEndereco.setText(null);
                        txtNumero.setText(null);
                        txtBairro.setText(null);
                        txtContato.setText(null);
                        txtLider.setText(null);
                        this.pesquisa_avancada();
                        this.populaComboBox();
                        this.dataendereco();
                        this.databairro();
                        this.datalider();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

            }
        }
    }

    private void pesquisa_avancada() {
        String sql = "select id_conv as ID,nome as Nome,idade as Idade,endereco as Endereço,numero as N,bairro as Bairro,contato as Contato,lider as Lider, nome_pr as NPastor,cor_rede as Cor_Rede,distrito as Distrito,cadastro as Cadastro,tipo as Tipo from pessoas where nome like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText() + "%");

            rs = pst.executeQuery();
            tblConvertidos.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void setacampos() {
        int setar = tblConvertidos.getSelectedRow();
        txtId_Convertidos.setText(tblConvertidos.getModel().getValueAt(setar, 0).toString());
        txtNome.setText(tblConvertidos.getModel().getValueAt(setar, 1).toString());
        cmbIdade.setSelectedItem(tblConvertidos.getModel().getValueAt(setar, 2).toString());
        txtEndereco.setText(tblConvertidos.getModel().getValueAt(setar, 3).toString());
        txtNumero.setText(tblConvertidos.getModel().getValueAt(setar, 4).toString());
        txtBairro.setText(tblConvertidos.getModel().getValueAt(setar, 5).toString());
        txtContato.setText(tblConvertidos.getModel().getValueAt(setar, 6).toString());
        txtLider.setText(tblConvertidos.getModel().getValueAt(setar, 7).toString());
        txtRede.setText(tblConvertidos.getModel().getValueAt(setar, 8).toString());
        cmbCorRede.setSelectedItem(tblConvertidos.getModel().getValueAt(setar, 9).toString());
        cmbDistrito.setSelectedItem(tblConvertidos.getModel().getValueAt(setar, 10).toString());
        cmbTipo.setSelectedItem(tblConvertidos.getModel().getValueAt(setar, 12).toString());

    }

    public void Limpar() {
        txtNome.setText(null);
        txtEndereco.setText(null);
        txtNumero.setText(null);
        txtBairro.setText(null);
        txtContato.setText(null);
        txtLider.setText(null);
        this.pesquisa_avancada();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtId_Convertidos = new javax.swing.JTextField();
        txtIdPastor = new javax.swing.JTextField();
        lblData = new javax.swing.JLabel();
        lblData1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbIdade = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtLider = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lblDataTela = new javax.swing.JLabel();
        btnAdicionar = new javax.swing.JButton();
        btnAuterar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        cmbDistrito = new javax.swing.JComboBox<>();
        cmbCorRede = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConvertidos = new javax.swing.JTable();
        txtRede = new javax.swing.JTextField();
        lblUsuariofinal = new javax.swing.JLabel();
        btnLimpar = new javax.swing.JButton();
        cmbTipo = new javax.swing.JComboBox<>();
        txtContato = new javax.swing.JFormattedTextField();

        lblData.setText("jLabel5");

        lblData1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblData1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblData1.setText("Data");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro N. Convertido");
        setPreferredSize(new java.awt.Dimension(656, 487));
        setRequestFocusEnabled(false);

        jLabel1.setText("*Nome:");

        txtNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeKeyReleased(evt);
            }
        });

        jLabel2.setText("Idade:");

        cmbIdade.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbIdade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100" }));

        jLabel3.setText("Endereço:");

        txtEndereco.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtEndereco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEnderecoKeyPressed(evt);
            }
        });

        jLabel4.setText("Num:");

        txtNumero.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroKeyPressed(evt);
            }
        });

        txtBairro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBairroKeyPressed(evt);
            }
        });

        jLabel6.setText("Lider:");

        txtLider.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtLider.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLiderKeyPressed(evt);
            }
        });

        jLabel7.setText("Contato:");

        jLabel8.setText("Rede:");

        jLabel9.setText("Distrito:");

        jLabel10.setText("Bairro:");

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setText("Data:");

        lblDataTela.setText("data");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(12, 12, 12)
                .addComponent(lblDataTela, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblDataTela))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/add.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        btnAdicionar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAdicionarKeyPressed(evt);
            }
        });

        btnAuterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/update.png"))); // NOI18N
        btnAuterar.setToolTipText("Auterar");
        btnAuterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAuterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuterarActionPerformed(evt);
            }
        });

        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/delet.png"))); // NOI18N
        btnDeletar.setToolTipText("Deletar");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        cmbDistrito.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        cmbCorRede.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbCorRede.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbCorRedeMousedbClicked(evt);
            }
        });
        cmbCorRede.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbCorRedeKeyReleased(evt);
            }
        });

        tblConvertidos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblConvertidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblConvertidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblConvertidos);

        txtRede.setEditable(false);
        txtRede.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtRede.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRedeMouseClicked(evt);
            }
        });
        txtRede.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtRedePropertyChange(evt);
            }
        });

        lblUsuariofinal.setText("USUÁRIO");

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/pincel.gif"))); // NOI18N
        btnLimpar.setToolTipText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        cmbTipo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CULTO", "NORMAL", "ENC. NO LAR", "ONLINE" }));

        try {
            txtContato.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) - ##### - ####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtContato.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(36, 36, 36)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtContato, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtLider, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(41, 41, 41)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cmbCorRede, 0, 163, Short.MAX_VALUE)
                                                .addComponent(cmbTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(44, 44, 44)
                                            .addComponent(lblUsuariofinal, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtNome)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cmbIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(40, 40, 40)
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cmbDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(132, 132, 132)
                                            .addComponent(btnAdicionar)
                                            .addGap(35, 35, 35)
                                            .addComponent(btnAuterar)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(24, 24, 24)
                                            .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(32, 32, 32)
                                            .addComponent(btnDeletar))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(16, 16, 16)
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtRede, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4)
                                .addGap(4, 4, 4)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel10)
                                .addGap(4, 4, 4)
                                .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCorRede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsuariofinal))))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(cmbIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtLider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAuterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeletar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setBounds(0, 0, 641, 487);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyReleased
        // Pesquisar avançada
        pesquisa_avancada();

    }//GEN-LAST:event_txtNomeKeyReleased

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // Adicionar
        adicionar();

        txtNome.requestFocus();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnAuterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuterarActionPerformed
        // Auterar
        auterar();
        txtNome.requestFocus();
    }//GEN-LAST:event_btnAuterarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        // deletar
        deletar();
        txtNome.requestFocus();
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void tblConvertidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblConvertidosMouseClicked
        // TODO add your handling code here:
        setacampos();
    }//GEN-LAST:event_tblConvertidosMouseClicked

    private void cmbCorRedeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCorRedeKeyReleased
        // TODO add your handling code here:
        combobox_rede();
        populaCombodistrito();
    }//GEN-LAST:event_cmbCorRedeKeyReleased

    private void txtRedeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRedeMouseClicked
        // TODO add your handling code here:
        populaCombodistrito();
    }//GEN-LAST:event_txtRedeMouseClicked

    private void txtEnderecoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnderecoKeyPressed
        //auto complete
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_BACK_SPACE:
                break;
            case KeyEvent.VK_ENTER:
                txtEndereco.setText(txtEndereco.getText());
                txtNumero.requestFocus();
                break;
            default:
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String txt = txtEndereco.getText();
                        autoComplete(txt);
                    }
                });
        }

    }//GEN-LAST:event_txtEnderecoKeyPressed

    private void txtNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyPressed
        // Auto Complete NUMERO
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_BACK_SPACE:
                break;
            case KeyEvent.VK_ENTER:
                txtNumero.setText(txtNumero.getText());
                txtBairro.requestFocus();
                break;
            default:
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String txt = txtNumero.getText();
                        //   autoCompleteNum(txt);
                    }
                });
        }
    }//GEN-LAST:event_txtNumeroKeyPressed

    private void txtBairroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBairroKeyPressed
        // Auto Complete BAIRRO
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_BACK_SPACE:
                break;
            case KeyEvent.VK_ENTER:
                txtBairro.setText(txtBairro.getText());
                txtContato.requestFocus();
                break;
            default:
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String txt = txtBairro.getText();
                        autoCompleteBairro(txt);
                    }
                });
        }
    }//GEN-LAST:event_txtBairroKeyPressed

    private void txtLiderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLiderKeyPressed
        // Auto Complete Lider
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_BACK_SPACE:
                break;
            case KeyEvent.VK_ENTER:
                txtLider.setText(txtLider.getText());
                break;
            default:
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String txt = txtLider.getText();
                        autoCompleteLider(txt);
                    }
                });
        }
    }//GEN-LAST:event_txtLiderKeyPressed

    private void txtRedePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtRedePropertyChange
        // TODO add your handling code here:
        populaCombodistrito();
    }//GEN-LAST:event_txtRedePropertyChange

    private void btnAdicionarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAdicionarKeyPressed
        // TODO add your handling code here:
        adicionar();

        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtNome.requestFocus();

        }

    }//GEN-LAST:event_btnAdicionarKeyPressed

    private void cmbCorRedeMousedbClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbCorRedeMousedbClicked
        // TODO add your handling code here:

        combobox_rede();

    }//GEN-LAST:event_cmbCorRedeMousedbClicked

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // TODO add your handling code here:
        Limpar();
    }//GEN-LAST:event_btnLimparActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAuterar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JComboBox<String> cmbCorRede;
    private javax.swing.JComboBox<String> cmbDistrito;
    private javax.swing.JComboBox<String> cmbIdade;
    public static javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblData1;
    private javax.swing.JLabel lblDataTela;
    public static javax.swing.JLabel lblUsuariofinal;
    private javax.swing.JTable tblConvertidos;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtContato;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtIdPastor;
    private javax.swing.JTextField txtId_Convertidos;
    private javax.swing.JTextField txtLider;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRede;
    // End of variables declaration//GEN-END:variables

}
