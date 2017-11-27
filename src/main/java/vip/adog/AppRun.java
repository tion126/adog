package vip.adog;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppRun {
    private static Logger log = Logger.getLogger(AppRun.class);
    public static void main(String[] args){
        SpringApplication.run(AppRun.class,args);
        log.info("========================= 项目已启动 =========================");
    }

}
