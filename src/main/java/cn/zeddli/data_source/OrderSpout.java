package cn.zeddli.data_source;

import cn.xpleaf.bigdata.storm.utils.TimeUtils;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Author:zedd Li
 * @date:2020/8/17 数据源生成，统计交易金额
 */
public class OrderSpout extends BaseRichSpout {

    private Map conf; //当前组件的配置信息
    private TopologyContext context; //当前组件上下文对象
    private SpoutOutputCollector collector; //发送tuple的组件

    @Override
    public void open(Map conf, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.conf = conf;
        this.context = context;
        this.collector = spoutOutputCollector;
    }

    /**
     * 接收数据的核心方法
     */
    @Override
    public void nextTuple() {
        long num = 0;
        while (true) {
            num++;
            try {
                Thread.sleep(10000);
                System.out.println("当前时间" + TimeUtils.getCurrentTime() + "产生的订单金额：" + num);
                this.collector.emit(new Values(num));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("order_cost"));
    }
}
