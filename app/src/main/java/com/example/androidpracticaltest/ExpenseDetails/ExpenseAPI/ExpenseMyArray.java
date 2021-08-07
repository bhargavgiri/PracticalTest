package com.example.androidpracticaltest.ExpenseDetails.ExpenseAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpenseMyArray {



        @SerializedName("pkID")
        @Expose
        private Integer pkID;
        @SerializedName("ExpenseTypeName")
        @Expose
        private String expenseTypeName;
        @SerializedName("IsLocationRequired")
        @Expose
        private Boolean isLocationRequired;

        public Integer getPkID() {
            return pkID;
        }

        public void setPkID(Integer pkID) {
            this.pkID = pkID;
        }

        public String getExpenseTypeName() {
            return expenseTypeName;
        }

        public void setExpenseTypeName(String expenseTypeName) {
            this.expenseTypeName = expenseTypeName;
        }

        public Boolean getIsLocationRequired() {
            return isLocationRequired;
        }

        public void setIsLocationRequired(Boolean isLocationRequired) {
            this.isLocationRequired = isLocationRequired;
        }


}
