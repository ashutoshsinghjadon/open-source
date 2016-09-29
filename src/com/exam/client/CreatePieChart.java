package com.exam.client;

import java.util.ArrayList;

import com.exam.client.dto.DeviceDTO;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.Selection;
import com.googlecode.gwt.charts.client.corechart.PieChart;
import com.googlecode.gwt.charts.client.corechart.PieChartOptions;
import com.googlecode.gwt.charts.client.event.ReadyEvent;
import com.googlecode.gwt.charts.client.event.ReadyHandler;

public class CreatePieChart extends DockLayoutPanel {
	private PieChart chart;
	
	public CreatePieChart(ArrayList<DeviceDTO> deviceData) {
		super(Unit.PX);
		initialize(deviceData);
	}

	private void initialize(final ArrayList<DeviceDTO> deviceData) {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				// Create and attach the chart
				chart = new PieChart();
				add(chart);
				draw(deviceData);
			}
		});
	}

	private void draw(ArrayList<DeviceDTO> deviceData) {
		// Prepare the data
		int shutdownCount = 0;
		int inspectedCount = 0;
		int earlyCount = 0;
		int uninspectedCount = 0;
		int duplicateCount = 0;
		int lateCount = 0;
		int unscheduledCount = 0;
		int downloadedCount = 0;
		for (DeviceDTO deviceDTO : deviceData) {
			if(deviceDTO.getData1()!=null && deviceDTO.getData1().equalsIgnoreCase("SHUTDOWN_EVENT")){
				shutdownCount++;
			}
			if(deviceDTO.getData1()!=null && deviceDTO.getData1().equalsIgnoreCase("INSPECTED")){
				inspectedCount++;
			}
			if(deviceDTO.getData1()!=null && deviceDTO.getData1().equalsIgnoreCase("EARLY")){
				earlyCount++;
			}
			if(deviceDTO.getData1()!=null && deviceDTO.getData1().equalsIgnoreCase("UNINSPECTED")){
				uninspectedCount++;
			}
			if(deviceDTO.getData1()!=null && deviceDTO.getData1().equalsIgnoreCase("DUPLICATE")){
				duplicateCount++;
			}
			if(deviceDTO.getData1()!=null && deviceDTO.getData1().equalsIgnoreCase("LATE")){
				lateCount++;
			}
			if(deviceDTO.getData1()!=null && deviceDTO.getData1().equalsIgnoreCase("UNSCHEDULED")){
				unscheduledCount++;
			}
			if(deviceDTO.getData1()!=null && deviceDTO.getData1().equalsIgnoreCase("DOWNLOADED")){
				downloadedCount++;
			}
		}
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Task");
		dataTable.addColumn(ColumnType.NUMBER, "Hours per Day");
		dataTable.addRows(8);
		dataTable.setValue(0, 0, "SHUTDOWN_EVENT");
		dataTable.setValue(0, 1, shutdownCount);
		dataTable.setValue(1, 0, "INSPECTED");
		dataTable.setValue(1, 1, inspectedCount);
		dataTable.setValue(2, 0, "EARLY");
		dataTable.setValue(2, 1, earlyCount);
		dataTable.setValue(3, 0, "UNINSPECTED");
		dataTable.setValue(3, 1, uninspectedCount);
		dataTable.setValue(4, 0, "DUPLICATE");
		dataTable.setValue(4, 1, duplicateCount);
		dataTable.setValue(5, 0, "LATE");
		dataTable.setValue(5, 1, lateCount);
		dataTable.setValue(6, 0, "UNSCHEDULED");
		dataTable.setValue(6, 1, unscheduledCount);
		dataTable.setValue(7, 0, "DOWNLOADED");
		dataTable.setValue(7, 1, downloadedCount);

		// Set options
		PieChartOptions options = PieChartOptions.create();
//		options.setBackgroundColor("#f0f0f0");

		// options.setColors(colors);
		options.setFontName("Tahoma");
		options.setIs3D(true);
		options.setPieResidueSliceColor("#000000");
//		options.setPieResidueSliceLabel("Others");
//		options.setSliceVisibilityThreshold(0.1);
		options.setTitle("Device break up on basis of data1:");

		// Draw the chart
		chart.draw(dataTable, options);
		chart.addReadyHandler(new ReadyHandler() {

			@Override
			public void onReady(ReadyEvent event) {
				chart.setSelection(Selection.create(1, null));
			}
		});
	}

}