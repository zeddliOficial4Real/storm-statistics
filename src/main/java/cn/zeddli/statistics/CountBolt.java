package cn.zeddli.statistics;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:zedd Li
 * @date:2020/8/18
 * 统计单词出现的次数
 */
public class CountBolt extends BaseRichBolt {

    private Map conf;   // 当前组件配置信息
    private TopologyContext context;    // 当前组件上下文对象
    private OutputCollector collector; // 发送tuple的组件

    @Override
    public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
        this.conf = conf;
        this.context = context;
        this.collector = collector;
    }

    private Map<String,Integer> map = new HashMap();

    @Override
    public void execute(Tuple input) {
        String word = input.getStringByField("word");
        Integer num = input.getIntegerByField("count");
        if (map.containsKey(word)){
            map.put(word, (map.get(word)+1));
        }else {
            map.put(word, 1);
        }
        System.out.println("==========");
        map.forEach((k,v)->{
            System.out.println(k+"---"+v);
        });
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
