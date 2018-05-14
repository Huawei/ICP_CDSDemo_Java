package com.huawei.cdsdemo.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tomcat.jni.Local;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.cdsdemo.bean.CDSResponse;
import com.huawei.cdsdemo.bean.Record;
import com.huawei.cdsdemo.bean.RecordData;
import com.huawei.cdsdemo.bean.RecordList;
import com.huawei.cdsdemo.bean.Subscribe;
import com.huawei.cdsdemo.request.Request;
import com.huawei.cdsdemo.session.UserSession;


public class GroupCallService
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    private static final Logger log = LoggerFactory.getLogger(GroupCallService.class);
    
    // getBean时， 忽略多余字段
    static
    {
        OBJECT_MAPPER.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    

    public CDSResponse<Object> recordGet(UserSession userSession, Record record)
    {
    	if (record == null || userSession == null)
    	{
			return null;
		}
    	
        String recordString = "";
        recordString += "pageIndex=" + record.getPageIndex() + "&pageSize=" + record.getPageSize();
        
        if (record.getSearchKey() != null && !record.getSearchKey().isEmpty())
        {
            recordString += "&searchKey=" + record.getSearchKey();
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        if (record.getStartTime() != null && !record.getStartTime().isEmpty())
        {
            record.setStartTime(sdf.format(record.getStartTime()));
            recordString += "&startTime=" + record.getStartTime();
        }
        
        if (record.getEndTime() != null && !record.getEndTime().isEmpty())
        {
            record.setEndTime(sdf.format(record.getEndTime()));
            recordString += "&endTime=" + record.getEndTime();
        }
        
        recordString += "&onlyLocalRecord=" + record.isOnlyLocalRecord();
        recordString += "&isConfRecord=" + record.isConfRecord();
        recordString += "&isMultiKey=true&isExactSearch=true";
        
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/opengw/corp/" + record.getCorpId() + "/recordlist?" + recordString;
        
        Map<String, Object> result = Request.get(userSession.getToken(), url);
        
        if (result != null)
        {
        	try 
        	{
        		RecordList records = new RecordList();
                records.setPageIndex((Integer)result.get("pageIndex"));
                records.setPageSize((Integer)result.get("pageSize"));
                records.setTotalCount((Integer)result.get("totalCount"));
                records.setReturnCode(Integer.parseInt(result.get("returnCode").toString()));
                records.setReturnDesc(result.get("returnDesc").toString());
                List<LinkedHashMap<String, Object>> recordlist = (List<LinkedHashMap<String, Object>>)result.get("recordList");
                List<RecordData> recordlists = new ArrayList<RecordData>();
                
                for (int i = 0; i < recordlist.size(); i++)
                {
                    RecordData recordData = new RecordData();
                    if (record.isConfRecord()) 
                    {
                        setRecorDataofConf(recordlist, i, recordData);
                    }
                    else
                    {
                        setRecorDataofNoConf(recordlist, i, recordData);
                    }
                    
                    Matcher matcher = Pattern.compile("([0-9]+)").matcher(recordData.getUserAccount());
                    String groupNum = Request.match(matcher);
                    CDSResponse<Object> rsps = GroupService.groupGet(userSession, groupNum);
                    if (rsps.getRetcode() == 0)
                    {
                        String groupName = "";
                        String resu = rsps.getData().toString();
                        int begin = resu.indexOf("talkGroupName") + 14;
                        int end = resu.substring(begin).indexOf(",");
                        groupName = resu.substring(begin, begin + end);
                        recordData.setGroupName(groupName);
                    }
                    
                    recordlists.add(recordData);
                }
                
                records.setRecordlist(recordlists);
                
                response.setData(records);
                response.setRetcode(records.getReturnCode());
                response.setRetDesc(records.getReturnDesc());
			} 
        	catch(NumberFormatException e)
        	{
        		 log.info("recordGet failed: " +  e.getMessage());
        	}       
        }
        
        return response;
    }

    private void setRecorDataofConf(List<LinkedHashMap<String, Object>> recordlist, int i, RecordData recordData) 
    {
        Map<String,Object> recordDataMap = recordlist.get(i);
        
        recordData.setRecordID(String.valueOf(recordDataMap.get("recordID")));
        recordData.setConfId(String.valueOf(recordDataMap.get("confId")));
        recordData.setRecordFile(String.valueOf(recordDataMap.get("recordFile")));
        recordData.setRecordFolder(String.valueOf(recordDataMap.get("recordFolder")));
        recordData.setUserAccount(String.valueOf(recordDataMap.get("userAccount")));
        recordData.setConfSubject(String.valueOf(recordDataMap.get("confSubject")));
        
        recordData.setLocalRecord((boolean)recordDataMap.get("localRecord"));
        recordData.setStartTime(String.valueOf(recordDataMap.get("startTime")));
        recordData.setDuration(Integer.valueOf(String.valueOf(recordDataMap.get("duration"))));
    }
    private void setRecorDataofNoConf(List<LinkedHashMap<String, Object>> recordlist, int i, RecordData recordData) 
    {

        Map<String,Object> recordDataMap = recordlist.get(i);
        
        recordData.setCorpIDs(String.valueOf("corpId"));
        recordData.setRecordID(String.valueOf(recordDataMap.get("recordID")));
        recordData.setRecordFile(String.valueOf(recordDataMap.get("recordFile")));
        recordData.setCallerRecordFile(String.valueOf(recordDataMap.get("callerRecordFile")));
        recordData.setCalleeRecordFile(String.valueOf(recordDataMap.get("calleeRecordFile")));
        recordData.setRecordFolder(String.valueOf(recordDataMap.get("recordFolder")));
        recordData.setCaller(String.valueOf(recordDataMap.get("caller")));
        recordData.setCallee(String.valueOf(recordDataMap.get("callee")));
        recordData.setStartTime(String.valueOf(recordDataMap.get("startTime")));
        recordData.setDuration(Integer.valueOf(String.valueOf(recordDataMap.get("duration"))));
        recordData.setLocalRecord((boolean)recordDataMap.get("localRecord"));
    }
    
    public CDSResponse<Object> getOnce(UserSession userSession)
    {
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/getNonce/sc";
        
        Map<String, Object> result = Request.oncePost(userSession.getToken(), url);
        
        if (result != null)
        {
        	try
        	{
                response.setData(result.get("data").toString());
                response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
                response.setRetDesc(result.get("returnDesc").toString());
			} 
        	catch (NumberFormatException e)
        	{
        		log.info("getOnce faile" + e.getMessage());
			}
        }
        
        return response;
    }
    
    
    
    
    public CDSResponse<Object> subscribe(UserSession userSession, String groupNumber, Subscribe sub)
    {
        log.info("subscribe begin!");
        
        if (groupNumber == null || groupNumber.isEmpty())
        {
            return null;
		}
        
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/talkGroup/" + groupNumber + "/subscribe";
        
        Map<String, Object> result = Request.put(userSession.getToken(), url, sub);
            
        if (result != null)
        {
        	try
        	{
                response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
                response.setRetDesc(result.get("returnDesc").toString());
			} 
        	catch (NumberFormatException e)
        	{
        		log.info("subscribe faile!");
        		
			}

        }
        
        log.info("subscribe end!");
        return response;
    }
    
    public CDSResponse<Object> select(UserSession userSession, String groupNumber)
    {
        log.info("select begin!");
        if (groupNumber == null || groupNumber.isEmpty())
        {
        	 return null;
		}
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/talkGroup/" + groupNumber + "/select";
        
        Map<String, Object> result = Request.post(userSession.getToken(), url, null);
            
        if (result != null)
        {
        	try 
        	{
        		 response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
                 response.setRetDesc(result.get("returnDesc").toString());
			}
        	catch (NumberFormatException e)
        	{
				// TODO: handle exception
        		log.info("select faile!");
			}           
        }
        
        log.info("select end!");
        return response;
    }
    
    public CDSResponse<Object> deSelect(UserSession userSession, String groupNumber)
    {
    	log.info("select begin!");
        if (groupNumber == null || groupNumber.isEmpty())
        {
        	 return null;
		}
        
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/talkGroup/" + groupNumber + "/select";
        
        Map<String, Object> result = Request.delete(userSession.getToken(), url);
            
        if (result != null)
        {
        	try 
        	{
                response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
                response.setRetDesc(result.get("returnDesc").toString());
			}
        	catch (NumberFormatException e) 
        	{
				// TODO: handle exception              
                log.info("deSelect faile! ");
			}
        }
               
        log.info("deSelect end! ");
        return response;
    }
    
    public CDSResponse<Object> monitor(UserSession userSession, String groupNumber)
    {
        log.info("monitor begin!");
        if (groupNumber == null || groupNumber.isEmpty())
        {
        	 return null;
		}
        
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/talkGroup/" + groupNumber + "/monitor";
        
        Map<String, Object> result = Request.post(userSession.getToken(), url, null);
            
        if (result != null)
        {
        	try 
        	{
                response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
                response.setRetDesc(result.get("returnDesc").toString());
			} 
        	catch (NumberFormatException e) 
        	{
				// TODO: handle exception
                log.info("monitor faile!");
			}
        }
        log.info("monitor end!"); 
        return response;
    }
    
    public CDSResponse<Object> deMonitor(UserSession userSession, String groupNumber)
    {
        log.info("monitor begin!");
        if (groupNumber == null || groupNumber.isEmpty())
        {
        	 return null;
		}
        
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/talkGroup/" + groupNumber + "/monitor";
        
        Map<String, Object> result = Request.delete(userSession.getToken(), url);
            
        if (result != null)
        {
        	try 
        	{
                response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
                response.setRetDesc(result.get("returnDesc").toString());
			}
        	catch (NumberFormatException e) 
        	{
                log.info("deMonitor faile!");
			}

        }
        
        log.info("deMonitor end!");
        return response;
    }
    
    public CDSResponse<Object> emergencyAnswer(UserSession userSession, String groupNumber)
    {
        log.info("monitor begin!");
        if (groupNumber == null || groupNumber.isEmpty())
        {
        	 return null;
		}
        
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/talkGroup/" + groupNumber + "/emergencyAnswer";
        
        Map<String, Object> result = Request.post(userSession.getToken(), url, null);
            
        if (result != null)
        {
        	try 
        	{
                response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
                response.setRetDesc(result.get("returnDesc").toString());
			} 
        	catch (NumberFormatException e) 
        	{
                log.info("emergencyAnswer faile!");
			}
        }
        
        log.info("emergencyAnswer end!");
        return response;
    }
    
    //紧急呼叫离会
    public CDSResponse<Object> emergencyExit(UserSession userSession, String groupNumber) 
    {
        log.info("emergencyExit begin!");
        if (null == groupNumber||groupNumber.isEmpty())
        {
            return null;
        }
        
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp()+"/CDS/talkGroup/"+groupNumber+"/emergencyExit";
        
        Map<String, Object> result = Request.delete(userSession.getToken(), url);
        
        if (null != result) 
        {
            try 
            {
                response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
                response.setRetDesc(result.get("returnDesc").toString());
            } 
            catch (NumberFormatException e) 
            {
                log.info("emergencyExit fail");
            }
        }
        
        log.info("emergencyExit end");
        return response;
    }
    
    public CDSResponse<Object> ptt(UserSession userSession, String groupNumber)
    {
        log.info("monitor begin!");
        if (groupNumber == null || groupNumber.isEmpty())
        {
        	 return null;
		}
        
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/talkGroup/" + groupNumber + "/ptt";
        
        Map<String, Object> result = Request.post(userSession.getToken(), url, null);
            
        if (result != null)
        {
        	try 
        	{
                response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
                response.setRetDesc(result.get("returnDesc").toString());
			} 
        	catch (NumberFormatException e) 
        	{
                log.info("ptt faile!");
			}
        }
        
        log.info("ptt end! ");
        return response;
    }
    
    public CDSResponse<Object> dePtt(UserSession userSession, String groupNumber)
    {
        log.info("monitor begin!");
        if (groupNumber == null || groupNumber.isEmpty())
        {
        	 return null;
		}
        
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/talkGroup/" + groupNumber + "/ptt";
        
        Map<String, Object> result = Request.delete(userSession.getToken(), url);
            
        if (result != null)
        {
        	try 
        	{
                response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
                response.setRetDesc(result.get("returnDesc").toString());
			} 
        	catch (NumberFormatException e) 
        	{
                log.info("dePtt faile!");
			}
        }
        
        log.info("dePtt end!");
        return response;
    }

    
}
