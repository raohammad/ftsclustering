/*
 * Delivered to Mr Houcine Senoussi, EISTI France
 * By, Hammad Aslam Khan [http://github.com/raohammad]
 * MIT License
 */

package fts.clustering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author hammadakhan
 */
public class FTSClusterGenerator {
    ArrayList<FTSDocData> ftsData;
    ArrayList<FTSTermsetData> ftsTsData;
    
    HashMap<String, Set<Arrays>> clusters;
    
    HashMap<FTSTermsetData, Double> entropies;
    
    public FTSClusterGenerator(ArrayList<FTSDocData> data, ArrayList<FTSTermsetData> ts){
        
        this.ftsData =data;
        this.ftsTsData = ts;
        
        clusters = new HashMap<>();
        entropies= new HashMap<>();
    }
    
    public void genCluster(){
        for(FTSTermsetData tsData: ftsTsData){
            entropies.put(tsData, tsData.calcEntropy(this.ftsData));
        }
        
        Iterator<FTSTermsetData> entIter = entropies.keySet().iterator();
        while(entIter.hasNext()){
            FTSTermsetData ent = entIter.next();
            System.out.println("Entropy of:"+ent.term+" is:"+entropies.get(ent));
        }
    }
}
