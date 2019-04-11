package com.xjl.sentinel.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.alibaba.sentinel.SentinelProperties;
import org.springframework.cloud.alibaba.sentinel.datasource.config.NacosDataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * 
 * @Title: DataSourceInitFunc.java
 * @Description: 配置Sentinel 熔断规则初始化配置类
 * @Team 电子科技大学自动化研究所
 * @Author 许京乐
 * @Date 2019年4月6日 下午9:50:02
 * @Version V1.0
 */
@Configuration
public class DataSourceInitFunc {

	Logger logger = LoggerFactory.getLogger(DataSourceInitFunc.class);

	@Autowired
	private SentinelProperties sentinelProperties;

	@Bean
	public DataSourceInitFunc init() throws Exception {

		logger.info("[NacosSource初始化,从Nacos中获取熔断规则]");

		sentinelProperties.getDatasource().entrySet().stream().filter(map -> {
			return map.getValue().getNacos() != null;
		}).forEach(map -> {
			NacosDataSourceProperties nacos = map.getValue().getNacos();
			// 限流规则，需要Nacos的dataId中包含flow字符串
			if(nacos.getDataId().contains("flow")){
				ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(nacos.getServerAddr(),
						nacos.getGroupId(), nacos.getDataId(),
						source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
						}));
				FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
			}
			
			// 降级规则，需要Nacos的dataId中包含degrade字符串
			if(nacos.getDataId().contains("degrade")){
				ReadableDataSource<String, List<DegradeRule>> flowRuleDataSource = new NacosDataSource<>(nacos.getServerAddr(),
						nacos.getGroupId(), nacos.getDataId(),
						source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {
						}));
				DegradeRuleManager.register2Property(flowRuleDataSource.getProperty());
			}
			
		});
		return new DataSourceInitFunc();
	}
}
