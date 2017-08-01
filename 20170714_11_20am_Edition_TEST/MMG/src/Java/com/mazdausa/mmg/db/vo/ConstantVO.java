/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.db.vo;

import com.mazdausa.mmg.db.constants.DatabaseConstants;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author PankajB
 */
@Entity
@Table(name = DatabaseConstants.TABLE_MMG_APP_CONSTANTS)
@NamedQueries({
    @NamedQuery(name = "com.mazdausa.mmg.db.vo.ConstantVO.findConstantByName", query = "from com.mazdausa.mmg.db.vo.ConstantVO  where trim(upper(name)) = ? "),
    @NamedQuery(name = "com.mazdausa.mmg.db.vo.ConstantVO.findConstantByModuleName", query = "from com.mazdausa.mmg.db.vo.ConstantVO  where trim(upper(modulename)) = ? "),
    @NamedQuery(name = "com.mazdausa.mmg.db.vo.ConstantVO.getConstant", query = "from com.mazdausa.mmg.db.vo.ConstantVO order by value desc, name, addedby, updatedby, modulename")})
    
public class ConstantVO implements Serializable {

    private int constantId;
    private String name, value, addedby, updatedby, modulename;
    private Date addedDate, updatedDate;

    @Column(name = DatabaseConstants.TABLE_MMG_APP_MAZDA_CONSTANTS_COLUMN_CONSTANT_ADDED_DATE)
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_MAZDA_CONSTANTS_COLUMN_CONSTANT_ADDED_BY)
    public String getAddedby() {
        return addedby;
    }

    public void setAddedby(String addedby) {
        this.addedby = addedby;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DatabaseConstants.TABLE_MMG_APP_MAZDA_CONSTANTS_COLUMN_CONSTANT_IDENT)
    public int getConstantId() {
        return constantId;
    }

    public void setConstantId(int constantId) {
        this.constantId = constantId;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_MAZDA_CONSTANTS_COLUMN_CONSTANT_NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_MAZDA_CONSTANTS_COLUMN_CONSTANT_UPDATED_DATE)
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_MAZDA_CONSTANTS_COLUMN_CONSTANT_UPDATED_BY)
    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_MAZDA_CONSTANTS_COLUMN_CONSTANT_VALUE)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_MAZDA_CONSTANTS_COLUMN_CONSTANT_MODULE_NAME)
    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }
    
}
