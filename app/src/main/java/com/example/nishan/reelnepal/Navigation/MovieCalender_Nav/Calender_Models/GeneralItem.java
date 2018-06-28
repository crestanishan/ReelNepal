package com.example.nishan.reelnepal.Navigation.MovieCalender_Nav.Calender_Models;

import com.example.nishan.reelnepal.Navigation.MovieCalender_Nav.ListItem;

public class GeneralItem extends ListItem {

    private ResultItem resultItem;

    public ResultItem getResultItem() {
        return resultItem;
    }

    public void setResultItem(ResultItem resultItem) {
        this.resultItem = resultItem;
    }

    @Override
    public int getType() {
        return TYPE_GENERAL;
    }
}
