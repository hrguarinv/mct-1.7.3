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
package gov.nasa.arc.mct.gui.dialogs;

import gov.nasa.arc.mct.components.AbstractComponent;
import gov.nasa.arc.mct.policy.ExecutionResult;
import gov.nasa.arc.mct.policy.PolicyContext;
import gov.nasa.arc.mct.policy.PolicyInfo;
import gov.nasa.arc.mct.policymgr.PolicyManagerImpl;
import gov.nasa.arc.mct.services.component.ComponentRegistry;
import gov.nasa.arc.mct.services.component.CreateWizardUI;
import gov.nasa.arc.mct.util.DataValidation;
import gov.nasa.arc.mct.util.MCTIcons;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DefaultWizardUI extends CreateWizardUI {

    private static final ResourceBundle bundle;
    
    private static final int ICON_HEIGHT = 16;
    private static final int ICON_WIDTH = 16;
    private static final int COL_SIZE = 30;
    private static final int MIN_LENGTH, MAX_LENGTH;

    private static final String ERRORMSG;
     
    
    static {
        bundle = ResourceBundle.getBundle("NewObjectResource"); //NOI18N
        MIN_LENGTH = Integer.parseInt(bundle.getString("MIN_LENGTH")); //NOI18N
        MAX_LENGTH = Integer.parseInt(bundle.getString("MAX_LENGTH")); //NOI18N
        ERRORMSG = String.format(bundle.getString("ERRMSG_LENGTH"), MIN_LENGTH, MAX_LENGTH); //NOI18N
    }
    
    private final JLabel message = new JLabel();
    private final JTextField name = new JTextField();
    private Class<? extends AbstractComponent> componentClass;
    
    public DefaultWizardUI(Class<? extends AbstractComponent> comp){
        this.componentClass = comp;
    }
    
    @Override
    public AbstractComponent createComp(ComponentRegistry comp, AbstractComponent targetComponent) {    
        String displayName = name.getText().trim();
        AbstractComponent component = comp.newInstance(componentClass, targetComponent);
        component.setDisplayName(displayName);
        
        return component;
    }

    @Override
    public JComponent getUI(final JButton create) {   
        JPanel contentPanel = new JPanel();
        JLabel prompt = new JLabel(bundle.getString("TEXT_FIELD_LABEL")); //NOI18N
        name.setText(bundle.getString("DEFAULT_OBJECT_NAME")); //NOI18N
        prompt.setLabelFor(name);
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
                message.setText((flag) ? "" : ERRORMSG);
                
                ExecutionResult exResult = checkReservedWordsNamingPolicy(name.getText().trim());
                if (!exResult.getStatus()) {
                    create.setEnabled(false);
                    message.setIcon(MCTIcons.getErrorIcon(ICON_WIDTH, ICON_HEIGHT));
                    message.setText(exResult.getMessage());
                }

            }

        });
        
        name.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                name.setForeground(!checkReservedWordsNamingPolicy(name.getText().trim()).getStatus() 
                        ? Color.RED
                        : Color.BLACK);
                }
            });
        contentPanel.add(prompt);
        contentPanel.add(name);
        
        JPanel messagePanel = new JPanel();
        messagePanel.add(message);
        
        JPanel UIPanel = new JPanel();
        UIPanel.setLayout(new GridLayout(2,1));
        UIPanel.add(contentPanel);
        UIPanel.add(messagePanel);
        
        return UIPanel;
    }
    
    private ExecutionResult checkReservedWordsNamingPolicy(String name) {
        
        PolicyContext context = new  PolicyContext();
        context.setProperty("NAME", name);
        String namingKey = PolicyInfo.CategoryType.COMPONENT_NAMING_POLICY_CATEGORY.getKey();
        ExecutionResult exResult = PolicyManagerImpl.getInstance().execute(namingKey, context);
        
        return exResult;            
    }




    
}