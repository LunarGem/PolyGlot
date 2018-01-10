/*
 * Copyright (c) 2014-2017, Draque Thompson, draquemail@gmail.com
 * All rights reserved.
 *
 * Licensed under: Creative Commons Attribution-NonCommercial 4.0 International Public License
 *  See LICENSE.TXT included with this code to read the full license agreement.

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package PolyGlot.Screens;

import PolyGlot.CustomControls.InfoBox;
import PolyGlot.CustomControls.PButton;
import PolyGlot.CustomControls.PDialog;
import PolyGlot.CustomControls.PLabel;
import PolyGlot.DictCore;
import PolyGlot.WebInterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author draque
 */
public class ScrUpdateAlert extends PDialog {

    private final Map<String, String> buttonMap = new HashMap<>();

    /**
     * Creates new form ScrUpdateAlert
     *
     * @param verbose run in verbose mode
     * @param _core current dictionary core
     * @throws java.lang.Exception if unable to connect
     */
    public ScrUpdateAlert(boolean verbose, DictCore _core) throws Exception {
        core = _core;
        setupKeyStrokes();
        initComponents();
        
        jTextPane1.setContentType("text/html");
        jPanel1.setBackground(Color.white);
        super.getRootPane().getContentPane().setBackground(Color.white);

        Document doc = WebInterface.checkForUpdates(core.getVersion());
        final Window parent = this;
        Node ver = doc.getElementsByTagName("Version").item(0);
        Node message = doc.getElementsByTagName("VersionText").item(0);

        Node buttons = doc.getElementsByTagName("LinkButtons").item(0);

        for (Node curButton = buttons.getFirstChild();
                curButton != null;
                curButton = curButton.getNextSibling()) {
            Node nameNode = ((Element) curButton.getChildNodes()).getElementsByTagName("Text").item(0);
            Node linkNode = ((Element) curButton.getChildNodes()).getElementsByTagName("Link").item(0);

            buttonMap.put(nameNode.getTextContent(), linkNode.getTextContent());

            PButton newButton = new PButton(core);
            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton thisButton = (JButton) e.getSource();
                    String link = buttonMap.get(thisButton.getText());
                    URI uri;

                    try {
                        uri = new URI(link);
                        uri.normalize();
                        java.awt.Desktop.getDesktop().browse(uri);
                    } catch (IOException | URISyntaxException ex) {
                        InfoBox.error("Browser Error", "Unable to open page: " + link, core.getRootWindow());
                    }
                }
            });

            newButton.setText(nameNode.getTextContent());
            newButton.setSize(358, 30);

            jPanel1.add(newButton);

            int height = this.getSize().height;
            newButton.setLocation(0, height - 30);
            this.setSize(height, height + newButton.getHeight());
        }

        this.setTitle("PolyGlot " + ver.getTextContent() + " available");
        jTextPane1.setText(message.getTextContent());
        txtVersion.setText("New Version: " + ver.getTextContent());

        if (ver.getTextContent().equals(core.getVersion())) {
            if (verbose) {
                InfoBox.info("Update Status", "You're up to date and on the newest version: "
                        + core.getVersion() + ".", core.getRootWindow());
            }

            this.setVisible(false);
            this.dispose();
        } else {
            setVisible(true);
        }
    }
    
    @Override
    public final Dimension getSize() {
        return super.getSize();
    }
    
    @Override
    public final void setTitle(String _title) {
        super.setTitle(_title);
    }
    
    @Override
    public final void setupKeyStrokes() {
        super.setupKeyStrokes();
    }
    
    @Override
    public final void setSize(int _width, int height) {
        super.setSize(_width, height);
    }
    
    @Override
    public void updateAllValues(DictCore _dictCore) {
        // No values to update
    }
    
    @Override
    public boolean thisOrChildrenFocused() {
        return this.isFocusOwner();
    }
    
    @Override
    public final void dispose() {
        super.dispose();
    }

    @Override
    public final void setVisible(boolean _visible) {
        super.setVisible(_visible);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        txtVersion = new PLabel("", core);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setViewportView(jTextPane1);

        txtVersion.setText("--");
        txtVersion.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtVersion)
                        .addGap(0, 344, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtVersion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void run(boolean verbose, DictCore core) throws Exception {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (javax.swing.UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ScrUpdateAlert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        ScrUpdateAlert s = new ScrUpdateAlert(verbose, core);
        s.setModal(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel txtVersion;
    // End of variables declaration//GEN-END:variables
}
