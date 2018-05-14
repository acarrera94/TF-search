# TF-search
Processing, Retrieving, and Ranking Documents in a Wikipedia collection

A simple commandline query promt that returns the top 10 most relevant documents, and their score.
Documents come from a predefined selection of Wikipedia articles.

The purpose of this project is to (i) apply various text operations (i.e., stopword removal and stem- ming) on a text document collection C to create the corresponding indexed structure, and (ii) use the indexed structure to retrieve and rank documents in C that are relevant to a user’s query based on TF×IDF.

This Project was created in scala. 

# Features:

- Word Tokenizer: removes capitalization, punctuation symbols, and hyphens.
- StopWords: Removes stopwords
- PorterStemmer: Reduces the non-stopwords in (the documents in) Wiki to their grammatical stems using the Porter Stemmer algorithm.
- indexed structure for the Wiki collection, which includes for each stem s in Wiki (i) the documents (identified by their IDs) in which s appears and (ii) the frequency of occurrence of s in each document.

# Usage:

Run the main and follow the pormpt.

score is calculated using term frequency and inverse term frequency.
![alt text](https://static.wixstatic.com/media/1cd646_b4950ac6d6da444580409aff6caefeb5~mv2.png/v1/fill/w_1006,h_414,al_c,usm_0.66_1.00_0.01/1cd646_b4950ac6d6da444580409aff6caefeb5~mv2.png "Formula")
