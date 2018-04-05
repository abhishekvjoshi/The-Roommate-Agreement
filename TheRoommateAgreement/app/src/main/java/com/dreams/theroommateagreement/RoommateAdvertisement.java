package com.dreams.theroommateagreement;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by root on 3/24/18.
 */

public class RoommateAdvertisement {
    private String name;
    private String address;
    private Date startDate;
    private boolean isImportant;

    RoommateAdvertisement(final String name,
                          final String address) {
        this.name = name;
        this.address = address;
//        this.startDate = startDate;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }
}
