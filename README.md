# About
FTS Clustering in text mining clustering of the documents based on frequent termsets. 

# Finding Frequent Termsets
Frequent termsets that are input to the FTS Clustering can be generated through a number of ways. This repository uses Apriori algorithm to find out the frequent termsets from a collection of document based on 'minimum support factor'.

# Further Work
Current document words can be specified in code directly (FTSClustering.java). Code can be enhanced to take input from files and removing stop words. For instance;

    itemsetList.add(new HashSet<>(Arrays.asList("a", "b")));

in above code, words from file can be directly read as 'Arrays.asList()' and fed into above statement.
