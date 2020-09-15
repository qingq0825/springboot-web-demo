package com.bm.sys.service.impl;

import com.bm.commont.dto.MongoMapperDTO;
import com.bm.commont.dto.Result;
import com.bm.commont.mongo.MongoRepository;
import com.bm.commont.handle.ResultFactory;
import com.bm.sys.service.UpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * 描述:
 * 更新档案馆的mongoDB和ES的内容
 *
 * @author 北明软件
 * @create 2020-07-29 15:15
 */

@Slf4j
@Service
@PropertySource("classpath:config.properties")
@SuppressWarnings("ALL")
public class UpdateServiceImpl implements UpdateService {
    public UpdateServiceImpl() {
        super();
    }

    @Value("${mapperCollectionName}")
    private String mapperCollectionName;

    @Autowired
    private MongoRepository mongoRepository;

    @Override
    public Result update(Integer oriDocumentId) {

        return ResultFactory.success(get(oriDocumentId));
    }

    /**
     * 获取MongoDB全量映射表中的记录
     *
     * @param oriDocumentId
     * @return
     */
    public MongoMapperDTO get(Integer oriDocumentId) {
        MongoMapperDTO mongoMapper = mongoRepository.getByOriDocumentId(mapperCollectionName, oriDocumentId);
        return mongoMapper;
    }
}
