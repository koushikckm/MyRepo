package com.mazda.configuration.common.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import com.mazda.common.utility.PostJsonObject;
import com.mazda.configuration.common.transferobject.RegistrationTO;
import com.mazda.configuration.common.transferobject.UploadImageTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class UploadImageAction extends ActionSupport implements ModelDriven<UploadImageTO>,ServletResponseAware {
	
	private static final long serialVersionUID = 1L;
	UploadImageTO uploadImageTO;
	
	private File myImage;
	private String myFileContentType;
	private String myFileFileName;
	
	
	final ActionContext actionContext 	= ActionContext.getContext();
	private HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST); 
	private HttpServletResponse servletResponse;
	
	List<UploadImageTO> loginUser=null;
	
	public UploadImageTO getModel() {
		uploadImageTO = new UploadImageTO();
		return uploadImageTO;
	}
	
	public String execute() {
		
		@SuppressWarnings("unchecked")
		List<RegistrationTO> loginUser = (List<RegistrationTO>) request.getSession().getAttribute("loginUser");		
		HttpClient httpclient = new DefaultHttpClient(); 
		
		//String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("userimages"); 
		

		if(loginUser == null){
			return LOGIN;
		}
		
		if(loginUser != null){
			RegistrationTO login = loginUser.get(0);
			RegistrationTO selectedVehical = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
			RegistrationTO loginDetail = (RegistrationTO) request.getSession().getAttribute("loginDetail");

			try {
				//String filePath = request.getSession().getServletContext().getRealPath("/");
				//String fileName = request.getSession().getServletContext().getContextPath();
				System.out.println("Src File name: " + myImage);
		     	System.out.println("Dst File name: " + myFileFileName);
			
		     	/*if(myImage==null){
		     		this.getServletResponse().getWriter().print("Select a file to upload");
		     		return null;
		     	}*/
				if (myImage != null) {
					String uploadUserImageUrl = request.getSession()
							.getAttribute("base_env_url") + "user/userImage";

					HttpPost httppost = new HttpPost(uploadUserImageUrl);

					// HttpPost httppost = new
					// HttpPost("http://localhost:8080/MyMazdaGarage/client/user/userImage");

					// To give path of file to be uploaded
					// FileBody file = new FileBody(new File(filePath));
					// FileBody file = new FileBody(new
					// File("D:/Chrysanthemum.jpg"));

					FileBody file = new FileBody(myImage);

					StringBody vin = new StringBody(selectedVehical.getVin());
					StringBody userId = new StringBody(loginDetail.getEmail());
					StringBody custId = new StringBody(login.getCustomerId());

					// MultipartEntity reqEntity = new MultipartEntity();
					MultipartEntityBuilder reqEntity = MultipartEntityBuilder
							.create();
					reqEntity.addPart("file", file);
					reqEntity.addPart("vin", vin);
					reqEntity.addPart("userId", userId);
					reqEntity.addPart("custId", custId);

					HttpEntity entity = reqEntity.build();
					httppost.setEntity(entity);
					HttpResponse response = httpclient.execute(httppost);

					System.out
							.println(response.getStatusLine().getStatusCode());

					if (response.getStatusLine().getStatusCode() == 200) {
						RegistrationAction registrationAction = new RegistrationAction();
						registrationAction
								.keepUpdateOfSelectedVehical(selectedVehical
										.getVin());
						return SUCCESS;
					} else {
						this.getServletResponse().getWriter().print("error");
					}
				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				addActionError(e.getMessage());
			} catch (Exception e) {
				System.out.println("Exception at uploadImage(): " + e);
				e.printStackTrace();
				addActionError(e.getMessage());
			} finally {
				httpclient.getConnectionManager().shutdown();
			}
		}
		return SUCCESS;
	}

	public String deleteImage(){
		
		List<RegistrationTO> loginUser = (List<RegistrationTO>) request.getSession().getAttribute("loginUser");
		if(loginUser != null){

			//RegistrationTO login = loginUser.get(0);
			RegistrationTO selectedVehical = (RegistrationTO) request.getSession().getAttribute("selectedVehical");
			
			RegistrationTO loginUserDetail = new RegistrationTO();
			loginUserDetail =  (RegistrationTO) request.getSession().getAttribute("loginDetail");
					
			//HttpClient httpclient = new DefaultHttpClient();
			try{
				JSONObject deleteImage = new JSONObject();
				
				String deleteUserImageUrl  = request.getSession().getAttribute("base_env_url")+"user/userImage/"+loginUserDetail.getCustomerId()+"/"+selectedVehical.getVin();

				//String deleteUserImageUrl  = "http://localhost:8080/MyMazdaGarage/client/user/userImage/"+loginUserDetail.getCustomerId()+"/"+selectedVehical.getVin();
				String postResponse = PostJsonObject.postJson(deleteImage, deleteUserImageUrl,request);
				JSONObject jsonObj = new JSONObject(postResponse);
				
				if(jsonObj.has("status") && jsonObj.getString("status").equalsIgnoreCase("success")){
					RegistrationAction registrationAction = new RegistrationAction();
            		registrationAction.keepUpdateOfSelectedVehical(selectedVehical.getVin());
					this.getServletResponse().getWriter().print("success");
					return SUCCESS;
				} else{
					this.getServletResponse().getWriter().print("error");
				}
			} 
			catch (Exception e) {
				System.out.println("Exception at deleteImage(): "+e);
				e.printStackTrace();
				try {
					this.getServletResponse().getWriter().print("error");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			/*try {
				HttpDelete httpdel = new HttpDelete(deleteUserImageUrl);
				httpdel.setHeader("Content-Type", "application/json");
		
				HttpResponse response = httpclient.execute(httpdel);
			
				String content=EntityUtils.toString(response.getEntity());
				System.out.println(content);
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
					this.getServletResponse().getWriter().print("success");
				}
				else{
					this.getServletResponse().getWriter().print("error");
				}
		
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				httpclient.getConnectionManager().shutdown();
			}*/
			/*Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
			WebTarget webTarget = client.target("http://localhost:8080/JerseyDemos/rest").path("employees").path("1");

			Invocation.Builder invocationBuilder =	webTarget.request();
			Response response = invocationBuilder.delete();

			System.out.println(response.getStatus());
			System.out.println(response.readEntity(String.class));*/
		}
			
			
		
		return SUCCESS;
	}
	
	public UploadImageTO getUploadImageTO() {
		return uploadImageTO;
	}

	public void setUploadImageTO(UploadImageTO uploadImageTO) {
		this.uploadImageTO = uploadImageTO;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getServletResponse() {
		return servletResponse;
	}
	
	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	public File getMyImage() {
		return myImage;
	}

	public void setMyImage(File myImage) {
		this.myImage = myImage;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	
}