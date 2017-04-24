/*
 * Delivered to Mr Houcine Senoussi, EISTI France
 * By, Hammad Aslam Khan [http://github.com/raohammad]
 * MIT License
 */

package fts.clustering;

import apriori.AprioriFTSGenerator;
import apriori.AprioriFTSData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author hammadakhan
 */
public class FTSClustering {

    /**
     * @param args the command line arguments
     */
    List<String> list = new ArrayList<>();
    static AprioriFTSGenerator<String> generator
            = new AprioriFTSGenerator<>();

    static List<Set<String>> itemsetList = new ArrayList<>();
    static ArrayList<FTSDocData> ftsData = new ArrayList<>();
    static ArrayList<FTSTermsetData> ftsTsData = new ArrayList<>();

    public static void main(String[] args) {

        itemsetList.add(new HashSet<>(Arrays.asList("a", "b")));
        itemsetList.add(new HashSet<>(Arrays.asList("b", "c", "d")));
        itemsetList.add(new HashSet<>(Arrays.asList("a", "c", "d", "e")));
        itemsetList.add(new HashSet<>(Arrays.asList("a", "d", "e")));
        //itemsetList.add(new HashSet<>(Arrays.asList("a", "b", "c")));

        //itemsetList.add(new HashSet<>(Arrays.asList("a", "b", "c", "d")));
        //itemsetList.add(new HashSet<>(Arrays.asList("a")));
        //itemsetList.add(new HashSet<>(Arrays.asList("a", "b", "c")));
        //itemsetList.add(new HashSet<>(Arrays.asList("a", "b", "d")));
        //itemsetList.add(new HashSet<>(Arrays.asList("b", "c", "e")));

        AprioriFTSData<String> data = generator.generate(itemsetList, 0.2);
        int i = 1;

        for (Set<String> doc : itemsetList) {
            FTSDocData thisData = new FTSDocData(doc);
            for (Set<String> itemset : data.getFrequentItemsetList()) {
                //System.out.printf("%2d: %9s, support: %1.1f\n", i++, itemset, data.getSupport(itemset));

                boolean contains = true;
                Iterator<String> iter = itemset.iterator();

                int itemsetnum = 0;
                while (iter.hasNext()) {
                    String token = iter.next();

                    if (doc.contains(token)) {
                        //System.out.println(token);
                    } else {
                        contains = false;
                    }
                    itemsetnum = itemsetnum + 1;
                }
                thisData.addTermset(itemset.toString(), contains);
                putInTermsets(itemset.toString(), doc.toString(), contains);
            }
            ftsData.add(thisData);
        }
        
        float entropy = 0;
        Iterator<FTSDocData> it = ftsData.iterator();
        while(it.hasNext()){
            FTSDocData doc = it.next();
            doc.calcSum();
        }   
        
        Iterator<FTSTermsetData> itd = ftsTsData.iterator();
        while(itd.hasNext()){
            FTSTermsetData doc = itd.next();
            doc.calcSum();
        }
        
        FTSClusterGenerator ftsGen = new FTSClusterGenerator(ftsData,ftsTsData);
        ftsGen.genCluster();
    }
    
    public static void putInTermsets(String ts, String doc, boolean contains){
        
        for (FTSTermsetData ftsd : ftsTsData) {
            if(ftsd.term.equalsIgnoreCase(ts)){
                ftsd.addDocument(doc, contains);
                return;
            }
        }
        FTSTermsetData ftsdoc = new FTSTermsetData(ts);
        ftsdoc.addDocument(doc, contains);
        ftsTsData.add(ftsdoc);
    }
}
