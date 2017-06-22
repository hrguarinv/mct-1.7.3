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
package gov.nasa.arc.mct.platform.spi;

import gov.nasa.arc.mct.components.AbstractComponent;
import gov.nasa.arc.mct.services.internal.component.ComponentInitializer;
import gov.nasa.arc.mct.services.internal.component.User;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RoleAccessTest {
    
    @Mock private User testUser;
    private AbstractComponent component;
    
    @BeforeTest
    void setup() {
        MockitoAnnotations.initMocks(this);
        component = new AbstractComponent() {
        };
    }
    
    @DataProvider(name="userAndComponentSetup")
    Object[][] userTests() {
        return new Object[][] {
               new Object[] {"admin", "xyz", true},
               new Object[] {"xyz", "xyz", true},
               new Object[] {"qqq", "xyz", false}
        };
    }
    
    void setupUserAndComponent(String user, String originalOwner) {
        Mockito.when(testUser.getUserId()).thenReturn(user);
        ComponentInitializer ci = component.getCapability(ComponentInitializer.class);
        ci.setOwner(originalOwner);
    }
    
    @Test(dataProvider="userAndComponentSetup")
    public void testCanChangeOwner(String userName, String originalOwner, boolean expectedValue) {
        setupUserAndComponent(userName, originalOwner);
        component.setOwner(userName);
        Assert.assertEquals(RoleAccess.canChangeOwner(component, testUser), expectedValue);
    }
}
