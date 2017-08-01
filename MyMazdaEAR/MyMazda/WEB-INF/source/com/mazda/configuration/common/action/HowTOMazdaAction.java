package com.mazda.configuration.common.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.mazda.common.utility.mail.GetJsonObject;
import com.mazda.configuration.common.transferTO.KeyValueTO;
import com.mazda.configuration.common.transferobject.HowToVehicalTO;
import com.mazda.configuration.common.transferobject.RegistrationTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class HowTOMazdaAction extends ActionSupport implements
		ModelDriven<HowToVehicalTO>, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<HowToVehicalTO> howToVehicalList = new ArrayList<HowToVehicalTO>();
	List<HowToVehicalTO> howToVehicalSubList = new ArrayList<HowToVehicalTO>();
	List<HowToVehicalTO> seatDetailList = new ArrayList<HowToVehicalTO>();
	List<HowToVehicalTO> lookupItems = new ArrayList<HowToVehicalTO>();
	List egiftTabOnSelectVideoDemos = new ArrayList();
	final ActionContext actionContext = ActionContext.getContext();
	private HttpServletRequest request = (HttpServletRequest) actionContext
			.get(StrutsStatics.HTTP_REQUEST);
	HowToVehicalTO howToVehicalTO;
	private HttpServletResponse servletResponse;
	String videoURL;

	public HowToVehicalTO getModel() {
		howToVehicalTO = new HowToVehicalTO();
		return howToVehicalTO;
	}

	public String modelPDFdetails() {
		return SUCCESS;
	}

	public String howTOMazdaNotLogin() {
		request.getSession().setAttribute("MdlCode", null);
		request.getSession().setAttribute("year", 0);
		request.getSession().setAttribute("globalVedioDemos", "globalVedioDemos");

		/*if (null != request.getSession().getAttribute("loginUser")) {
			request.getSession().removeAttribute("loginUser");
		}*/
		return SUCCESS;
	}

	public String execute() {
		try {
			System.out.println("Inside HowTOMazdaAction >>>");

			List<RegistrationTO> vehicalsList = (List<RegistrationTO>) request
					.getSession().getAttribute("vehicalsList");
			if (vehicalsList == null) {
				return NONE;
			}
			RegistrationTO selectedVehicle = (RegistrationTO) request
					.getSession().getAttribute("selectedVehical");
			RegistrationTO loginDetail = (RegistrationTO) request.getSession().getAttribute("loginDetail");
			
			int size = 0;
			request.getSession().setAttribute("egiftTab", 0);
			request.getSession().setAttribute("egiftTabOnRelPage", 0);
			request.getSession().setAttribute("egiftTabOnSelectVeh", 0);
			
			String eGiftUrl = request.getSession().getAttribute("base_env_url")+"user/getUserEgifts/"+loginDetail.getCustomerId()+"/"+selectedVehicle.getVin();
			String jsonEgift = GetJsonObject.getWebServceObj(eGiftUrl,request);
			JSONObject jsonObjEgift = new JSONObject(jsonEgift);
			if(jsonObjEgift.has("status") && jsonObjEgift.getString("status").equalsIgnoreCase("Success")){
				if(jsonObjEgift.getString("count").equalsIgnoreCase("0")) {
					egiftTabOnSelectVideoDemos.add("0");
					size = egiftTabOnSelectVideoDemos.size();
					request.getSession().setAttribute("egiftTabOnVideoDemosChange", size);
				}
				else{
					request.getSession().setAttribute("egiftTabOnVodeoDemosChange", 0);
				}
			}
			egiftTabOnSelectVideoDemos = null;
			
			
			String trim = selectedVehicle.getTrim();
			if (trim == null) {
				trim = "All";
			}

			request.getSession().setAttribute("MdlCode", null);
			request.getSession().setAttribute("year", 0);
			// Properties properties = Utility.getProperties("env.properties");
			/*
			 * String url = request.getSession().getAttribute("base_env_url") +
			 * "howto/model/" + "CX5" + "/year/" + 2014 + "/trim/" + "ALL" +
			 * "/2014q2";
			 */
			String url = request.getSession().getAttribute("base_env_url")
					+ "howto/model/" + selectedVehicle.getCarlineCode()
					+ "/year/" + selectedVehicle.getMdlYear() + "/trim/" + trim
					+ "/2014q2";
			request.getSession().setAttribute("MdlCode",
					selectedVehicle.getCarlineCode());
			request.getSession().setAttribute("year",
					selectedVehicle.getMdlYear());
			String json = GetJsonObject.getWebServceObj(url, request);
			JSONObject jsonObj = new JSONObject(json);
			JSONArray jsonLookUpItemsArray = jsonObj
					.getJSONArray("lookupItems");
			List<HowToVehicalTO> lookUpItemList = new ArrayList<HowToVehicalTO>();
			for (int j = 0; j < jsonLookUpItemsArray.length(); j++) {
				JSONObject jsonLookUpObj = jsonLookUpItemsArray
						.getJSONObject(j);
				JSONArray jsonLookUpFileterArray = jsonLookUpObj
						.getJSONArray("filters");
				for (int k = 0; k < jsonLookUpFileterArray.length(); k++) {
					HowToVehicalTO lookUpItem = new HowToVehicalTO();
					lookUpItem.setName(jsonLookUpFileterArray.getString(k));
					lookUpItem.setTitle(jsonLookUpObj.getString("title"));
					lookUpItem.setContantType(jsonLookUpObj
							.getString("contentType"));
					lookUpItem.setContentId(jsonLookUpObj.getInt("contentId"));
					lookUpItemList.add(lookUpItem);
				}
			}
			request.getSession().setAttribute("lookupItems", lookUpItemList);
			JSONArray jsonFilterArray = jsonObj.getJSONArray("filterObjects");
			for (int i = 0; i < jsonFilterArray.length(); i++) {
				HowToVehicalTO filterObject = new HowToVehicalTO();
				JSONObject jsonHowToObject = jsonFilterArray.getJSONObject(i);
				filterObject.setName(jsonHowToObject.getString("name"));
				filterObject.setId(jsonHowToObject.getInt("id"));
				filterObject.setIconUrl(jsonHowToObject.getString("iconUrl"));
				filterObject
						.setTrackingId(jsonHowToObject.getInt("trackingId"));
				if (jsonHowToObject.has("subItems")) {
					JSONArray jsonSubItemArr1 = jsonHowToObject
							.getJSONArray("subItems");
					List<HowToVehicalTO> SubItemList1 = new ArrayList<HowToVehicalTO>();
					for (int j = 0; j < jsonSubItemArr1.length(); j++) {
						HowToVehicalTO subItem1 = new HowToVehicalTO();
						JSONObject jsonSubItemObj1 = jsonSubItemArr1
								.getJSONObject(j);
						subItem1.setName(jsonSubItemObj1.getString("name"));
						subItem1.setId(jsonSubItemObj1.getInt("id"));
						subItem1.setIconUrl(jsonSubItemObj1
								.getString("iconUrl"));
						subItem1.setTrackingId(jsonSubItemObj1
								.getInt("trackingId"));
						if (jsonSubItemObj1.has("subItems")) {
							JSONArray jsonSubItemArr2 = jsonSubItemObj1
									.getJSONArray("subItems");
							List<HowToVehicalTO> SubItemList2 = new ArrayList<HowToVehicalTO>();
							for (int k = 0; k < jsonSubItemArr2.length(); k++) {
								HowToVehicalTO subItem2 = new HowToVehicalTO();
								JSONObject jsonSubItemObj2 = jsonSubItemArr2
										.getJSONObject(k);
								subItem2.setName(jsonSubItemObj2
										.getString("name"));
								subItem2.setId(jsonSubItemObj2.getInt("id"));
								subItem2.setIconUrl(jsonSubItemObj2
										.getString("iconUrl"));
								subItem2.setTrackingId(jsonSubItemObj2
										.getInt("trackingId"));
								if (jsonSubItemObj2.has("subItems")) {
								}
								getLookUpItems(subItem2, lookUpItemList);
								SubItemList2.add(subItem2);
							}
							subItem1.setSubItemList(SubItemList2);
						}
						getLookUpItems(subItem1, lookUpItemList);
						SubItemList1.add(subItem1);
					}
					filterObject.setSubItemList(SubItemList1);
				}
				howToVehicalList.add(filterObject);
			}
			System.out.println("howToVehicalList::::"+howToVehicalList.size());
			request.getSession().setAttribute("vehicleHowTo", howToVehicalList);

			// request.getSession().setAttribute("howtoFilterList",
			// lookUpItemList);
			request.setAttribute("status", "LOGIN");
			videoList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	public String audio() {
		return SUCCESS;
	}

	public String howToVehicleSubDetail() {
		try {
			/*
			 * List<HowToVehicalTO> vehicleHowToList = (List<HowToVehicalTO>)
			 * request.getSession().getAttribute("vehicleHowTo"); String name =
			 * howToVehicalTO.getName().trim(); for(int i=0;
			 * i<vehicleHowToList.size(); i++){
			 * if(name.equalsIgnoreCase(vehicleHowToList.get(i).getName())){
			 * howToVehicalSubList = vehicleHowToList.get(i).getSubItemList();
			 * if(howToVehicalSubList.size() == 0){ List<HowToVehicalTO>
			 * lookupItemsList = (List<HowToVehicalTO>)
			 * request.getSession().getAttribute("lookupItems"); for(int
			 * j=0;j<lookupItemsList.size();j++){
			 * if(name.equalsIgnoreCase(lookupItemsList.get(j).getName())){
			 * lookupItems.add(lookupItemsList.get(j)); } } } } }
			 */
			List<HowToVehicalTO> lookupItems = (List<HowToVehicalTO>) request
					.getSession().getAttribute("lookupItems");
			/*
			 * for(int i=0;i<vehicleHowToList.size();i++){ HowToVehicalTO
			 * sublist = vehicleHowToList.get(i);
			 * if(sublist.getName().equalsIgnoreCase(howToVehicalTO.getName())){
			 * howToVehicalSubList = vehicleHowToList.get(i).getSubItemList(); }
			 * }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String howToVehicalOption() {
		try {
			System.out.println("howToVehicleOption Method Started");
			if (null != request.getSession().getAttribute("vehicleHowTo")) {
				request.getSession().removeAttribute("vehicleHowTo");
			}
			if (null != request.getSession().getAttribute("subItemList")) {
				request.getSession().removeAttribute("subItemList");
			}
			if (null != request.getSession().getAttribute("lookUplist")) {
				request.getSession().removeAttribute("lookUplist");
			}

			if (null != request.getSession().getAttribute("searchFilterList")) {
				request.getSession().removeAttribute("searchFilterList");
			}

			String trim = howToVehicalTO.getTrim();
			if (trim == null) {
				trim = "All";
			}
			/**
			 * This Session is using in How to search option because there is no
			 * option for selection of these objects
			 */
			request.getSession().setAttribute("MdlCode",
					howToVehicalTO.getMdlCode());
			request.getSession().setAttribute("year", howToVehicalTO.getYear());
			/**
			 * ----------------------------------------------------------------
			 * ---------------
			 */
			String url = null;
			RegistrationTO selectedVehicle = new RegistrationTO();
			System.out.println("year Model URL Hited");
			if (howToVehicalTO != null && howToVehicalTO.getMdlCode() != null) {
				url = request.getSession().getAttribute("base_env_url")
						+ "howto/model/" + howToVehicalTO.getMdlCode()
						+ "/year/" + howToVehicalTO.getYear() + "/trim/" + trim
						+ "/2014q2";
			} else {
				List<RegistrationTO> loginVehicalList = (List<RegistrationTO>) request
						.getSession().getAttribute("vehicalsList");
				if (howToVehicalTO.getVin() != null) {
					int index = howToVehicalTO.getId();
					selectedVehicle = loginVehicalList.get(index);
				} else
					selectedVehicle = (RegistrationTO) request.getSession()
							.getAttribute("selectedVehical");
				url = request.getSession().getAttribute("base_env_url")
						+ "howto/model/" + selectedVehicle.getCarlineCode()
						+ "/year/" + selectedVehicle.getMdlYear() + "/trim/"
						+ trim + "/2014q2";
				request.getSession().setAttribute("MdlCode",
						selectedVehicle.getCarlineCode());
				request.getSession().setAttribute("year",
						selectedVehicle.getMdlYear());
				/* keepUpdateOfSelectedVehical() */
				RegistrationAction registrationAction = new RegistrationAction();
				registrationAction.keepUpdateOfSelectedVehical(selectedVehicle
						.getVin());
			}
			System.out.println("Before SSG URL");

			String ssgResponce = null;
			try {
				// String tempDir =
				// "/mymazda/MyMazda_Content/SSG/XML/"+howToVehicalTO.getYear()+"/"+howToVehicalTO.getMdlCode()+"/DEFAULT/SSG.XML";
				// String tempDir =
				// "\\mymazda\\MyMazda_Content\\SSG\\XML\\2015\\CX5\\DEFAULT\\SSG.XML\\";
				String tempDir = "C:\\mazdaworkspace\\MyMazda\\WebContent\\view\\\\vhelper\\ssgDesktop\\SSG.XML";
				System.out.println("SSG File Location :-- " + tempDir);
				File file = new File(tempDir);
				FileInputStream fis = null;
				fis = new FileInputStream(file);
				// System.out.println(file.length());
				byte[] data = new byte[(int) file.length()];
				System.out.println("Read SSG File Data");
				fis.read(data);
				fis.close();
				ssgResponce = new String(data, "UTF-8");
				System.out.println("Responce Comes");
				request.getSession().setAttribute("ssgResponce", ssgResponce);
				System.out.print("SSG Responce :- "
						+ ssgResponce.replaceAll("\n", ""));

			} catch (Exception e) {
				System.out.println("Exception has caught :" + e);
			}

			String json = GetJsonObject.getWebServceObj(url, request);// cover
																		// images
																		// url
			JSONObject jsonObj = new JSONObject(json);
			String imagePath = jsonObj.getString("imageUrl");
			System.out.println("Cover Image Path" + imagePath);

			/* PostJsonObject.postJson(jsonObj, imagePath); */
			String mainUrl = null;
			try {
				// final URL urlMy = new URL(imagePath);
				HttpURLConnection connection = null;
				String isEnable = (String) request.getSession().getAttribute(
						"prox_set");
				isEnable = "no";
				if (isEnable != null && isEnable.equalsIgnoreCase("yes")) {
					Proxy proxy = new Proxy(
							Proxy.Type.HTTP,
							new InetSocketAddress("proxyarray.mazdausa.com", 80));
					connection = (HttpURLConnection) new URL(imagePath)
							.openConnection(proxy);
				} else {
					connection = (HttpURLConnection) new URL(imagePath)
							.openConnection();
					// **"Proxy disabled..."+url);
				}
				int responseCode = connection.getResponseCode();

				// int responseCode = 404;
				if (responseCode == 200) {
					mainUrl = imagePath;
				}
				System.out
						.println("Cover Image Path Setted for further implementation");
				request.getSession().setAttribute("coverImage", mainUrl);
			} catch (Exception e) {
				// System.err.println(e);
				System.out.println("Cover Image Exception caught" + e);

			}// cover image end

			Long readingssgBefore = new Date().getTime();
			System.out.println("Start parsing/Reading SSG XML Content = "
					+ readingssgBefore);

			JSONArray jsonLookUpItemsArray = jsonObj
					.getJSONArray("lookupItems");
			List<HowToVehicalTO> lookUpItemList = new ArrayList<HowToVehicalTO>();

			for (int j = 0; j < jsonLookUpItemsArray.length(); j++) {
				JSONObject jsonLookUpObj = jsonLookUpItemsArray
						.getJSONObject(j);
				JSONArray jsonLookUpFileterArray = jsonLookUpObj
						.getJSONArray("filters");
				System.out.println("getting data from Filter List");
				for (int k = 0; k < jsonLookUpFileterArray.length(); k++) {
					String trimNames = new String();
					HowToVehicalTO lookUpItem = new HowToVehicalTO();

					if (!jsonLookUpFileterArray.isNull(k)) {
						lookUpItem.setName(jsonLookUpFileterArray.getString(k));
					}

					if (jsonLookUpObj.has("title")) {
						lookUpItem.setTitle(jsonLookUpObj.getString("title"));
						lookUpItem.setContantType(jsonLookUpObj
								.getString("contentType"));
						lookUpItem.setContentId(jsonLookUpObj
								.getInt("contentId"));
						JSONArray trimName = jsonLookUpObj
								.getJSONArray("trimNames");
						for (int q = 0; q < trimName.length(); q++) {
							trimNames = trimNames + ", "
									+ trimName.getString(q);
						}
						if (!trimNames.equals("")) {
							lookUpItem.setTrim(trimNames.substring(1));
						} else {
							lookUpItem.setTrim("All");
						}
					}
					lookUpItemList.add(lookUpItem);
				}
			}
			request.getSession().setAttribute("lookupItems", lookUpItemList);
			System.out.println("LookUp List Completed" + lookUpItemList.size());
			JSONArray jsonFilterArray = jsonObj.getJSONArray("filterObjects");

			request.getSession().setAttribute("jsonFilterArray",
					jsonFilterArray);
			HowToVehicalTO filterObject = null;
			List<HowToVehicalTO> jsonFilterList = new ArrayList<HowToVehicalTO>();
			List<HowToVehicalTO> SubItemList1 = null;
			List<HowToVehicalTO> SubItemList2 = null;

			HowToVehicalTO subItem1 = null;
			HowToVehicalTO subItem2 = null;

			JSONObject jsonSubItemObj2 = null;
			JSONArray jsonSubItemArr2 = null;

			for (int i = 0; i < jsonFilterArray.length(); i++) {
				filterObject = new HowToVehicalTO();
				JSONObject jsonHowToObject = jsonFilterArray.getJSONObject(i);
				filterObject.setName(jsonHowToObject.getString("name"));
				filterObject.setId(jsonHowToObject.getInt("id"));
				filterObject.setIconUrl(jsonHowToObject.getString("iconUrl"));
				filterObject
						.setTrackingId(jsonHowToObject.getInt("trackingId"));

				/* =========checking child node for Index ===== */
				if (jsonHowToObject.getString("name").equalsIgnoreCase(
						"Popular")) {
					// **"i am here ***");
				}

				if (jsonHowToObject.has("subItems")) {
					SubItemList1 = new ArrayList<HowToVehicalTO>();
					JSONArray jsonSubItemArr1 = jsonHowToObject
							.getJSONArray("subItems");

					for (int j = 0; j < jsonSubItemArr1.length(); j++) {

						JSONObject jsonSubItemObj1 = jsonSubItemArr1
								.getJSONObject(j);

						/* =========checking child node for SUB-Index ===== */

						if (jsonSubItemObj1.has("subItems")) {

							jsonSubItemArr2 = jsonSubItemObj1
									.getJSONArray("subItems");

							SubItemList2 = new ArrayList<HowToVehicalTO>();
							for (int k = 0; k < jsonSubItemArr2.length(); k++) {

								jsonSubItemObj2 = jsonSubItemArr2
										.getJSONObject(k);

								// look up for 2nd level
								subItem2 = new HowToVehicalTO();
								subItem2.setName(jsonSubItemObj2
										.getString("name"));
								subItem2.setId(jsonSubItemObj2.getInt("id"));
								subItem2.setIconUrl(jsonSubItemObj2
										.getString("iconUrl"));
								subItem2.setTrackingId(jsonSubItemObj2
										.getInt("trackingId"));
								getLookUpItems(subItem2, lookUpItemList);

								if (subItem2.getLookUplist() != null
										&& subItem2.getLookUplist().size() > 0) {
									SubItemList2.add(subItem2);
								}
								System.out.println("subItem List Created");
							}

							if (SubItemList2 != null && SubItemList2.size() > 0) {
								subItem1 = new HowToVehicalTO();

								subItem1.setName(jsonSubItemObj1
										.getString("name"));
								subItem1.setId(jsonSubItemObj1.getInt("id"));
								subItem1.setIconUrl(jsonSubItemObj1
										.getString("iconUrl"));
								subItem1.setTrackingId(jsonSubItemObj1
										.getInt("trackingId"));
								subItem1.setSubItemList(SubItemList2);
								SubItemList1.add(subItem1);
							}

						} else {
							subItem1 = new HowToVehicalTO();
							subItem1.setName(jsonSubItemObj1.getString("name"));
							subItem1.setId(jsonSubItemObj1.getInt("id"));
							subItem1.setIconUrl(jsonSubItemObj1
									.getString("iconUrl"));
							subItem1.setTrackingId(jsonSubItemObj1
									.getInt("trackingId"));

							getLookUpItems(subItem1, lookUpItemList);

							if (subItem1.getLookUplist() != null
									&& subItem1.getLookUplist().size() > 0) {
								SubItemList1.add(subItem1);
							}
						}
						/* =========checking END child node for SUB-Index ===== */

					}
					if (SubItemList1 != null && SubItemList1.size() > 0) {
						filterObject.setSubItemList(SubItemList1);
					}

				} else {
					getLookUpItems(filterObject, lookUpItemList);
				}

				if (filterObject != null
						&& ((filterObject.getLookUplist() != null && filterObject
								.getLookUplist().size() > 0) || (filterObject
								.getSubItemList() != null && filterObject
								.getSubItemList().size() > 0)))

					jsonFilterList.add(filterObject);
			}
			Long readingssgAfter = new Date().getTime();
			System.out
					.println("Time End After parsing/Reading SSG XML Content = "
							+ readingssgAfter);

			Long diff = readingssgAfter - readingssgBefore;

			System.out
					.println("Total time Difference Captured for Parsing/Reading SSG xml = "
							+ diff);

			request.getSession().setAttribute("vehicleHowTo", jsonFilterList);
			System.out.println("jsonFilterList::::"+jsonFilterList.size());
			//videoList();
			if (howToVehicalTO != null && howToVehicalTO.getMdlCode() != null) {
				if (jsonFilterList.size() <= 0) {
					this.getServletResponse().getWriter().print("NO");
					return null;
				}
			}
		} catch (Exception e) {
			System.err.println(e);
			System.out.println("Exception # 567 : - " + e);
		}
		RegistrationAction registrationAction = new RegistrationAction();
		if (howToVehicalTO.getVin() != null) {
			registrationAction.keepUpdateOfSelectedVehical(howToVehicalTO
					.getVin());
		}
		return SUCCESS;
	}

	public String videoList() {
		String status = (String) request.getAttribute("status");

		try {

			request.getSession().removeAttribute("subItemList");
			request.getSession().removeAttribute("howtoFilterList");
			int index = howToVehicalTO.getIndex();
			@SuppressWarnings("unchecked")
			List<HowToVehicalTO> jsonFilterList = (List<HowToVehicalTO>) request
					.getSession().getAttribute("vehicleHowTo");

			@SuppressWarnings("unchecked")
			List<HowToVehicalTO> lookUplist = (List<HowToVehicalTO>) request
					.getSession().getAttribute("lookupItems");

			/*
			 * jsonFilterList.get( howToVehicalTO.getIndex()).getLookUplist();
			 */

			List<HowToVehicalTO> howtoFilterList = new ArrayList<HowToVehicalTO>();
			HowToVehicalTO vehicalTO = null;
			for (int i = 0; i < lookUplist.size(); i++) {

				vehicalTO = new HowToVehicalTO();
				if (lookUplist.get(i).getContantType() != null) {
					String url = request.getSession().getAttribute(
							"base_env_url")
							+ "howto/type/"
							+ lookUplist.get(i).getContantType().toUpperCase()
							+ "/id/" + lookUplist.get(i).getContentId();
					String json = GetJsonObject.getWebServceObj(url, request);
					JSONObject jsonObj = new JSONObject(json);
					if (jsonObj.has("streamUrl")) {
						String videoURL = jsonObj.getString("streamUrl");
						vehicalTO.setStreemURL(videoURL);
						String videoTitle = jsonObj.getString("title");
						vehicalTO.setTitle(videoTitle);
						vehicalTO.setTrim(lookUplist.get(i).getTrim());
						howtoFilterList.add(vehicalTO);
					}
				}
			}
			request.getSession().setAttribute("howtoFilterList",
					howtoFilterList);

		} catch (Exception e) {
			System.out.println("Second Level Exception :- " + e);
		}

		if ("LOGIN".equalsIgnoreCase(status)) {
			return null;
		} else
			return SUCCESS;

	}

	@SuppressWarnings("unchecked")
	public String secondLevel() {
		try {
			HowToVehicalTO vehicalTO1 = null;
			request.getSession().removeAttribute("subItemList");
			request.getSession().removeAttribute("howtoFilterList");
			int index = howToVehicalTO.getIndex();
			List<HowToVehicalTO> jsonFilterList = (List<HowToVehicalTO>) request
					.getSession().getAttribute("vehicleHowTo");
			HowToVehicalTO howtVehicalTO = jsonFilterList.get(index) != null ? jsonFilterList
					.get(index) : null;

			if (howtVehicalTO != null && howtVehicalTO.getSubItemList() != null
					&& howtVehicalTO.getSubItemList().size() > 0) {
				List<HowToVehicalTO> subItemList = howtVehicalTO
						.getSubItemList();
				request.getSession().setAttribute("subItemList", subItemList);
				request.getSession().setAttribute("resultType", "success");
				return SUCCESS;
			} else {
				List<HowToVehicalTO> lookUplist = jsonFilterList.get(
						howToVehicalTO.getIndex()).getLookUplist();
				if (lookUplist.size() < 0 || lookUplist == null) {
					lookUplist = (List<HowToVehicalTO>) request.getSession()
							.getAttribute("lookupItems");
				}

				List<HowToVehicalTO> howtoFilterList = new ArrayList<HowToVehicalTO>();
				HowToVehicalTO vehicalTO = null;
				for (int i = 0; i < lookUplist.size(); i++) {
					vehicalTO = new HowToVehicalTO();
					if (lookUplist.get(i).getContantType() != null) {
						String url = request.getSession().getAttribute(
								"base_env_url")
								+ "howto/type/"
								+ lookUplist.get(i).getContantType()
										.toUpperCase()
								+ "/id/"
								+ lookUplist.get(i).getContentId();
						String json = GetJsonObject.getWebServceObj(url,
								request);
						JSONObject jsonObj = new JSONObject(json);
						if (jsonObj.has("streamUrl")) {
							String videoURL = jsonObj.getString("streamUrl");
							vehicalTO.setStreemURL(videoURL);
							String videoTitle = jsonObj.getString("title");
							vehicalTO.setTitle(videoTitle);
							vehicalTO.setTrim(lookUplist.get(i).getTrim());
							howtoFilterList.add(vehicalTO);
						}
					}
				}
				request.getSession().setAttribute("howtoFilterList",
						howtoFilterList);
				request.getSession().setAttribute("videoTitle",
						howToVehicalTO.getTitle());
				request.getSession().setAttribute("resultType", "input");
				return INPUT;
			}
			/*
			 * request.getSession().removeAttribute("vehicleHowTo");
			 */
		} catch (Exception e) {
			System.out.println("Second Level Exception :- " + e);
		}
		return null;
	}

	public String thirdLevel() {
		try {
			String selectedOptionTitle = howToVehicalTO.getTitle();
			List<HowToVehicalTO> subItemList = (List<HowToVehicalTO>) request
					.getSession().getAttribute("subItemList");
			List<HowToVehicalTO> lookUplist = subItemList.get(
					howToVehicalTO.getIndex()).getLookUplist();
			if (lookUplist.size() == 0
					&& (subItemList.get(howToVehicalTO.getIndex())
							.getSubItemList().size() > 0)) {
				lookUplist = subItemList.get(howToVehicalTO.getIndex())
						.getSubItemList().get(0).getLookUplist();
			}
			List<HowToVehicalTO> howtoFilterList = new ArrayList<HowToVehicalTO>();
			HowToVehicalTO vehicalTO = null;
			for (int i = 0; i < lookUplist.size(); i++) {
				vehicalTO = new HowToVehicalTO();
				if (lookUplist.get(i).getContantType() != null) {
					String url = request.getSession().getAttribute(
							"base_env_url")
							+ "howto/type/"
							+ lookUplist.get(i).getContantType().toUpperCase()
							+ "/id/" + lookUplist.get(i).getContentId();
					String json = GetJsonObject.getWebServceObj(url, request);
					JSONObject jsonObj = new JSONObject(json);

					if (jsonObj.has("streamUrl")) {
						String videoURL = jsonObj.getString("streamUrl");
						vehicalTO.setStreemURL(videoURL);
						String videoTitle = jsonObj.getString("title");
						vehicalTO.setTitle(videoTitle);
						vehicalTO.setTrim(lookUplist.get(i).getTrim());
						howtoFilterList.add(vehicalTO);
					}
				}
			}
			request.getSession().setAttribute("howtoFilterList",
					howtoFilterList);

			request.getSession().setAttribute("videoTitle",
					howToVehicalTO.getTitle());
		} catch (Exception e) {
			System.out.println("Third Level Exception :- " + e);
		}
		return INPUT;
	}

	public String howtoVideoPlay() {

		try {

			if (howToVehicalTO.getStreemURL() != null) {
				request.getSession().setAttribute("selectedVideoURL",
						howToVehicalTO.getStreemURL());
				request.getSession().setAttribute("videoTitle",
						howToVehicalTO.getTitle());

			} else {
				String model = (String) request.getSession().getAttribute(
						"MdlCode");
				int year = (Integer) request.getSession().getAttribute("year");
				request.getSession().setAttribute("selectedVideoURL", null);

				String image = howToVehicalTO.getStreemImg();

				String title = howToVehicalTO.getTitle();

				String ssgResponce = (String) request.getSession()
						.getAttribute("ssgResponce");

				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				InputSource is = new InputSource(new StringReader(ssgResponce));
				Document doc = db.parse(is);

				String ssgStr = null;

				NodeList nodeList = doc.getElementsByTagName("Section");

				for (int i = 0; i < nodeList.getLength(); i++) {
					Node nNodeSection = nodeList.item(i);

					if (nNodeSection.getNodeType() == Node.ELEMENT_NODE) {
						// Section
						System.out.println("Section node = "
								+ nNodeSection.getNodeName());
						Element catElement = (Element) nNodeSection;

						System.out.println("category = "
								+ catElement.getAttribute("Category"));
						if (catElement.getAttribute("Category")
								.equalsIgnoreCase(title.toLowerCase())) { // category
																			// check

							// NodeList catElementNodes =
							// catElement.getElementsByTagName("*");//nodes list
							// inside category
							NodeList catElementNodes = catElement
									.getChildNodes();

							ssgStr = getSsgStr(catElementNodes, year, model);

							System.out
									.println("Generated ssg html = " + ssgStr);
							break;
						}// category iterat

					}// end element check

				}// section iterat

				// request.getSession().setAttribute("selectedImageURL",
				// ImageList);
				request.getSession().setAttribute("generatedSSGStr", ssgStr);
			}

		} catch (Exception e) {
			System.out.println("How To Play Exception :- " + e);
		}

		return SUCCESS;
	}

	// New method created ti fix SSG issue --------------------------------->
	// @05062015
	String getSsgStr(NodeList catElementNodes, int year, String model) {
		StringBuffer temp = new StringBuffer();
		ArrayList<KeyValueTO> divItems = new ArrayList<KeyValueTO>();

		// Create ssg str
		for (int j = 0; j < catElementNodes.getLength(); j++) {
			Node catElmNode = catElementNodes.item(j);

			if (catElmNode.getNodeType() == Node.ELEMENT_NODE) {

				System.out.println("Cat element node = "
						+ catElmNode.getNodeName());

				// Processing TextContent start
				if (catElmNode.getNodeName().equals("TextContent")) {
					Element eElementTitle = (Element) catElmNode;
					String titleTxt = eElementTitle.getAttribute("Title");
					String txt = eElementTitle.getTextContent();

					System.out.println("Title = " + titleTxt
							+ " | text content = " + txt);

					if (titleTxt.equalsIgnoreCase("Y"))
						temp.append("<h3>").append(txt).append("</h3>")
								.append("<br/>");
					else
						temp.append(txt).append("<br/>");

				}// Processing text end

				temp.append("<br/>");

				try {// Processing ImageContent start

					if (catElmNode.getNodeName().equals("ImageContent")) {
						Element eElementTitle = (Element) catElmNode;
						String imgPath = eElementTitle.getAttribute("Source");
						// String txt = eElementTitle.getTextContent();

						String[] parts = imgPath.split("/");
						String part1 = parts[0];
						String part2 = parts[1];
						String finalUrl = new String();

						String[] imgSpace = part2.split(" ");
						for (int imgs = 0; imgs < imgSpace.length; imgs++) {
							if (imgs == 0) {
								finalUrl = imgSpace[imgs];
							} else {
								finalUrl = finalUrl + "%20" + imgSpace[imgs];
							}
						}

						String ssgImg = "http://test.mymazda.com/MyMazdaWeb/SSG/XML/"
								+ year
								+ "/"
								+ model
								+ "/IMAGES/DESKTOP/img/"
								+ part2; // use for html
						System.out.println("image url = " + ssgImg);
						// Image
						temp.append("<img src=\"")
								.append(ssgImg)
								.append("\" id=\"imageid\" usemap=\"#ssgmap"
										+ j + "\">");
						temp.append("<map name=\"ssgmap" + j + "\">");

						// reading image & hotspot coordinates
						URL imgURL = new URL(
								"http://test.mymazda.com/MyMazdaWeb/SSG/XML/"
										+ year + "/" + model
										+ "/IMAGES/DESKTOP/img/" + finalUrl);

						BufferedImage bimg = ImageIO.read(imgURL);
						int imgWidth = bimg.getWidth();
						int imgHeight = bimg.getHeight();

						System.out
								.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Hotspot processing start >>>>>>>");

						// *********************************PROCESSING
						// HOTSPOT**************************************************

						// NodeList nListHotSpots =
						// eElementTitle.getElementsByTagName("HotSpots");
						NodeList nListHotSpots = eElementTitle.getChildNodes();

						int hotCount = nListHotSpots.getLength();
						System.out.println("Hot count = " + hotCount);

						/****************** HOTSPOTS ************************/

						for (int q = 0; q < nListHotSpots.getLength(); q++) {
							Node nNodeHotSpots = nListHotSpots.item(q);

							if (nNodeHotSpots.getNodeType() == Node.ELEMENT_NODE)
								if (nNodeHotSpots.getNodeName()
										.equalsIgnoreCase("HotSpots")) {
									Element eElementHotSpots = (Element) nNodeHotSpots;
									int hotSpotsRows = Integer
											.parseInt(eElementHotSpots
													.getAttribute("Rows"));
									int hotSpotsColumns = Integer
											.parseInt(eElementHotSpots
													.getAttribute("Columns"));

									System.out
											.println("Rows = " + hotSpotsRows
													+ " | Columns = "
													+ hotSpotsColumns);

									/****************** HOTSPOT ************************/
									NodeList nListHotSpt = eElementHotSpots
											.getChildNodes();
									// NodeList nListHotSpt =
									// eElementHotSpots.getElementsByTagName("HotSpot");

									for (int r = 0; r < nListHotSpt.getLength(); r++) {
										Node nNodeHotSpt = nListHotSpt.item(r);

										if (nNodeHotSpt.getNodeType() == Node.ELEMENT_NODE)
											if (nNodeHotSpt
													.getNodeName()
													.equalsIgnoreCase("HotSpot")) {
												Element eElementHotSpt = (Element) nNodeHotSpt;

												/* ROW*********** */int hotSptRows = Integer
														.parseInt(eElementHotSpt
																.getAttribute("Row"));
												/* Column*********** */int hotSptColumns = Integer
														.parseInt(eElementHotSpt
																.getAttribute("Column"));

												System.out.println("Hotspost "
														+ r + " > Rows = "
														+ hotSptRows
														+ " | Columns = "
														+ hotSptColumns);

												float imgLength = (float) imgWidth
														/ hotSpotsColumns;
												float imgHei = (float) imgHeight
														/ hotSpotsRows;

												float x1 = (hotSptColumns - 1)
														* imgLength;
												float y1 = (hotSptRows - 1)
														* imgHei;
												float x2 = x1 + imgLength;
												float y2 = y1 + imgHei;

												System.out.println("x1=" + x1
														+ " | y1=" + y1
														+ " | x2=" + x2
														+ " | y2=" + y2);

												// NodeList textContent =
												// eElementHotSpt.getElementsByTagName("TextContent");
												NodeList textContent = eElementHotSpt
														.getChildNodes();

												StringBuffer temp2 = new StringBuffer();

												// if(textContent.getLength() >
												// 0 ){
												for (int tc = 0; tc < textContent
														.getLength(); tc++) {
													Node nNodetextContent = textContent
															.item(tc);

													if (nNodetextContent
															.getNodeType() == Node.ELEMENT_NODE) {
														// get text content
														if (nNodetextContent
																.getNodeName()
																.equals("TextContent")) {
															Element eElementContent = (Element) nNodetextContent;
															String titleTxt = eElementContent
																	.getAttribute("Title");
															String txt = eElementContent
																	.getTextContent()
																	.replaceAll(
																			"\"",
																			"&#34;")
																	.replaceAll(
																			"'",
																			"&#39;");

															if (titleTxt
																	.equalsIgnoreCase("Y"))
																temp2.append(
																		"<b>")
																		.append(txt)
																		.append("</b>")
																		.append("<br/>");
															else
																temp2.append(
																		txt)
																		.append("<br/>");
														}

														temp2.append("<br/>");

														// get img content
														if (nNodetextContent
																.getNodeName()
																.equals("ImageContent")) // 2nd
																							// label
																							// image
																							// content
														{
															System.out
																	.println("Hotspot node image");
															Element eElementContent = (Element) nNodetextContent;
															String hotspotImgPath = eElementContent
																	.getAttribute("Source");
															String ssgImg2 = "http://test.mymazda.com/MyMazdaWeb/SSG/XML/"
																	+ year
																	+ "/"
																	+ model
																	+ "/IMAGES/DESKTOP/"
																	+ hotspotImgPath
																			.replaceAll(
																					" ",
																					"%20"); // use
																							// for
																							// html
															System.out
																	.println("Hotspot image url = "
																			+ ssgImg2);
															// Image
															// temp2.append("<img src=").append(ssgImg2).append(">").append("<br/>");

															// ---------Processing
															// hotspot of
															// embedded
															// image-------------

															// Image
															temp2.append(
																	"<img src=")
																	.append(ssgImg2)
																	.append(" id=imageid usemap=#ssgmap"
																			+ (50 + tc)
																			+ ">");
															temp2.append("<map name=ssgmap"
																	+ (50 + tc)
																	+ ">");

															// reading image &
															// hotspot
															// coordinates
															URL imgURL2 = new URL(
																	ssgImg2);

															BufferedImage bimg2 = ImageIO
																	.read(imgURL2);
															int imgWidth2 = bimg2
																	.getWidth();
															int imgHeight2 = bimg2
																	.getHeight();

															// *************PROCESSING
															// HOTSPOTS***********

															// NodeList
															// innerHotspots =
															// eElementContent.getElementsByTagName("HotSpots");
															NodeList innerHotspots = eElementContent
																	.getChildNodes();

															/****************** HOTSPOTS ******************/

															for (int q2 = 0; q2 < innerHotspots
																	.getLength(); q2++) {
																Node inHotSpot = innerHotspots
																		.item(q2);

																if (inHotSpot
																		.getNodeType() == Node.ELEMENT_NODE)
																	if (inHotSpot
																			.getNodeName()
																			.equalsIgnoreCase(
																					"HotSpots")) {
																		Element inHorSpotElement = (Element) inHotSpot;
																		int hotSpotsRows2 = Integer
																				.parseInt(inHorSpotElement
																						.getAttribute("Rows"));
																		int hotSpotsColumns2 = Integer
																				.parseInt(inHorSpotElement
																						.getAttribute("Columns"));

																		System.out
																				.println("222>>>>>Rows = "
																						+ hotSpotsRows2
																						+ " | Columns = "
																						+ hotSpotsColumns2);

																		/****************** HOTSPOT ************************/
																		// NodeList
																		// hotSpotList
																		// =
																		// inHorSpotElement.getElementsByTagName("HotSpot");
																		NodeList hotSpotList = inHorSpotElement
																				.getChildNodes();

																		for (int r2 = 0; r2 < hotSpotList
																				.getLength(); r2++) {
																			Node hotSpot = hotSpotList
																					.item(r2);

																			if (hotSpot
																					.getNodeType() == Node.ELEMENT_NODE)
																				if (hotSpot
																						.getNodeName()
																						.equalsIgnoreCase(
																								"HotSpot")) {
																					Element elmHotSpot = (Element) hotSpot;
																					int hotSptRows2 = Integer
																							.parseInt(elmHotSpot
																									.getAttribute("Row"));
																					int hotSptColumns2 = Integer
																							.parseInt(elmHotSpot
																									.getAttribute("Column"));

																					System.out
																							.println("Hotspost2 "
																									+ r2
																									+ " > Rows = "
																									+ hotSptRows2
																									+ " | Columns = "
																									+ hotSptColumns2);

																					float imgLength2 = (float) imgWidth2
																							/ hotSpotsColumns2;
																					float imgHei2 = (float) imgHeight2
																							/ hotSpotsRows2;

																					float x12 = (hotSptColumns2 - 1)
																							* imgLength2;
																					float y12 = (hotSptRows2 - 1)
																							* imgHei2;
																					float x22 = x12
																							+ imgLength2;
																					float y22 = y12
																							+ imgHei2;

																					System.out
																							.println("x12="
																									+ x12
																									+ " | y12="
																									+ y12
																									+ " | x22="
																									+ x22
																									+ " | y22="
																									+ y22);

																					// NodeList
																					// textContent2
																					// =
																					// elmHotSpot.getElementsByTagName("TextContent");
																					NodeList textContent2 = hotSpot
																							.getChildNodes();

																					StringBuffer temp22 = new StringBuffer();

																					for (int tc2 = 0; tc2 < textContent2
																							.getLength(); tc2++) {
																						Node nNodetextContent2 = textContent2
																								.item(tc2);

																						if (nNodetextContent2
																								.getNodeType() == Node.ELEMENT_NODE) {
																							// get
																							// text
																							// content
																							if (nNodetextContent2
																									.getNodeName()
																									.equals("TextContent")) {
																								Element eElementContent2 = (Element) nNodetextContent2;
																								String titleTxt = eElementContent2
																										.getAttribute("Title");
																								String txt = eElementContent2
																										.getTextContent()
																										.replaceAll(
																												"\"",
																												"&#34;")
																										.replaceAll(
																												"'",
																												"&#39;");

																								if (titleTxt
																										.equalsIgnoreCase("Y"))
																									temp22.append(
																											"<b>")
																											.append(txt)
																											.append("</b>")
																											.append("<br/>");
																								else
																									temp22.append(
																											txt)
																											.append("<br/>");
																							}

																							temp22.append("<br/>");

																							// get
																							// img
																							// content
																							if (nNodetextContent2
																									.getNodeName()
																									.equals("ImageContent")) // 2nd
																																// label
																																// image
																																// content
																							{
																								System.out
																										.println("Hotspot node image");
																								Element eElementContent2 = (Element) nNodetextContent2;
																								String hotspotImgPath2 = eElementContent2
																										.getAttribute("Source");
																								String ssgImg22 = "http://test.mymazda.com/MyMazdaWeb/SSG/XML/"
																										+ year
																										+ "/"
																										+ model
																										+ "/IMAGES/DESKTOP/"
																										+ hotspotImgPath2
																												.replaceAll(
																														" ",
																														"%20"); // use
																																// for
																																// html
																								System.out
																										.println("Hotspot image url 2 = "
																												+ ssgImg22);
																								// Image
																								temp22.append(
																										"<img src=")
																										.append(ssgImg22)
																										.append(">");
																							}

																						}// is
																							// element
																							// node
																							// ??
																					}

																					// Generating
																					// div
																					// ids
																					int divId = 50
																							+ tc
																							+ q2
																							+ r2
																							+ (int) (x12
																									+ x22
																									+ y12 + y22);
																					divItems.add(new KeyValueTO(
																							""
																									+ divId,
																							temp22.toString()));

																					// temp2.append("<area shape=rect style=outline: none; coords=").append(x12).append(",").append(y12).append(",").append(x22).append(",").append(y22).append(" href=javaScript:; onclick=return showSSGMsg2(&quot").append(temp22).append("&quot)>");
																					temp2.append(
																							"<area shape=rect style=outline: none; coords=")
																							.append(x12)
																							.append(",")
																							.append(y12)
																							.append(",")
																							.append(x22)
																							.append(",")
																							.append(y22)
																							.append(" href=# onclick=showSSGMsg2(")
																							.append(divId)
																							.append(")>");

																				}
																		}

																	}
															}
															temp2.append("</map><br/>");
															// ----------------------------------------------------------

														} // 2nd label image
															// content end
													}
												}
												System.out
														.println("Hotspot text temp2 = "
																+ temp2);

												// Image MAP
												temp.append(
														"<area shape=\"rect\" style=\"outline: none;\" coords=\"")
														.append(x1)
														.append(",")
														.append(y1)
														.append(",")
														.append(x2)
														.append(",")
														.append(y2)
														.append("\" href=\"javaScript:;\" onclick=\"return showSSGMsg('")
														.append(temp2)
														.append("')\">");
											}
									}

									temp.append("</map><br/>");
								}
						}

						// *****************************************************************************************
						System.out
								.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Hotspot processing end >\n"
										+ temp);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// Processing ImageContent end
			}

		}// cat element nodes iterate

		request.setAttribute("divItems", divItems);

		return temp.toString();
	}

	static String getSsgStr_x(NodeList catElementNodes, int year, String model) {
		StringBuffer temp = new StringBuffer();

		// Create ssg str
		for (int j = 0; j < catElementNodes.getLength(); j++) {
			Node catElmNode = catElementNodes.item(j);

			if (catElmNode.getNodeType() == Node.ELEMENT_NODE) {

				System.out.println("Cat element node = "
						+ catElmNode.getNodeName());

				// Processing TextContent start
				if (catElmNode.getNodeName().equals("TextContent")) {
					Element eElementTitle = (Element) catElmNode;
					String titleTxt = eElementTitle.getAttribute("Title");
					String txt = eElementTitle.getTextContent();

					System.out.println("Title = " + titleTxt
							+ " | text content = " + txt);

					if (titleTxt.equalsIgnoreCase("Y"))
						temp.append("<b>").append(txt).append("</b>")
								.append("<br/>");
					else
						temp.append(txt).append("<br/>");

				}// Processing text end

				temp.append("<br/>");

				try {// Processing ImageContent start

					if (catElmNode.getNodeName().equals("ImageContent")) {
						Element eElementTitle = (Element) catElmNode;
						String imgPath = eElementTitle.getAttribute("Source");
						// String txt = eElementTitle.getTextContent();

						String[] parts = imgPath.split("/");
						String part1 = parts[0];
						String part2 = parts[1];
						String finalUrl = new String();

						String[] imgSpace = part2.split(" ");
						for (int imgs = 0; imgs < imgSpace.length; imgs++) {
							if (imgs == 0) {
								finalUrl = imgSpace[imgs];
							} else {
								finalUrl = finalUrl + "%20" + imgSpace[imgs];
							}
						}

						String ssgImg = "http://test.mymazda.com/MyMazdaWeb/SSG/XML/"
								+ year
								+ "/"
								+ model
								+ "/IMAGES/DESKTOP/img/"
								+ part2; // use for html
						System.out.println("image url = " + ssgImg);
						// Image
						temp.append("<img src=\"")
								.append(ssgImg)
								.append("\" id=\"imageid\" usemap=\"#ssgmap\">");

						// reading image & hotspot coordinates
						URL imgURL = new URL(
								"http://test.mymazda.com/MyMazdaWeb/SSG/XML/"
										+ year + "/" + model
										+ "/IMAGES/DESKTOP/img/" + finalUrl);

						BufferedImage bimg = ImageIO.read(imgURL);
						int imgWidth = bimg.getWidth();
						int imgHeight = bimg.getHeight();

						System.out
								.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Hotspot processing start >>>>>>>");

						// *********************************PROCESSING
						// HOTSPOT**************************************************

						NodeList nListHotSpots = eElementTitle
								.getElementsByTagName("HotSpots");

						int hotCount = nListHotSpots.getLength();
						System.out.println("Hot count = " + hotCount);

						/****************** HOTSPOTS ************************/

						for (int q = 0; q < nListHotSpots.getLength(); q++) {
							Node nNodeHotSpots = nListHotSpots.item(q);
							Element eElementHotSpots = (Element) nNodeHotSpots;
							/* Height*********** */int hotSpotsRows = Integer
									.parseInt(eElementHotSpots
											.getAttribute("Rows"));
							/* Length*********** */int hotSpotsColumns = Integer
									.parseInt(eElementHotSpots
											.getAttribute("Columns"));

							System.out.println("Rows = " + hotSpotsRows
									+ " | Columns = " + hotSpotsColumns);

							/****************** HOTSPOT ************************/
							NodeList nListHotSpt = eElementHotSpots
									.getElementsByTagName("HotSpot");
							// coordsList = new ArrayList<HowToVehicalTO>();
							for (int r = 0; r < nListHotSpt.getLength(); r++) {
								// howToVehicalTOCords = new HowToVehicalTO();
								Node nNodeHotSpt = nListHotSpt.item(r);
								Element eElementHotSpt = (Element) nNodeHotSpt;
								/* ROW*********** */int hotSptRows = Integer
										.parseInt(eElementHotSpt
												.getAttribute("Row"));
								/* Column*********** */int hotSptColumns = Integer
										.parseInt(eElementHotSpt
												.getAttribute("Column"));

								System.out.println("Hotspost " + r
										+ " > Rows = " + hotSptRows
										+ " | Columns = " + hotSptColumns);

								float imgLength = (float) imgWidth
										/ hotSpotsColumns;
								float imgHei = (float) imgHeight / hotSpotsRows;

								float x1 = (hotSptColumns - 1) * imgLength;
								float y1 = (hotSptRows - 1) * imgHei;
								float x2 = x1 + imgLength;
								float y2 = y1 + imgHei;

								System.out.println("x1=" + x1 + " | y1=" + y1
										+ " | x2=" + x2 + " | y2=" + y2);

								NodeList textContent = eElementHotSpt
										.getElementsByTagName("TextContent");

								StringBuffer temp2 = new StringBuffer();

								if (textContent.getLength() > 0) {
									for (int tc = 0; tc < textContent
											.getLength(); tc++) {
										Node nNodetextContent = nListHotSpt
												.item(r);
										Element eElementtextContent = (Element) nNodetextContent;

										// get title attribute of text
										Element titleTxtElement = (Element) eElementtextContent
												.getElementsByTagName(
														"TextContent").item(tc);
										String titleTxt = titleTxtElement
												.getAttribute("Title");

										String txt = eElementtextContent
												.getElementsByTagName(
														"TextContent")
												.item(tc)
												.getTextContent()
												.replaceAll("[^\\x20-\\x7e]",
														"").trim();

										if (titleTxt.equalsIgnoreCase("Y"))
											temp2.append("<b>").append(txt)
													.append("</b>")
													.append("<br/>");
										else
											temp2.append(txt).append("<br/>");

									}
									// howToVehicalTOCords.setTextContent(temp.toString());
									System.out.println("Hotspot text = "
											+ temp2);

								} else {
									NodeList imContent = eElementHotSpt
											.getElementsByTagName("ImageContent");
									Node nNodetextContent = imContent.item(0);
									Element eElementtextContent = (Element) nNodetextContent;
									String txt = eElementtextContent
											.getAttribute("Source");
									System.out
											.println("Other image hotspot >> "
													+ txt);
									// howToVehicalTOCords.setTextContent(txt);
								}

								// coordsList.add(howToVehicalTOCords);
								// Image MAP
								temp.append(
										"<map name=\"ssgmap\"><area shape=\"rect\" style=\"outline: none;\" coords=\"")
										.append(x1)
										.append(",")
										.append(y1)
										.append(",")
										.append(x2)
										.append(",")
										.append(y2)
										.append("\" href=\"javaScript:;\" onclick=\"return showSSGMsg('")
										.append(temp2).append("')\"></map>");
							}
						}
						// ImageList.get(index).setCoordsList(coordsList);

						// *****************************************************************************************
						System.out
								.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Hotspot processing end >\n"
										+ temp);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// Processing ImageContent end
			}

		}// cat element nodes iterate

		return temp.toString();
	}

	public String howtoVideoPlay_bkps() {
		try {
			if (howToVehicalTO.getStreemURL() != null) {
				request.getSession().setAttribute("selectedVideoURL",
						howToVehicalTO.getStreemURL());
			} else {
				String model = (String) request.getSession().getAttribute(
						"MdlCode");
				int year = (Integer) request.getSession().getAttribute("year");
				List<HowToVehicalTO> ImageList = new ArrayList<HowToVehicalTO>();
				request.getSession().setAttribute("selectedVideoURL", null);

				String image = howToVehicalTO.getStreemImg();
				String title = howToVehicalTO.getTitle();

				HowToVehicalTO vehicalTO = null;
				HowToVehicalTO howToVehicalTOCords = null;

				String ssgResponce = (String) request.getSession()
						.getAttribute("ssgResponce");

				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				InputSource is = new InputSource(new StringReader(ssgResponce));
				Document doc = db.parse(is);
				NodeList nList = doc.getElementsByTagName("Section");

				// var section =
				// xmlhttp.responseXML.documentElement.getElementsByTagName("Section");

				String textCont = null;
				for (int j = 0; j < nList.getLength(); j++) { // sections start
					Node nNodeSection = nList.item(j);
					if (nNodeSection.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNodeSection;
						// if(eElement.getAttribute("Category").toLowerCase().contains(title.toLowerCase()))
						if (eElement.getAttribute("Category").toLowerCase()
								.equalsIgnoreCase(title.toLowerCase())) {
							vehicalTO = new HowToVehicalTO();

							/**
							 * Write here for textcontent Text - Start
							 */
							StringBuffer temp = new StringBuffer();

							NodeList nListTextContent = eElement
									.getElementsByTagName("TextContent");

							for (int p = 0; p < nListTextContent.getLength(); p++) {
								Node nNodeTitle = nListTextContent.item(p);
								Element eElementTitle = (Element) nNodeTitle;
								String titleTxt = eElementTitle
										.getAttribute("Title");
								String txt = eElement
										.getElementsByTagName("TextContent")
										.item(p).getTextContent();

								if (titleTxt.equalsIgnoreCase("Y"))
									temp.append("<b>").append(txt)
											.append("</b>").append("<br/>");
								else
									temp.append(txt).append("<br/>");
							}
							vehicalTO.setTextContent(temp.toString());

							// Text Content End

							NodeList nListImageContent = eElement
									.getElementsByTagName("ImageContent");
							int p = 0;
							List<HowToVehicalTO> coordsList = null;
							// int index = 0;
							for (p = 0; p < nListImageContent.getLength(); p++) {
								Node nNodeImg = nListImageContent.item(p);
								eElement = (Element) nNodeImg;
								String imgPath = eElement
										.getAttribute("Source");

								String[] parts = imgPath.split("/");
								String part1 = parts[0];
								String part2 = parts[1];
								String finalUrl = new String();

								String[] imgSpace = part2.split(" ");
								for (int imgs = 0; imgs < imgSpace.length; imgs++) {
									if (imgs == 0) {
										finalUrl = imgSpace[imgs];
									} else {
										finalUrl = finalUrl + "%20"
												+ imgSpace[imgs];
									}
								}

								String ssgImg = "http://test.mymazda.com/MyMazdaWeb/SSG/XML/"
										+ year
										+ "/"
										+ model
										+ "/IMAGES/DESKTOP/img/" + part2;
								vehicalTO.setStreemImg(ssgImg);
								URL imgURL = new URL(
										"http://test.mymazda.com/MyMazdaWeb/SSG/XML/"
												+ year + "/" + model
												+ "/IMAGES/DESKTOP/img/"
												+ finalUrl);

								BufferedImage bimg = ImageIO.read(imgURL);

								int imgWidth = bimg.getWidth();
								int imgHeight = bimg.getHeight();

								// HowToVehicalTO howto = new
								// HowToVehicalTO();//-----------------
								// ImageList.add(howto);
								// howto.setStreemImg(ssgImg);//-------------------

								Node nNodeImgCont = nListImageContent.item(p);

								Element eElementImgCont = (Element) nNodeImgCont;

								NodeList nListHotSpots = eElementImgCont
										.getElementsByTagName("HotSpots");
								int hotCount = nListHotSpots.getLength();
								/****************** HOTSPOTS ************************/
								int q = 0;
								for (q = 0; q < nListHotSpots.getLength(); q++) {
									Node nNodeHotSpots = nListHotSpots.item(q);
									Element eElementHotSpots = (Element) nNodeHotSpots;
									/* Height*********** */int hotSpotsRows = Integer
											.parseInt(eElementHotSpots
													.getAttribute("Rows"));
									/* Length*********** */int hotSpotsColumns = Integer
											.parseInt(eElementHotSpots
													.getAttribute("Columns"));

									/****************** HOTSPOT ************************/
									NodeList nListHotSpt = eElementHotSpots
											.getElementsByTagName("HotSpot");
									/**********************/
									coordsList = new ArrayList<HowToVehicalTO>();
									for (int r = 0; r < nListHotSpt.getLength(); r++) {
										howToVehicalTOCords = new HowToVehicalTO();
										Node nNodeHotSpt = nListHotSpt.item(r);
										Element eElementHotSpt = (Element) nNodeHotSpt;
										/* ROW*********** */int hotSptRows = Integer
												.parseInt(eElementHotSpt
														.getAttribute("Row"));
										/* Column*********** */int hotSptColumns = Integer
												.parseInt(eElementHotSpt
														.getAttribute("Column"));

										float imgLength = (float) imgWidth
												/ hotSpotsColumns;
										float imgHei = (float) imgHeight
												/ hotSpotsRows;

										float x1 = (hotSptColumns - 1)
												* imgLength;
										float y1 = (hotSptRows - 1) * imgHei;
										float x2 = x1 + imgLength;
										float y2 = y1 + imgHei;
										howToVehicalTOCords.setX1(x1);
										howToVehicalTOCords.setY1(y1);
										howToVehicalTOCords.setX2(x2);
										howToVehicalTOCords.setY2(y2);

										NodeList textContent = eElementHotSpt
												.getElementsByTagName("TextContent");

										temp = new StringBuffer();

										if (textContent.getLength() > 0) {
											for (int tc = 0; tc < textContent
													.getLength(); tc++) {
												Node nNodetextContent = nListHotSpt
														.item(r);
												Element eElementtextContent = (Element) nNodetextContent;

												// get title attribute of text
												Element titleTxtElement = (Element) eElementtextContent
														.getElementsByTagName(
																"TextContent")
														.item(tc);
												String titleTxt = titleTxtElement
														.getAttribute("Title");

												String txt = eElementtextContent
														.getElementsByTagName(
																"TextContent")
														.item(tc)
														.getTextContent()
														.replaceAll(
																"[^\\x20-\\x7e]",
																"");

												if (titleTxt
														.equalsIgnoreCase("Y"))
													temp.append("<b>")
															.append(txt)
															.append("</b>")
															.append("<br/>");
												else
													temp.append(txt).append(
															"<br/>");

											}
											howToVehicalTOCords
													.setTextContent(temp
															.toString());

										} else {
											NodeList imContent = eElementHotSpt
													.getElementsByTagName("ImageContent");
											Node nNodetextContent = imContent
													.item(0);
											Element eElementtextContent = (Element) nNodetextContent;
											String txt = eElementtextContent
													.getAttribute("Source");
											howToVehicalTOCords
													.setTextContent(txt);
										}

										coordsList.add(howToVehicalTOCords);
										// p = p+1;

									}
								}
								// ImageList.get(index).setCoordsList(coordsList);
								vehicalTO.setCoordsList(coordsList);
								// index = index+1;
								ImageList.add(vehicalTO);
							}// image node end

							// ImageList.add(vehicalTO);
						}// category end

					}// sections element end
				}// Sections end
				request.getSession()
						.setAttribute("selectedImageURL", ImageList);
			}

		} catch (Exception e) {
			System.out.println("How To Play Exception :- " + e);
		}

		return SUCCESS;
	}

	public String LookUpContent() {
		try {
			List<HowToVehicalTO> howtoFilterList = new ArrayList<HowToVehicalTO>();
			HowToVehicalTO vehicalTO = new HowToVehicalTO();
			// Properties properties = Utility.getProperties("env.properties");
			String url = request.getSession().getAttribute("base_env_url")
					+ "howto/type/"
					+ howToVehicalTO.getContantType().toUpperCase() + "/id/"
					+ howToVehicalTO.getContentId();
			String json = GetJsonObject.getWebServceObj(url, request);
			JSONObject jsonObj = new JSONObject(json);
			if (jsonObj.has("streamUrl")) {
				String videoURL = jsonObj.getString("streamUrl");
				vehicalTO.setStreemURL(videoURL);
				String videoTitle = jsonObj.getString("title");
				vehicalTO.setTitle(videoTitle);
				howtoFilterList.add(vehicalTO);
				request.getSession().setAttribute("howtoFilterList",
						howtoFilterList);
			}
			request.getSession().setAttribute("videoTitle",
					jsonObj.getString("title"));

		} catch (Exception e) {
			System.out.println("LookUp Content Exception :- " + e);
		}
		return SUCCESS;
	}

	public String LookUpContentPopular() {
		try {
			List<HowToVehicalTO> jsonFilterList = (List<HowToVehicalTO>) request
					.getSession().getAttribute("vehicleHowTo");
			/*
			 * List<HowToVehicalTO> subItemList =
			 * jsonFilterList.get(0).getSubItemList();
			 */
			List<HowToVehicalTO> lookItemList = jsonFilterList.get(0)
					.getLookUplist();
			List<HowToVehicalTO> howtoFilterList = new ArrayList<HowToVehicalTO>();
			HowToVehicalTO vehicalTO = null;
			for (int i = 0; i < lookItemList.size(); i++) {
				vehicalTO = new HowToVehicalTO();
				String url = request.getSession().getAttribute("base_env_url")
						+ "howto/type/"
						+ lookItemList.get(i).getContantType().toUpperCase()
						+ "/id/" + lookItemList.get(i).getContentId();
				String json = GetJsonObject.getWebServceObj(url, request);
				JSONObject jsonObj = new JSONObject(json);
				if (jsonObj.has("streamUrl")) {
					String videoURL = jsonObj.getString("streamUrl");
					vehicalTO.setStreemURL(videoURL);
					String videoTitle = jsonObj.getString("title");
					vehicalTO.setTitle(videoTitle);
					howtoFilterList.add(vehicalTO);
				}
			}
			request.getSession().setAttribute("searchFilterList",
					howtoFilterList);
			request.getSession().setAttribute("videoTitle", "Popular");

			/*
			 * videoURL = jsonObj.getString("streamUrl"); String videoTitle =
			 * jsonObj.getString("title");
			 * request.getSession().setAttribute("videoURL", videoURL);
			 * request.getSession().setAttribute("videoTitle", videoTitle);
			 * request.getSession().setAttribute("contentType",
			 * howToVehicalTO.getContantType().toUpperCase());
			 */
		} catch (Exception e) {
			System.out.println("LookUp Popular Exception :- " + e);
		}
		return SUCCESS;
	}

	public String openHowToSearch() {
		return SUCCESS;
	}

	@SuppressWarnings("unused")
	public String findHowToSearch() {
		try {
			request.getSession().removeAttribute("searchFilterList");
			request.getSession().removeAttribute("videoTitle");
			HowToVehicalTO filterObject;
			List<HowToVehicalTO> howtoFilterList = new ArrayList<HowToVehicalTO>();

			String model = (String) request.getSession()
					.getAttribute("MdlCode");
			int year = 0;
			if (request.getSession().getAttribute("year") != null) {
				year = (Integer) request.getSession().getAttribute("year");
			}
			RegistrationTO selectedVehicle = new RegistrationTO();
			String url = null;
System.out.println("howToVehicalTO.getSquery():::"+howToVehicalTO.getSquery());
request.getSession().removeAttribute("VehiclesearchFilterList");
			if (model != null && year != 0) {
				url = request.getSession().getAttribute("base_env_url")
						+ "howto/model/" + model + "/year/" + year + "/squery/"
						+ howToVehicalTO.getSquery() + "/2014q2";
			} else {

				@SuppressWarnings("unchecked")
				List<RegistrationTO> loginVehicalList = (List<RegistrationTO>) request
						.getSession().getAttribute("vehicalsList");
				selectedVehicle = (RegistrationTO) request.getSession()
						.getAttribute("selectedVehical");

				/*
				 * url = request.getSession().getAttribute("base_env_url") +
				 * "howto/model/" + "CX5" + "/year/" + 2014 + "/squery/" +
				 * howToVehicalTO.getSquery() + "/2014q2";
				 */
				url = request.getSession().getAttribute("base_env_url")
						+ "howto/model/" + selectedVehicle.getCarlineCode()
						+ "/year/" + selectedVehicle.getMdlYear() + "/squery/"
						+ howToVehicalTO.getSquery() + "/2014q2";
			}
			String json = GetJsonObject.getWebServceObj(url, request);
			JSONObject jsonObj = new JSONObject(json);
			JSONArray jsonFilterArray = jsonObj.getJSONArray("lookupItems");

			if (jsonFilterArray.length() > 0 && !jsonFilterArray.equals(null)) {
				for (int i = 0; i < jsonFilterArray.length(); i++) {
					String trimNames = new String();
					HowToVehicalTO vehicalTO = new HowToVehicalTO();
					filterObject = new HowToVehicalTO();
					JSONObject jsonHowToObject = jsonFilterArray
							.getJSONObject(i);
					int contentId = jsonHowToObject.getInt("contentId");
					JSONArray trimName = jsonHowToObject
							.getJSONArray("trimNames");
					for (int j = 0; j < trimName.length(); j++) {
						trimNames = trimNames + ", " + trimName.getString(j);
					}
					url = request.getSession().getAttribute("base_env_url")
							+ "howto/type/VIDEO/id/" + contentId;
					String json1 = GetJsonObject.getWebServceObj(url, request);
					jsonObj = new JSONObject(json1);
					if(jsonObj.getString("title").contains(howToVehicalTO.getSquery())){
						System.out.println("Inside::::");
					if (jsonObj.has("streamUrl")) {
						String videoURL = jsonObj.getString("streamUrl");
						vehicalTO.setStreemURL(videoURL);
						vehicalTO.setContentId(contentId);
						String videoTitle = jsonObj.getString("title");
						vehicalTO.setTitle(videoTitle);
						if (!trimNames.equals("")) {
							vehicalTO.setTrim(trimNames.substring(1));
						} else {
							vehicalTO.setTrim("All");
						}
						howtoFilterList.add(vehicalTO);
						/*
						 * if (howtoFilterList.size() > 0) { if
						 * (!isContentIDsame(howtoFilterList, vehicalTO))
						 * howtoFilterList.add(vehicalTO); } else {
						 * howtoFilterList.add(vehicalTO); }
						 */
					}
					request.getSession().setAttribute("VehiclesearchFilterList",
							howtoFilterList);
					}
				}

			}
			request.getSession().setAttribute("videoTitle",
					howToVehicalTO.getSquery());
		} catch (Exception e) {
			System.out.println("Find How To Exception :- " + e);
		}
		return SUCCESS;
	}

	public boolean isContentIDsame(List<HowToVehicalTO> howtoFilterList,
			HowToVehicalTO vehicalTO) {
		for (int i = 0; i < howtoFilterList.size(); i++) {
			if (vehicalTO.isSame(howtoFilterList.get(i).getContentId()))
				return true;
		}
		return false;
	}

	private void getLookUpItems(HowToVehicalTO subItem,
			List<HowToVehicalTO> lookUpItemList) {

		List<HowToVehicalTO> vehicalLookupTOs = new ArrayList<HowToVehicalTO>();
		HowToVehicalTO lookupItem = null;
		String Model = (String) request.getSession().getAttribute("MdlCode");
		int year = (Integer) request.getSession().getAttribute("year");
		try {
			for (int i = 0; i < lookUpItemList.size(); i++) {
				if (lookUpItemList.get(i) != null
						&& lookUpItemList.get(i).getName() != null
						&& lookUpItemList.get(i).getName()
								.equalsIgnoreCase(subItem.getName())) {

					lookupItem = new HowToVehicalTO();
					lookupItem.setTitle(lookUpItemList.get(i).getTitle());
					lookupItem.setContantType(lookUpItemList.get(i)
							.getContantType());
					lookupItem.setContentId(lookUpItemList.get(i)
							.getContentId());
					lookupItem.setTrim(lookUpItemList.get(i).getTrim());
					vehicalLookupTOs.add(lookupItem);
					subItem.setLookUplist(vehicalLookupTOs);
				}
			}
			/**
			 * Checking and adding lookup Content From SSG.XML Data
			 * 
			 * @author Riaz.Ansari
			 */
			String ssgResponce = (String) request.getSession().getAttribute(
					"ssgResponce");
			System.out.println("Parsing Code Executed");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(ssgResponce));
			Document doc = db.parse(is);
			NodeList nList = doc.getElementsByTagName("Section");
			for (int j = 0; j < nList.getLength(); j++) {
				Node nNodeSection = nList.item(j);
				if (nNodeSection.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNodeSection;
					System.out.println("Item Name for search in SSG XML:-"
							+ subItem.getName() + "----"
							+ "Category Name of SSG XML :-"
							+ eElement.getAttribute("Category"));
					if (eElement.getAttribute("Category").equalsIgnoreCase(
							subItem.getName())) {
						lookupItem = new HowToVehicalTO();
						String textCont = eElement
								.getElementsByTagName("TextContent").item(0)
								.getTextContent();
						lookupItem.setTitle(textCont);
						/*
						 * lookupItem.setContantType(lookUpItemList.get(i).
						 * getContantType());
						 * lookupItem.setContentId(lookUpItemList
						 * .get(i).getContentId());
						 */
						lookupItem.setTrim("ALL");
						vehicalLookupTOs.add(lookupItem);
					}
				}
			}
			subItem.setLookUplist(vehicalLookupTOs);
		} catch (Exception e) {
			System.err.println(e);
			System.out.println("Error accured Get lookup Item Exception :- "
					+ e.getMessage());
		}

	}

	public HowToVehicalTO getHowToVehicalTO() {
		return howToVehicalTO;
	}

	public void setHowToVehicalTO(HowToVehicalTO howToVehicalTO) {
		this.howToVehicalTO = howToVehicalTO;
	}

	public List<HowToVehicalTO> getHowToVehicalList() {
		return howToVehicalList;
	}

	public void setHowToVehicalList(List<HowToVehicalTO> howToVehicalList) {
		this.howToVehicalList = howToVehicalList;
	}

	public HttpServletResponse getServletResponse() {
		return servletResponse;
	}

	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	public List<HowToVehicalTO> getSeatDetailList() {
		return seatDetailList;
	}

	public void setSeatDetailList(List<HowToVehicalTO> seatDetailList) {
		this.seatDetailList = seatDetailList;
	}

	/**
	 * @return the videoURL
	 */
	public String getVideoURL() {
		return videoURL;
	}

	/**
	 * @param videoURL
	 *            the videoURL to set
	 */
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

}
