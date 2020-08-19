package cn.zeddli.topology;

import cn.zeddli.data_source.OrderSpout;
import cn.zeddli.statistics.SumBolt;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;

/**
 * @Author:zedd Li
 * @date:2020/8/17
 */
public class SumTopology {

    /**
     * 构建拓扑
     */

    public static void main(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();

        /**
         * 设置spout和bolt的DAG有向无环图
         * */
        builder.setSpout("id_order_spout", new OrderSpout());
        builder.setBolt("id_sumBolt", new SumBolt()).shuffleGrouping("id_order_spout");

        StormTopology topology = builder.createTopology();

        LocalCluster localCluster = new LocalCluster();
        String topologyName = SumTopology.class.getSimpleName();
        Config config = new Config();
        localCluster.submitTopology(topologyName,config,topology);
    }
}
