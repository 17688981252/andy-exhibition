package com.zel.common.enums;

/**
 * 展会状态
 * 
 * @author andy
 */
public enum ExhibitionStatus
{
    SAVE(1, "保存"),
    PROSPECT(2, "勘展"),
    ARRANGE(3, "布展"),
    REVOKE(4, "撤展"),
    END(5,"结束");

    private final int code;
    private final String info;

    ExhibitionStatus(int code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public int getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}



