package io.github.yoandreferreira.shopapi.enumeration;

import lombok.Getter;

@Getter
public enum UtilEnum {

    pending("PENDING");

    private String id;

    UtilEnum(String id) {
        this.id = id;
    }


}
