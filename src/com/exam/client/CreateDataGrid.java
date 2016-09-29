package com.exam.client;

import java.util.ArrayList;
import java.util.List;

import com.exam.client.dto.DeviceDTO;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

/**
* Entry point class define <code>onModuleLoad()</code>.
*/
public class CreateDataGrid {
/**
* A simple data type that represents an Address.
*/
private static class Device {
private int part2Id;
private int idCompany;
private String address;
private String data1;
private String time1;
private String time2;

public Device(int part2Id, int idCompany, String address, String data1, String time1, String time2) {
  this.part2Id = part2Id;
  this.idCompany = idCompany;
  this.address = address;
  this.data1 = data1;
  this.time1 = time1;
  this.time2 = time2;
 }
}

/*
* The list of data to display.
*/
//private static final List<Address> ADDRESS = Arrays.asList(
//  new Address("123", "Lloyds Road", "Middlesex", "TE0 6NB", "United Kingdom")
//  ,new Address("456", "Oxford Street", "Oxford", "LK9 0CV", "United Kingdom"));
private static List<Device> deviceList = new ArrayList<Device>();

public SimpleLayoutPanel getDataGrid(ArrayList<DeviceDTO> result) {
	for (DeviceDTO deviceDTO : result) {
		Device d = new Device(deviceDTO.getPart2Id(), deviceDTO.getIdCompany(), deviceDTO.getAddress1(), deviceDTO.getData1(), deviceDTO.getTime1(), deviceDTO.getTime2());
		deviceList.add(d);
	}
	
  DataGrid<Device> table = new DataGrid<Device>();
  table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

  TextColumn<Device> part2Id = new TextColumn<Device>() {
    @Override
    public String getValue(Device object) {
    return ""+object.part2Id;
  }};
  table.addColumn(part2Id, "Part2 Id");

  TextColumn<Device> idCompany = new TextColumn<Device>() {
    @Override
    public String getValue(Device object) {
    return ""+object.idCompany;
  }};
 table.addColumn(idCompany, "Id Company");

  TextColumn<Device> address = new TextColumn<Device>() {
    @Override
    public String getValue(Device object) {
    return object.address;
  }};
 table.addColumn(address, "Address");

  TextColumn<Device> data1 = new TextColumn<Device>() {
    @Override
    public String getValue(Device object) {
    return object.data1;
  }};
  table.addColumn(data1, "Data1");

  TextColumn<Device> time1 = new TextColumn<Device>() {
    @Override
    public String getValue(Device object) {
    return object.time1;
  }};
  table.addColumn(time1, "Time1");
  
  TextColumn<Device> time2 = new TextColumn<Device>() {
	  @Override
	  public String getValue(Device object) {
		  return object.time2;
	  }};
	  table.addColumn(time2, "Time2");

// Add a selection model to handle user selection.
  final SingleSelectionModel<Device> selectionModel = new SingleSelectionModel<Device>();
  table.setSelectionModel(selectionModel);
  selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
    public void onSelectionChange(SelectionChangeEvent event) {
    Device selected = selectionModel.getSelectedObject();
    if (selected != null) {
      Window.alert("You selected: " + selected.part2Id + " " + selected.idCompany + " " + selected.address
+ " " + selected.data1 + " " + selected.time1+ " " + selected.time2);
}
}
});
  table.setRowCount(deviceList.size(), true);
  table.setRowData(0, deviceList);
  table.setWidth("100%");
  SimpleLayoutPanel slp = new SimpleLayoutPanel();
  slp.add(table);

return slp;
}
}