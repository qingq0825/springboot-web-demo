package com.bm.commont.elastic;

import com.bm.commont.enums.IAppEnum;
import com.bm.commont.exception.IResultException;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Repository;

/**
 * 描述:
 * 操作elasticsearch工具类
 *
 * @author 北明软件
 * @create 2020-07-29 17:22
 */
@Slf4j
@Repository
@SuppressWarnings("ALL")
public class EsRepository {
    public EsRepository() {
        super();
    }

    /**
     * 配置es的index
     */
    @Value("${indexName}")
    private String indexName;

    /**
     * 配置es的index
     */
    @Value("${indexType}")
    private String indexType;

    @Autowired
    private ElasticsearchTemplate template;


    /**
     * 根据termQuery查询ES获取单片稿件
     *
     * @param termQuery
     * @return
     */
    public SearchHits findOne(TermQueryBuilder termQuery) {
        SearchResponse response = this.getClient().prepareSearch(indexName)
                .setTypes(indexType)
                .setQuery(termQuery)
                .get();

        //获取总数量
        long totalCount = response.getHits().getTotalHits();
        if (totalCount == 0) {
            log.warn("ES search 查无数据，查询条件termQuery：{}", termQuery);
            throw new IResultException(IAppEnum.QUERY_RESULT_IS_EMPTY_EXCEPTION);
        }

        SearchHits hits = response.getHits();
        return hits;
    }

    /**
     * 获取ES连接客户端
     *
     * @return
     */
    private Client getClient() {
        Client client = template.getClient();
        return client;
    }
}
