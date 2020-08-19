package cn.zeddli.topology;

import cn.zeddli.data_source.FileSpout;
import cn.zeddli.data_source.OrderSpout;
import cn.zeddli.statistics.CountBolt;
import cn.zeddli.statistics.SplitBolt;
import cn.zeddli.statistics.SumBolt;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;

/**
 * @Author:zedd Li
 * @date:2020/8/18
 * 监控指定目录下的文件，E:\data.txt
 */
public class ConutTopology {

    /**
     * 构建拓扑，组装Spout和Bolt结点
     * */
    public static void main(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();

        /**
         * 设置spout和bolt的DAG有向无环图
         * */
        builder.setSpout("id_file_spout", new FileSpout());
        builder.setBolt("id_splitBolt", new SplitBolt()).shuffleGrouping("id_file_spout");
        builder.setBolt("id_count_bolt", new CountBolt()).shuffleGrouping("id_splitBolt");

        StormTopology topology = builder.createTopology();

        LocalCluster localCluster = new LocalCluster();
        String topologyName = SumTopology.class.getSimpleName();
        Config config = new Config();
        localCluster.submitTopology(topologyName,config,topology);
    }
}
