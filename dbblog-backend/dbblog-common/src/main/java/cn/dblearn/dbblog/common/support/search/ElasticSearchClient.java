package cn.dblearn.dbblog.common.support.search;

import java.net.InetAddress;

import cn.dblearn.dbblog.common.Constant;
import cn.dblearn.dbblog.common.util.PropertiesUtil;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Component;

@Component
public class ElasticSearchClient {

	public String hostName;
	public int port;
	public String clusterName;

	TransportClient client = null;

	@SuppressWarnings("resource")
	public ElasticSearchClient(){
		this.hostName = PropertiesUtil.getString("es.host","127.0.0.1");
		this.port = PropertiesUtil.getInt("es.port",9300);
		this.clusterName = PropertiesUtil.getString("es.clusterName", Constant.ES_DEFAULT_CLUSTERNAME);
		try {
	          //设置集群名称
	          Settings settings = Settings.builder().put("cluster.name", clusterName).build();
	          //创建client
	          client = new PreBuiltTransportClient(settings)
	                  .addTransportAddress(new TransportAddress(InetAddress.getByName(hostName), port));
	     } catch (Exception e) {
	    	  client.close();
	          e.printStackTrace();
	     }
	}

	@SuppressWarnings("resource")
	public ElasticSearchClient(String hostName, int port, String clusterName){
		this.hostName = hostName;
		this.port = port;
		this.clusterName = clusterName;
		try {
	          //设置集群名称
	          Settings settings = Settings.builder().put("cluster.name", clusterName).build();
	          //创建client
	          client = new PreBuiltTransportClient(settings)
	                  .addTransportAddress(new TransportAddress(InetAddress.getByName(hostName), port));
	     } catch (Exception e) {
	    	  client.close();
	          e.printStackTrace();
	     }
	}

	public  TransportClient getConnection(){
        if (client == null){
            synchronized (ElasticSearchClient.class){
                if (client==null){
                    new ElasticSearchClient(hostName, port, clusterName);
                }
            }
        }
        return  client;

	}

	public static void close(TransportClient client) {
        if (client != null)
            client.close();
    }
}
