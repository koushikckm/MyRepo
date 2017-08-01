/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.batch.writer;

import com.mazdausa.mmg.batch.utility.BatchProcessUtility;
import com.mazdausa.mmg.db.vo.UserVO;
import com.mazdausa.mmg.db.vo.UserVehicleVO;
import java.util.Calendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author PankajB
 */
public class UsersChunkWriter implements ItemWriter {

    private static final Logger logger = LoggerFactory.getLogger(UsersChunkWriter.class);
    @Autowired
    BatchProcessUtility batchProcessor;

    /**
     * This is the function, which will be called, when we are being passed a chunk from the Reader.
     * @param list1
     * @throws Exception
     */
    public void write(List usersList) throws Exception {
        logger.debug("****************  START PROCESS AT  {}   ********************************", Calendar.getInstance().getTime());
        List<UserVO> list = (List<UserVO>) usersList.get(0);
        for (UserVO lUserVO : list) {

            logger.debug("Processing Records for CustomerID = {}", lUserVO.getCustomerId());
            for (UserVehicleVO vehicleVO : lUserVO.getVehicles()) {
                logger.debug("Process Service Reminders for VIN ={} ",vehicleVO.getVehicleVIN());
                batchProcessor.processServiceReminders(lUserVO.getCustomerId(), vehicleVO.getVehicleVIN(), lUserVO.getApplicationInstallationVO().getDeviceAPNSToken());
                batchProcessor.processVinRecall(vehicleVO.getVehicleVIN(), lUserVO.getApplicationInstallationVO().getDeviceAPNSToken());

            }

        }
        list = null;

        logger.debug("****************   COMPLETED  AT  {}     ************************* ", Calendar.getInstance().getTime());
    }
}
