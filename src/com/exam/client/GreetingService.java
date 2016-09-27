package com.exam.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 *  * Author Ashutosh
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {

	String getDeviceData(String input);
}
