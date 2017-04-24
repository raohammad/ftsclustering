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
public class FTSTermsetData {
    
    //termset, contains
    HashMap<String,Boolean> data;
    //Document itself
    public String term;
    //Entropy
    double entropy=0;
    
    public FTSTermsetData(String doc){
        term = doc;
        data = new HashMap<>();
    }
    
    public double calcEntropy(ArrayList<FTSDocData> ftsData){
        
        for(FTSDocData ftD:ftsData){
            entropy = entropy + ftD.calcFuncForTerm(this.term);
        }
        System.out.println("Entropy for: "+this.term+" :"+this.entropy);
        return entropy;
    }
    
    public int calcSum(){
        int sum = 0;
        Iterator<Boolean> values = data.values().iterator();
        while(values.hasNext()){
            boolean thisValue = values.next().booleanValue();
            if(thisValue){
                sum+=1;
            }
        }
        return sum;
    }
    
    public void addDocument(String termset, boolean contains){
        //System.out.println(termset+" : "+contains);
        data.put(termset, new Boolean(contains));
    }
    
    public int getTermset(String doc){
        
        if(data.containsKey(doc)){
            System.out.println(this.term+" contained by:"+doc);
            return 1;
        }
        else{
            System.out.println(this.term+" not contained by:"+doc);
            return 0;
        }
    }
    
}