package com.self.webhdfs;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("hadoop-context.xml")
@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private FileSystem fs;

    @Override
    public void run(String... strings) throws Exception {
        Path[] paths = new Path[strings.length];
        for (int i = 0; i < strings.length; ++i) {
            paths[i] = new Path(strings[i]);
        }
        for (FileStatus stat: fs.listStatus(paths)) {
            System.out.println(stat.getPath());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}