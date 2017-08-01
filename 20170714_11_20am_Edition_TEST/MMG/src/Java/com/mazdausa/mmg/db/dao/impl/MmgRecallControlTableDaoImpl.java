package com.mazdausa.mmg.db.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mazdausa.mmg.db.dao.iface.MmgRecallControlTableDaoIFace;
import com.mazdausa.mmg.db.vo.MmgRecallControlTableVO;


/**
 * This is an DAO Layer implementation of MmgRecallControlTableDaoImpl
 * @Author Sarat
 */
@Repository
public class MmgRecallControlTableDaoImpl extends HibernateDaoSupport implements MmgRecallControlTableDaoIFace{
	
	/**
     * Declaring the Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(MmgRecallControlTableDaoImpl.class.getCanonicalName());
    
    /**
     * Auto wiring the Session Factory for this
     */
    @Autowired
    public void setMySessionFactory(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    
    /**5
     * Function to return the list of mmg recall Control table data.
     * @return
     */
    public List<MmgRecallControlTableVO> getRecallControlTableData() {
        logger.debug(" >> Entering {} getRecallControlTableData() with input=", this.getClass());
        List<MmgRecallControlTableVO> listMmgRecallControlTableVO =(List<MmgRecallControlTableVO>) this.getHibernateTemplate().loadAll(MmgRecallControlTableVO.class);
        logger.debug(" << Exiting {} getRecallControlTableData() with Result Size= {}", this.getClass(), listMmgRecallControlTableVO.size());
        return listMmgRecallControlTableVO;
    }


}
