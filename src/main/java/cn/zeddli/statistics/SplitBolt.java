package cn.zeddli.statistics;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * @Author:zedd Li
 * @date:2020/8/18
 * 将接收到的数据切分一个个单词并发送到下一个结点
 */
public class SplitBolt extends BaseRichBolt {

    private Map conf;   // 当前组件配置信息
    private TopologyContext context;    // 当前组件上下文对象
    private OutputCollector collector; // 发送tuple的组件

    @Override
    public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
        this.conf = conf;
        this.context = context;
        this.collector = collector;
    }

    /**
     * 对单词进行分词
     * */
    @Override
    public void execute(Tuple input) {
        String line = input.getStringByField("line");
        String[] words=line.split("");
        for (String word : words) {
            this.collector.emit(new Values(word,1));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
            declarer.declare(new Fields("word","count"));
    }
}
