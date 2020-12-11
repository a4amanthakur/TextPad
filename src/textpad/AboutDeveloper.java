/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textpad;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author thakur-huni
 */
public class AboutDeveloper extends javax.swing.JFrame {

    /**
     * Creates new form AboutDeveloper
     */
    public AboutDeveloper() {
        initComponents();
        lblTextPad.setFont(new Font("Arial",1,25));
        lblLinkedIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblInsta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblFb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblTwitter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlLinkedIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlInsta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlFb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlTwitter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icons/logo.png")));
        
        lblDetails.setText("<html><body>"
                +"<strong>TextPad</strong> is a word processing program, which allows changing of text in a computer file.<br>The program has options such as changing the font, convert text into speech etc. You can edit all type of txt documents.</i>"
                + "</body></html>");
    }

    void panelMouseEnter(JPanel pnl,JLabel lbl,String path)
    {
        pnl.setBackground(new Color(39,1,246));
        lbl.setForeground(Color.white);
        lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));
    }
    void panelMouseExit(JPanel pnl,JLabel lbl,String path)
    {
        pnl.setBackground(new Color(247,247,247));
        lbl.setForeground(Color.black);
        lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource(path)));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTextPad = new javax.swing.JLabel();
        lblDetails = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        pnlLinkedIn = new javax.swing.JPanel();
        lblLinkedIn = new javax.swing.JLabel();
        lblTextPad1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pnlFb = new javax.swing.JPanel();
        lblFb = new javax.swing.JLabel();
        pnlInsta = new javax.swing.JPanel();
        lblInsta = new javax.swing.JLabel();
        pnlTwitter = new javax.swing.JPanel();
        lblTwitter = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About ");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(39, 1, 246));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/logo2.png"))); // NOI18N

        lblTextPad.setFont(new java.awt.Font("Ubuntu", 1, 25)); // NOI18N
        lblTextPad.setForeground(new java.awt.Color(254, 254, 254));
        lblTextPad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTextPad.setText("TextPad");

        lblDetails.setForeground(new java.awt.Color(254, 254, 254));
        lblDetails.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDetails.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblDetails.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(lblTextPad, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lblDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTextPad, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(254, 254, 254));

        pnlLinkedIn.setBackground(new java.awt.Color(248, 247, 247));
        pnlLinkedIn.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        pnlLinkedIn.setToolTipText("LinkedIn");
        pnlLinkedIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlLinkedInMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlLinkedInMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlLinkedInMouseEntered(evt);
            }
        });

        lblLinkedIn.setFont(new java.awt.Font("Ubuntu", 1, 19)); // NOI18N
        lblLinkedIn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLinkedIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/linkedin_b.png"))); // NOI18N
        lblLinkedIn.setToolTipText("LinkedIn");
        lblLinkedIn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblLinkedIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLinkedInMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLinkedInMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLinkedInMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout pnlLinkedInLayout = new javax.swing.GroupLayout(pnlLinkedIn);
        pnlLinkedIn.setLayout(pnlLinkedInLayout);
        pnlLinkedInLayout.setHorizontalGroup(
            pnlLinkedInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLinkedIn, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );
        pnlLinkedInLayout.setVerticalGroup(
            pnlLinkedInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLinkedInLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLinkedIn)
                .addContainerGap())
        );

        lblTextPad1.setFont(new java.awt.Font("Ubuntu", 1, 25)); // NOI18N
        lblTextPad1.setForeground(new java.awt.Color(39, 1, 246));
        lblTextPad1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTextPad1.setText("<Developer>");
        lblTextPad1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel2.setText("Name : ");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(39, 1, 246));
        jLabel3.setText("Aman Kr. Thakur");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel4.setText("Age : ");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(39, 1, 246));
        jLabel5.setText("21 Years");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel6.setText("Location :");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(39, 1, 246));
        jLabel7.setText("Una, Himachal Pradesh.");

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(39, 1, 246));
        jLabel8.setText("Earth.");

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel9.setText("Contact :");

        pnlFb.setBackground(new java.awt.Color(248, 247, 247));
        pnlFb.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        pnlFb.setToolTipText("Facebook");
        pnlFb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlFbMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlFbMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlFbMouseEntered(evt);
            }
        });

        lblFb.setFont(new java.awt.Font("Ubuntu", 1, 19)); // NOI18N
        lblFb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/fb_b.png"))); // NOI18N
        lblFb.setToolTipText("Facebook");
        lblFb.setFocusable(false);
        lblFb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFbMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblFbMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblFbMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout pnlFbLayout = new javax.swing.GroupLayout(pnlFb);
        pnlFb.setLayout(pnlFbLayout);
        pnlFbLayout.setHorizontalGroup(
            pnlFbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFb, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );
        pnlFbLayout.setVerticalGroup(
            pnlFbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFbLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFb)
                .addContainerGap())
        );

        pnlInsta.setBackground(new java.awt.Color(248, 247, 247));
        pnlInsta.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        pnlInsta.setToolTipText("Instagram");
        pnlInsta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlInstaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlInstaMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlInstaMouseEntered(evt);
            }
        });

        lblInsta.setFont(new java.awt.Font("Ubuntu", 1, 19)); // NOI18N
        lblInsta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInsta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/insta_b.png"))); // NOI18N
        lblInsta.setToolTipText("Instagram");
        lblInsta.setFocusable(false);
        lblInsta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblInsta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInstaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblInstaMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblInstaMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout pnlInstaLayout = new javax.swing.GroupLayout(pnlInsta);
        pnlInsta.setLayout(pnlInstaLayout);
        pnlInstaLayout.setHorizontalGroup(
            pnlInstaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInsta, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );
        pnlInstaLayout.setVerticalGroup(
            pnlInstaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInstaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblInsta)
                .addContainerGap())
        );

        pnlTwitter.setBackground(new java.awt.Color(248, 247, 247));
        pnlTwitter.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        pnlTwitter.setToolTipText("Twitter");
        pnlTwitter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTwitterMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlTwitterMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlTwitterMouseEntered(evt);
            }
        });

        lblTwitter.setFont(new java.awt.Font("Ubuntu", 1, 19)); // NOI18N
        lblTwitter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTwitter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/twitter_B.png"))); // NOI18N
        lblTwitter.setToolTipText("Twitter");
        lblTwitter.setFocusable(false);
        lblTwitter.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTwitter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTwitterMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTwitterMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTwitterMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout pnlTwitterLayout = new javax.swing.GroupLayout(pnlTwitter);
        pnlTwitter.setLayout(pnlTwitterLayout);
        pnlTwitterLayout.setHorizontalGroup(
            pnlTwitterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTwitter, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );
        pnlTwitterLayout.setVerticalGroup(
            pnlTwitterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTwitterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTwitter)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTextPad1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(pnlLinkedIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pnlInsta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pnlFb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pnlTwitter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel9))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTextPad1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLinkedIn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlInsta, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlFb, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTwitter, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pnlLinkedInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLinkedInMouseClicked
            try 
            {
             Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/aman-kr-thakur/"));
                JOptionPane.showMessageDialog(this, "Redirecting...");
            } catch (IOException | URISyntaxException e1) {
                        JOptionPane.showMessageDialog(this, "Try another.");
            }  
    }//GEN-LAST:event_pnlLinkedInMouseClicked

    private void pnlLinkedInMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLinkedInMouseExited
        panelMouseExit(pnlLinkedIn,lblLinkedIn,"/Icons/linkedin_b.png");
    }//GEN-LAST:event_pnlLinkedInMouseExited

    private void pnlLinkedInMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLinkedInMouseEntered
        panelMouseEnter(pnlLinkedIn,lblLinkedIn,"/Icons/linked_w.png");
    }//GEN-LAST:event_pnlLinkedInMouseEntered

    private void pnlFbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlFbMouseClicked
         try 
                   {
                    Desktop.getDesktop().browse(new URI("https://www.facebook.com/j4javaassignment/"));
                       JOptionPane.showMessageDialog(this, "Redirecting...");
                   } catch (IOException | URISyntaxException e1) {
                               JOptionPane.showMessageDialog(this, "Try another.");
                   }          // TODO add your handling code here:
    }//GEN-LAST:event_pnlFbMouseClicked

    private void pnlFbMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlFbMouseExited
         panelMouseExit(pnlFb,lblFb,"/Icons/fb_b.png");
    }//GEN-LAST:event_pnlFbMouseExited

    private void pnlFbMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlFbMouseEntered
        panelMouseEnter(pnlFb,lblFb,"/Icons/fb_w.png");
    }//GEN-LAST:event_pnlFbMouseEntered

    private void pnlInstaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlInstaMouseClicked
                    try 
                   {
                    Desktop.getDesktop().browse(new URI("https://www.instagram.com/aman_kr._thakur"));
                       JOptionPane.showMessageDialog(this, "Redirecting...");
                   } catch (IOException | URISyntaxException e1) {
                               JOptionPane.showMessageDialog(this, "Try another.");
                   }          // TODO add your handling code here:
    }//GEN-LAST:event_pnlInstaMouseClicked

    private void pnlInstaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlInstaMouseExited
        panelMouseExit(pnlInsta,lblInsta,"/Icons/insta_b.png");
    }//GEN-LAST:event_pnlInstaMouseExited

    private void pnlInstaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlInstaMouseEntered
      panelMouseEnter(pnlInsta,lblInsta,"/Icons/insta_w.png");
    }//GEN-LAST:event_pnlInstaMouseEntered

    private void pnlTwitterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTwitterMouseClicked
         try 
                   {
                    Desktop.getDesktop().browse(new URI("https://twitter.com/AmanKrThakur2"));
                       JOptionPane.showMessageDialog(this, "Redirecting...");
                   } catch (IOException | URISyntaxException e1) {
                               JOptionPane.showMessageDialog(this, "Try another.");
                   }           // TODO add your handling code here:
    }//GEN-LAST:event_pnlTwitterMouseClicked

    private void pnlTwitterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTwitterMouseExited
        panelMouseExit(pnlTwitter,lblTwitter,"/Icons/twitter_B.png");
    }//GEN-LAST:event_pnlTwitterMouseExited

    private void pnlTwitterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTwitterMouseEntered
        panelMouseEnter(pnlTwitter,lblTwitter,"/Icons/twitter_w.png");
    }//GEN-LAST:event_pnlTwitterMouseEntered

    private void lblLinkedInMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLinkedInMouseEntered
                panelMouseEnter(pnlLinkedIn,lblLinkedIn,"/Icons/linked_w.png");
    }//GEN-LAST:event_lblLinkedInMouseEntered

    private void lblLinkedInMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLinkedInMouseExited
                panelMouseExit(pnlLinkedIn,lblLinkedIn,"/Icons/linkedin_b.png");        // TODO add your handling code here:
    }//GEN-LAST:event_lblLinkedInMouseExited

    private void lblInstaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInstaMouseEntered
              panelMouseEnter(pnlInsta,lblInsta,"/Icons/insta_w.png");
    }//GEN-LAST:event_lblInstaMouseEntered

    private void lblInstaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInstaMouseExited
               panelMouseExit(pnlInsta,lblInsta,"/Icons/insta_b.png");
    }//GEN-LAST:event_lblInstaMouseExited

    private void lblFbMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFbMouseEntered
              panelMouseEnter(pnlFb,lblFb,"/Icons/fb_w.png");
    }//GEN-LAST:event_lblFbMouseEntered

    private void lblFbMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFbMouseExited
                panelMouseExit(pnlFb,lblFb,"/Icons/fb_b.png");        // TODO add your handling code here:
    }//GEN-LAST:event_lblFbMouseExited

    private void lblTwitterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTwitterMouseEntered
         panelMouseEnter(pnlTwitter,lblTwitter,"/Icons/twitter_w.png");
    }//GEN-LAST:event_lblTwitterMouseEntered

    private void lblTwitterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTwitterMouseExited
        panelMouseExit(pnlTwitter,lblTwitter,"/Icons/twitter_B.png");        // TODO add your handling code here:
    }//GEN-LAST:event_lblTwitterMouseExited

    private void lblLinkedInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLinkedInMouseClicked
            try 
            {
             Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/aman-kr-thakur/"));
            } catch (IOException | URISyntaxException e1) {
                        JOptionPane.showMessageDialog(this, "Try another.");
            }        // TODO add your handling code here:
    }//GEN-LAST:event_lblLinkedInMouseClicked

    private void lblInstaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInstaMouseClicked
        try 
            {
             Desktop.getDesktop().browse(new URI("https://www.instagram.com/aman_kr._thakur"));
                JOptionPane.showMessageDialog(this, "Redirecting...");
            } catch (IOException | URISyntaxException e1) {
                        JOptionPane.showMessageDialog(this, "Try another.");
            }         // TODO add your handling code here:
    }//GEN-LAST:event_lblInstaMouseClicked

    private void lblFbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFbMouseClicked
                   try 
                   {
                    Desktop.getDesktop().browse(new URI("https://www.facebook.com/j4javaassignment/"));
                       JOptionPane.showMessageDialog(this, "Redirecting...");
                   } catch (IOException | URISyntaxException e1) {
                               JOptionPane.showMessageDialog(this, "Try another.");
                   }          // TODO add your handling code here:
    }//GEN-LAST:event_lblFbMouseClicked

    private void lblTwitterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTwitterMouseClicked
             try 
                   {
                    Desktop.getDesktop().browse(new URI("https://twitter.com/AmanKrThakur2"));
                       JOptionPane.showMessageDialog(this, "Redirecting...");
                   } catch (IOException | URISyntaxException e1) {
                               JOptionPane.showMessageDialog(this, "Try another.");
                   }          // TODO add your handling code here:
    }//GEN-LAST:event_lblTwitterMouseClicked

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
            java.util.logging.Logger.getLogger(AboutDeveloper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AboutDeveloper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AboutDeveloper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AboutDeveloper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AboutDeveloper().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel lblDetails;
    private javax.swing.JLabel lblFb;
    private javax.swing.JLabel lblInsta;
    private javax.swing.JLabel lblLinkedIn;
    private javax.swing.JLabel lblTextPad;
    private javax.swing.JLabel lblTextPad1;
    private javax.swing.JLabel lblTwitter;
    private javax.swing.JPanel pnlFb;
    private javax.swing.JPanel pnlInsta;
    private javax.swing.JPanel pnlLinkedIn;
    private javax.swing.JPanel pnlTwitter;
    // End of variables declaration//GEN-END:variables
}
