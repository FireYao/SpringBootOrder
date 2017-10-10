package com.simpletour.kettle;

import org.junit.Test;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.TimedRow;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

/**
 * Created by lly on 2017/8/30
 */
public class KettleTest {

    private final static String jobName = "AtoB.kjb";
    private final static String transName = "trade.ktr";

    private final static Logger logger = LoggerFactory.getLogger(KettleTest.class);

    private String DB_HOST = "127.0.0.1";

    private String DB_PORT = "5432";

    private String DB_NAME = "myLocalDatabase";

    private String USERNAME = "postgres";

    private String PASSWORD = "root";


    @Transactional
    public void doJob() {
        try {
            // 初始化
            KettleEnvironment.init();
            String path = this.getClass().getResource("/kettle/").getPath();
            JobMeta meta = new JobMeta(path + jobName, null);
            Job job = new Job(null, meta);
            job.run();
            job.waitUntilFinished();
            if (job.getErrors() > 0) {
                logger.error(jobName + ":Job run Failure!");
            } else {
                logger.debug(jobName + ":Job run successfully!");
            }
        } catch (KettleException e) {
            e.printStackTrace();
        }

    }

    @Transactional
    public void doTrans() {
        try {
            // 初始化
            KettleEnvironment.init();
            String path = this.getClass().getResource("/kettle/").getPath();
            TransMeta transMeta = new TransMeta(path + transName);
            Trans trans = new Trans(transMeta);
            // 执行转换

            trans.setParameterValue("db_host", DB_HOST);
            trans.setParameterValue("db_port", DB_PORT);
            trans.setParameterValue("db_name", DB_NAME);
            trans.setParameterValue("username", USERNAME);
            trans.setParameterValue("password", PASSWORD);

            trans.execute(null);
            // 等待转换执行结束
            trans.waitUntilFinished();
            if (trans.getErrors() > 0) {
                logger.error(jobName + ":doTrans run Failure!");
            } else {
                logger.debug(jobName + ":doTrans run successfully!");
            }
        } catch (KettleException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void runKettle() throws Exception {
        KettleTest kettleTest = new KettleTest();
        kettleTest.doTrans();
//        kettleTest.doJob();

    }
}
