package com.changgou.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.*;

import java.util.List;

/**
 * @author chen.qian
 * @date 2018/3/19
 */
@CanalEventListener//修饰类，当数据库的表被修改了就会执行该类中的某一些方法
public class MyEventListener {

   /* @InsertListenPoint//当某一个表发生了添加的操作的时候触发以下的方法
    public void onEvent(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        rowData.getAfterColumnsList().forEach((c) -> System.err.println("By--Annotation: " + c.getName() + " ::   " + c.getValue()));
    }*/

    //实现我们的业务逻辑：更新数据到redis中
    @UpdateListenPoint(destination = "example",schema = "changgou_content",table = "tb_content")
    public void onEvent1(CanalEntry.RowData rowData) {
        System.err.println("UpdateListenPoint");
        //更新前的数据
        List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
        for (CanalEntry.Column column : beforeColumnsList) {
            String name = column.getName();
            String value = column.getValue();
            System.out.println(name+":"+value);
        }
        System.out.println("=====================================================");
        //更新后数据
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        for (CanalEntry.Column column : afterColumnsList) {
            String name = column.getName();
            String value = column.getValue();
            System.out.println(name+":"+value);
        }
    }

   /* @DeleteListenPoint
    public void onEvent3(CanalEntry.EventType eventType) {
        System.err.println("DeleteListenPoint");
    }

    //客制化，可以指定任意的类型（insert ,delete ,update ,create index ）
    @ListenPoint(destination = "example", schema = "canal-test", table = {"t_user", "test_table"}, eventType = CanalEntry.EventType.UPDATE)
    public void onEvent4(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.err.println("DeleteListenPoint");
        rowData.getAfterColumnsList().forEach((c) -> System.err.println("By--Annotation: " + c.getName() + " ::   " + c.getValue()));
    }*/
}
