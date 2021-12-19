package com.changgou;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: saijie
 * @date: 2021/12/6 9:27
 */
public class fastdfs {

    //上传图片   client-->tracker server      storage server
    @Test
    public void upload() throws MyException, IOException {
        //1.创建一个配置文件 用于填写服务端的ip和端口
        //2.加载配置文件 建立链接
        ClientGlobal.init("E:\\JAVA_Learning\\98changgou\\changgou-parent\\changgou-service\\changgou-service-file\\src\\main\\resources\\fdfs_client.conf");
        //3.创建trackerClient
        TrackerClient trackerClient = new TrackerClient();
        //4.根据trackerclient获取到链接对象 trackerServer
        TrackerServer trackerServer = trackerClient.getConnection();
        //5.创建storageServer对象 设置null
        //6.创建stroageClient --->提供了很多的操作图片的API的代码（上传图片，下载 ，删除）
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //参数1 指定要上传图片的本地的图片的绝对路径
        //参数2 指定要上传图片的图片的扩展名（jpg/png）不要带点
        //参数3 指定元数据 指的是 图片的高度 日期，像素......    可以不给，
        String[] jpgs = storageClient.upload_file("C:\\Users\\saijie\\Desktop\\lena_origian.jpg", "jpg", null);
        for (String jpg : jpgs) {
            System.out.println(jpg);
        }
    }

    //下载图片
    @Test
    public void download() throws MyException, IOException {
        //1.创建一个配置文件 用于填写服务端的ip和端口
        //2.加载配置文件 建立链接
        ClientGlobal.init("E:\\JAVA_Learning\\98changgou\\changgou-parent\\changgou-service\\changgou-service-file\\src\\main\\resources\\fdfs_client.conf");
        //3.创建trackerClient
        TrackerClient trackerClient = new TrackerClient();
        //4.根据trackerclient获取到链接对象 trackerServer
        TrackerServer trackerServer = trackerClient.getConnection();
        //5.创建storageServer对象 设置null
        //6.创建stroageClient --->提供了很多的操作图片的API的代码（上传图片，下载 ，删除）
        StorageClient storageClient = new StorageClient(trackerServer, null);

        //7.下载图片
        //参数1 指定要下载的组名
        //参数2 指定要下载的远程文件路径
        byte[] bytes = storageClient.download_file("group1", "M00/00/00/wKjThGGtoDiAVpRQAAJr9w1IS_8374.jpg");
        //写入磁盘
        FileOutputStream fileOutputStream = new FileOutputStream(new File("e:/saijie1206.jpg"));
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    //删除图片
    @Test
    public void delete() throws MyException, IOException {
        //1.创建一个配置文件 用于填写服务端的ip和端口
        //2.加载配置文件 建立链接
        ClientGlobal.init("E:\\JAVA_Learning\\98changgou\\changgou-parent\\changgou-service\\changgou-service-file\\src\\main\\resources\\fdfs_client.conf");
        //3.创建trackerClient
        TrackerClient trackerClient = new TrackerClient();
        //4.根据trackerclient获取到链接对象 trackerServer
        TrackerServer trackerServer = trackerClient.getConnection();
        //5.创建storageServer对象 设置null

        //6.创建stroageClient --->提供了很多的操作图片的API的代码（上传图片，下载 ，删除）
        StorageClient storageClient = new StorageClient(trackerServer, null);

        int group1_flag = storageClient.delete_file("group1", "M00/00/00/wKjThGGtoDiAVpRQAAJr9w1IS_8374.jpg");
        if (group1_flag == 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    //查询 获取文件的信息
    @Test
    public void getInfo() throws Exception{
        //1.创建一个配置文件 用于填写服务端的ip和端口
        //2.加载配置文件 建立链接
        ClientGlobal.init("E:\\JAVA_Learning\\98changgou\\changgou-parent\\changgou-service\\changgou-service-file\\src\\main\\resources\\fdfs_client.conf");
        //3.创建trackerClient
        TrackerClient trackerClient = new TrackerClient();
        //4.根据trackerclient获取到链接对象 trackerServer
        TrackerServer trackerServer = trackerClient.getConnection();
        //5.创建storageServer对象 设置null

        //6.创建stroageClient --->提供了很多的操作图片的API的代码（上传图片，下载 ，删除）
        StorageClient storageClient = new StorageClient(trackerServer, null);

        FileInfo group1 = storageClient.get_file_info("group1", "M00/00/00/wKjThF9p5EyATgZtAAX7uZ3HmDk203.jpg");
        System.out.println(group1.getCreateTimestamp());
        System.out.println(group1.getFileSize()+group1.getSourceIpAddr());
    }
}
