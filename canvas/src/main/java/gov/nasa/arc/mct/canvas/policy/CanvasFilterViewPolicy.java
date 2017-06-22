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
package gov.nasa.arc.mct.canvas.policy;

import gov.nasa.arc.mct.canvas.view.CanvasManifestation;
import gov.nasa.arc.mct.components.AbstractComponent;
import gov.nasa.arc.mct.policy.ExecutionResult;
import gov.nasa.arc.mct.policy.Policy;
import gov.nasa.arc.mct.policy.PolicyContext;
import gov.nasa.arc.mct.services.component.ViewInfo;

public class CanvasFilterViewPolicy implements Policy {
	
	@Override
	public ExecutionResult execute(PolicyContext context) {
		ExecutionResult trueResult = new ExecutionResult(context, true, "");

		AbstractComponent targetComponent = context.getProperty(PolicyContext.PropertyName.TARGET_COMPONENT.getName(), AbstractComponent.class);
		ViewInfo viewInfo = context.getProperty(PolicyContext.PropertyName.TARGET_VIEW_INFO.getName(), ViewInfo.class);
		if (!viewInfo.getViewClass().equals(CanvasManifestation.class)) {
		    return trueResult;
		}
		
		if (!targetComponent.isLeaf())
			return trueResult;
		else		
			return new ExecutionResult(context, false, CanvasManifestation.class.getName() 
			                    + " is not applicable for component type "
			                    + targetComponent.getClass().getName()); 
	}
}
