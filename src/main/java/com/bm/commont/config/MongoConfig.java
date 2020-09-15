package com.bm.commont.config;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.net.UnknownHostException;


/**
 * 描述:
 * 配置MongoDB多数据源
 *
 * @author 北明软件
 * @create 2020-07-29 16:19
 */
@Configuration
@SuppressWarnings("ALL")
public class MongoConfig  {
    public MongoConfig() {
        super();
    }

    /**
     * 档案馆正式环境MongoDB的连接的URI
     */
    @Value("${spring.data.mongodb.pro.uri}")
    private String proMongoUri;

    /**
     * 档案馆测试环境MongoDB的连接URI
     */
    @Value("${spring.data.mongodb.dev.uri}")
    private String devMongoUri;

    // ===================== 连接到第一个 mongodb 服务器 =================================

    /**
     * 配置默认的连接
     *
     * @return
     */
    @Bean
    @Primary
    public MongoMappingContext mongoMappingContext() {
        MongoMappingContext mappingContext = new MongoMappingContext();
        return mappingContext;
    }

    /**
     * 使用自定义的typeMapper去除写入mongodb时的“_class”字段
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Primary
    public MappingMongoConverter mappingMongoConverter() throws Exception {
        DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(this.dbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

    /**
     * 设置MongoDB1连接
     *
     * @return
     * @throws UnknownHostException
     */
    @Bean
    @Primary
    public MongoDbFactory dbFactory() throws UnknownHostException {
        return new SimpleMongoDbFactory(new MongoClientURI(proMongoUri));
    }

    /**
     * 实例化MongoTemplate1
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Primary
    public MongoTemplate proMongoTemplate() throws Exception {
        return new MongoTemplate(dbFactory(), this.mappingMongoConverter());
    }

// ===================== 连接到第二个 mongodb 服务器 =================================

    @Bean
    public MongoMappingContext mongoMappingContext2() {
        MongoMappingContext mappingContext = new MongoMappingContext();
        return mappingContext;
    }

    /**
     * 使用自定义的typeMapper去除写入mongodb时的“_class”字段
     *
     * @return
     * @throws Exception
     */
    @Bean
    public MappingMongoConverter mappingMongoConverter2() throws Exception {
        DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(this.dbFactory2());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext2());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

    /**
     * 设置MongoDB2连接
     *
     * @return
     * @throws UnknownHostException
     */
    @Bean
    public MongoDbFactory dbFactory2() throws UnknownHostException {
        return new SimpleMongoDbFactory(new MongoClientURI(devMongoUri));
    }

    /**
     * 实例化MongoTemplate2
     *
     * @return
     * @throws Exception
     */
    @Bean
    public MongoTemplate devMongoTemplate() throws Exception {
        return new MongoTemplate(this.dbFactory2(), this.mappingMongoConverter2());
    }


}
