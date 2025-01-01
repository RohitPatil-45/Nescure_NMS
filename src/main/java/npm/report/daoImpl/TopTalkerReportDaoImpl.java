package npm.report.daoImpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.TemporalType;

import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import npm.configuration.AbstractDao;
import npm.model.AddNodeModel;
import npm.model.MailTemplate;
import npm.model.TOP_TALKER_LOG;
import npm.model.TopTalkerModel;
import npm.report.dao.TopTalkerReportDao;

@Repository
@Transactional
public class TopTalkerReportDaoImpl extends AbstractDao<Integer, AddNodeModel> implements TopTalkerReportDao {

	// Top Talker IP Wise Report
	public JSONArray topTalkerIpWise(String from_date, String to_date, String ipWiseClick, String userScopeData) {

		int sr_no = 0;
		HashMap<Integer, String> protocolmap = null;
		protocolmap = new HashMap<Integer, String>();

		protocolmap.put(6, "TCP");
		protocolmap.put(17, "UDP");
		protocolmap.put(1, "ICMP");

		JSONArray jsonArray = null;
		jsonArray = new JSONArray();

		JSONObject jsonObject = null;
		// TODO Auto-generated method stub

		try {

			Query q = getSession().createSQLQuery("select SOURCE_IP,DESTINATION_IP,SOURCE_PORT,DESTINATION_PORT,PROTOCOL,sum((DESTINATION_OCTECT)) as sum from TOP_TALKER_LOG WHERE DATE_TIME BETWEEN  '"+from_date +"' AND '"+ to_date +"' group by SOURCE_IP,DESTINATION_IP,SOURCE_PORT,DESTINATION_PORT, PROTOCOL order by sum desc ");

			List<Object[]> datalist = q.list();
			String protocol = null;
			for (Object[] temp : datalist) {

				JSONArray jsonobj = new JSONArray();

				sr_no++;

				jsonobj.put(sr_no);
				jsonobj.put(temp[0]);
				jsonobj.put(temp[1]);
				jsonobj.put(temp[2]);
				jsonobj.put(temp[3]);
//				jsonobj.put(temp[4]);
				String protobg = temp[4].toString();

				try {
					int port = Integer.parseInt(protobg);
//						int port = Integer.parseInt(protobg);
					protocol = protocolmap.get(port);
					if (protocol == null) {
						protocol = temp[1].toString();
					}
					System.out.println("protocolSummary : " + protocol);
				} catch (Exception e) {
					protocol = temp[1].toString();
					System.out.println("Exception:" + e);
				}

//				jsonobj.put(temp[5]);
				jsonobj.put(protocol);
				String hrSize = null;
				String s = temp[5].toString();

				long size = Long.parseLong(s);
				long b = size;
				long k = size / 1024;
				long m = ((size / 1024) / 1024);
				long g = (((size / 1024) / 1024) / 1024);
				long t = ((((size / 1024) / 1024) / 1024) / 1024);

				DecimalFormat dec = new DecimalFormat("0.00");

				if (t > 1) {
					hrSize = String.valueOf(t).concat(" TB");
				} else if (g > 1) {
					hrSize = String.valueOf(g).concat(" GB");
				} else if (m > 1) {
					hrSize = String.valueOf(m).concat(" MB");
				} else if (k > 1) {
					hrSize = String.valueOf(k).concat(" KB");
				} else {
					hrSize = String.valueOf(b).concat(" Bytes");
				}
				jsonobj.put(hrSize);

				jsonArray.put(jsonobj);
//                System.out.println("finalarray"+jsonArray);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonArray;
	}

	// Top Talker Source Wise Report
	public JSONArray topTalkerSourceWise(String from_date, String to_date, String sourceWiseClick,
			String userScopeData) {
		int sr_no = 0;

		JSONArray jsonArray = null;
		jsonArray = new JSONArray();

		JSONObject jsonObject = null;
		try {

			Query q = getSession().createSQLQuery("select  SOURCE_IP,DESTINATION_IP,sum((DESTINATION_OCTECT)) from TOP_TALKER_LOG  WHERE   DATE_TIME BETWEEN '"
					+ from_date + "' AND '" + to_date + "' group by SOURCE_IP,DESTINATION_IP");
//	            System.out.println("Qury IO log=" + q);

			List<Object[]> datalist = q.list();
			for (Object[] temp : datalist) {

				JSONArray jsonobj = new JSONArray();

				sr_no++;

				jsonobj.put(sr_no);
				jsonobj.put(temp[0]);
				jsonobj.put(temp[1]);
				String hrSize = null;
				String s = temp[2].toString();

				long size = Long.parseLong(s);
				long b = size;
				long k = size / 1024;
				long m = ((size / 1024) / 1024);
				long g = (((size / 1024) / 1024) / 1024);
				long t = ((((size / 1024) / 1024) / 1024) / 1024);

				DecimalFormat dec = new DecimalFormat("0.00");

				if (t > 1) {
					hrSize = String.valueOf(t).concat(" TB");
				} else if (g > 1) {
					hrSize = String.valueOf(g).concat(" GB");
				} else if (m > 1) {
					hrSize = String.valueOf(m).concat(" MB");
				} else if (k > 1) {
					hrSize = String.valueOf(k).concat(" KB");
				} else {
					hrSize = String.valueOf(b).concat(" Bytes");
				}
				jsonobj.put(hrSize);

				jsonArray.put(jsonobj);
//	                System.out.println("finalarray"+jsonArray);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonArray;
	}

	// Top Talker Protocol Wise Report
	public JSONArray topTalkerProtocolWise(String from_date, String to_date, String protocolWiseClick,
			String userScopeData) {
		int sr_no = 0;
		HashMap<Integer, String> protocolmap = null;
		protocolmap = new HashMap<Integer, String>();

		protocolmap.put(6, "TCP");
		protocolmap.put(17, "UDP");
		protocolmap.put(1, "ICMP");
		protocolmap.put(2, "IGMP");

		JSONArray jsonArray = null;
		jsonArray = new JSONArray();

		JSONObject jsonObject = null;
		try {

			Query q = getSession().createSQLQuery("select  sum((DESTINATION_OCTECT)),protocol from TOP_TALKER_LOG where  DATE_TIME BETWEEN '"
					+ from_date + "' AND '" + to_date + "'group by protocol");
//	            System.out.println("Qury IO log=" + q);

			List<Object[]> datalist1 = q.list();
			String protocol = null;
			for (Object[] temp : datalist1) {

				JSONArray jsonobj = new JSONArray();

				sr_no++;

				jsonobj.put(sr_no);
//				jsonobj.put(temp[1]);
//				jsonobj.put(temp[0]);
				String protobg = temp[1].toString();

				try {
					int port = Integer.parseInt(protobg);
//						int port = Integer.parseInt(protobg);
					protocol = protocolmap.get(port);
					if (protocol == null) {
						protocol = temp[1].toString();
					}
					// System.out.println("protocolSummary : " + protocol);
				} catch (Exception e) {
					protocol = temp[1].toString();
					System.out.println("Exception:" + e);
				}

				String hrSize = null;
				String s = temp[0].toString();

				long size = Long.parseLong(s);
				long b = size;
				long k = size / 1024;
				long m = ((size / 1024) / 1024);
				long g = (((size / 1024) / 1024) / 1024);
				long t = ((((size / 1024) / 1024) / 1024) / 1024);

				DecimalFormat dec = new DecimalFormat("0.00");

				if (t > 1) {
					hrSize = String.valueOf(t).concat(" TB");
				} else if (g > 1) {
					hrSize = String.valueOf(g).concat(" GB");
				} else if (m > 1) {
					hrSize = String.valueOf(m).concat(" MB");
				} else if (k > 1) {
					hrSize = String.valueOf(k).concat(" KB");
				} else {
					hrSize = String.valueOf(b).concat(" Bytes");
				}
				jsonobj.put(protocol);
				jsonobj.put(hrSize);
				jsonArray.put(jsonobj);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonArray;
	}

	public JSONArray topTalkerReport(String from_date, String to_date, String userScopeData) {
		JSONArray finalArray = new JSONArray();
		JSONArray jsonArray = null;
		HashMap<Integer, String> protocolmap = null;
		protocolmap = new HashMap<Integer, String>();
		protocolmap.put(6, "TCP");
		protocolmap.put(17, "UDP");
		protocolmap.put(1, "ICMP");
		int sr = 0;
		String protocol = null;
		try {

			Query query = getSession().createQuery("from TOP_TALKER_LOG where DATE_TIME BETWEEN '" + from_date + "' AND '" + to_date + "'");

			List<TOP_TALKER_LOG> dataList = query.list();
			for (TOP_TALKER_LOG data : dataList) {
				sr++;
				jsonArray = new JSONArray();
				jsonArray.put(sr);
				jsonArray.put(data.getDEVICE_IP());
				jsonArray.put(data.getSOURCE_IP());
				jsonArray.put(data.getSOURCE_INTERFACE());
				jsonArray.put(data.getSOURCE_PORT());

				String protobg = data.getPROTOCOL().toString();
				try {
					int port = Integer.parseInt(protobg);
					protocol = protocolmap.get(port);
					if (protocol == null) {
						protocol = data.getPROTOCOL().toString();
					}
				} catch (Exception e) {
					protocol = data.getPROTOCOL().toString();
				}

				jsonArray.put(protocol);
				jsonArray.put(data.getDATE_TIME());
				jsonArray.put(data.getNEXT_HOP());
				jsonArray.put(data.getDESTINATION_IP());
				jsonArray.put(data.getDESTINATION_INTERFACE());
				jsonArray.put(data.getDESTINATION_PORT());
				jsonArray.put(data.getDESTINATION_OCTECT());
				jsonArray.put(data.getDESTINATION_PACKETS());

				finalArray.put(jsonArray);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalArray;
	}

}
