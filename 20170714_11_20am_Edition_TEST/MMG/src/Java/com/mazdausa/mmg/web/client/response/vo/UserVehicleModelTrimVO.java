package com.mazdausa.mmg.web.client.response.vo;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class UserVehicleModelTrimVO implements Comparable<UserVehicleModelTrimVO>{
	
    private String year;
    private String modelCode;
    private String modelName;
    private Set<TrimVO> trims;
    //private Set<String> trims;
	
    public int compareTo(UserVehicleModelTrimVO userVehicleModelTrimVO){
    	String name = this.modelName.replace("-", "").toUpperCase();
    	String name1 = userVehicleModelTrimVO.modelName.replace("-", "").toUpperCase();
        int mdlNm = name.compareTo(name1);
    	return (mdlNm != 0 ? mdlNm : name.compareTo(name1) );
    }
    
	public UserVehicleModelTrimVO(String modelCode, Set<TrimVO> trims) {
		super();
		this.modelCode = modelCode;
		this.trims = trims;
	}


	public UserVehicleModelTrimVO() {
		super();
	}




	public Set<TrimVO> getTrims() {
		return trims;
	}


	public void setTrims(Set<TrimVO> trims) {
		this.trims = trims;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}




	public String getModelCode() {
		return modelCode;
	}


	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}


	public String getModelName() {
		return modelName;
	}


	public void setModelName(String modelName) {
		this.modelName = modelName;
	}




	@Override
	public String toString() {
		return "UserVehicleModelTrimVO [year=" + year + ", modelCode="
				+ modelCode + ", modelName=" + modelName + ", trims=" + trims
				+ "]";
	}



	
}
