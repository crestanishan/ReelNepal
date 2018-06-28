package com.example.nishan.reelnepal.Navigation.MovieCalender_Nav;

public class DateItem extends ListItem {

    private String month;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public int getType() {
        return TYPE_DATE;
    }
}
