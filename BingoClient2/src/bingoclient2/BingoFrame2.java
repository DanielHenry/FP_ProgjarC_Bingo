
package bingoclient2;

import bingoserializables.Chat;
import bingoserializables.Whisper;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ListModel;
import bingoserializables.*;

public class BingoFrame2 extends javax.swing.JFrame {

    
    private DefaultListModel playerListModel;
    private Boolean[][] cek;
    private int putNum;
    private Boolean isPlaying;
    private Boolean isActive;
    private CustomButton[][] buttons;
    private ArrayList<CustomButton> buttonsByNumber;
    private int bingosize;
    
    
    public void activate() {
        isActive = true;
    }
    
    public boolean updateBoard(int num) {
        CustomButton b = buttonsByNumber.get(num-1);
        b.setBackground(Color.GREEN);
        cek[b.getRow()][b.getCol()] = true;
        
        int bingo = 0;
        boolean apakahTercoretSatuLine;
        
        //cek semua horizontal
        for (int i=0;i<bingosize;i++)
        {
            apakahTercoretSatuLine = true;
            for (int j=0;j<bingosize;j++)
            {   
                if (cek[i][j]==false)
                {
                   apakahTercoretSatuLine = false;
                    break;
                }
            }
            if (apakahTercoretSatuLine==true) bingo++;
        }
        
        for (int i=0;i<bingosize;i++)
        {
            apakahTercoretSatuLine = true;
            for (int j=0;j<bingosize;j++)
            {   
                if (cek[j][i]==false)
                {
                   apakahTercoretSatuLine = false;
                    break;
                }
            }
            if (apakahTercoretSatuLine==true) bingo++;
        }
        
        apakahTercoretSatuLine = true;
        for (int i=0;i<bingosize;i++)
        {   
            if (cek[i][i]==false)
            {
               apakahTercoretSatuLine = false;
                break;
            }
        }
        if (apakahTercoretSatuLine==true) bingo++;
        
        apakahTercoretSatuLine = true;
        for (int i=0;i<bingosize;i++)
        {   
            if (cek[bingosize-1-i][i]==false)
            {
               apakahTercoretSatuLine = false;
                break;
            }
        }
        if (apakahTercoretSatuLine==true) bingo++;
        
        if (bingo==bingosize) return true;
        else return false;
    }
    
    public ListModel getPlayerListModel() {
        return playerListModel;
    }
    
    public void removePlayer(String s) {
        playerListModel.removeElement(s);
        jListPlayer.setModel(playerListModel);
        jListPlayer.revalidate();
    }
    
    public void addPlayer(String s) {
        playerListModel.addElement(s);
        jListPlayer.setModel(playerListModel);
        jListPlayer.revalidate();
    }
    
    public void setPlayerListModel (DefaultListModel l) {
        playerListModel = l;
        jListPlayer.setModel(l);
        jListPlayer.revalidate();
    }
    
    public void addChat(String s) {
        System.out.println("(GameRoom) addChat " + s);
        jTextAreaChat.setText(jTextAreaChat.getText() + "\n" + s);
    }
    
    private class CustomButton extends JButton {
        private final int row;
        private final int col;
        private int num;
        public int getRow() {
            return row;
        }
        public int getCol() {
            return col;
        }
        public CustomButton(String s, int i, int j) {
            super(s);
            row = i;
            col = j;
            num = -1;
        }
        public CustomButton(int i, int j) {
            super();
            row = i;
            col = j;
            num = -1;
        }
        public Boolean setNum(int n) {
            if (num == -1) {
                num = n;
                this.setText(String.valueOf(n));
                return true;
            }
            else 
                return false;
        }
        public int getNum() {
            return num;
        }
    }
    
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CustomButton cb = (CustomButton)e.getSource();
            if (isPlaying) {
                System.out.println("ISPLAYING");
            }
            if (!isPlaying) {
                if (putNum < bingosize * bingosize) {
                    putNum = cb.setNum(putNum) ? putNum + 1 : putNum;
                    buttonsByNumber.add(cb);
                }
                else if (putNum == bingosize * bingosize) {
                    putNum = cb.setNum(putNum) ? putNum + 1 : putNum;
                    buttonsByNumber.add(cb);
                    jButtonReady.setEnabled(true);
                }
            }
            else if (isActive) {
            
            }
        }
    }
            
    public BingoFrame2(int bsize) {
        initComponents();
        bingosize = bsize;
        buttons = new CustomButton[bsize][bsize];
        jPanel1.setLayout(new GridLayout(7,7));
        for (int i=0; i<7; i++) {
            for (int j=0; j<7; j++) {
                if (i < bsize && j < bsize) {
                    buttons[i][j] = new CustomButton("-", i, j);
                    buttons[i][j].addActionListener(new ButtonListener());
                    jPanel1.add(buttons[i][j]);
                }
                else {
                    jPanel1.add(new JLabel("."));
                }
            }
        }
        cek = new Boolean[bsize][bsize];
        isPlaying = false;
        isActive = false;
        putNum = 1;
        buttonsByNumber = new ArrayList();
        jButtonReady.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListPlayer = new javax.swing.JList();
        jLabelPlayer = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaChat = new javax.swing.JTextArea();
        jTextFiledWispName = new javax.swing.JTextField();
        jTextFieldChat = new javax.swing.JTextField();
        jButtonSendChat = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButtonReady = new javax.swing.JButton();

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

        jTextAreaChat.setColumns(20);
        jTextAreaChat.setRows(5);
        jScrollPane3.setViewportView(jTextAreaChat);

        jTextFiledWispName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFiledWispNameActionPerformed(evt);
            }
        });

        jButtonSendChat.setText("Send");
        jButtonSendChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendChatActionPerformed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(410, 410));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        jButtonReady.setText("Ready!");
        jButtonReady.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReadyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonReady)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabelPlayer)
                                .addGap(24, 24, 24))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFiledWispName, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldChat, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSendChat, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonReady)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabelPlayer)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFiledWispName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSendChat))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jListPlayerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListPlayerMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jListPlayerMousePressed

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

    private void jButtonSendChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendChatActionPerformed
        if (jTextFiledWispName.getText().length() > 0) {
            Whisper w = new Whisper(jTextFieldChat.getText(), ClientGlobals.id, jTextFiledWispName.getText());
            try {
                ClientGlobals.os.writeObject(w);
                ClientGlobals.os.flush();
            } catch (IOException ex) {
                Logger.getLogger(LobbyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            Chat c = new Chat(jTextFieldChat.getText(), ClientGlobals.id);
            try {
                ClientGlobals.os.writeObject(c);
                ClientGlobals.os.flush();
            } catch (IOException ex) {
                Logger.getLogger(LobbyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jScrollPane3.getViewport().setViewPosition(new Point(0, (jTextAreaChat.getLineCount()+1)*80));
        jTextFieldChat.setText(null);
    }//GEN-LAST:event_jButtonSendChatActionPerformed

    private void jButtonReadyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReadyActionPerformed
        PlayerReady pr = new PlayerReady(ClientGlobals.id, ClientGlobals.activeRoomID);
        try {
            ClientGlobals.os.writeObject(pr);
            ClientGlobals.os.flush();
        } catch (IOException ex) {
            Logger.getLogger(BingoFrame2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonReadyActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonReady;
    private javax.swing.JButton jButtonSendChat;
    private javax.swing.JLabel jLabelPlayer;
    private javax.swing.JList jListPlayer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextAreaChat;
    private javax.swing.JTextField jTextFieldChat;
    private javax.swing.JTextField jTextFiledWispName;
    // End of variables declaration//GEN-END:variables
}
