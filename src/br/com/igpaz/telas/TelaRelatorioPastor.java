
package br.com.igpaz.telas;

import br.com.igpaz.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TelaRelatorioPastor extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaRelatorioPastor() {
        initComponents();
        conexao = ModuloConexao.conector();
        this.populaComboBoxTipoAss();
        this.populaCmbRede();
        Calendar calendarData = Calendar.getInstance();
        int numeroDiasParaSubtrair = 0;
        calendarData.add(Calendar.DATE, numeroDiasParaSubtrair);

    }

    public void populaComboBoxTipoAss() {

        String sql = "select distinct tipo from pessoas";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                cmbDataTipoAss.addItem(rs.getString("tipo"));
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void populaComboBoxDataAss() {

        cmbData.removeAllItems();
        cmbDataRede.removeAllItems();
        cmbDataIni.removeAllItems();
        cmbDataFim.removeAllItems();

        String sql = "select distinct data from pessoas where tipo like ?";
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, cmbDataTipoAss.getSelectedItem().toString());
            rs = pst.executeQuery();
            while (rs.next()) {

                java.sql.Date date = rs.getDate("data");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = dateFormat.format(date);

                cmbData.addItem(dateStr);
                cmbDataRede.addItem(dateStr);
                cmbDataIni.addItem(dateStr);
                cmbDataFim.addItem(dateStr);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void populaCmbRede() {

        String sql = "select distinct cor_rede from pessoas order by cor_rede asc ";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {

                cmbDataRedInd.addItem(rs.getString("cor_rede"));

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void imprimirRelatorioAss() {
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma Impressão de Relatório?", "Atenção", JOptionPane.YES_NO_CANCEL_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {
            try {

                String date = cmbData.getSelectedItem().toString();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println(date);

                Date date1 = format.parse(date);
                DateFormat formatBR = new SimpleDateFormat("yyyy-MM-dd");
                String dateFormated = formatBR.format(date1);

                HashMap filtro = new HashMap();

                filtro.put("data", dateFormated);
                filtro.put("tipo", cmbDataTipoAss.getSelectedItem());

                System.out.println(filtro);

                JasperPrint print = JasperFillManager.fillReport("C:/C.N.Convertidos/Pastor.jasper", filtro, conexao);
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
            }

        }

    }

    private void imprimirRelGeral() {
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma Impressão de Relatório?", "Atenção", JOptionPane.YES_NO_CANCEL_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {
            try {

                String date = cmbData.getSelectedItem().toString();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println(date);

                Date date1 = format.parse(date);
                DateFormat formatBR = new SimpleDateFormat("yyyy-MM-dd");
                String dateFormated = formatBR.format(date1);

                HashMap filtro = new HashMap();

                filtro.put("data", dateFormated);
                filtro.put("tipo", cmbDataTipoAss.getSelectedItem());

                System.out.println(filtro);

                JasperPrint print = JasperFillManager.fillReport("C:/C.N.Convertidos/Rede.jasper", filtro, conexao);
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
            }

        }

    }

    private void imprimirRedeseparado() {
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma Impressão de Relatório?", "Atenção", JOptionPane.YES_NO_CANCEL_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                String date = cmbDataIni.getSelectedItem().toString();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date date1 = format.parse(date);
                DateFormat formatBR = new SimpleDateFormat("yyyy-MM-dd");
                String dateFormated = formatBR.format(date1);

                String dateFim = cmbDataFim.getSelectedItem().toString();
                SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
                Date date2 = format2.parse(dateFim);
                DateFormat formatBR1 = new SimpleDateFormat("yyyy-MM-dd");
                String dateFormated2 = formatBR1.format(date2);

                HashMap filtro = new HashMap();
                filtro.put("data", dateFormated);
                filtro.put("data2", dateFormated2);
                filtro.put("cor_rede", cmbDataRedInd.getSelectedItem().toString());
                filtro.put("tipo", cmbDataTipoAss.getSelectedItem().toString());

                System.out.println(filtro);

                JasperPrint print = JasperFillManager.fillReport("C:/C.N.Convertidos/imp_intevalar.jasper", filtro, conexao);
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnImpRelPastor = new javax.swing.JButton();
        btnImpGeral = new javax.swing.JButton();
        cmbDataRede = new javax.swing.JComboBox<>();
        cmbData = new javax.swing.JComboBox<>();
        cmbDataIni = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbDataFim = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btnImpInt = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cmbDataRedInd = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cmbDataTipoAss = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("IMPRESSÃO DE RELATÓRIOS");

        jLabel1.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        jLabel1.setText("IMPRESSÃO DE RELATÓRIOS");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("PASTORES ASS.");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("INICIO:");

        btnImpRelPastor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/print.png"))); // NOI18N
        btnImpRelPastor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImpRelPastorActionPerformed(evt);
            }
        });

        btnImpGeral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/print.png"))); // NOI18N
        btnImpGeral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImpGeralMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("REDE GERAL.");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 255));
        jLabel7.setText("FINAL:");

        btnImpInt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/igpaz/icones/print.png"))); // NOI18N
        btnImpInt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImpIntActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 255));
        jLabel8.setText("REDE INDIVIDUAL:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 255));
        jLabel9.setText("TIPO IMP.");

        cmbDataTipoAss.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbDataTipoAssMouseClicked(evt);
            }
        });
        cmbDataTipoAss.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbDataTipoAssKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbData, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbDataIni, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbDataRedInd, 0, 170, Short.MAX_VALUE)
                                .addComponent(cmbDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbDataTipoAss, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbDataRede, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnImpGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImpRelPastor, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImpInt, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)))
                .addContainerGap())
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDataTipoAss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(btnImpRelPastor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbDataRede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(btnImpGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDataRedInd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDataIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(btnImpInt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        setBounds(0, 0, 547, 409);
    }// </editor-fold>//GEN-END:initComponents

    private void btnImpRelPastorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImpRelPastorActionPerformed
        // Imprimir Relatorio Pastor assinatura
        imprimirRelatorioAss();
    }//GEN-LAST:event_btnImpRelPastorActionPerformed

    private void btnImpIntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImpIntActionPerformed
        // Imprimir Relatorio de Rede
        imprimirRedeseparado();
    }//GEN-LAST:event_btnImpIntActionPerformed

    private void cmbDataTipoAssKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDataTipoAssKeyReleased
        // TODO add your handling code here:
        populaComboBoxDataAss();
    }//GEN-LAST:event_cmbDataTipoAssKeyReleased

    private void cmbDataTipoAssMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDataTipoAssMouseClicked
        // TODO add your handling code here:
        populaComboBoxDataAss();
    }//GEN-LAST:event_cmbDataTipoAssMouseClicked

    private void btnImpGeralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImpGeralMouseClicked
        // TODO add your handling code here:
        imprimirRelGeral();
    }//GEN-LAST:event_btnImpGeralMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImpGeral;
    private javax.swing.JButton btnImpInt;
    private javax.swing.JButton btnImpRelPastor;
    private javax.swing.JComboBox<String> cmbData;
    private javax.swing.JComboBox<String> cmbDataFim;
    private javax.swing.JComboBox<String> cmbDataIni;
    private javax.swing.JComboBox<String> cmbDataRedInd;
    private javax.swing.JComboBox<String> cmbDataRede;
    private javax.swing.JComboBox<Object> cmbDataTipoAss;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
