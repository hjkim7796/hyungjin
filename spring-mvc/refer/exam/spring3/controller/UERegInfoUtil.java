package exam.spring3.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.minieum.tcp.SortedList;
import com.minieum.tcp.UERegInfo;
 
public final class UERegInfoUtil {
	
	final static String DEFAULT_IP = "127.0.0.1";
	//final static String DEFAULT_IP = "10.0.1.234";
	final static int DEFAULT_PORT = 32008;
			
	final static int INDEX_CLEAR=UERegInfo.INDEX_IMSI_MAC + 1;
	
	final static List<String> linkName = new ArrayList<String>() {
		{
			add("TIME Ordering");
			add("ESN Ordering");
			add("MEID Ordering");
			add("IMSI");
			add("MAC Ordering");
			add("GPS_TIME Ordering");
			add("Remote IP");
			add("IMSI and MAC Ordering");
			add("Clear");
		}
	};
	
	final static List<String> linkHrefName = new ArrayList<String>() {
		{
			add("ueRegInfo_time.html");
			add("ueRegInfo_esn.html");
			add("ueRegInfo_meid.html");
			add("ueRegInfo_imsi.html");
			add("ueRegInfo_mac.html");
			add("ueRegInfo_gpstime.html");
			add("ueRegInfo_rip.html");
			add("ueRegInfo_imsi_mac.html");
			add("ueRegInfo_clear.html");
		}
	};
	
	public static String theadMessage() {
		return
			"<tr>" +
				"<th>Time Stamp (UTC)</th>" +
				"<th>Event Type</th>" +
				"<th>Event Name</th>" +
				"<th>ESN</th>" +
				"<th>MEID</th>" +
				"<th>IMSI</th>" +
				"<th>MAC</th>" +
				"<th>GPS TIME</th>" +
				"<th>REMOTE IP</th>" +
			"</tr>";
	}
	
	public static String trMessages(List<UERegInfo> data) {
		String message = "";

    	for(UERegInfo ueRegInfo : data) {
    		message += String.format(
				"<tr>" +
					"<td>%s</td>" +
					"<td>%s</td>" +
					"<td>%s</td>" +
					"<td>%s</td>" +
					"<td>%s</td>" +
					"<td>%s</td>" +
					"<td>%s</td>" +
					"<td>%s</td>" +
					"<td>%s</td>" +
				"</tr>",
				ueRegInfo.getLocalTimeStr(), ueRegInfo.getIdTypeStr(), "ServiceListener.HandleTcpClient()", ueRegInfo.getIdTypeStr() + ueRegInfo.getEsnStr(), 
				ueRegInfo.getMeidStr(), ueRegInfo.getImsiStr(), ueRegInfo.getMacIdStr(), ueRegInfo.getGpsTimeStr(),ueRegInfo.getRemoteAddressStr());
    	}
    	return message;
	}
	
	public static String linkMessage(int currentIndex) {
		String message = "";
		
		for(int i=0; i< linkName.size(); i++) {
			if (i != 0) {
				message += " | ";
			}
			if(currentIndex == i) {
				message += "<font color=\"red\">" + linkName.get(i) + "</font>";
			} else {
				if(i == INDEX_CLEAR)
					message += "<a href=\"" + linkHrefName.get(i) + "\" onClick=\"return confirm('Are you sure?')\">" + linkName.get(i) + "</a>";
				else
					message += "<a href=\"" + linkHrefName.get(i) + "\">" + linkName.get(i) + "</a>";
			}
		}
			
		return message;
		
	}
	
	public static List<UERegInfo> getUERegInfoList(int ordering) {
		
		String ip=DEFAULT_IP;
		int port=DEFAULT_PORT; 
		Socket socket = null;
		BufferedOutputStream out = null;
		ObjectInputStream in = null;

		List<UERegInfo> result = new ArrayList<UERegInfo>();
		
		try {
			socket = new Socket(ip, port);
			out = new BufferedOutputStream(socket.getOutputStream());

			byte[] type = new byte[1];
			type[0] = UERegInfo.GetList;
			out.write(type, 0, 1);
			out.flush();

			in = new ObjectInputStream(socket.getInputStream());

			final int size = Integer.SIZE / 8;
			byte[] infoSizeByte = new byte[size];
			in.read(infoSizeByte, 0, size);
			
			int infoSizeInt = UERegInfo.byteArrayToInt(infoSizeByte);
			
			System.out.println("UERegInfo Size : " + infoSizeInt);
			for (int i = 0; i < infoSizeInt; i++) {
				UERegInfo ueRegInfo = (UERegInfo) in.readObject();
				ueRegInfo.setOrdering(ordering);
				result.add(ueRegInfo);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try{in.close();}catch(IOException e){}
			if (out != null)
				try{out.close();}catch(IOException e){}
			if (socket != null)
				try{socket.close();}catch(IOException e){}
		}
		
		if(ordering == UERegInfo.INDEX_IMSI_MAC) {
			//Collections.sort(result, new UERegInfo(UERegInfo.INDEX_IMSI));
			
			HashSet<UERegInfo> ueRegInfoSet = new HashSet<UERegInfo>();
			ueRegInfoSet.addAll(result);
			System.out.println("Set size : " + ueRegInfoSet.size());
			result.clear();
			result.addAll(ueRegInfoSet);
			//sort by time
			Collections.sort(result, new UERegInfo(UERegInfo.INDEX_IMSI_MAC));
			return result;
		} else {
			//sort
			Collections.sort(result, new UERegInfo(ordering));
			return result;
		}
		
	}
	
	public static void clear(String ip, int port) {
		Socket socket = null;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			socket = new Socket(ip, port);
			
			out = new BufferedOutputStream(socket.getOutputStream());
			byte[] type = new byte[1];
			type[0] = UERegInfo.Clear;
			out.write(type,0,1);
			out.flush();
			
			in = new BufferedInputStream(socket.getInputStream());
			int ack = in.read();
			System.out.println("ACK:"+ack);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if (in != null)
				try{in.close();}catch(IOException e){}
			if (out != null)
				try{out.close();}catch(IOException e){}
			if (socket != null)
				try{socket.close();}catch(IOException e){}
		}
	}
}


