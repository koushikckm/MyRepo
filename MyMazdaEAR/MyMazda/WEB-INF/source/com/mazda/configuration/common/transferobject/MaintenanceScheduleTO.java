package com.mazda.configuration.common.transferobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceScheduleTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int schedule;
	private int mileage;
	private int milesPerDay;
	private String carlineDesc;
	private String mdlCode;
	private int mdlYear;
	private int interval;
	private String itemName;
	private String nonIntervalDescription;
	private String itemDescription;
	private String itemFootNote;
	private String scheduleType;
	private String vin;
	private List<MaintenanceScheduleTO> replaceItemList =new ArrayList<MaintenanceScheduleTO>();
	private List<MaintenanceScheduleTO> lubrItemList =new ArrayList<MaintenanceScheduleTO>();
	private List<MaintenanceScheduleTO> inspecItemList =new ArrayList<MaintenanceScheduleTO>();
	private List<MaintenanceScheduleTO> cleanItemList =new ArrayList<MaintenanceScheduleTO>();
	private List<MaintenanceScheduleTO> tightenItemList =new ArrayList<MaintenanceScheduleTO>();
	private List<MaintenanceScheduleTO> maintenanceNoteList =new ArrayList<MaintenanceScheduleTO>();
	private List<MaintenanceScheduleTO> itemFootNoteList =new ArrayList<MaintenanceScheduleTO>();
		
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSchedule() {
		return schedule;
	}
	public void setSchedule(int schedule) {
		this.schedule = schedule;
	}
	
	public String getCarlineDesc() {
		return carlineDesc;
	}
	public void setCarlineDesc(String carlineDesc) {
		this.carlineDesc = carlineDesc;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public int getMilesPerDay() {
		return milesPerDay;
	}
	public void setMilesPerDay(int milesPerDay) {
		this.milesPerDay = milesPerDay;
	}
	public String getMdlCode() {
		return mdlCode;
	}
	public void setMdlCode(String mdlCode) {
		this.mdlCode = mdlCode;
	}
	public int getMdlYear() {
		return mdlYear;
	}
	public void setMdlYear(int mdlYear) {
		this.mdlYear = mdlYear;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemFootNote() {
		return itemFootNote;
	}
	public void setItemFootNote(String itemFootNote) {
		this.itemFootNote = itemFootNote;
	}
	
	
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	public String getNonIntervalDescription() {
		return nonIntervalDescription;
	}
	public void setNonIntervalDescription(String nonIntervalDescription) {
		this.nonIntervalDescription = nonIntervalDescription;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public List<MaintenanceScheduleTO> getReplaceItemList() {
		return replaceItemList;
	}
	public void setReplaceItemList(List<MaintenanceScheduleTO> replaceItemList) {
		this.replaceItemList = replaceItemList;
	}
	public List<MaintenanceScheduleTO> getLubrItemList() {
		return lubrItemList;
	}
	public void setLubrItemList(List<MaintenanceScheduleTO> lubrItemList) {
		this.lubrItemList = lubrItemList;
	}
	public List<MaintenanceScheduleTO> getInspecItemList() {
		return inspecItemList;
	}
	public void setInspecItemList(List<MaintenanceScheduleTO> inspecItemList) {
		this.inspecItemList = inspecItemList;
	}
	public List<MaintenanceScheduleTO> getCleanItemList() {
		return cleanItemList;
	}
	public void setCleanItemList(List<MaintenanceScheduleTO> cleanItemList) {
		this.cleanItemList = cleanItemList;
	}
	public List<MaintenanceScheduleTO> getTightenItemList() {
		return tightenItemList;
	}
	public void setTightenItemList(List<MaintenanceScheduleTO> tightenItemList) {
		this.tightenItemList = tightenItemList;
	}
	public List<MaintenanceScheduleTO> getMaintenanceNoteList() {
		return maintenanceNoteList;
	}
	public void setMaintenanceNoteList(
			List<MaintenanceScheduleTO> maintenanceNoteList) {
		this.maintenanceNoteList = maintenanceNoteList;
	}
	public String getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}
	/**
	 * @param vin the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}
	public List<MaintenanceScheduleTO> getItemFootNoteList() {
		return itemFootNoteList;
	}
	public void setItemFootNoteList(List<MaintenanceScheduleTO> itemFootNoteList) {
		this.itemFootNoteList = itemFootNoteList;
	}


}
