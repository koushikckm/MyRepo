/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.db.dao.impl;

import com.mazdausa.mmg.db.dao.iface.ApplicationConstantDaoIFace;
import com.mazdausa.mmg.db.vo.ConstantVO;
import java.util.ArrayList;
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
 * This is an Implementation Class for Interface ApplicationConstantDaoIFace
 * @author pankajkh
 * version 1.0
 */
@Repository
public class AppConstantDaoImpl extends HibernateDaoSupport implements ApplicationConstantDaoIFace {

    public static final String findConstantByName = "com.mazdausa.mmg.db.vo.ConstantVO.findConstantByName";
    public static final String findAllConstant = "com.mazdausa.mmg.db.vo.ConstantVO.getConstant";
    public static final String findConstantByModuleName = "com.mazdausa.mmg.db.vo.ConstantVO.findConstantByModuleName";
    /**
     * Declaring the Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(AppConstantDaoImpl.class.getCanonicalName());

    /**
     * Auto wiring the Session Factory for this
     */
    @Autowired
    public void setMySessionFactory(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * This Function will return the Application's constant Details given the constant id.
     * @param constantId Application Constant Id
     * @return
     */
    public ConstantVO getAppConstantDetails(int constantId) {

        ConstantVO constantVO = null;

        logger.debug(" >> Entering {} getAppConstantDetails() with appDetalsId= {}", this.getClass(), constantId);

        constantVO = (ConstantVO) this.getHibernateTemplate().get(ConstantVO.class, constantId);

        if (constantVO != null && constantVO.getName() == null && constantVO.getValue() == null) {

            constantVO = null;

        }
        logger.debug(" >> Entering {} getAppConstantDetails() with appDetalsId= {}", this.getClass(), constantVO);
        return constantVO;
    }

    /**
     * This Function will add the Application's constant Details given to the database table.
     * @param constantVO Application Constant VO
     * @return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void addAppConstantDetails(ConstantVO constantVO) {

        logger.debug(" >> Entering {} addAppConstantDetails() with input= {}", this.getClass(), constantVO);
        this.getHibernateTemplate().persist(constantVO);
        logger.debug(" >> Entering {} addAppConstantDetails() with true= {}", this.getClass(), constantVO);
    }

    /**
     * This Function will update the Application's constant Details in database table.
     * @param constantVO Application Constant VO     
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void updateAppConstantDetails(ConstantVO constantVO) {

        logger.debug(" >> Entering {} updateAppConstantDetails() with input= {}", this.getClass(), constantVO);
        this.getHibernateTemplate().update(constantVO);
        logger.debug(" >> Entering {} updateAppConstantDetails() with true= {}", this.getClass(), constantVO);
    }

    /**
     * This Function will delete Application's constant Details from the database table.
     * @param constantVO Application Constant VO     
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void deleteAppConstantDetails(ConstantVO constantVO) {

        logger.debug(" >> Entering {} deleteAppConstantDetails() with input= {}", this.getClass(), constantVO);
        this.getHibernateTemplate().delete(constantVO);
        logger.debug(" >> Entering {} deleteAppConstantDetails() with true= {}", this.getClass(), constantVO);
    }

    /**
     * This Function returns the Application Constant if present in the database, otherwise it will return null
     * @param constantName
     * @return
     */
    public ConstantVO getAppConstantDetails(String constantName) {
        logger.debug(" >> Entering {} getAppConstantDetails() with input= {}", this.getClass(), constantName);
        ConstantVO constantVO = null;
        List<ConstantVO> listOfConstants = this.getHibernateTemplate().findByNamedQuery(findConstantByName, constantName);
        //List<ConstantVO> listOfConstants = this.getHibernateTemplate().findByNamedQuery(findConstantByName);
        if (listOfConstants != null && listOfConstants.size() > 0) {
            constantVO = listOfConstants.get(0);
        }
        logger.debug(" << Exiting {} getAppConstantDetails() with true= {}", this.getClass(), constantVO);
        return constantVO;
    }

    public List<ConstantVO> getAllConstantDetails(){
        logger.debug(" >> Entering {} getAllConstantDetails() with input= {}", this.getClass());
        List<ConstantVO> constantVO = (List<ConstantVO>)this.getHibernateTemplate().findByNamedQuery(findAllConstant);

        if(constantVO == null){
            constantVO = new ArrayList<ConstantVO>();
        }
        logger.debug(" << Exiting {} getAllConstantDetails() with true= {}", this.getClass(), constantVO);
        return constantVO;
    }

    /**
     * This Function returns the Application Constant if present in the database, otherwise it will return null
     * @param constantName
     * @return result boolean
     **/
    public boolean getAppConstantDetailsByModule(String moduleName) {
        logger.debug(" >> Entering {} getAppConstantDetailsByModule() with input= {}", this.getClass(), moduleName);
        boolean result = false;
        List<ConstantVO> listOfConstants = this.getHibernateTemplate().findByNamedQuery(findConstantByModuleName, moduleName);
        //List<ConstantVO> listOfConstants = this.getHibernateTemplate().findByNamedQuery(findConstantByName);
        if (listOfConstants != null && listOfConstants.size() > 0) {
            //constantVO = listOfConstants.get(0);
            result = true;
        }
        logger.debug(" << Exiting {} getAppConstantDetailsByModule() with true= {}", this.getClass(), result);
        return result;
    }

    /**
     * This Function returns the Application Constant if present in the database, otherwise it will return null
     * @param moduleName
     * @return constantVO
     */
    public ConstantVO getConstantDetailsByModule(String moduleName) {
        logger.debug(" >> Entering {} getConstantDetailsByModule() with input= {}", this.getClass(), moduleName);
        ConstantVO constantVO = null;        
        List<ConstantVO> listOfConstants = this.getHibernateTemplate().findByNamedQuery(findConstantByModuleName, moduleName);
        //List<ConstantVO> listOfConstants = this.getHibernateTemplate().findByNamedQuery(findConstantByName);
        if (listOfConstants != null && listOfConstants.size() > 0) {
            constantVO = listOfConstants.get(0);
        }
        logger.debug(" << Exiting {} getConstantDetailsByModule() with true= {}", this.getClass(), constantVO);
        return constantVO;
    }
}
