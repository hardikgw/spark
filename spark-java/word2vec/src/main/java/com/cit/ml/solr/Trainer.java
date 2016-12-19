package com.cit.ml.solr;

import com.cit.ml.solr.client.Fetcher;
import com.cit.ml.solr.client.Model;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by hp on 12/19/16.
 */
public class Trainer {

    private Trainer() {

    }

    public static void trainWordToVec(String zkStr) throws IOException, SolrServerException {
        Fetcher f = new Fetcher(zkStr);
        Boolean hasMore = true;
        Integer start = 0;
        Integer rows = 100;
        String file = "sentences.txt";
        System.out.println("Writing to file: " + file);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file))) {
            while (hasMore) {
                Model m = f.getData(rows, start);
                List<String> results = m.getData();
                writer.write(results.toString());
                start += rows;
                hasMore = start < m.getNumFound();
            }
        }
    }
}
