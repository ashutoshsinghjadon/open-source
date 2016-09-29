package com.exam.client;

import java.util.ArrayList;
import java.util.Date;

import com.exam.client.dto.DeviceDTO;
import com.exam.client.dto.EventDTO;
import com.exam.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
//import com.googlecode.gwt.charts.client.corechart.PieChart;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * Author Ashutosh
 */
public class Unitech implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	
//	private SimpleLayoutPanel layoutPanel;
//	private PieChart pieChart;
	
	public void onModuleLoad() {
		final Button sendButton = new Button("Send");
		final TextBox userName = new TextBox();
		
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(userName);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		userName.setFocus(true);
		userName.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		
		final DialogBox dialogBox1 = new DialogBox();
		dialogBox1.setText("Remote Procedure Call");
		dialogBox1.setAnimationEnabled(true);
		final Button closeButton1 = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton1.getElement().setId("closeButton");
		VerticalPanel dialogVPanel1 = new VerticalPanel();
		dialogVPanel1.addStyleName("dialogVPanel");
		dialogVPanel1.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel1.add(serverResponseLabel);
		dialogVPanel1.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel1.add(closeButton1);
		dialogBox1.setWidget(dialogVPanel1);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
//				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});
		closeButton1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox1.hide();
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = userName.getText();
				if (!FieldVerifier.isValidId(textToServer)) {
					errorLabel.setText("Not a valid id. Please provide valid id.");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				
				EventDTO eventDTO = new EventDTO();
				
				eventDTO.setTitle("firstEvent");
				eventDTO.setDate(new Date());
				greetingService.getDeviceData(textToServer, new AsyncCallback<ArrayList<DeviceDTO>>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						sendButton.setEnabled(true);
						closeButton.setFocus(true);
					}

					public void onSuccess(final ArrayList<DeviceDTO> result) {
						/*dialogBox.setText("Remote Procedure Call");
						serverResponseLabel.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);*/
						sendButton.setEnabled(true);
						HorizontalPanel panel = new HorizontalPanel();
						Button exportButton = new Button("ExportToPDF");
						exportButton.addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
//								String link = GWT.getModuleBaseURL() + "myfiledownload";
								greetingService.exportToPdf(result, new AsyncCallback<String>() {
									public void onFailure(Throwable caught) {
										// Show the RPC error message to the user
										dialogBox1.setText("Remote Procedure Call - Failure");
										serverResponseLabel.addStyleName("serverResponseLabelError");
										serverResponseLabel.setHTML(SERVER_ERROR);
										dialogBox1.center();
										closeButton1.setFocus(true);
									}

									public void onSuccess(String result) {
										dialogBox1.setText("Remote Procedure Call");
										serverResponseLabel.removeStyleName("serverResponseLabelError");
										serverResponseLabel.setHTML(result);
										dialogBox1.center();
										closeButton1.setFocus(true);
										}
									});
							}
						});
						panel.add(exportButton);
						CreateDataGrid dataGrid = new CreateDataGrid();
						  SplitLayoutPanel splitLayoutPanel = new SplitLayoutPanel();
						  splitLayoutPanel.addWest(new CreatePieChart(result), 520);
						  splitLayoutPanel.addNorth(panel, 384);
						  splitLayoutPanel.add(dataGrid.getDataGrid(result));
						  RootLayoutPanel.get().add(splitLayoutPanel);
//						RootLayoutPanel.get().add(new CreatePieChart());
//						RootLayoutPanel.get().add(dataGrid.getDataGrid());
//						RootLayoutPanel.get().add(panel);
						
					}
				});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		userName.addKeyUpHandler(handler);
	}
	
	
	
//	in phase development for pie chart
	
	/*@Override
	public void onModuleLoad() {
		Window.enableScrolling(false);
		Window.setMargin("0px");

		RootLayoutPanel.get().add(getSimpleLayoutPanel());

		// Create the API Loader
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				getSimpleLayoutPanel().setWidget(getPieChart());
				drawPieChart();
			}
		});
	}

	private SimpleLayoutPanel getSimpleLayoutPanel() {
		if (layoutPanel == null) {
			layoutPanel = new SimpleLayoutPanel();
		}
		return layoutPanel;
	}

	private Widget getPieChart() {
		if (pieChart == null) {
			pieChart = new PieChart();
		}
		return pieChart;
	}

	private void drawPieChart() {
		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Subject");
		dataTable.addColumn(ColumnType.NUMBER, "Number of students");
		dataTable.addRows(4);
		dataTable.setValue(0, 0, "History");
		dataTable.setValue(1, 0, "Computers");
		dataTable.setValue(2, 0, "Management");
		dataTable.setValue(3, 0, "Politics");
		dataTable.setValue(0, 1, 20);
		dataTable.setValue(1, 1, 25);
		dataTable.setValue(2, 1, 30);
		dataTable.setValue(3, 1, 35);

		// Draw the chart
		pieChart.draw(dataTable);
	}*/
}
