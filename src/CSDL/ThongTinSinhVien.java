/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSDL;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author tienn
 */
public class ThongTinSinhVien extends javax.swing.JFrame {

    /**
     * Creates new form ThongTinSinhVien
     */
    public ThongTinSinhVien() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_port2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_port3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_port4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txt_port2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_port2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Họ tên");

        jLabel5.setText("MSSV");

        txt_port3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_port3ActionPerformed(evt);
            }
        });

        jLabel6.setText("SDT");

        txt_port4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_port4ActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(29, 29, 29)
                                .addComponent(txt_port2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(29, 29, 29)
                                .addComponent(txt_port3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(29, 29, 29)
                                .addComponent(txt_port4, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jButton1)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_port2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_port3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_port4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(90, 90, 90)
                .addComponent(jButton1)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_port2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_port2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_port2ActionPerformed

    private void txt_port3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_port3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_port3ActionPerformed

    private void txt_port4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_port4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_port4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        
        DatagramSocket client;
        try {
            client = new DatagramSocket();
            byte[] arrayChoice = new byte[256];

            arrayChoice = String.valueOf(3).getBytes();

            InetAddress IP = StaticClass.IP;
            int port = StaticClass.port;

            DatagramPacket packetChoice = new DatagramPacket(arrayChoice, arrayChoice.length, IP, port);
            client.send(packetChoice);

            String hoten = "NGUYEN VAN TIEN";
            String mssv = "008";
            String sdt = "999999999";
            
            String thongtinsv = hoten + "," + mssv + "," + sdt;
            byte arraythongtinsv[] = new byte[1000];
            arraythongtinsv = thongtinsv.getBytes();

            DatagramPacket packetthongtin = new DatagramPacket( arraythongtinsv , arraythongtinsv.length , IP , port );
            client.send(packetthongtin);

            byte array[] = new byte [1000];
            DatagramPacket packet = new DatagramPacket( array , array.length );
            client.receive(packet);

            String result = new String( packet.getData() , 0 , packet.getLength() );
            if(result.equals("Sinh viên đã thi!")){
                JOptionPane.showMessageDialog(this, result);
                return;
            }
            
            List<CauHoi> listcauhoi = new ArrayList<>();
            for(int i=0; i<StaticClass.soluongcauhoi; i++){
                array = new byte [1000];
                packet = new DatagramPacket( array , array.length );
                client.receive(packet);

                String c = new String( packet.getData() , 0 , packet.getLength() );
                String[] cauhoi = c.split("//");
                int id = Integer.parseInt(cauhoi[0]);
                listcauhoi.add(new CauHoi(id, cauhoi[1], cauhoi[2], cauhoi[3], cauhoi[4], cauhoi[5]));
                System.out.println(c);
            }
            
            client.close();
            KiemTraFrame frm = new KiemTraFrame(listcauhoi, mssv);
            frm.setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            System.out.println("CANNOT CONNECT TO SERVER " + ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ThongTinSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongTinSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongTinSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongTinSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongTinSinhVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txt_port2;
    private javax.swing.JTextField txt_port3;
    private javax.swing.JTextField txt_port4;
    // End of variables declaration//GEN-END:variables
}
