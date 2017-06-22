/*******************************************************************************
 * Mission Control Technologies, Copyright (c) 2009-2012, United States Government
 * as represented by the Administrator of the National Aeronautics and Space 
 * Administration. All rights reserved.
 *
 * The MCT platform is licensed under the Apache License, Version 2.0 (the 
 * "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations under 
 * the License.
 *
 * MCT includes source code licensed under additional open source licenses. See 
 * the MCT Open Source Licenses file included with this distribution or the About 
 * MCT Licenses dialog available at runtime from the MCT Help menu for additional 
 * information. 
 *******************************************************************************/
package gov.nasa.arc.mct.evaluator.enums;

import gov.nasa.arc.mct.util.DataValidation;
import gov.nasa.arc.mct.util.LafColor;
import gov.nasa.arc.mct.util.MCTIcons;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Show dialog box for associating objects with a new multi.
 */
@SuppressWarnings("serial")
public class PlaceObjectsInMultiDialog extends JDialog {
    
	private static ResourceBundle bundle = ResourceBundle.getBundle("MultiComponent");
	
    private static final int ICON_HEIGHT = 16;
    private static final int ICON_WIDTH = 16;
    private static final int DIALOG_HEIGHT = 225;
    private static final int DIALOG_WIDTH = 600;
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 300;
    private static final int COL_SIZE = 30;

    private static String errorMessage;
    static {
        errorMessage = "Multi name must contain" + " " + MIN_LENGTH
                + " to " + MAX_LENGTH + " characters.";

    }
    private boolean confirmed = false;
    private final JLabel message = new JLabel();
    private final JTextField name = new JTextField();
    private final JButton create = new JButton();

    /**
     * Place multi objects in new collection dialog window. 
     * @param frame the Frame.
     * @param selectedComponentNames selective component names.
     */
    public PlaceObjectsInMultiDialog(Frame frame, String [] selectedComponentNames) {
        super(frame, ModalityType.DOCUMENT_MODAL);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        setTitle("Place Objects in New Multi" + " - " + frame.getTitle());
        
        JLabel prompt = new JLabel(bundle.getString("DialogPrompt"));
        name.setText("untitled Multi");
        name.selectAll();
        name.setColumns(COL_SIZE);
        name.getDocument().addDocumentListener(new DocumentListener() {
           
            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                doAction();                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                doAction();  
            }
            
            private boolean verify(String input) {
                return DataValidation.validateLength(input, MIN_LENGTH, MAX_LENGTH);
            }
            
            private void doAction() {
                boolean flag = verify(name.getText().trim());
                create.setEnabled(flag);
                message.setIcon((flag) ? null : MCTIcons.getErrorIcon(ICON_WIDTH, ICON_HEIGHT));
                message.setText((flag) ? "" : errorMessage);
            }
        });
        
        name.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { 
            	name.setForeground(Color.BLACK);
            }
        });
        
        //Control panel for creating or canceling out of the creation window
        JPanel controlPanel = new JPanel();
        
        //Create button for creation window
        create.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	PlaceObjectsInMultiDialog.this.setVisible(false);
            	PlaceObjectsInMultiDialog.this.dispose();
            	confirmed = true;
            }
        });
        create.setText(bundle.getString("Create"));
        create.setName("createButton");
        
        //Cancel button for creation window
        JButton cancel = new JButton(bundle.getString("Cancel"));
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PlaceObjectsInMultiDialog.this.dispose();
            }

        });
        cancel.setName("cancelButton");
        
        controlPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        controlPanel.add(create);
        controlPanel.add(cancel);
        
        //Displays what telemetry elements are associated with the new multi
        JPanel messagePanel = new JPanel();
        messagePanel.add(message);
        
        JLabel titleLabel = new JLabel(bundle.getString("TitleLabel"));
        JTextArea textArea = new JTextArea();
        textArea.setBorder(BorderFactory.createEmptyBorder());
        textArea.setBackground(LafColor.MENUBAR_BACKGROUND);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        textArea.setEditable(false);
        String newLine = "\n";
        String space = " ";
        for (String name : selectedComponentNames)
            textArea.append('\u2022' + space + name + newLine);
        textArea.setCaretPosition(0);
        
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        
        c.gridy = 0;
        c.weightx = 0.01;
        add(prompt, c);
        
        c.gridx = 1;
        c.weightx = 0.99;
        c.insets = new Insets(10, 0, 0, 10);
        add(name, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.gridwidth = 2;
        c.insets = new Insets(0, 10, 0, 10);
        add(messagePanel, c);
        
        c.gridy = 2;
        c.insets = new Insets(0, 10, 10, 10);
        add(titleLabel, c);
        
        c.gridy = 3;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        add(scrollPane, c);
        
        c.gridy = 4;
        c.insets = new Insets(0, 0, 0, 5);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(controlPanel, c);
        
        //Set Create button as default to respond to enter key
        this.getRootPane().setDefaultButton(create);
        
        setSize(DIALOG_WIDTH,DIALOG_HEIGHT);
        setLocationRelativeTo(frame);
        setVisible(true);
    }
   
    /**
     * Gets the confirmed multi name.
     * @return the multi name.
     */
    public String getConfirmedMultiName() {
        if (confirmed)
            return name.getText().trim();
        else
            return "";
    }
    
}
