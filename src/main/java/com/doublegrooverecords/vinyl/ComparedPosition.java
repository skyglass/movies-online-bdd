package com.doublegrooverecords.vinyl;

public enum ComparedPosition {
    NoChange("no-change"), Increased("increased"), Decreased("decreased"), NewToList("new-to-list");

    private final String displayName;

    ComparedPosition(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
