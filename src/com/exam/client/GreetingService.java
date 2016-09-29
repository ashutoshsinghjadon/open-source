package com.exam.client;

import java.util.ArrayList;

import com.exam.client.dto.DeviceDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 *  * Author Ashutosh
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {

//	String getDeviceData(String input);
	String exportToPdf(ArrayList<DeviceDTO> input);
	ArrayList<DeviceDTO> getDeviceData(String input);
}
