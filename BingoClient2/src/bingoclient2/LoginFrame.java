
package bingoclient2;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.ObjectOutputStream;
import org.jdesktop.xswingx.PromptSupport;

public class LoginFrame extends javax.swing.JFrame {

    
    public LoginFrame() {
        initComponents();
    }
    
    private void CheckUsername(String s) throws UserException{
        if("".equals(s)){
            UserException nx = new UserException();
            nx.setName("EmptyName");
            throw nx;
        }
    }
    private void CheckServer(String s) throws UserException{
        if("".equals(s)){
            UserException nx = new UserException();
            nx.setName("EmptyServer");
            throw nx;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jLabelUser = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jLabelServer = new javax.swing.JLabel();
        jTextFieldServer = new javax.swing.JTextField();
        jButtonLogin = new javax.swing.JButton();
        jLabelStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabelTitle.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("Welcome to Bingo!");

        jLabelUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelUser.setText("Username");

        jLabelServer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelServer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelServer.setText("Server");

        jTextFieldServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldServerActionPerformed(evt);
            }
        });

        jButtonLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonLogin.setText("Link Start!");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        jLabelStatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelStatus.setText("Free to play! Open beta till forever!!!");
        jLabelStatus.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(105, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelServer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldServer, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(104, 104, 104))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUser)
                    .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelServer)
                    .addComponent(jTextFieldServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        PromptSupport.setPrompt("Username", jTextFieldUsername);
        PromptSupport.setPrompt("Server Address", jTextFieldServer);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        try {
            ClientGlobals.id = jTextFieldUsername.getText();
            CheckUsername(ClientGlobals.id);
            CheckServer(jTextFieldServer.getText());
            Socket s = new Socket(jTextFieldServer.getText(), 4545);
            ClientGlobals.os = new ObjectOutputStream(s.getOutputStream());
            ClientGlobals.is = new ObjectInputStream(s.getInputStream());
            try {
                new LobbyFrame().setVisible(true);
                this.setVisible(false);
            } catch (Exception ex) {
                Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UserException ex) {
            String s = ex.getName();
            if(s.contains("EmptyName")){
            jLabelStatus.setText("Warning: Username can't be empty.");
            jLabelStatus.setForeground(Color.red);
            jLabelStatus.repaint();
            }
            else if(s.contains("EmptyServer")){
            jLabelStatus.setText("Warning: Server address can't be empty.");
            jLabelStatus.setForeground(Color.red);
            jLabelStatus.repaint();
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
            String st = ex.getClass().getName();
            if (st.contains("UnknownHost")) {
                jLabelStatus.setText("Error: Unknown host. Check server address.");
                jLabelStatus.setForeground(Color.red);
                jLabelStatus.repaint();
            }
            else if(st.contains("ConnectE")){
                jLabelStatus.setText("Error: Can't connect to server. Is server online?");
                jLabelStatus.setForeground(Color.red);
                jLabelStatus.repaint();
            }
        }
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void jTextFieldServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldServerActionPerformed
        // TODO add your handling code here:
        jButtonLoginActionPerformed(evt);
    }//GEN-LAST:event_jTextFieldServerActionPerformed

    public static void main(String args[]) {
        /* Create and display the form */
        ClientGlobals.activeRoomID = null;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JLabel jLabelServer;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JTextField jTextFieldServer;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables
}
