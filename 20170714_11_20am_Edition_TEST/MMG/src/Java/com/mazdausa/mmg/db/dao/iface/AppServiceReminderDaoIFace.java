/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.db.dao.iface;

import com.mazdausa.mmg.db.vo.ServiceReminderVO;
import java.util.List;

/**
 *
 * @author pankajkh
 */
public interface AppServiceReminderDaoIFace {

    public ServiceReminderVO getAppServiceReminderDetails(int serviceReminderId);

    public ServiceReminderVO getAppServiceReminders(String vin, String dealerCode, String seviceReminderNumber);

    public void addAppSerivceReminderDetails(ServiceReminderVO serviceReminderVO);

    public void updateAppSerivceReminderDetails(ServiceReminderVO serviceReminderVO);

    public void deleteAppSerivceReminderDetails(ServiceReminderVO serviceReminderVO);
}
