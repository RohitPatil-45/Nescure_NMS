package npm.report.dao;

import java.util.List;

import org.json.JSONArray;

import npm.model.TopTalkerModel;

public interface TopTalkerReportDao {
	

	JSONArray topTalkerIpWise(String from_date, String to_date, String ipWiseClick,String userScopeData);

	JSONArray topTalkerSourceWise(String from_date, String to_date, String sourceWiseClick,String userScopeData);

	JSONArray topTalkerProtocolWise(String from_date, String to_date, String protocolWiseClick,String userScopeData);

	JSONArray topTalkerReport(String from_date, String to_date,String userScopeData);

}
