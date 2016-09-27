package com.exam.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 * Author Ashutosh
 */
public interface GreetingServiceAsync {
	void getDeviceData(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
}
