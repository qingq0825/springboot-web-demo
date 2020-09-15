package com.bm.commont.dto;

import lombok.Data;

/**
 * 描述:
 * 用于接收MongoDB全量映射表返回的字段
 *
 * @author 北明软件
 * @create 2020-07-29 11:27
 */
@Data
@SuppressWarnings("ALL")
public class MongoMapperDTO {
    public MongoMapperDTO() {
        super();
    }

    /**
     * 原始稿件ID
     */
    private Integer oriDocumentId;

    /**
     * 原始库ID
     */
    private Integer oriDocLibId;

    /**
     * 档案馆存的稿件ID
     */
    private String docId;
}
