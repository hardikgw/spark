package com.cit.ml.solr.client;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;

/**
 * Created by hp on 12/19/16.
 */
public class Connection {

    public SolrClient getConnection(String zkStr) {
        SolrClient solrClient = new CloudSolrClient.Builder().withZkHost(zkStr).build();
        return solrClient;
    }
}
