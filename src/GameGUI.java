import jade.gui.GuiEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author krmig
 */
public class GameGUI extends javax.swing.JFrame {

    PlayGUI myAgent;
    public GameGUI(PlayGUI a) {
        myAgent = a;
        initComponents();
    }
    public void ChangeSelection(String[] strings){
        ActionsList.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/photo/background_image.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.getContentPane().setBackground(Color.cyan);
        jScrollPane2 = new javax.swing.JScrollPane();
        MapTextArea = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        ActionsList = new javax.swing.JList<>();
        ActionButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LivesCountLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        LivesNumber = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DungeonMasterTextArea = new javax.swing.JTextArea();
        RestartButton = new javax.swing.JButton();
        DungeonMasterTextArea.setEditable(false);
        MapTextArea.setEditable(false);
        LivesCountLabel.setOpaque(true);
        LivesCountLabel.setBackground(Color.decode("#cda8eb"));
        jLabel1.setOpaque(true);
        jLabel1.setBackground(Color.decode("#cda8eb"));
        jLabel2.setOpaque(true);
        jLabel2.setBackground(Color.decode("#cda8eb"));
        jLabel3.setOpaque(true);
        jLabel3.setBackground(Color.decode("#cda8eb"));
        LivesNumber.setOpaque(true);
        LivesNumber.setBackground(Color.decode("#cda8eb"));
        DungeonMasterTextArea.setBackground(Color.decode("#e6d4f5"));
        MapTextArea.setBackground(Color.decode("#e6d4f5"));
        ActionsList.setBackground(Color.decode("#e6d4f5"));
        ActionsList.setSelectionBackground(new java.awt.Color(213, 30, 226));

        ActionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ActionButton.setBackground(Color.decode("#cda8eb"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                ActionButton.setBackground(Color.decode("#e6d4f5"));
            }
        });
        RestartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RestartButton.setBackground(Color.decode("#cda8eb"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                RestartButton.setBackground(Color.decode("#e6d4f5"));
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MapTextArea.setColumns(20);
        MapTextArea.setRows(5);
        jScrollPane2.setViewportView(MapTextArea);

        ActionsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ActionsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ActionsList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ActionsList.setSelectionBackground(new java.awt.Color(153, 0, 255));
        jScrollPane4.setViewportView(ActionsList);

        ActionButton.setText("DO!");
        ActionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Dungeon Master ");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Game map");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Choose your action:");

        LivesCountLabel.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        LivesCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LivesCountLabel.setText("Lives left:");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel5.setText("RPG game");

        LivesNumber.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        LivesNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LivesNumber.setText("0");
        LivesNumber.setMaximumSize(new java.awt.Dimension(10, 16));
        LivesNumber.setMinimumSize(new java.awt.Dimension(10, 16));

        DungeonMasterTextArea.setColumns(20);
        DungeonMasterTextArea.setRows(5);
        jScrollPane1.setViewportView(DungeonMasterTextArea);

        RestartButton.setText("Restart");
        RestartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RestartButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(ActionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(LivesCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(6, 6, 6)
                                                                .addComponent(LivesNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(83, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(214, 214, 214)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(RestartButton)
                                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(RestartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(LivesNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(LivesCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ActionButton, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>
    private void RestartButtonActionPerformed(java.awt.event.ActionEvent evt) {
        PlayGUI.STATUS = 1;
        String[] DifficultiesSelection = new String[]{"Easy", "Medium", "Hard"};
        this.ChangeSelection(DifficultiesSelection);

    }
    private void ActionButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(PlayGUI.STATUS == PlayGUI.DIFFICULTY){
            GuiEvent ge = new GuiEvent(this, PlayGUI.DIFFICULTY);
            ge.addParameter(ActionsList.getSelectedValue());
            myAgent.postGuiEvent(ge);
            PlayGUI.STATUS = 2;
        }
        else if(PlayGUI.STATUS == PlayGUI.DM){
            GuiEvent ge = new GuiEvent(this, PlayGUI.DM);
            ge.addParameter(ActionsList.getSelectedValue());
            myAgent.postGuiEvent(ge);
            PlayGUI.STATUS = 3;
        }
        else if(PlayGUI.STATUS == PlayGUI.GAMING){
            GuiEvent ge = new GuiEvent(this, PlayGUI.GAMING);
            ge.addParameter(ActionsList.getSelectedValue()); // Or get selected index
            myAgent.postGuiEvent(ge);
        }


    }

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GameGUI().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify
    private javax.swing.JButton ActionButton;
    private javax.swing.JList<String> ActionsList;
    private javax.swing.JTextArea DungeonMasterTextArea;
    private javax.swing.JLabel LivesCountLabel;
    private javax.swing.JLabel LivesNumber;
    private javax.swing.JTextArea MapTextArea;
    private javax.swing.JButton RestartButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration
}
