package com.mazdausa.mmg.web.client.service.iface;

import com.mazdausa.mmg.web.client.request.vo.PushNotificationVO;


/**
 * This is an Service level Interface and being responsible for all push notification feature Service Layer Functions.
 * @author AnilK
 *
 */
public interface PushNotificationServiceIface {



	/**
	 * This method is used to save vehicle - vin mapping.
	 * @param year
	 * @param model
	 * @param vin
	 * @return
	 */
	public boolean mapVehicleVin(String year,String model,
			String vin);
	
/**
 * This method is used to save device details (if not already saved) to the database.
 * @param pushNotificationVo
 * @return status
 */
	public PushNotificationVO registerDevice(String deviceType,String deviceToken);


/**
 * 	This method is used to send push notifications to registered devices and save notification details to database.
 * @param pushNotificationVo
 * @return status
 */
	public PushNotificationVO sendNotification(PushNotificationVO pushNotificationVo);
	
/**
 * This method is used to send push-notification based on vehicle year & model.	
 * @param pushNotificationVo
 * @param year
 * @param model
 * @return
 */
	public PushNotificationVO sendNotification(PushNotificationVO pushNotificationVo,String year,String model);
	
	
	/**
	 * This method is used to Stores Data on MMG Db	
	 * @return List Vin Recall data
	 */
	public boolean recallPushnotificaionData();
	public boolean getClosedStatusRecallsAndUpdate();
}
