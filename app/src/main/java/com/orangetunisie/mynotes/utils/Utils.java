package com.orangetunisie.mynotes.utils;

import android.text.format.DateUtils;

import com.orangetunisie.mynotes.data.entity.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {

    public static String timetogo(long timestamp) {
        return DateUtils.getRelativeTimeSpanString(timestamp,new Date().getTime(), DateUtils.FORMAT_ABBREV_WEEKDAY).toString();
    }

    public static List<Note> data = new ArrayList<>();
}
