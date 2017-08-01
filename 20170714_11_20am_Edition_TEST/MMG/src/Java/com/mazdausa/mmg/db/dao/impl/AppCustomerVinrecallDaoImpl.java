/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.db.dao.impl;

import com.mazdausa.mmg.db.dao.iface.AppCustomerVinRecallDaoIFace;
import com.mazdausa.mmg.db.vo.CustomerVINRecallVO;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the DAO Layer implementation class of Customer VIN Recall DAO Interface
 * @author pankajkh
 */
@Repository
public class AppCustomerVinrecallDaoImpl extends HibernateDaoSupport implements AppCustomerVinRecallDaoIFace {

    /**
     * Declaring the Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(ApplicationUsersDaoImpl.class.getCanonicalName());
    private static final String FINDVINRECALL_BYRECALLNO = "com.mazdausa.mmg.db.vo.CustomerVINRecallVO.findCustomerVINRecallByRecallNo";
    private static final String FINDVINRECALLBY_VINRECALLSSPNOSTARTDATE="com.mazdausa.mmg.db.vo.CustomerVINRecallVO.findCustomerVINRecallByRecallNoVINStartDate";

    /**
     * Auto wiring the Session Factory for this DAO.
     */
    @Autowired
    public void setMySessionFactory(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /*
     * This function will return Customer VIN Recall detials by vinRecallId
     * param vinRecallId Application's Customer VIN Recall ID.
     * return
     */
    public CustomerVINRecallVO getCustomerVinRecallDetails(int vinRecallId) {

        logger.debug(" >> Entering {} getCustomerVinRecallDetails() with vinRecallId= {}", this.getClass(), vinRecallId);

        CustomerVINRecallVO customerVINRecallVO = null;

        customerVINRecallVO = (CustomerVINRecallVO) this.getHibernateTemplate().get(CustomerVINRecallVO.class, vinRecallId);

        if (customerVINRecallVO != null && customerVINRecallVO.getVinrecallSSPNo() == null) {

            customerVINRecallVO = null;
        }
        logger.debug(" << Exiting getCustomerVinRecallDetails() with result= {}", customerVINRecallVO);
        return customerVINRecallVO;
    }

    /*
     * This function will add new Customer VIN Recall detials to the database
     * param customerVINRecallVO Application's Customer VIN Recall VO.
     * return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void addCustomerVinRecallDetails(CustomerVINRecallVO customerVINRecallVO) {

        logger.debug(" >> Entering {} addCustomerVinRecallDetails() with input= {}", this.getClass(), customerVINRecallVO);
        boolean result = false;
        try {
            this.getHibernateTemplate().persist(customerVINRecallVO);
            result = true;
        } catch (RuntimeException rEx) {
            logger.error("An Exception has occured while adding Customer VIN Recall Details. ", rEx);
        }

        logger.debug(" << Exiting {} addCustomerVinRecallDetails() with result= {}", this.getClass(), result);
    }

    /*
     * This function will update Customer VIN Recall detials in the database
     * param customerVINRecallVO Application's Customer VIN Recall VO.
     * return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void updateCustomerVinRecallDetails(CustomerVINRecallVO customerVINRecallVO) {

        logger.debug(" >> Entering {} updateCustomerVinRecallDetails() with input= {}", this.getClass(), customerVINRecallVO);


        boolean result = false;
        try {
            this.getHibernateTemplate().update(customerVINRecallVO);
            result = true;
        } catch (RuntimeException rEx) {
            logger.error("An Exception has occured while UPDATING Customer VIN Recall Details. ", rEx);
        }

        logger.debug(" << Exiting {} updateCustomerVinRecallDetails() with result= {}", this.getClass(), result);
    }

    /*
     * This function will delete Customer VIN Recall detials from the database
     * param customerVINRecallVO Application's Customer VIN Recall VO.
     * return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void deleteCustomerVinRecallDetails(CustomerVINRecallVO customerVINRecallVO) {

        logger.debug(" >> Entering {} deleteCustomerVinRecallDetails() with input= {}", this.getClass(), customerVINRecallVO);
        boolean result = false;
        try {
            this.getHibernateTemplate().delete(customerVINRecallVO);
            result = true;
        } catch (RuntimeException rEx) {
            logger.error("An Exception has occured while UPDATING Customer VIN Recall Details. ", rEx);
        }

        logger.debug(" <<<Exiting {} deleteCustomerVinRecallDetails() with result= {}", this.getClass(), result);
    }

    /**
     * This is the function which will find an Customer VIN Record in database by providing the VIN No.
     * @param vinRecallNo
     * @return
     */
    public CustomerVINRecallVO getCustomerVinRecallDetails(String vinRecallNo) {
        logger.debug(" >> Entering {} getCustomerVinRecallDetails() with input= {}", this.getClass(), vinRecallNo);
        CustomerVINRecallVO customerVinRecallVO = null;
        List<CustomerVINRecallVO> listOfCustomerVinRecall = (List<CustomerVINRecallVO>) this.getHibernateTemplate().findByNamedQuery(FINDVINRECALL_BYRECALLNO, vinRecallNo);
        if (listOfCustomerVinRecall != null && listOfCustomerVinRecall.size() > 0) {
            customerVinRecallVO = listOfCustomerVinRecall.get(0);
        }
        logger.debug(" >> Exiting {} getCustomerVinRecallDetails() with result= {}", this.getClass(), customerVinRecallVO);
        return customerVinRecallVO;
    }

    /**
     * This is the function, which will be responsible for returning the Customer VIN Recall details, giving the VIn,RecallNo and startDate
     * @param vin
     * @param vinRecallNo
     * @param startDate
     * @return
     */
    public CustomerVINRecallVO getCustomerVinRecallDetails(String vin, String vinRecallNo, Date startDate) {
        logger.debug(" >> Entering {} getCustomerVinRecallDetails() with input= {}", this.getClass(), vinRecallNo);
        CustomerVINRecallVO customerVinRecallVO = null;
        List<CustomerVINRecallVO> listOfCustomerVinRecall = (List<CustomerVINRecallVO>) this.getHibernateTemplate().findByNamedQuery(FINDVINRECALLBY_VINRECALLSSPNOSTARTDATE, new Object[]{vin,vinRecallNo,startDate});
        if (listOfCustomerVinRecall != null && listOfCustomerVinRecall.size() > 0) {
            customerVinRecallVO = listOfCustomerVinRecall.get(0);
        }
        logger.debug(" >> Exiting {} getCustomerVinRecallDetails() with result= {}", this.getClass(), customerVinRecallVO);
        return customerVinRecallVO;
    }


}
