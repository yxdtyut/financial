package com.yxdtyut.saller.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @Author : yangxudong
 * @Description :   渠道枚举
 * @Date : 下午1:58 2018/6/13
 */
@Getter
public enum ChanEnum {
    ABC("123","ABC","/Users/yangxudong/Desktop/abc");
    private String chanId;
    private String chanName;
    private String ftpUrl,ftpName,ftppwd;
    private String rootDir;

    ChanEnum(String chanId, String chanName, String rootDir) {
        this.chanId = chanId;
        this.chanName = chanName;
        this.rootDir = rootDir;
    }

    public static ChanEnum getChanEnumById(String chanId) {
        ChanEnum[] values = ChanEnum.values();
        for (ChanEnum chanEnum: values) {
            if (chanEnum.getChanId().equals(chanId)) {
                return chanEnum;
            }
        }
        return null;
    }
}
