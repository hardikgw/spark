package com.cit.ml.solr;

import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * Created by hp on 12/19/16.
 */
public class App {
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static Properties getProperties() {
        Properties gicProperties = new Properties();
        FileInputStream file;
        String path = "./ml.properties";
        try {
            file = new FileInputStream(path);
            gicProperties.load(file);
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gicProperties;
    }

    public static void main(String arg[]) throws IOException, InterruptedException, SolrServerException {
        Properties gicProperties = getProperties();
        String zkStr = gicProperties.getProperty("solr.zkstr", "localhost:2181");
        log.debug("Using {}:{}@{}:{}", zkStr, zkStr, zkStr, zkStr);
        Trainer.trainWordToVec(zkStr);



    }

}
