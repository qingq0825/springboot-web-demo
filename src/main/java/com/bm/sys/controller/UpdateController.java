package com.bm.sys.controller;

import com.bm.commont.dto.Result;
import com.bm.commont.elastic.EsRepository;
import com.bm.sys.service.UpdateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 描述:
 * 更新档案馆中MongoDB+ES中的content字段值
 *
 * @author 北明软件
 * @create 2020-07-19 19:01
 */
@Api(tags = "档案馆相关接口")
@Slf4j
@RestController
@Validated
@SuppressWarnings("ALL")
public class UpdateController {
    public UpdateController() {
        super();
    }

    @Autowired
    private UpdateService updateService;

    @Autowired
    private EsRepository esRepository;

    /**
     * 更新档案馆的MongoDB+ES的稿件内容
     *
     * @param oriDocId
     * @return
     */
    @ApiOperation(value = "更新档案馆系统中的稿件内容")
    @GetMapping(value = "update")
    public Object update(@NotNull(message = "oriDocId 不能为空！！") @RequestParam(value = "oriDocId") Integer oriDocId) {
        log.info("接收参数oriDocId ：{}", oriDocId);

        Result update = updateService.update(oriDocId);

        return update.getData();
    }

    @ApiOperation(value = "查询档案馆ES中的稿件信息")
    @GetMapping("search")
    public void search() {
        // 使用docId简单查询
        TermQueryBuilder termQuery = QueryBuilders.termQuery("docId", "211002020061604058418");

        SearchHits search = esRepository.findOne(termQuery);
        SearchHit[] hits = search.getHits();
        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println();
        }
    }
}
