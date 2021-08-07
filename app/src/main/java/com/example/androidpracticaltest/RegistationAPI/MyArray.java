package com.example.androidpracticaltest.RegistationAPI;

import java.util.Date;

public class MyArray {
    public int pkId;
   // @JsonProperty("CompanyName")
    public String companyName;
   // @JsonProperty("SiteURL")
    public String siteURL;
   // @JsonProperty("ExpiryDate")
    public String expiryDate;
   // @JsonProperty("MobileAppVersion")
    public String mobileAppVersion;
   // @JsonProperty("PortNo")
    public int portNo;
   // @JsonProperty("ISAMC")
    public String iSAMC;

    public int getPkId() {
        return pkId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getSiteURL() {
        return siteURL;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getMobileAppVersion() {
        return mobileAppVersion;
    }

    public int getPortNo() {
        return portNo;
    }

    public String getiSAMC() {
        return iSAMC;
    }
}
