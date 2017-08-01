/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.db.dao.impl;

import com.mazdausa.mmg.db.dao.iface.VehicleCodeDaoIFace;
import com.mazdausa.mmg.db.vo.VehicleCodeVO;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * This is a DAO layer implementation of VehicleCodeDaoIFace interface.
 * @author pankajkh
 * @version 1.0
 */
@Repository
public class VehicleCodeDaoImpl extends HibernateDaoSupport implements VehicleCodeDaoIFace {

    public static final String findVehicleCodeByYear = "com.mazdausa.mmg.db.vo.VehicleCodeVO.findVehicleByYear";
    public static final String findVehicleCodeByModel = "com.mazdausa.mmg.db.vo.VehicleCodeVO.findVehicleByModel";
    public static final String getVehiclesOrderedByYear = "com.mazdausa.mmg.db.vo.VehicleCodeVO.GetVehiclesByYear";
    /**
     * Declaring the Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(VehicleCodeDaoImpl.class.getCanonicalName());

    /**
     * Auto wiring the Session Factory for this
     */
    @Autowired
    public void setMySessionFactory(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    /*
     * This function will all vehicle details
     * @return
     */

    public List<VehicleCodeVO> getAllVehicleModels() {

        logger.debug(" >> Entering {} getAllVehicleModels() with input=", this.getClass());
        List<VehicleCodeVO> listVehicleCodeVO = (List<VehicleCodeVO>) this.getHibernateTemplate().findByNamedQuery(getVehiclesOrderedByYear);
        if (listVehicleCodeVO == null) {
            listVehicleCodeVO = new ArrayList<VehicleCodeVO>();
        }
        logger.debug(" << Exiting {} getAllVehicleModels() with result size={}", this.getClass(), listVehicleCodeVO.size());
        return listVehicleCodeVO;
    }
    /*
     * This function will return the vehicle details.
     * @param vehicleYear
     * @return
     */

    public List<VehicleCodeVO> getVehicleByYear(String vehicleYear) {

        logger.debug(" >> Entering {} getVehicleByYear() with vehicle Year= {}", this.getClass(), vehicleYear);
        List<VehicleCodeVO> listVehicleCodeVO = null;
        listVehicleCodeVO = (List<VehicleCodeVO>) this.getHibernateTemplate().findByNamedQuery(findVehicleCodeByYear, vehicleYear);
        logger.debug(" << Exiting {} getVehicleByYear() with result= {}", this.getClass(), listVehicleCodeVO.size());
        return listVehicleCodeVO;
    }

    /*
     * This function will return the vehicle details.
     * @param vehicleModel
     * @return
     */
    public List<VehicleCodeVO> getVehicleByModel(String vehicleModel) {

        VehicleCodeVO vehicleCodeVO = null;
        logger.debug(" >> Entering {} getVehicleByYear() with vehicle MODEL= {}", this.getClass(), vehicleModel);
        List<VehicleCodeVO> listVehicleCodeVO = null;
        listVehicleCodeVO = (List<VehicleCodeVO>) this.getHibernateTemplate().findByNamedQuery(findVehicleCodeByModel, vehicleModel);
        logger.debug(" << Exiting {} getVehicleByYear() with result= {}", this.getClass(), vehicleCodeVO);
        return listVehicleCodeVO;
    }

    /*
     * This method will add the vehicle code to the DB Table
     * @param VehicleCodeVO
     */
    public void addVehicleCode(VehicleCodeVO vehicleCodeVO) {

        logger.debug(" >> Entering {} addVehicleCode() with input={}", this.getClass(), vehicleCodeVO);
        this.getHibernateTemplate().persist(vehicleCodeVO);
        logger.debug(" << Exiting {} addVehicleCode() with True", this.getClass());

    }

    /*
     * This function will update the vehicle details in to the DB table
     * @param VehicleCodeVO
     */
    public void updateVehicleCode(VehicleCodeVO vehicleCodeVO) {

        logger.debug(" >> Entering {} updateVehicleCode() with input={}", this.getClass(), vehicleCodeVO);
        this.getHibernateTemplate().saveOrUpdate(vehicleCodeVO);
        logger.debug(" << Exiting {} updateVehicleCode() with result True", this.getClass());

    }

    /*
     * This function will delete the vehicle details from the DB table
     * @param VehicleCodeVO
     */
    public void deleteVehicleCode(VehicleCodeVO vehicleCodeVO) {

        logger.debug(" >> Entering {} deleteVehicleCode() with input={}", this.getClass(), vehicleCodeVO);
        this.getHibernateTemplate().delete(vehicleCodeVO);
        logger.debug(" << Exiting {} deleteVehicleCode() with result True", this.getClass());

    }
}
