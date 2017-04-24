/*
 * Delivered to Mr Houcine Senoussi, EISTI France
 * By, Hammad Aslam Khan [http://github.com/raohammad]
 * MIT License
 */

package fts.clustering;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author hammadakhan
 */
public class FTSDocData {
    
    //termset, contains
    HashMap<String,Boolean> data;
    //Document itself
    Set<Arrays> document;
    float func = 0;
    
    public FTSDocData(Set doc){
        document = doc;
        data = new HashMap<>();
    }
    
    public double calcFuncForTerm(String ter){
        return (data.get(ter)?calcFunc():0);
    }
    
    public double calcFunc(){
        double val = -1 * (1/calcSum()*Math.log10(1/calcSum()));
        return val;
    }
    
    public double calcSum(){
        //-1/D15*LN(1/D15)
        double fj = 0;
        
        Iterator<Boolean> values = data.values().iterator();
        
        while(values.hasNext()){
            boolean thisValue = values.next().booleanValue();
            if(thisValue){
                fj+=1;
            }
        }
        return fj;
        //System.out.println(document.toString()+" fj value is: "+fj);
    }
    
    public void addTermset(String termset, boolean contains){
        //System.out.println(termset+" : "+contains);
        data.put(termset, new Boolean(contains));
    }
    
    public int getTermset(String termset){
        
        if(data.containsKey(termset)){
            System.out.println(this.document.toString()+" contains"+termset);
            return 1;
        }
        else{
            System.out.println(this.document.toString()+" does not contains"+termset);
            return 0;
        }
    }
    
    
}
