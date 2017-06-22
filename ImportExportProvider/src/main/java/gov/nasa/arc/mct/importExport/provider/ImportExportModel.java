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
package gov.nasa.arc.mct.importExport.provider;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The MCT Collection Component's model.
 * This model role provides persistent meta data.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportExportModel {
    
    /** Table of data associated with the model. */
    private ImportExportData metaData = new ImportExportData();
 
    public synchronized String getMetaData(String name) {
        return this.metaData.getMetaData(name);
    }

    public synchronized String addMetaData(String name, String value) {
        return this.metaData.addMetaData(name, value);
    }

    public synchronized ImportExportData getMetaData() {
        return this.metaData;
    }

    public synchronized void setMetaData(ImportExportData metaData) {
        this.metaData = metaData;
    }
}
