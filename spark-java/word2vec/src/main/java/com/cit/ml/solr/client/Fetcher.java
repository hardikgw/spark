package com.cit.ml.solr.client;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hp on 12/19/16.
 */
public class Fetcher {

    private SolrClient sc = null;

    private Fetcher(String zkStr) {
        Connection solrCnn = new Connection();
        sc = solrCnn.getConnection(zkStr);
    }

    public Model getData(Integer rows, Integer start) throws IOException, SolrServerException {
        SolrQuery q = new SolrQuery();
        q.setRequestHandler("/search");
        q.setFields("body");
        q.setStart(start);
        q.setRows(rows);
        q.setSort("id", SolrQuery.ORDER.asc);
        QueryResponse response = sc.query(q);
        SolrDocumentList list = response.getResults();
        List<String> results = new ArrayList();
        for(SolrDocument doc : list) {
            results.add(doc.get("body").toString());
        }
        Model m = new Model();
        m.setStart(start);
        m.setPageSize(rows);
        m.setNumFound(list.getNumFound());
        m.setData(results);
        return m;
    }

}
