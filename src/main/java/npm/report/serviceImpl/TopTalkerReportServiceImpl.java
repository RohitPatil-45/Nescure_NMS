package npm.report.serviceImpl;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import npm.model.TopTalkerModel;
import npm.report.dao.TopTalkerReportDao;
import npm.report.service.TopTalkerReportService;

@Service
public class TopTalkerReportServiceImpl implements TopTalkerReportService{

	@Autowired
	TopTalkerReportDao reportDao;
	
	public JSONArray topTalkerIpWise(String from_date, String to_date, String ipWiseClick,String userScopeData) {
		// TODO Auto-generated method stub
		return reportDao.topTalkerIpWise(from_date, to_date, ipWiseClick,userScopeData);
	}

	public JSONArray topTalkerSourceWise(String from_date, String to_date, String sourceWiseClick,String userScopeData) {
		// TODO Auto-generated method stub
		return reportDao.topTalkerSourceWise(from_date, to_date, sourceWiseClick,userScopeData);
	}

	public JSONArray topTalkerProtocolWise(String from_date, String to_date, String protocolWiseClick,String userScopeData) {
		// TODO Auto-generated method stub
		return reportDao.topTalkerProtocolWise(from_date, to_date, protocolWiseClick,userScopeData);
	}

	public JSONArray topTalkerReport(String from_date, String to_date, String userScopeData) {
		// TODO Auto-generated method stub
		return reportDao.topTalkerReport(from_date, to_date,userScopeData);
	}

}
