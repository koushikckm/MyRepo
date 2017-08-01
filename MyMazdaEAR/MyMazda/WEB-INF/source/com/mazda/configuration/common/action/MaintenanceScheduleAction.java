package com.mazda.configuration.common.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.StrutsStatics;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mazda.common.utility.Utility;
import com.mazda.common.utility.mail.GetJsonObject;
import com.mazda.configuration.common.transferobject.MaintenanceScheduleTO;
import com.mazda.configuration.common.transferobject.RegistrationTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MaintenanceScheduleAction extends ActionSupport implements ModelDriven<MaintenanceScheduleTO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MaintenanceScheduleTO maintenanceScheduleTO;
	List<MaintenanceScheduleTO> scheduleTOs = new ArrayList<MaintenanceScheduleTO>();
	final ActionContext actionContext 	= ActionContext.getContext();
	private HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
	RegistrationTO registrationTO = new RegistrationTO();
	List<String>  maintenanceNotes = new ArrayList<String>();
	List<RegistrationTO> loginVehicalList=new ArrayList<RegistrationTO>();
	List<Integer> yearList = new ArrayList<Integer>();
	
	public MaintenanceScheduleTO getModel() {
		maintenanceScheduleTO = new MaintenanceScheduleTO();
		return maintenanceScheduleTO;
	}
	
	public String execute() {
		//callWebService();
	return SUCCESS;
}
	public String callWebService(){
		try {
			@SuppressWarnings("unchecked")
			List<RegistrationTO> loginUser = (List<RegistrationTO>) request.getSession().getAttribute("loginUser");
				 String url      	= "" ;
				if(loginUser !=null && loginUser.size() >0){
					 RegistrationTO selectedVehical = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
					 List<RegistrationTO> vehicalsList = (List<RegistrationTO>) request.getSession().getAttribute("vehicalsList");
						if(vehicalsList==null){
							return NONE;
						}
					 // checking if driving condition for null and blank
					 if(selectedVehical.getDrivingCondition()!=null && !selectedVehical.getDrivingCondition().equals("") && !selectedVehical.getDrivingCondition().equals(" ")){
						  url = request.getSession().getAttribute("base_env_url")+"maintenanceschedule/vehicle/"+selectedVehical.getVin()+"/drivinghabit/"+selectedVehical.getDrivingCondition();
					 }else{					 
						  url = request.getSession().getAttribute("base_env_url")+"maintenanceschedule/vehicle/"+selectedVehical.getVin()+"/drivinghabit/S";
					 }
				 
			    String json = GetJsonObject.getWebServceObj(url,request);
			    JSONObject jsonObj = new JSONObject(json);
			    /**
			     * Convert into json to arraylist
			     */
			    if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("Success")){
			    	List<MaintenanceScheduleTO> itemNameList = new ArrayList<MaintenanceScheduleTO>();
			    	
			    	JSONObject jsonMaintenanceInterObj = jsonObj.getJSONObject("maintenanceschedule");
			    	JSONArray  jsonIntervalItemArray = jsonMaintenanceInterObj.getJSONArray("listOfIntervalItem");
			    	int noOfIntervals = jsonIntervalItemArray.length();
			    	int intervalDef = jsonIntervalItemArray.getJSONObject(0).getInt("interval");
			    	request.setAttribute("intervalDef", intervalDef);
			    	for(int i =0; i<noOfIntervals;i++){
			    		
			    		MaintenanceScheduleTO maintenanceInterObj = new MaintenanceScheduleTO();
			    		
			    		JSONArray jsonMaintenanceNonInterObj = jsonObj.getJSONArray("maintenancenoninterval");
				    	for(int x=0; x<jsonMaintenanceNonInterObj.length(); x++){
				    		JSONObject nonIntervalObj = jsonMaintenanceNonInterObj.getJSONObject(x);
				    		MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
				    		maintenanceObj.setItemDescription(nonIntervalObj.getString("itemDescription"));
				    		maintenanceObj.setNonIntervalDescription(nonIntervalObj.getString("nonIntervalDescription"));
				    		if(nonIntervalObj.has("itemFootNote")){
				    			maintenanceObj.setItemFootNote(nonIntervalObj.getString("itemFootNote"));
				    		}
				    		itemNameList.add(maintenanceObj);
				    	}
				    	maintenanceInterObj.setItemFootNoteList(itemNameList);
				    	itemNameList = new ArrayList<MaintenanceScheduleTO>();
			    		
			    		
			    		JSONObject jsonIntervalItemObj = jsonIntervalItemArray.getJSONObject(i);
			    		int interval = jsonIntervalItemObj.getInt("interval");
			    		maintenanceInterObj.setInterval(interval);
			    		if(jsonIntervalItemObj.has("listOfReplaceElement")){
			    		JSONArray jsonReplacementItemArray = jsonIntervalItemObj.getJSONArray("listOfReplaceElement");
			    		for(int j=0; j<jsonReplacementItemArray.length(); j++){
			    			JSONObject jsonRepItemObj = jsonReplacementItemArray.getJSONObject(j);
			    			MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
			    			maintenanceObj.setItemName(jsonRepItemObj.getString("itemName"));
			    			itemNameList.add(maintenanceObj);
			    		}
			    		maintenanceInterObj.setReplaceItemList(itemNameList);
			    		itemNameList = new ArrayList<MaintenanceScheduleTO>();
			    		}
			    		if(jsonIntervalItemObj.has("listOfLubricateElement")){
			    		JSONArray jsonLubEleArray = jsonIntervalItemObj.getJSONArray("listOfLubricateElement");
			    		for(int j=0; j<jsonLubEleArray.length(); j++){
			    			JSONObject jsonRepLubObj = jsonLubEleArray.getJSONObject(j);
			    			MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
			    			maintenanceObj.setItemName(jsonRepLubObj.getString("itemName"));
			    			itemNameList.add(maintenanceObj);
			    		}
			    		maintenanceInterObj.setLubrItemList(itemNameList);
			    		itemNameList = new ArrayList<MaintenanceScheduleTO>();
			    		}
			    		if(jsonIntervalItemObj.has("listOfInspectElement")){
			    			JSONArray jsonInspEleArray = jsonIntervalItemObj.getJSONArray("listOfInspectElement");
				    		for(int j=0; j<jsonInspEleArray.length(); j++){
				    			JSONObject jsonInspEleObj = jsonInspEleArray.getJSONObject(j);
				    			MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
				    			maintenanceObj.setItemName(jsonInspEleObj.getString("itemName"));
				    			itemNameList.add(maintenanceObj);
				    		}
				    		maintenanceInterObj.setInspecItemList(itemNameList);
				    		itemNameList = new ArrayList<MaintenanceScheduleTO>();
			    		}
			    		if(jsonIntervalItemObj.has("listOfCleanElement")){
			    			JSONArray jsonCleanEleArray = jsonIntervalItemObj.getJSONArray("listOfCleanElement");
			    			for(int j=0; j<jsonCleanEleArray.length(); j++){
			    				JSONObject jsonCleanEleObj = jsonCleanEleArray.getJSONObject(j);
			    				MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
				    			maintenanceObj.setItemName(jsonCleanEleObj.getString("itemName"));
				    			itemNameList.add(maintenanceObj);
			    			}
			    			maintenanceInterObj.setInspecItemList(itemNameList);
			    			itemNameList = new ArrayList<MaintenanceScheduleTO>();
			    		}
			    		if(jsonIntervalItemObj.has("listOfTightenElement")){
			    			JSONArray jsonTightenEleArray = jsonIntervalItemObj.getJSONArray("listOfTightenElement");
			    			for(int j=0; j<jsonTightenEleArray.length(); j++){
			    				JSONObject jsonTightenEleObj = jsonTightenEleArray.getJSONObject(j);
			    				MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
				    			maintenanceObj.setItemName(jsonTightenEleObj.getString("itemName"));
				    			itemNameList.add(maintenanceObj);
			    			}
			    			maintenanceInterObj.setTightenItemList(itemNameList);
			    			itemNameList = new ArrayList<MaintenanceScheduleTO>();
			    		}
			    		if(jsonIntervalItemObj.has("maintenanceNotes")){
			    			JSONArray jsonMaintenanceNotesArray = jsonIntervalItemObj.getJSONArray("maintenanceNotes");
			    			for(int j=0; j<jsonMaintenanceNotesArray.length(); j++){
			    				MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
				    			maintenanceObj.setItemFootNote(jsonMaintenanceNotesArray.getString(j));
				    			itemNameList.add(maintenanceObj);
			    			}
			    			maintenanceInterObj.setMaintenanceNoteList(itemNameList);
			    			itemNameList = new ArrayList<MaintenanceScheduleTO>();
			    		}
			    		 scheduleTOs.add(maintenanceInterObj);
			    	}
			    	JSONArray  jsonMaintenanceNotesArray = jsonMaintenanceInterObj.getJSONArray("maintenanceNotes");
			    	
			    	for(int k=0;k<jsonMaintenanceNotesArray.length();k++){
			    		String notes =  jsonMaintenanceNotesArray.getString(k);
			    		maintenanceNotes.add(notes);
			    	}
			    	
				 }
			}
		  } catch (Exception  e) {
			e.printStackTrace();
		  } 
		request.setAttribute("scheduleListSize", scheduleTOs.size());
		return SUCCESS;
	}
	
	
	public String applyToUpdateSelectedVehical(){
		RegistrationAction registrationAction = new RegistrationAction();
		registrationAction.keepUpdateOfSelectedVehical(maintenanceScheduleTO.getVin());
		callWebService();
		return SUCCESS;
	}
	
	
	
	public String maintenanceScheduleTwo(){
		try {
			
			 RegistrationTO selectedVehical = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
			 String scheduleType = maintenanceScheduleTO.getScheduleType();
			 String url = request.getSession().getAttribute("base_env_url")+"maintenanceschedule/vehicle/"+selectedVehical.getVin()+"/drivinghabit/"+scheduleType;
		    String json = GetJsonObject.getWebServceObj(url,request);
		    JSONObject jsonObj = new JSONObject(json);
		    if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("Success")){
		    	List<MaintenanceScheduleTO> itemNameList = new ArrayList<MaintenanceScheduleTO>();
		    	/*JSONArray jsonMaintenanceNonInterObj = jsonObj.getJSONArray("maintenancenoninterval");
		    	for(int x=0; x<jsonMaintenanceNonInterObj.length(); x++){
		    		JSONObject nonIntervalObj = jsonMaintenanceNonInterObj.getJSONObject(x);
		    		MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
		    		maintenanceObj.setItemDescription(nonIntervalObj.getString("itemDescription"));
		    		maintenanceObj.setNonIntervalDescription(nonIntervalObj.getString("nonIntervalDescription"));
		    		if(nonIntervalObj.has("itemFootNote")){
		    			maintenanceObj.setItemFootNote(nonIntervalObj.getString("itemFootNote"));
		    			itemNameList.add(maintenanceObj);
		    		}
		    		itemNameList = new ArrayList<MaintenanceScheduleTO>();
		    	}*/
		    	JSONObject jsonMaintenanceInterObj = jsonObj.getJSONObject("maintenanceschedule");
		    	JSONArray  jsonIntervalItemArray = jsonMaintenanceInterObj.getJSONArray("listOfIntervalItem");
		    	int noOfIntervals = jsonIntervalItemArray.length();
		    	int intervalDef = jsonIntervalItemArray.getJSONObject(0).getInt("interval");
		    	request.setAttribute("intervalDef", intervalDef);
		    	for(int i =0; i<noOfIntervals;i++){
		    		
		    		MaintenanceScheduleTO maintenanceInterObj = new MaintenanceScheduleTO();
		    		
		    		
		    		JSONArray jsonMaintenanceNonInterObj = jsonObj.getJSONArray("maintenancenoninterval");
			    	for(int x=0; x<jsonMaintenanceNonInterObj.length(); x++){
			    		JSONObject nonIntervalObj = jsonMaintenanceNonInterObj.getJSONObject(x);
			    		MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
			    		maintenanceObj.setItemDescription(nonIntervalObj.getString("itemDescription"));
			    		maintenanceObj.setNonIntervalDescription(nonIntervalObj.getString("nonIntervalDescription"));
			    		if(nonIntervalObj.has("itemFootNote")){
			    			maintenanceObj.setItemFootNote(nonIntervalObj.getString("itemFootNote"));
			    		}
			    		itemNameList.add(maintenanceObj);
			    	}
			    	maintenanceInterObj.setItemFootNoteList(itemNameList);
			    	itemNameList = new ArrayList<MaintenanceScheduleTO>();
		    		
			    	JSONObject jsonIntervalItemObj = jsonIntervalItemArray.getJSONObject(i);
		    		int interval = Integer.parseInt(jsonIntervalItemObj.getString("interval"));
		    		maintenanceInterObj.setInterval(interval);
		    	if(jsonIntervalItemObj.has("listOfReplaceElement")){
		    		JSONArray jsonReplacementItemArray = jsonIntervalItemObj.getJSONArray("listOfReplaceElement");
		    		for(int j=0; j<jsonReplacementItemArray.length(); j++){
		    			JSONObject jsonRepItemObj = jsonReplacementItemArray.getJSONObject(j);
		    			MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
		    			maintenanceObj.setItemName(jsonRepItemObj.getString("itemName"));
		    			itemNameList.add(maintenanceObj);
		    		}
		    		maintenanceInterObj.setReplaceItemList(itemNameList);
		    		itemNameList = new ArrayList<MaintenanceScheduleTO>();
		    	}
		    		if(jsonIntervalItemObj.has("listOfLubricateElement")){
		    		JSONArray jsonLubEleArray = jsonIntervalItemObj.getJSONArray("listOfLubricateElement");
		    		for(int j=0; j<jsonLubEleArray.length(); j++){
		    			JSONObject jsonRepLubObj = jsonLubEleArray.getJSONObject(j);
		    			MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
		    			maintenanceObj.setItemName(jsonRepLubObj.getString("itemName"));
		    			itemNameList.add(maintenanceObj);
		    		}
		    		maintenanceInterObj.setLubrItemList(itemNameList);
		    		itemNameList = new ArrayList<MaintenanceScheduleTO>();
		    		}
		    		if(jsonIntervalItemObj.has("listOfInspectElement")){
		    			JSONArray jsonInspEleArray = jsonIntervalItemObj.getJSONArray("listOfInspectElement");
			    		for(int j=0; j<jsonInspEleArray.length(); j++){
			    			JSONObject jsonInspEleObj = jsonInspEleArray.getJSONObject(j);
			    			MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
			    			maintenanceObj.setItemName(jsonInspEleObj.getString("itemName"));
			    			itemNameList.add(maintenanceObj);
			    		}
			    		maintenanceInterObj.setInspecItemList(itemNameList);
			    		itemNameList = new ArrayList<MaintenanceScheduleTO>();
		    		}
		    		if(jsonIntervalItemObj.has("listOfCleanElement")){
		    			JSONArray jsonCleanEleArray = jsonIntervalItemObj.getJSONArray("listOfCleanElement");
		    			for(int j=0; j<jsonCleanEleArray.length(); j++){
		    				JSONObject jsonCleanEleObj = jsonCleanEleArray.getJSONObject(j);
		    				MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
			    			maintenanceObj.setItemName(jsonCleanEleObj.getString("itemName"));
			    			itemNameList.add(maintenanceObj);
		    			}
		    			maintenanceInterObj.setInspecItemList(itemNameList);
		    			itemNameList = new ArrayList<MaintenanceScheduleTO>();
		    		}
		    		if(jsonIntervalItemObj.has("listOfTightenElement")){
		    			JSONArray jsonTightenEleArray = jsonIntervalItemObj.getJSONArray("listOfTightenElement");
		    			for(int j=0; j<jsonTightenEleArray.length(); j++){
		    				JSONObject jsonTightenEleObj = jsonTightenEleArray.getJSONObject(j);
		    				MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
			    			maintenanceObj.setItemName(jsonTightenEleObj.getString("itemName"));
			    			itemNameList.add(maintenanceObj);
		    			}
		    			maintenanceInterObj.setTightenItemList(itemNameList);
		    			itemNameList = new ArrayList<MaintenanceScheduleTO>();
		    		}
		    		if(jsonIntervalItemObj.has("maintenanceNotes")){
		    			JSONArray jsonMaintenanceNotesArray = jsonIntervalItemObj.getJSONArray("maintenanceNotes");
		    			for(int j=0; j<jsonMaintenanceNotesArray.length(); j++){
		    				MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
			    			maintenanceObj.setItemFootNote(jsonMaintenanceNotesArray.getString(j));
			    			itemNameList.add(maintenanceObj);
		    			}
		    			maintenanceInterObj.setMaintenanceNoteList(itemNameList);
		    			itemNameList = new ArrayList<MaintenanceScheduleTO>();
		    		}
		    		 scheduleTOs.add(maintenanceInterObj);
		    	}
		    	JSONArray  jsonMaintenanceNotesArray = jsonMaintenanceInterObj.getJSONArray("maintenanceNotes");
		    	
		    	for(int k=0;k<jsonMaintenanceNotesArray.length();k++){
		    		String notes =  jsonMaintenanceNotesArray.getString(k);
		    		maintenanceNotes.add(notes);
		    	}
		    }
		  } catch (Exception  e) {
			e.printStackTrace();
		  } 
		request.setAttribute("scheduleListSize", scheduleTOs.size());
		return SUCCESS;
	}
	public String maintenanceSchedule(){
		try {
			if(maintenanceScheduleTO.getMileage() >0){
				request.setAttribute("mileage", maintenanceScheduleTO.getMileage());
			String url = request.getSession().getAttribute("base_env_url")+"maintenanceschedule/model/"+maintenanceScheduleTO.getMdlCode()+
					"/year/"+maintenanceScheduleTO.getMdlYear()+"/drivinghabit/"+maintenanceScheduleTO.getScheduleType();
		
			String json = GetJsonObject.getWebServceObj(url,request);
			JSONObject jsonObj = new JSONObject(json);
			/**
			 * Convert into json to arraylist
			 */
			if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("Success")){
			List<MaintenanceScheduleTO> itemNameList = new ArrayList<MaintenanceScheduleTO>();
			/*JSONArray jsonMaintenanceNonInterObj = jsonObj.getJSONArray("maintenancenoninterval");
			for(int x=0; x<jsonMaintenanceNonInterObj.length(); x++){
				JSONObject nonIntervalObj = jsonMaintenanceNonInterObj.getJSONObject(x);
				MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
				maintenanceObj.setItemDescription(nonIntervalObj.getString("itemDescription"));
				maintenanceObj.setNonIntervalDescription(nonIntervalObj.getString("nonIntervalDescription"));
				if(nonIntervalObj.has("itemFootNote")){
					maintenanceObj.setItemFootNote(nonIntervalObj.getString("itemFootNote"));
					itemNameList.add(maintenanceObj);
				}
			}*/
			itemNameList = new ArrayList<MaintenanceScheduleTO>();
			JSONObject jsonMaintenanceInterObj = jsonObj.getJSONObject("maintenanceschedule");
			JSONArray  jsonIntervalItemArray = jsonMaintenanceInterObj.getJSONArray("listOfIntervalItem");
			int noOfIntervals = jsonIntervalItemArray.length();
			int intervalDef = jsonIntervalItemArray.getJSONObject(0).getInt("interval");
	    	request.setAttribute("intervalDef", intervalDef);
			for(int i =0; i<noOfIntervals;i++){
	    		
	    		MaintenanceScheduleTO maintenanceInterObj = new MaintenanceScheduleTO();
	    		
	    		JSONArray jsonMaintenanceNonInterObj = jsonObj.getJSONArray("maintenancenoninterval");
		    	for(int x=0; x<jsonMaintenanceNonInterObj.length(); x++){
		    		JSONObject nonIntervalObj = jsonMaintenanceNonInterObj.getJSONObject(x);
		    		MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
		    		maintenanceObj.setItemDescription(nonIntervalObj.getString("itemDescription"));
		    		maintenanceObj.setNonIntervalDescription(nonIntervalObj.getString("nonIntervalDescription"));
		    		if(nonIntervalObj.has("itemFootNote")){
		    			maintenanceObj.setItemFootNote(nonIntervalObj.getString("itemFootNote"));
		    		}
		    		itemNameList.add(maintenanceObj);
		    	}
		    	maintenanceInterObj.setItemFootNoteList(itemNameList);
		    	itemNameList = new ArrayList<MaintenanceScheduleTO>();
	    		
	    		
	    		JSONObject jsonIntervalItemObj = jsonIntervalItemArray.getJSONObject(i);
	    		int interval = jsonIntervalItemObj.getInt("interval");
	    		maintenanceInterObj.setInterval(interval);
	    		if(jsonIntervalItemObj.has("listOfReplaceElement")){
	    		JSONArray jsonReplacementItemArray = jsonIntervalItemObj.getJSONArray("listOfReplaceElement");
	    		for(int j=0; j<jsonReplacementItemArray.length(); j++){
	    			JSONObject jsonRepItemObj = jsonReplacementItemArray.getJSONObject(j);
	    			MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
	    			maintenanceObj.setItemName(jsonRepItemObj.getString("itemName"));
	    			itemNameList.add(maintenanceObj);
	    		}
	    		maintenanceInterObj.setReplaceItemList(itemNameList);
	    		itemNameList = new ArrayList<MaintenanceScheduleTO>();
	    		}
	    		if(jsonIntervalItemObj.has("listOfLubricateElement")){
	    		JSONArray jsonLubEleArray = jsonIntervalItemObj.getJSONArray("listOfLubricateElement");
	    		for(int j=0; j<jsonLubEleArray.length(); j++){
	    			JSONObject jsonRepLubObj = jsonLubEleArray.getJSONObject(j);
	    			MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
	    			maintenanceObj.setItemName(jsonRepLubObj.getString("itemName"));
	    			itemNameList.add(maintenanceObj);
	    		}
	    		maintenanceInterObj.setLubrItemList(itemNameList);
	    		itemNameList = new ArrayList<MaintenanceScheduleTO>();
	    		}
	    		if(jsonIntervalItemObj.has("listOfInspectElement")){
	    			JSONArray jsonInspEleArray = jsonIntervalItemObj.getJSONArray("listOfInspectElement");
		    		for(int j=0; j<jsonInspEleArray.length(); j++){
		    			JSONObject jsonInspEleObj = jsonInspEleArray.getJSONObject(j);
		    			MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
		    			maintenanceObj.setItemName(jsonInspEleObj.getString("itemName"));
		    			itemNameList.add(maintenanceObj);
		    		}
		    		maintenanceInterObj.setInspecItemList(itemNameList);
		    		itemNameList = new ArrayList<MaintenanceScheduleTO>();
	    		}
	    		if(jsonIntervalItemObj.has("listOfCleanElement")){
	    			JSONArray jsonCleanEleArray = jsonIntervalItemObj.getJSONArray("listOfCleanElement");
	    			for(int j=0; j<jsonCleanEleArray.length(); j++){
	    				JSONObject jsonCleanEleObj = jsonCleanEleArray.getJSONObject(j);
	    				MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
		    			maintenanceObj.setItemName(jsonCleanEleObj.getString("itemName"));
		    			itemNameList.add(maintenanceObj);
	    			}
	    			maintenanceInterObj.setInspecItemList(itemNameList);
	    			itemNameList = new ArrayList<MaintenanceScheduleTO>();
	    		}
	    		if(jsonIntervalItemObj.has("listOfTightenElement")){
	    			JSONArray jsonTightenEleArray = jsonIntervalItemObj.getJSONArray("listOfTightenElement");
	    			for(int j=0; j<jsonTightenEleArray.length(); j++){
	    				JSONObject jsonTightenEleObj = jsonTightenEleArray.getJSONObject(j);
	    				MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
		    			maintenanceObj.setItemName(jsonTightenEleObj.getString("itemName"));
		    			itemNameList.add(maintenanceObj);
	    			}
	    			maintenanceInterObj.setTightenItemList(itemNameList);
	    			itemNameList = new ArrayList<MaintenanceScheduleTO>();
	    		}
	    		if(jsonIntervalItemObj.has("maintenanceNotes")){
	    			JSONArray jsonMaintenanceNotesArray = jsonIntervalItemObj.getJSONArray("maintenanceNotes");
	    			for(int j=0; j<jsonMaintenanceNotesArray.length(); j++){
	    				MaintenanceScheduleTO maintenanceObj = new MaintenanceScheduleTO();
		    			maintenanceObj.setItemFootNote(jsonMaintenanceNotesArray.getString(j));
		    			itemNameList.add(maintenanceObj);
	    			}
	    			maintenanceInterObj.setMaintenanceNoteList(itemNameList);
	    			itemNameList = new ArrayList<MaintenanceScheduleTO>();
	    		}
	    		 scheduleTOs.add(maintenanceInterObj);
	    	}
			JSONArray  jsonMaintenanceNotesArray = jsonMaintenanceInterObj.getJSONArray("maintenanceNotes");
	    	
	    	for(int k=0;k<jsonMaintenanceNotesArray.length();k++){
	    		String notes =  jsonMaintenanceNotesArray.getString(k);
	    		maintenanceNotes.add(notes);
	    	}
			
		}
			else {
				addActionMessage("There is no maintenance schedule for the year and model provided.");
			}
		}
		} catch (Exception  e) {
			e.printStackTrace();
		} 
		request.setAttribute("scheduleListSize", scheduleTOs.size());
		return SUCCESS;
	}
	public List<MaintenanceScheduleTO> getScheduleTOs() {
		return scheduleTOs;
	}
	public void setScheduleTOs(List<MaintenanceScheduleTO> scheduleTOs) {
		this.scheduleTOs = scheduleTOs;
	}

	public MaintenanceScheduleTO getMaintenanceScheduleTO() {
		return maintenanceScheduleTO;
	}

	public void setMaintenanceScheduleTO(MaintenanceScheduleTO maintenanceScheduleTO) {
		this.maintenanceScheduleTO = maintenanceScheduleTO;
	}

	public List<Integer> getYearList() {
		return yearList;
	}

	public void setYearList(List<Integer> yearList) {
		this.yearList = yearList;
	}

	public List<String> getMaintenanceNotes() {
		return maintenanceNotes;
	}

	public void setMaintenanceNotes(List<String> maintenanceNotes) {
		this.maintenanceNotes = maintenanceNotes;
	}

	
}


	
	
	
/*BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
String output;
while ((output = br.readLine()) != null) {
}*/

	
	
	
	
	
	
/*
ApacheHttpClientGet apacheHttpClientGet=new ApacheHttpClientGet();
apacheHttpClientGet.getWebserviceData();
DefaultHttpClient httpClient = new DefaultHttpClient();
HttpGet getRequest = new HttpGet("http://mobilegarage.mazdausa.com/MyMazdaGarage/client/user/0002071547/vehicles");
getRequest.addHeader("accept;, "application/json");
HttpResponse response = httpClient.execute(getRequest);
if (response.getStatusLine().getStatusCode() != 200) {
	throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
	}
String json = EntityUtils.toString(response.getEntity());
JSONObject jsonObj = new JSONObject(json);
httpClient.getConnectionManager().shutdown();*/
	
	
	
	
	



















