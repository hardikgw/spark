package com.cit.ml.solr.w2v;

import com.google.common.annotations.VisibleForTesting;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.junit.Test;

import java.util.Collection;

/**
 * Created by hp on 12/16/16.
 */
public class Dl4jTest {

    private static Word2Vec vec = WordVectorSerializer.readWord2VecModel("model.txt");

    @Test
    public void testModel() {
        runModel("day");
        runModel("game");
    }

    private void runModel(String word) {
        Collection<String> lst = vec.wordsNearest(word, 10);
        System.out.println("10 Words closest to " + word + " : " + lst);
    }

}
