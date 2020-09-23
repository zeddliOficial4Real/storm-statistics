package cn.zeddli.statistics;

import cn.xpleaf.bigdata.storm.utils.TimeUtils;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

/**
 * @Author:zedd Li
 * @date:2020/8/17 统计金额bolt
 */
public class SumBolt extends BaseRichBolt {

    private Map conf; //当前组件的配置信息
    private TopologyContext context; //当前组件上下文对象
    private OutputCollector collector; //发送tuple的组件

    Long sumOrderCost = 0l;

    @Override
    public void prepare(Map conf, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.conf = conf;
        this.context = context;
        this.collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        Long orderCost = tuple.getLongByField("order_cost");
        sumOrderCost += orderCost;
        System.out.println("商城网站到目前" + TimeUtils.getCurrentTime() + "的商品总交易额" + sumOrderCost);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
