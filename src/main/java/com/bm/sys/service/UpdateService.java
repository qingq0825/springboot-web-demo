package com.bm.sys.service;

import com.bm.commont.dto.Result;

/**
 * 描述:
 * 更新档案馆数据逻辑接口
 *
 * @author 北明软件
 * @create 2020-07-29 11:14
 */
public interface UpdateService {

    /**
     * 用于更新MongoDB和ES中的内容
     *
     * @param oriDocumentId
     * @return
     */
    Result update(Integer oriDocumentId);

}
