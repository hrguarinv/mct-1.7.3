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
package gov.nasa.arc.mct.gui.actions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestHelpMCTAction {
    
    private HelpMCTAction helpClass;

    @BeforeClass
    public void setup() {
        helpClass = new HelpMCTAction();
    }

    @Test
    public void testConstructor() {
        assertNotNull(helpClass);
    }

    @Test
    public void testIsEnabled() {        
        assertEquals(helpClass.isEnabled(), true);
    }

//    @Test
//    public void testActionPerformed() {
//        if (GraphicsEnvironment.isHeadless()) {
//            return;
//        }
//        helpClass.actionPerformed(null);
//        assertTrue(helpClass.getBrowserReturned());
//    }

    @Test
    public void testCanHandle() {
        assertEquals(helpClass.canHandle(null), true);
    }
}
