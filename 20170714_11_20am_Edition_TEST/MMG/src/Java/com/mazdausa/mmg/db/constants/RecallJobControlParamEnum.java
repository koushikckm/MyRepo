package com.mazdausa.mmg.db.constants;

public enum RecallJobControlParamEnum 

{
	RECALLSTATUS {
	@Override
	public String getId() {
		return "recallStatus";
	}
},
TYPEOFRECALL {
	@Override
	public String getId() {
		return "typeOfRecall";
	}
},
THRESHOLDFREQ {
	@Override
	public String getId() {
		return "thresholdFreq";
	}
},
MAXRECALL {
	@Override
	public String getId() {
		return "maxRecall";
	}
};
public abstract String getId();
	
}
