/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.soap.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;


/**
 * This VO will actually contain the data of the Coupons.
 * @author PankajB
 * @version 1.0
 */
public class CouponRemindersDataDetailVO {

    private String blankOffer, couponHeader1, couponHeader2, couponNumber, details, dollarOff, free;
    private String lineText1, lineText2, lineText3, lineText4, lineText5, lineText6, lineText7, lineText8, lineText9, lineText10;
    private String lineText11, lineText12, lineText13, lineText14, lineText15, lineText16, lineText17, lineText18, lineText19, lineText20, lineText21, lineText22, lineText23, lineText24, lineText25;
    private String pctOff, priceText, regPrice, specificationPrice, startingFrom, dealerCode, letterCode, vin;
    private String dealerName, dealerAddress,dealerAddr1,dealerAddr2,dealerCity,dealerCountry,dealerState,dealerZip;
    private CouponRemindersDataDetailLineTextVO textLine1, textLine2, textLine3, textLine4, textLine5, textLine6, textLine7, textLine8, textLine9, textLine10, textLine11, textLine12, textLine13, textLine14, textLine15, textLine16, textLine17, textLine18, textLine19, textLine20, textLine21, textLine22, textLine23, textLine24, textLine25;
    private CouponReminderHeaderVO leftheader, rightheader;
    private String expirationDate, disclaimerId;
    

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_BLANKOFFER)
    public String getBlankOffer() {
        return blankOffer;
    }

    public void setBlankOffer(String blankOffer) {
        this.blankOffer = blankOffer;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_COUPONHEADER1)
    public String getCouponHeader1() {
        return couponHeader1;
    }

    public void setCouponHeader1(String couponHeader1) {
        if (couponHeader1 != null) {
            this.couponHeader1 = couponHeader1.trim();
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_COUPONHEADER2)
    public String getCouponHeader2() {
        return couponHeader2;
    }

    public void setCouponHeader2(String couponHeader2) {
        if (couponHeader2 != null) {
            this.couponHeader2 = couponHeader2.trim();
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_COUPONNUMBER)
    public String getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(String couponNumber) {
        if (couponNumber != null) {
            this.couponNumber = couponNumber.trim();
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_DEALERCODE)
    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_DETAILS)
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_DOLLAROFF)
    public String getDollarOff() {
        return dollarOff;
    }

    public void setDollarOff(String dollarOff) {
        this.dollarOff = dollarOff;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_FREE)
    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LETTERCODE)
    public String getLetterCode() {
        return letterCode;
    }

    public void setLetterCode(String letterCode) {
        this.letterCode = letterCode;
    }

    //@XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT1)
    public String getLineText1() {
        return lineText1;
    }

    public void setLineText1(String lineText1) {
        if (lineText1 != null) {
            this.lineText1 = lineText1;
        }

    }

    //@XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT10)
    public String getLineText10() {
        return lineText10;
    }

    public void setLineText10(String lineText10) {
        if (lineText10 != null) {
            this.lineText10 = lineText10.trim();
        }
    }

    //@XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT2)
    public String getLineText2() {
        return lineText2;
    }

    public void setLineText2(String lineText2) {
        if (lineText2 != null) {
            this.lineText2 = lineText2.trim();
        }
    }

    //@XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT3)
    public String getLineText3() {
        return lineText3;
    }

    public void setLineText3(String lineText3) {
        if (lineText3 != null) {
            this.lineText3 = lineText3.trim();
        }
    }

    //@XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT4)
    public String getLineText4() {
        return lineText4;
    }

    public void setLineText4(String lineText4) {
        if (lineText4 != null) {
            this.lineText4 = lineText4.trim();
        }
    }

    //@XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT5)
    public String getLineText5() {
        return lineText5;
    }

    public void setLineText5(String lineText5) {
        if (lineText5 != null) {
            this.lineText5 = lineText5.trim();
        }
    }

    //@XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT6)
    public String getLineText6() {
        return lineText6;
    }

    public void setLineText6(String lineText6) {
        if (lineText6 != null) {
            this.lineText6 = lineText6.trim();
        }
    }

    //@XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT7)
    public String getLineText7() {
        return lineText7;
    }

    public void setLineText7(String lineText7) {
        if (lineText7 != null) {
            this.lineText7 = lineText7.trim();
        }
    }

    // @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT8)
    public String getLineText8() {
        return lineText8;
    }

    public void setLineText8(String lineText8) {
        if (lineText8 != null) {
            this.lineText8 = lineText8.trim();
        }
    }

    // @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT9)
    public String getLineText9() {
        return lineText9;
    }

    public void setLineText9(String lineText9) {
        if (lineText9 != null) {
            this.lineText9 = lineText9.trim();
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_PCTOFF)
    public String getPctOff() {
        return pctOff;
    }

    public void setPctOff(String pctOff) {
        this.pctOff = pctOff;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_PRICETEXT)
    public String getPriceText() {
        return priceText;
    }

    public void setPriceText(String priceText) {
        if (priceText != null) {
            this.priceText = priceText.trim();
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_REGPRICE)
    public String getRegPrice() {
        return regPrice;
    }

    public void setRegPrice(String regPrice) {
        this.regPrice = regPrice;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_SPECPRICE)
    public String getSpecificationPrice() {
        return specificationPrice;
    }

    public void setSpecificationPrice(String specificationPrice) {
        this.specificationPrice = specificationPrice;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_STARTINGFROM)
    public String getStartingFrom() {
        return startingFrom;
    }

    public void setStartingFrom(String startingFrom) {
        this.startingFrom = startingFrom;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_VIN)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_DEALERADDRESS)
    public String getDealerAddress() {
        return dealerAddress;
    }

    public void setDealerAddress(String dealerAddress) {
        this.dealerAddress = dealerAddress;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_DEALERNAME)
    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    //************************* NEW SET OF LINE TEXT ELEMENTS ******************************
    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT1)
    public CouponRemindersDataDetailLineTextVO getTextLine1() {
        return textLine1;
    }

    public void setTextLine1(CouponRemindersDataDetailLineTextVO textLine1) {
        this.textLine1 = textLine1;
        if (textLine1.getText() != null && textLine1.getText().trim().length() != 0) {
           if(textLine1.getLayout() !=null && textLine1.getLayout().getTypeCode()!=null && !textLine1.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText1 = textLine1.getText().trim();
            }else {
                lineText1 = "";
            }
        } else {
            lineText1 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT10)
    public CouponRemindersDataDetailLineTextVO getTextLine10() {
        return textLine10;
    }

    public void setTextLine10(CouponRemindersDataDetailLineTextVO textLine10) {
        this.textLine10 = textLine10;
        if (textLine10.getText() != null && textLine10.getText().trim().length() != 0) {
            
            if(textLine10.getLayout() !=null && textLine10.getLayout().getTypeCode()!=null && !textLine10.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText10 = textLine10.getText().trim();
            }else {
                lineText10 = "";
            }
            
        } else {
            lineText10 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT11)
    public CouponRemindersDataDetailLineTextVO getTextLine11() {
        return textLine11;
    }

    public void setTextLine11(CouponRemindersDataDetailLineTextVO textLine11) {
        this.textLine11 = textLine11;
        if (textLine11.getText() != null && textLine11.getText().trim().length() != 0) {
            
            if(textLine11.getLayout() !=null && textLine11.getLayout().getTypeCode()!=null && !textLine11.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText11 = textLine11.getText().trim();
            }else {
                lineText11 = "";
            }
            
        } else {
            lineText11 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT12)
    public CouponRemindersDataDetailLineTextVO getTextLine12() {
        return textLine12;
    }

    public void setTextLine12(CouponRemindersDataDetailLineTextVO textLine12) {
        this.textLine12 = textLine12;
        if (textLine12.getText() != null && textLine12.getText().trim().length() != 0) {
            
            if(textLine12.getLayout() !=null && textLine12.getLayout().getTypeCode()!=null && !textLine12.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText12 = textLine12.getText().trim();
            }else {
                lineText12 = "";
            }
            
        } else {
            lineText12 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT13)
    public CouponRemindersDataDetailLineTextVO getTextLine13() {
        return textLine13;
    }

    public void setTextLine13(CouponRemindersDataDetailLineTextVO textLine13) {
        this.textLine13 = textLine13;
        if (textLine13.getText() != null && textLine13.getText().trim().length() != 0) {
            
            if(textLine13.getLayout() !=null && textLine13.getLayout().getTypeCode()!=null && !textLine13.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText13 = textLine13.getText().trim();
            }else {
                lineText13 = "";
            }
            
        } else {
            lineText13 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT14)
    public CouponRemindersDataDetailLineTextVO getTextLine14() {
        return textLine14;
    }

    public void setTextLine14(CouponRemindersDataDetailLineTextVO textLine14) {
        this.textLine14 = textLine14;
        if (textLine14.getText() != null && textLine14.getText().trim().length() != 0) {
            
            if(textLine14.getLayout() !=null && textLine14.getLayout().getTypeCode()!=null && !textLine14.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText14 = textLine14.getText().trim();
            }else {
                lineText14 = "";
            }
            
        } else {
            lineText14 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT15)
    public CouponRemindersDataDetailLineTextVO getTextLine15() {
        return textLine15;
    }

    public void setTextLine15(CouponRemindersDataDetailLineTextVO textLine15) {
        this.textLine15 = textLine15;
        if (textLine15.getText() != null && textLine15.getText().trim().length() != 0) {
            
            if(textLine15.getLayout() !=null && textLine15.getLayout().getTypeCode()!=null && !textLine15.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText15 = textLine15.getText().trim();
            }else {
                lineText15 = "";
            }
            
        } else {
            lineText15 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT2)
    public CouponRemindersDataDetailLineTextVO getTextLine2() {
        return textLine2;
    }

    public void setTextLine2(CouponRemindersDataDetailLineTextVO textLine2) {
        this.textLine2 = textLine2;
        if (textLine2.getText() != null && textLine2.getText().trim().length() != 0) {
            
            if(textLine2.getLayout() !=null && textLine2.getLayout().getTypeCode()!=null && !textLine2.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText2 = textLine2.getText().trim();
            }else {
                lineText2 = "";
            }
            
        } else {
            lineText2 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT3)
    public CouponRemindersDataDetailLineTextVO getTextLine3() {
        return textLine3;
    }

    public void setTextLine3(CouponRemindersDataDetailLineTextVO textLine3) {
        this.textLine3 = textLine3;
        if (textLine3.getText() != null && textLine3.getText().trim().length() != 0) {
            
            if(textLine3.getLayout() !=null && textLine3.getLayout().getTypeCode()!=null && !textLine3.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText3 = textLine3.getText().trim();
            }else {
                lineText3 = "";
            }
            
        } else {
            lineText3 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT4)
    public CouponRemindersDataDetailLineTextVO getTextLine4() {
        return textLine4;
    }

    public void setTextLine4(CouponRemindersDataDetailLineTextVO textLine4) {
        this.textLine4 = textLine4;
        if (textLine4.getText() != null && textLine4.getText().trim().length() != 0) {
            
            if(textLine4.getLayout() !=null && textLine4.getLayout().getTypeCode()!=null && !textLine4.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText4 = textLine4.getText().trim();
            }else {
                lineText4 = "";
            }
            
        } else {
            lineText4 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT5)
    public CouponRemindersDataDetailLineTextVO getTextLine5() {
        return textLine5;
    }

    public void setTextLine5(CouponRemindersDataDetailLineTextVO textLine5) {
        this.textLine5 = textLine5;
        if (textLine5.getText() != null && textLine5.getText().trim().length() != 0) {
            
            if(textLine5.getLayout() !=null && textLine5.getLayout().getTypeCode()!=null && !textLine5.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText5 = textLine5.getText().trim();
            }else {
                lineText5 = "";
            }
            
        } else {
            lineText5 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT6)
    public CouponRemindersDataDetailLineTextVO getTextLine6() {
        return textLine6;
    }

    public void setTextLine6(CouponRemindersDataDetailLineTextVO textLine6) {
        this.textLine6 = textLine6;
        if (textLine6.getText() != null && textLine6.getText().trim().length() != 0) {
            
            if(textLine6.getLayout() !=null && textLine6.getLayout().getTypeCode()!=null && !textLine6.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText6 = textLine6.getText().trim();
            }else {
                lineText6 = "";
            }
            
        } else {
            lineText6 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT7)
    public CouponRemindersDataDetailLineTextVO getTextLine7() {
        return textLine7;
    }

    public void setTextLine7(CouponRemindersDataDetailLineTextVO textLine7) {
        this.textLine7 = textLine7;
        if (textLine7.getText() != null && textLine7.getText().trim().length() != 0) {
            
             if(textLine7.getLayout() !=null && textLine7.getLayout().getTypeCode()!=null && !textLine7.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText7 = textLine7.getText().trim();
            }else {
                lineText7 = "";
            }
            
        } else {
            lineText7 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT8)
    public CouponRemindersDataDetailLineTextVO getTextLine8() {
        return textLine8;
    }

    public void setTextLine8(CouponRemindersDataDetailLineTextVO textLine8) {
        this.textLine8 = textLine8;
        if (textLine8.getText() != null && textLine8.getText().trim().length() != 0) {
            
             if(textLine8.getLayout() !=null && textLine8.getLayout().getTypeCode()!=null && !textLine8.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText8 = textLine8.getText().trim();
            }else {
                lineText8 = "";
            }
            
        } else {
            lineText8 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT9)
    public CouponRemindersDataDetailLineTextVO getTextLine9() {
        return textLine9;
    }

    public void setTextLine9(CouponRemindersDataDetailLineTextVO textLine9) {
        this.textLine9 = textLine9;
        if (textLine9.getText() != null && textLine9.getText().trim().length() != 0) {
             if(textLine9.getLayout() !=null && textLine9.getLayout().getTypeCode()!=null && !textLine9.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText9 = textLine9.getText().trim();
            }else {
                lineText9 = "";
            }
            
        } else {
            lineText9 = "";
        }
    }

    // @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT11)
    public String getLineText11() {
        return lineText11;
    }

    public void setLineText11(String lineText11) {
        this.lineText11 = lineText11;
    }

    // @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT12)
    public String getLineText12() {
        return lineText12;
    }

    public void setLineText12(String lineText12) {
        this.lineText12 = lineText12;
    }

    // @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT13)
    public String getLineText13() {
        return lineText13;
    }

    public void setLineText13(String lineText13) {
        this.lineText13 = lineText13;
    }

    // @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT14)
    public String getLineText14() {
        return lineText14;
    }

    public void setLineText14(String lineText14) {
        this.lineText14 = lineText14;
    }

    // @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT15)
    public String getLineText15() {
        return lineText15;
    }

    public void setLineText15(String lineText15) {
        this.lineText15 = lineText15;
    }

    //  @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT16)
    public String getLineText16() {
        return lineText16;
    }

    public void setLineText16(String lineText16) {
        this.lineText16 = lineText16;
    }

    // @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT17)
    public String getLineText17() {
        return lineText17;
    }

    public void setLineText17(String lineText17) {
        this.lineText17 = lineText17;
    }

    //@XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT18)
    public String getLineText18() {
        return lineText18;
    }

    public void setLineText18(String lineText18) {
        this.lineText18 = lineText18;
    }

    // @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT19)
    public String getLineText19() {
        return lineText19;
    }

    public void setLineText19(String lineText19) {
        this.lineText19 = lineText19;
    }

    //  @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT20)
    public String getLineText20() {
        return lineText20;
    }

    public void setLineText20(String lineText20) {
        this.lineText20 = lineText20;
    }

    // @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT21)
    public String getLineText21() {
        return lineText21;
    }

    public void setLineText21(String lineText21) {
        this.lineText21 = lineText21;
    }

    // @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT22)
    public String getLineText22() {
        return lineText22;
    }

    public void setLineText22(String lineText22) {
        this.lineText22 = lineText22;
    }

    // @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT23)
    public String getLineText23() {
        return lineText23;
    }

    public void setLineText23(String lineText23) {
        this.lineText23 = lineText23;
    }

    // @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT24)
    public String getLineText24() {
        return lineText24;
    }

    public void setLineText24(String lineText24) {
        this.lineText24 = lineText24;
    }

    //  @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT25)
    public String getLineText25() {
        return lineText25;
    }

    public void setLineText25(String lineText25) {
        this.lineText25 = lineText25;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_DISCLAIMER)
    public String getDisclaimerId() {
        return disclaimerId;
    }

    public void setDisclaimerId(String disclaimerId) {
        this.disclaimerId = disclaimerId;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_EXPIRATIONDATE)
    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT16)
    public CouponRemindersDataDetailLineTextVO getTextLine16() {
        return textLine16;
    }

    public void setTextLine16(CouponRemindersDataDetailLineTextVO textLine16) {
        this.textLine16 = textLine16;
        if (textLine16.getText() != null && textLine16.getText().trim().length() != 0) {
            
            if(textLine16.getLayout() !=null && textLine16.getLayout().getTypeCode()!=null && !textLine16.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText16 = textLine16.getText().trim();
            }else {
                lineText16 = "";
            }
            
        } else {
            lineText16 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT17)
    public CouponRemindersDataDetailLineTextVO getTextLine17() {
        return textLine17;
    }

    public void setTextLine17(CouponRemindersDataDetailLineTextVO textLine17) {
        this.textLine17 = textLine17;
        if (textLine17.getText() != null && textLine17.getText().trim().length() != 0) {
            
            if(textLine17.getLayout() !=null && textLine17.getLayout().getTypeCode()!=null && !textLine17.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText17 = textLine17.getText().trim();
            }else {
                lineText17 = "";
            }
            
        } else {
            lineText17 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT18)
    public CouponRemindersDataDetailLineTextVO getTextLine18() {
        return textLine18;
    }

    public void setTextLine18(CouponRemindersDataDetailLineTextVO textLine18) {
        this.textLine18 = textLine18;
        if (textLine18.getText() != null && textLine18.getText().trim().length() != 0) {
            
            if(textLine18.getLayout() !=null && textLine18.getLayout().getTypeCode()!=null && !textLine18.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText18 = textLine18.getText().trim();
            }else {
                lineText18 = "";
            }
            
        } else {
            lineText18 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT19)
    public CouponRemindersDataDetailLineTextVO getTextLine19() {
        return textLine19;
    }

    public void setTextLine19(CouponRemindersDataDetailLineTextVO textLine19) {
        this.textLine19 = textLine19;
        if (textLine19.getText() != null && textLine19.getText().trim().length() != 0) {
            
            if(textLine19.getLayout() !=null && textLine19.getLayout().getTypeCode()!=null && !textLine19.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText19 = textLine19.getText().trim();
            }else {
                lineText19 = "";
            }
            
        } else {
            lineText19 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT20)
    public CouponRemindersDataDetailLineTextVO getTextLine20() {
        return textLine20;
    }

    public void setTextLine20(CouponRemindersDataDetailLineTextVO textLine20) {
        this.textLine20 = textLine20;
        if (textLine20.getText() != null && textLine20.getText().trim().length() != 0) {
            
            if(textLine20.getLayout() !=null && textLine20.getLayout().getTypeCode()!=null && !textLine20.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText20 = textLine20.getText().trim();
            }else {
                lineText20 = "";
            }
            
        } else {
            lineText20 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT21)
    public CouponRemindersDataDetailLineTextVO getTextLine21() {
        return textLine21;
    }

    public void setTextLine21(CouponRemindersDataDetailLineTextVO textLine21) {
        this.textLine21 = textLine21;
        if (textLine21.getText() != null && textLine21.getText().trim().length() != 0) {
            
            if(textLine21.getLayout() !=null && textLine21.getLayout().getTypeCode()!=null && !textLine21.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText21 = textLine21.getText().trim();
            }else {
                lineText21 = "";
            }
            
        } else {
            lineText21 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT22)
    public CouponRemindersDataDetailLineTextVO getTextLine22() {
        return textLine22;
    }

    public void setTextLine22(CouponRemindersDataDetailLineTextVO textLine22) {
        this.textLine22 = textLine22;
        if (textLine22.getText() != null && textLine22.getText().trim().length() != 0) {
            
             if(textLine22.getLayout() !=null && textLine22.getLayout().getTypeCode()!=null && !textLine22.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText22 = textLine22.getText().trim();
            }else {
                lineText22 = "";
            }
            
        } else {
            lineText22 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT23)
    public CouponRemindersDataDetailLineTextVO getTextLine23() {
        return textLine23;
    }

    public void setTextLine23(CouponRemindersDataDetailLineTextVO textLine23) {
        this.textLine23 = textLine23;
        if (textLine23.getText() != null && textLine23.getText().trim().length() != 0) {
            
            if(textLine23.getLayout() !=null && textLine23.getLayout().getTypeCode()!=null && !textLine23.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText23 = textLine23.getText().trim();
            }else {
                lineText23 = "";
            }
            
        } else {
            lineText23 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT24)
    public CouponRemindersDataDetailLineTextVO getTextLine24() {
        return textLine24;
    }

    public void setTextLine24(CouponRemindersDataDetailLineTextVO textLine24) {
        this.textLine24 = textLine24;
        if (textLine24.getText() != null && textLine24.getText().trim().length() != 0) {
            if(textLine24.getLayout() !=null && textLine24.getLayout().getTypeCode()!=null && !textLine24.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText24 = textLine24.getText().trim();
            }else {
                lineText24 = "";
            }
        } else {
            lineText24 = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LINETEXT25)
    public CouponRemindersDataDetailLineTextVO getTextLine25() {
        return textLine25;
    }

    public void setTextLine25(CouponRemindersDataDetailLineTextVO textLine25) {
        this.textLine25 = textLine25;
        if (textLine25.getText() != null && textLine25.getText().trim().length() != 0) {            
            if(textLine25.getLayout() !=null && textLine25.getLayout().getTypeCode()!=null && !textLine25.getLayout().getTypeCode().trim().equalsIgnoreCase(ApplicationConstants.SERVICEREMINDER_LINETEXT_LAYOUT_TYPECODE_CONSTANT)){
                lineText25 = textLine25.getText().trim();
            }else {
                lineText25 = "";
            }
        } else {
            lineText25 = "";
        }
    }

    // New Set of properites to be introduced on 10 August.
    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_DEALERADDRESS1)
    public String getDealerAddr1() {
        return dealerAddr1;
    }

    public void setDealerAddr1(String dealerAddr1) {
        this.dealerAddr1 = dealerAddr1;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_DEALERADDRESS2)
    public String getDealerAddr2() {
        return dealerAddr2;
    }

    public void setDealerAddr2(String dealerAddr2) {
        this.dealerAddr2 = dealerAddr2;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_DEALERCITY)
    public String getDealerCity() {
        return dealerCity;
    }

    public void setDealerCity(String dealerCity) {
        this.dealerCity = dealerCity;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_DEALERCOUNTRY)
    public String getDealerCountry() {
        return dealerCountry;
    }

    public void setDealerCountry(String dealerCountry) {
        this.dealerCountry = dealerCountry;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_DEALERSTATE)
    public String getDealerState() {
        return dealerState;
    }

    public void setDealerState(String dealerState) {
        this.dealerState = dealerState;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_DEALERZIP)
    public String getDealerZip() {
        return dealerZip;
    }

    public void setDealerZip(String dealerZip) {
        this.dealerZip = dealerZip;
    }
    
    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_LEFTHEADER)
    public CouponReminderHeaderVO getLeftheader() {
        return leftheader;
    }

    public void setLeftheader(CouponReminderHeaderVO leftheader) {
        this.leftheader = leftheader;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_RIGHTHEADER)
    public CouponReminderHeaderVO getRightheader() {
        return rightheader;
    }

    public void setRightheader(CouponReminderHeaderVO rightheader) {
        this.rightheader = rightheader;
    }


}
