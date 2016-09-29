package com.exam.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.exam.client.GreetingService;
import com.exam.client.dto.DeviceDTO;
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
	
	public ArrayList<DeviceDTO> getDeviceData(String input) throws IllegalArgumentException {
		
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Session session=HibernateUtil.getSessionFactory().openSession();
		Criteria cr = session.createCriteria(DeviceData.class);
		cr.add(Restrictions.eq("part2Id", Integer.parseInt(input)));
		List results = cr.list();	
		session.close();
		JSONObject jObject = new JSONObject();
		JSONArray jArray = new JSONArray();
		ArrayList<DeviceDTO> deviceList = new ArrayList<DeviceDTO>();
		
		for (Iterator iterator = results.iterator(); iterator.hasNext();)
		{
			DeviceData deviceData = (DeviceData) iterator.next();
			DeviceDTO deviceDTO = new DeviceDTO();
			deviceDTO.setPart2Id(deviceData.getPart2Id());
			deviceDTO.setIdCompany(deviceData.getIdCompany());
			if(deviceData.getAddress1()!=null)
			deviceDTO.setAddress1(deviceData.getAddress1());
			if(deviceData.getData1()!=null)
			deviceDTO.setData1(deviceData.getData1());
			if(deviceData.getTime1()!=null)
			deviceDTO.setTime1(formatter.format(deviceData.getTime1()));
			if(deviceData.getTime2()!=null)
			deviceDTO.setTime2(formatter.format(deviceData.getTime2()));
			deviceList.add(deviceDTO);
		         JSONObject deviceJson = new JSONObject();
		         deviceJson.put("idsession", deviceData.getIdSession());
		         deviceJson.put("idcompany", deviceData.getIdCompany());
		         deviceJson.put("idservice", deviceData.getIdService());
		         deviceJson.put("status", deviceData.getStatus());
//		         deviceJson.put("part1id", deviceData.getPart1Id());
//		         deviceJson.put("part2id", deviceData.getPart2Id());
//		         deviceJson.put("time1", deviceData.getTime1());
//		         deviceJson.put("time1b", deviceData.getTime1B());
//		         deviceJson.put("time2", deviceData.getTime2());
//		         deviceJson.put("time2b", deviceData.getTime2B());
//		         deviceJson.put("type1id", deviceData.getType1Id());
//		         deviceJson.put("type2name", deviceData.getType2Name());
//		         deviceJson.put("type2id", deviceData.getType2Id());
		         deviceJson.put("address1", deviceData.getAddress1());
		         deviceJson.put("location1", deviceData.getLocation1());
		         deviceJson.put("notes", deviceData.getNotes());
		         deviceJson.put("data1", deviceData.getData1());
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

//		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>Device data found:<br>"
//				+ deviceJson;
		return deviceList;
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

	@Override
	public String exportToPdf(ArrayList<DeviceDTO> input) {
		new ConvertPDF(input);
		
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		
		String saveInfo="File is saved at location C:/Users/ADMIN/Desktop/UserReport.pdf";

		// Escape data from the client to avoid cross-site script vulnerabilities.
		 saveInfo = escapeHtml(saveInfo);

		return "Hello, " + input.get(0).getPart2Id() + "!<br><br>I am running " + serverInfo + ".<br><br><br>"
				+ saveInfo;
	}
}
