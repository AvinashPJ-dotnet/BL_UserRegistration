package com.bridgelab.configurations;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
//@EnableElasticsearchRepositories
public class ElasticSearchConfig {
	
	@Bean
	public RestHighLevelClient elasticsearchClient() {
		 final RestHighLevelClient clientConfiguration =
	                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http")));

	        return clientConfiguration;

	}
	

//	@Value("${elasticsearch.host}")
//    private String EsHost;
//
//    @Value("${elasticsearch.port}")
//    private int EsPort;
//
//    @Value("${elasticsearch.clustername}")
//    private String EsClusterName;
//
//    @Bean
//    public Client client() throws Exception {
//        Settings esSettings = Settings.settingsBuilder()
//                .put("cluster.name", EsClusterName)
//                .build();
//
//        return TransportClient.builder()
//                .settings(esSettings)
//                .build()
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort));
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
//        return new ElasticsearchTemplate(client());
//    }
}
