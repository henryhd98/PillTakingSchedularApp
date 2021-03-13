package com.Henry.poppinsmarter.data;

public class ActivityHelperClass {



        String dateTime, activity;

        public ActivityHelperClass() {
        }

        public ActivityHelperClass(String dateTime, String history) {
            this.dateTime = dateTime;
            this.activity = history;

        }



        public String getDatetime() {
            return dateTime;
        }

        public void setDatetime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getActivity() {
            return activity;
        }

        public void setActivity(String activity) {

            this.activity = activity;
        }


    }

