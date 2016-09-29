package com.exam.client;

import java.util.ArrayList;

import com.exam.client.dto.DeviceDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 * Author Ashutosh
 */
public interface GreetingServiceAsync {
//	void getDeviceData(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
	void exportToPdf(ArrayList<DeviceDTO> input, AsyncCallback<String> callback) throws IllegalArgumentException;
	void getDeviceData(String input, AsyncCallback<ArrayList<DeviceDTO>> callback) throws IllegalArgumentException;
}
