package com.doublegrooverecords.vinyl;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Publisher {
    private Long id;
    private String name;
    private String shortName;
    private String contactNumber;
    private String contactName;

    @Override
    public boolean equals(Object o) {
        if ((Publisher)o == null)
            return false;

        Publisher other = (Publisher)o;
        return other.getId() == this.getId();
    }
}
