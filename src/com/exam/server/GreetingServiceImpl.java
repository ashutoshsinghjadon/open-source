package com.exam.server;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.exam.client.GreetingService;
import com.exam.server.hibernate.HibernateUtil;
import com.exam.server.hibernate.model.DeviceData;
import com.exam.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * The server-side implementation of the RPC service.
 *  * Author Ashutosh
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	
	/**
	 * get multi device data from mysql
	 * 
	 * @param user id 
	 * 
	 */
	
	public String getDeviceData(String input) throws IllegalArgumentException {
		
		Session session=HibernateUtil.getSessionFactory().openSession();
		Criteria cr = session.createCriteria(DeviceData.class);
		cr.add(Restrictions.eq("part2Id", Integer.parseInt(input)));
		List results = cr.list();	
		session.close();
		JSONObject jObject = new JSONObject();
		JSONArray jArray = new JSONArray();
		
		for (Iterator iterator = results.iterator(); iterator.hasNext();)
		{
			DeviceData deviceData = (DeviceData) iterator.next(); 
		         JSONObject deviceJson = new JSONObject();
		         deviceJson.put("address", deviceData.getAddress1());
		         jArray.add(deviceJson);
		}
		jObject.put("results", jArray);
		
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidId(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		String deviceJson = escapeHtml(jObject.toString());

		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>Device data found:<br>"
				+ deviceJson;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}