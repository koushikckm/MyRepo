/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.batch.scheduler;

import com.mazdausa.mmg.constants.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is the class, which is responsible for performing scheduling of the Batch Process of the application.
 * @author PankajB
 * @version 1.0
 */
public class BatchProcessScheduler {

    private static final Logger logger = LoggerFactory.getLogger(BatchProcessScheduler.class);
    @Autowired
    Job job;
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    ApplicationConstants appConstants;

    /**
     * This is the function, which will be responsible for invoking the Batch process
     */
    public void startBatchProcess() {
        logger.info("********************************************************************************");
        logger.info("*************     SPRING MYMAZDAGARAGE BATCH PROCESS STARTED     ***************");
        logger.info("********************************************************************************");

        try {

            // If the APNS Push notifcation service has been Active.
            if (appConstants.IPHONE_PUSH_NOTIFICATION_ACTIVE) {
                JobParametersBuilder builder = new JobParametersBuilder();
                builder.addLong("currentTime", new Long(System.currentTimeMillis()));
                jobLauncher.run(job, builder.toJobParameters());
            } else {
                logger.info("The Batch Process is being disabled. ");
            }

        } catch (JobExecutionAlreadyRunningException ex) {
            logger.error("An JobExecutionAlreadyRunningException is thrown by the Batch Process. ", ex);
        } catch (JobRestartException ex) {
            logger.error("An JobRestartException is thrown by the Batch Process. ", ex);
        } catch (JobInstanceAlreadyCompleteException ex) {
            logger.error("An JobInstanceAlreadyCompleteException is thrown by the Batch Process. ", ex);
        }

        logger.info("********************************************************************************");
        logger.info("*************     SPRING MYMAZDAGARAGE BATCH PROCESS COMPLETED     ***************");
        logger.info("********************************************************************************");
    }
}
