/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoclient2;

import bingoserializables.*;

public class LobbyFrame extends javax.swing.JFrame {

    
    public LobbyFrame() throws Exception {
        initComponents();
        PlayerConnected pc = new PlayerConnected(ClientGlobals.id);
        ClientGlobals.os.writeObject(pc);
        ClientGlobals.os.flush();
        Object o = ClientGlobals.is.readObject();
        System.out.println(o.getClass());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListPlayer = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListRoom = new javax.swing.JList();
        jLabelRoom = new javax.swing.JLabel();
        jLabelPlayer = new javax.swing.JLabel();
        jButtonCreateRoom = new javax.swing.JButton();
        jButtonJoinRoom = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextFiledWispName = new javax.swing.JTextField();
        jTextFieldChat = new javax.swing.JTextField();
        jButtonSendChat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(840, 640));
        setResizable(false);

        jListPlayer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jListPlayer.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Player 1", "Player 2", "Player 3", "Player 4", "Player 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListPlayer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListPlayer.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        jListPlayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListPlayerMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jListPlayerMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jListPlayer);

        jListRoom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jListRoom.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Room 1", "Room 2", "Room 3", "Room 4", "Room 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListRoom.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListRoom.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        jScrollPane2.setViewportView(jListRoom);

        jLabelRoom.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelRoom.setText("Room List");
        jLabelRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelRoomMouseClicked(evt);
            }
        });

        jLabelPlayer.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelPlayer.setText("Player List");
        jLabelPlayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelPlayerMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelPlayerMousePressed(evt);
            }
        });

        jButtonCreateRoom.setText("Create");
        jButtonCreateRoom.setMaximumSize(new java.awt.Dimension(78, 23));
        jButtonCreateRoom.setMinimumSize(new java.awt.Dimension(78, 23));
        jButtonCreateRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateRoomActionPerformed(evt);
            }
        });

        jButtonJoinRoom.setText("Join");
        jButtonJoinRoom.setMaximumSize(new java.awt.Dimension(78, 23));
        jButtonJoinRoom.setMinimumSize(new java.awt.Dimension(78, 23));
        jButtonJoinRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonJoinRoomActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jTextFiledWispName.setText("Whisper");
        jTextFiledWispName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFiledWispNameActionPerformed(evt);
            }
        });

        jTextFieldChat.setText("Chat");

        jButtonSendChat.setText("Send");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jButtonCreateRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(280, 280, 280)
                                .addComponent(jLabelRoom)))
                        .addGap(0, 256, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonJoinRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelPlayer)
                        .addGap(24, 24, 24))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFiledWispName, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldChat, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSendChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRoom)
                    .addComponent(jLabelPlayer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCreateRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonJoinRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFiledWispName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSendChat))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jListPlayerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListPlayerMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jListPlayerMousePressed

    private void jButtonJoinRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonJoinRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonJoinRoomActionPerformed

    private void jButtonCreateRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateRoomActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButtonCreateRoomActionPerformed

    private void jListPlayerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListPlayerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jListPlayerMouseClicked

    private void jLabelPlayerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPlayerMouseClicked
        // TODO add your handling code here:
        jListPlayer.clearSelection();
        jListPlayer.repaint();
    }//GEN-LAST:event_jLabelPlayerMouseClicked

    
    private void jTextFiledWispNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFiledWispNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFiledWispNameActionPerformed

    private void jLabelPlayerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPlayerMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelPlayerMousePressed

    private void jLabelRoomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelRoomMouseClicked
        // TODO add your handling code here:
        jListRoom.clearSelection();
        jListRoom.repaint();
    }//GEN-LAST:event_jLabelRoomMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCreateRoom;
    private javax.swing.JButton jButtonJoinRoom;
    private javax.swing.JButton jButtonSendChat;
    private javax.swing.JLabel jLabelPlayer;
    private javax.swing.JLabel jLabelRoom;
    private javax.swing.JList jListPlayer;
    private javax.swing.JList jListRoom;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldChat;
    private javax.swing.JTextField jTextFiledWispName;
    // End of variables declaration//GEN-END:variables
}
