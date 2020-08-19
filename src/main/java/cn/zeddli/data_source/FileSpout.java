package cn.zeddli.data_source;

import org.apache.commons.io.FileUtils;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author:zedd Li
 * @date:2020/8/18
 */
public class FileSpout extends BaseRichSpout {

    private Map conf;
    private SpoutOutputCollector collector;
    private TopologyContext context;
    /**
     * 获取数据源，持续读取
     * */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("lines"));
    }

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.conf = conf;
        this.collector = collector;
        this.context = context;
    }

    @Override
    public void nextTuple() {
        File path = new File("E:\\data");
        //指定读取的文件后缀名  extensions参数代表采集某些后缀名的文件
        Collection<File> listFile = FileUtils.listFiles(path, new String[]{"txt"}, true);
        for (File file : listFile) {
            try {
                List<String> lines=FileUtils.readLines(file, "utf-8");
                for (String line : lines) {
                    this.collector.emit(new Values(line));
                }
                //重命名被消费的文件
                File desFile = new File(file.getAbsolutePath()+"_"+ UUID.randomUUID().toString()+".compile");
                FileUtils.moveFile(file, desFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
