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
package gov.nasa.arc.mct.fastplot.bridge;

import gov.nasa.arc.mct.components.FeedProvider;
import gov.nasa.arc.mct.fastplot.bridge.PlotConstants.AxisOrientationSetting;
import gov.nasa.arc.mct.fastplot.bridge.PlotConstants.LimitAlarmState;
import gov.nasa.arc.mct.fastplot.bridge.PlotConstants.NonTimeAxisSubsequentBoundsSetting;
import gov.nasa.arc.mct.fastplot.bridge.PlotConstants.PlotLineConnectionType;
import gov.nasa.arc.mct.fastplot.bridge.PlotConstants.PlotLineDrawingFlags;
import gov.nasa.arc.mct.fastplot.bridge.PlotConstants.TimeAxisSubsequentBoundsSetting;
import gov.nasa.arc.mct.fastplot.bridge.PlotConstants.XAxisMaximumLocationSetting;
import gov.nasa.arc.mct.fastplot.bridge.PlotConstants.YAxisMaximumLocationSetting;
import gov.nasa.arc.mct.fastplot.utils.AbbreviatingPlotLabelingAlgorithm;
import gov.nasa.arc.mct.fastplot.view.Axis;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Map;
import java.util.SortedMap;

import javax.swing.JPanel;

/**
 * Implementation of the AbstractPlottingPackage interface for use in testing.
 * It allows us to push data to the "plot" and then inspect it without the 
 * overhead of using a real plotting package. 
 *
 */
public class ShellPlotPackageImplementation implements AbstractPlottingPackage{

	private Map<String, ArrayList<Double>> plotDataSet;
	
	private boolean paused;
	
	private Axis nonTimeAxis = new Axis();

	private AbbreviatingPlotLabelingAlgorithm plotLabelingAlgorithm = new AbbreviatingPlotLabelingAlgorithm();
	
	@Override
	public void createChart(
			AxisOrientationSetting theAxisOrientation,
			String theTimeSystem,
            String theTimeFormat,
			XAxisMaximumLocationSetting theXAxisSetting,
			YAxisMaximumLocationSetting theYAxisSetting,
			TimeAxisSubsequentBoundsSetting theTimeAxisSubsequentSetting,
			NonTimeAxisSubsequentBoundsSetting theNonTimeAxisMinSubsequentSetting,
			NonTimeAxisSubsequentBoundsSetting theNonTimeAxisMaxSubsequentSetting,
			Font timeAxisFont, int plotLineThickness,
			Color plotBackgroundFrameColor, Color plotAreaBackgroundColor,
			int timeAxisIntercept, Color timeAxisColor,
			Color timeAxisLabelColor, Color nonTimeAxisLabelColor,
			String timeAxisDataFormat, Color nonTimeAxisColor,
			Color gridLineColor, int minSamplesForAutoScale,
			double scrollRescaleTimeMargine, 
			double scrollRescaleNonTimeMargineMax, 
			double scrollRescaleNonTimeMargineMin, 
			double theNonTimeVaribleAxisMinValue,
			double theNonTimeVaribleAxisMaxValue,
			long theTimeVariableAxisMinValue, long theTimeVariableAxisMaxValue, boolean compressionenabled, boolean time, boolean label, boolean ordinal, 
			PlotLineDrawingFlags plotLineDraw,
			PlotLineConnectionType plotLineConnectionType,
			PlotAbstraction pa,
			AbbreviatingPlotLabelingAlgorithm thePlotLabelingAlgorithm) {
		
		plotLabelingAlgorithm = thePlotLabelingAlgorithm;
		
		plotDataSet = new  Hashtable<String, ArrayList<Double>>();
		
	}

	public Map<String, ArrayList<Double>> getDataSet() {
		return plotDataSet;
	}
	
	@Override
	public void addDataSet(String dataSetName, Color plottingColor) {
		ArrayList<Double> data = new ArrayList<Double>();
		plotDataSet.put(dataSetName, data);
	}
	
	@Override
	public int getDataSetSize() {
	return plotDataSet.size();
	}

