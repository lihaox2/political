package com.bayee.political;

import com.bayee.political.mapper.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author xxl
 * @date 2021/8/19
 */
@Configuration
@PropertySource({ "classpath:db.properties"})
public class InspectorAppConfiguration {

    @Autowired
    private Environment env;

    @Bean("jdSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.getObject().getConfiguration().addMapper(ConductBureauRecordTypeViewsMapper.class);
        sqlSessionFactory.getObject().getConfiguration().addMapper(ConductBureauRecordViewsMapper.class);
        sqlSessionFactory.getObject().getConfiguration().addMapper(MeasuresViewsMapper.class);
        return sqlSessionFactory.getObject();
    }

    @Bean("jdDataSourceTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource());
        return txManager;
    }

    @Bean
    public MeasuresViewsMapper measuresViewsMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sessionTemplate.getMapper(MeasuresViewsMapper.class);
    }

    @Bean
    public ConductBureauRecordViewsMapper conductBureauRecordViewsMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sessionTemplate.getMapper(ConductBureauRecordViewsMapper.class);
    }

    @Bean
    public ConductBureauRecordTypeViewsMapper conductBureauRecordTypeViewsMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sessionTemplate.getMapper(ConductBureauRecordTypeViewsMapper.class);
    }

    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jd.jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jd.jdbc.url"));
        dataSource.setUsername(env.getProperty("jd.jdbc.username"));
        dataSource.setPassword(env.getProperty("jd.jdbc.password"));
        dataSource.setDefaultAutoCommit(true);
        return dataSource;
    }

}
