package com.bm.commont.mongo;

import com.bm.commont.dto.MongoMapperDTO;
import com.bm.commont.enums.IAppEnum;
import com.bm.commont.exception.IParamsException;
import com.bm.commont.exception.IResultException;
import com.bm.commont.util.Constant;
import com.bm.commont.util.StringLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * 描述:
 * 操作MongoDB处理类
 *
 * @author 北明软件
 * @create 2020-07-29 11:17
 */

@SuppressWarnings("ALL")
@Slf4j
@Repository
public class MongoRepository {
    public MongoRepository() {
        super();
    }

    /**
     * 档案馆正式环境的MongoDB模板
     */
    @Autowired
    private MongoTemplate proMongoTemplate;

    /**
     * 档案馆测试环境的MongoDB模板
     */
    @Autowired
    private MongoTemplate devMongoTemplate;

    /**
     * 根据oriDocumentId获取mongoDB中记录
     *
     * @param collectionName 集合名称
     * @param oriDocumentId  原始稿件ID
     * @return
     */
    public MongoMapperDTO getByOriDocumentId(String collectionName, Integer oriDocumentId) {
        if (StringLocalUtils.isEmpty(oriDocumentId) || StringUtils.isBlank(collectionName)) {
            // 如果参数为空，则抛出参数异常
            log.warn("getByOriDocumentId 参数异常, collectionName={},oriDocumentId={}", collectionName, oriDocumentId);
            throw new IParamsException(IAppEnum.PARAMS_EXCEPTION);
        }
        Criteria criteria = Criteria.where(Constant.ORI_DOCUMENTID).is(oriDocumentId);
        Query query = new Query(criteria);
        query.fields().include("oriDocumentId").include("oriDocLibId").include("docId");
        MongoMapperDTO mapperDTO = devMongoTemplate.findOne(query, MongoMapperDTO.class, collectionName);
        if (mapperDTO == null) {
            log.warn("getByOriDocumentId 查无数据，查询条件query：{}", query);
            throw new IResultException(IAppEnum.QUERY_RESULT_IS_EMPTY_EXCEPTION);
        }

        return mapperDTO;
    }

}
