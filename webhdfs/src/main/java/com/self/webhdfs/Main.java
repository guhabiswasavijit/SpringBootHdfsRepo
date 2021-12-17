package com.self.webhdfs;

import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.AbstractFileSystem;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.WebHdfs;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

	private static final String NAME_NODE = "hdfs://webhdfs:50020";

    @Override
    public void run(String... strings) throws Exception {
    	Configuration conf = new Configuration();
    	Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", NAME_NODE);
        configuration.set("fs.hdfs.impl",org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        configuration.set("fs.file.impl",org.apache.hadoop.fs.LocalFileSystem.class.getName());
        FileSystem hdfsFileSystem = FileSystem.get(configuration);
        Path writeFile = new Path("/geeks/newFile.txt");
        hdfsFileSystem.create(writeFile);
        FileUtil.write(hdfsFileSystem,writeFile,"I'm new to hdfs".getBytes());
        Path readPath = new Path("/geeks/newToHdfs.txt");
        AbstractFileSystem webhdfs = WebHdfs.get(new URI("http://webhdfs:50070"),conf);
        FSDataInputStream inputStream = webhdfs.open(readPath);
        String out= IOUtils.toString(inputStream, "UTF-8");
        System.out.println(out);
        

    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}