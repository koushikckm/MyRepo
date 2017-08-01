/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.db.dao.impl;

import com.mazdausa.mmg.db.dao.iface.AppServiceReminderDaoIFace;
import com.mazdausa.mmg.db.vo.ServiceReminderVO;
import java.util.List;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pankajkh
 */
@Repository
public class AppServiceReminderDaoImpl extends HibernateDaoSupport implements AppServiceReminderDaoIFace {

    /**
     * Declaring the Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(ApplicationUsersDaoImpl.class.getCanonicalName());

    private static final String FINDSERVICEREMINDER_BYVINDEALERCODECOUPONNUMBER="com.mazdausa.mmg.db.vo.ServiceReminderVO.findServiceReminderByVinDealerCodeCouponNumber";

    /**
     * Auto wiring the Session Factory for this DAO.
     */
    @Autowired
    public void setMySessionFactory(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /*
     * This function will return service reminder detials by serviceReminderID
     * param serviceReminderId Application's service reminder Id.
     * return
     */
    public ServiceReminderVO getAppServiceReminderDetails(int serviceReminderId) {

        ServiceReminderVO serviceReminderVO = null;

        logger.debug(" >> Entering {} getAppSerivceReminderDetails() with serviceReminderId= {}", this.getClass(), serviceReminderId);

        serviceReminderVO = (ServiceReminderVO) this.getHibernateTemplate().get(ServiceReminderVO.class, serviceReminderId);

        if (serviceReminderVO != null && serviceReminderVO.getCouponserviceremindernumber() == null) {
            serviceReminderVO = null;
        }

        logger.debug(" >> Entering {} getAppSerivceReminderDetails() with serviceReminderId= {}", this.getClass(), serviceReminderVO);
        return serviceReminderVO;
    }

    /*
     * This function will add the application's service reminder details to the database
     * param serviceReminderVO Application's service reminder VO
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    @Async
    public void addAppSerivceReminderDetails(ServiceReminderVO serviceReminderVO) {

        logger.debug(" >> Entering {} addAppSerivceReminderDetails() with input= {}", this.getClass(), serviceReminderVO);

        this.getHibernateTemplate().persist(serviceReminderVO);

        logger.debug(" >> Entering {} addAppSerivceReminderDetails() with input= {}", this.getClass(), serviceReminderVO);

    }

    /*
     * This function will update the application's service reminder details to the database
     * param serviceReminderVO Application's service reminder VO
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void updateAppSerivceReminderDetails(ServiceReminderVO serviceReminderVO) {

        logger.debug(" >> Entering {} updateAppSerivceReminderDetails() with input= {}", this.getClass(), serviceReminderVO);

        this.getHibernateTemplate().update(serviceReminderVO);

        logger.debug(" >> Existing {} updateAppSerivceReminderDetails()", this.getClass());


    }

    /*
     * This function will delete the application's service reminder details from the database
     * param serviceReminderVO Application's service reminder VO
     * return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void deleteAppSerivceReminderDetails(ServiceReminderVO serviceReminderVO) {

        logger.debug(" >> Entering {} deleteAppSerivceReminderDetails() with input= {}", this.getClass(), serviceReminderVO);

        this.getHibernateTemplate().delete(serviceReminderVO);

        logger.debug(" << Existing {} deleteAppSerivceReminderDetails() with input= {}", this.getClass(), serviceReminderVO);
    }

    /**
     * This function will return a particular service reminder
     * @param vin
     * @param dealerCode
     * @param seviceReminderNumber
     * @return
     */
    public ServiceReminderVO getAppServiceReminders(String vin, String dealerCode, String serviceReminderNumber) {

        logger.debug(" >> Entering getAppSerivceReminders() with input= {} {} {}", new Object[]{vin,dealerCode,serviceReminderNumber});
        ServiceReminderVO serviceReminderVO=null;

        List<ServiceReminderVO> serviceReminderList=(List<ServiceReminderVO>) this.getHibernateTemplate().findByNamedQuery(FINDSERVICEREMINDER_BYVINDEALERCODECOUPONNUMBER, new Object[]{vin,dealerCode,serviceReminderNumber});
        if(serviceReminderList !=null && serviceReminderList.size() > 0)
        {
            serviceReminderVO=(ServiceReminderVO)serviceReminderList.get(0);
        }

        logger.debug(" >> Existing  getAppSerivceReminders() with result= {}",serviceReminderVO);
        return serviceReminderVO;
    }
}