	@Override
	public JPanel getPlotPanel() {
		return new JPanel();
	}

	@Override
	public boolean isKnownDataSet(String setName) {
		return plotDataSet.containsKey(setName);
	}

	@Override
	public void refreshDisplay() {
	
	}
	
	@Override
	 public LimitAlarmState getNonTimeMaxAlarmState() {
		return LimitAlarmState.NO_ALARM;
	}
	
	@Override
	 public LimitAlarmState getNonTimeMinAlarmState() {
		return LimitAlarmState.NO_ALARM;
	}
	
	@Override
	public long getCurrentTimeAxisMaxAsLong() {
		return getCurrentTimeAxisMax().getTimeInMillis();
	}
	
	@Override
	public long getCurrentTimeAxisMinAsLong() {
		return getCurrentTimeAxisMin().getTimeInMillis();
	}
	
	@Override
	public GregorianCalendar getCurrentTimeAxisMin() {
		return new GregorianCalendar();
	}
	
	@Override
	public GregorianCalendar getCurrentTimeAxisMax() {
		return new GregorianCalendar();
	}

	@Override
	public NonTimeAxisSubsequentBoundsSetting getNonTimeAxisSubsequentMaxSetting() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NonTimeAxisSubsequentBoundsSetting getNonTimeAxisSubsequentMinSetting() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getInitialNonTimeMaxSetting() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getNonTimeMaxPadding() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getInitialNonTimeMinSetting() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getNonTimeMinPadding() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AxisOrientationSetting getAxisOrientationSetting() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TimeAxisSubsequentBoundsSetting getTimeAxisSubsequentSetting() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getInitialTimeMaxSetting() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getInitialTimeMinSetting() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTimePadding() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public XAxisMaximumLocationSetting getXAxisMaximumLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public YAxisMaximumLocationSetting getYAxisMaximumLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDataSet(String lowerCase, Color plottingColor,
			String displayName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTimeSyncLine() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showTimeSyncLine(GregorianCalendar time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTimeSyncLineVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPlotView(PlotAbstraction plotView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyGlobalTimeSyncFinished() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean inTimeSyncMode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getNonTimeMaxDataValueCurrentlyDisplayed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getNonTimeMinDataValueCurrentlyDisplayed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCurrentNonTimeAxisMax() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCurrentNonTimeAxisMin() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isCompresionEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCompressionEnabled(boolean state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informUpdateCacheDataStreamCompleted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informUpdateCachedDataStreamStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informUpdateFromLiveDataStreamCompleted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informUpdateFromLiveDataStreamStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTimeAxisStartAndStop(long startTime, long endTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addData(String feed, long time, double value) {
		ArrayList<Double> data = plotDataSet.get(feed);
		data.add(value);
	}
	
	@Override
	public void addData(String feedID, SortedMap<Long, Double> points) {
		ArrayList<Double> data = plotDataSet.get(feedID);
		data.addAll(points.values());
	}

	@Override
	public void updateLegend(String dataSetName, FeedProvider.RenderingInfo info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserversTimeChange() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerObservor(PlotObserver o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver(PlotObserver o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearAllDataFromPlot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause(boolean b) {
		paused = b;
	}


	@Override
	public boolean isPaused() {
		return paused;
	}

	@Override
	public boolean getOrdinalPositionInStackedPlot() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Axis getNonTimeAxis() {
		return nonTimeAxis;
	}
	
	@Override
	public void updateResetButtons() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbbreviatingPlotLabelingAlgorithm getPlotLabelingAlgorithm() {
		return plotLabelingAlgorithm;
	}

	@Override
	public void setPlotLabelingAlgorithm(AbbreviatingPlotLabelingAlgorithm thePlotLabelingAlgorithm) {
		plotLabelingAlgorithm = thePlotLabelingAlgorithm; 
	}

	@Override
	public void setTruncationPoint(double min) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateCompressionRatio() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getTimeSystemSetting() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTimeFormatSetting() {
		// TODO Auto-generated method stub
		return null;
	}


}
