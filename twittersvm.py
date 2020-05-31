from nltk.tokenize import wordpunct_tokenize
from nltk.corpus import stopwords
from nltk.stem import PorterStemmer, SnowballStemmer
from operator import itemgetter
from gensim import corpora, models, similarities

import sys
import getopt
import argparse
import os.path


def preprocess_document(doc):
    stopset = set(stopwords.words('spanish'))
    stemmer = PorterStemmer()
    tokens = wordpunct_tokenize(doc)
    clean = [token.lower() for token in tokens if token.lower()
             not in stopset and len(token) > 2]
    final = [stemmer.stem(word) for word in clean]
    return final


def create_dictionary(docs, filename='/tmp/vsm.dict', recompile=False):
    dictionary = None
    if recompile or not os.path.exists(filename):
        pdocs = [preprocess_document(doc) for doc in docs]
        dictionary = corpora.Dictionary(pdocs)
        dictionary.save(filename)
    else:
        dictionary = corpora.Dictionary().load(filename)
    return dictionary


def docs2bows(corpus, dictionary, filename='/tmp/vsm_docs.mm'):
    docs = [preprocess_document(d) for d in corpus]
    vectors = [dictionary.doc2bow(doc) for doc in docs]
    corpora.MmCorpus.serialize(filename, vectors)


def create_TF_IDF_model(corpus, filename='/tmp/vsm_docs.mm', recompile=False):
    dictionary = create_dictionary(corpus, recompile=recompile)
    if recompile or not os.path.exists(filename):
        docs2bows(corpus, dictionary)
    loaded_corpus = corpora.MmCorpus(filename)
    tfidf = models.TfidfModel(loaded_corpus)
    return tfidf, dictionary


def launch_query(corpus, q, filename='/tmp/vsm_docs.mm', precission=0.25, recompile_corpus=False):
    tfidf, dictionary = create_TF_IDF_model(corpus, recompile=recompile_corpus)
    loaded_corpus = corpora.MmCorpus(filename)
    index = similarities.MatrixSimilarity(
        loaded_corpus, num_features=len(dictionary))
    pq = preprocess_document(q)
    vq = dictionary.doc2bow(pq)
    qtfidf = tfidf[vq]
    sim = index[qtfidf]
    ranking = sorted(enumerate(sim), key=itemgetter(1), reverse=True)
    for doc, score in ranking:
        if score > precission:
            print("[ Score = " + "%.3f" % round(score, 3) + "] " + corpus[doc])


if __name__ == "__main__":

    parser = argparse.ArgumentParser()
    parser.add_argument('query', nargs=1, help='set precission')
    parser.add_argument('-p', '--precission', type=float,
                        default=0.25, nargs=1, help='set precission')
    parser.add_argument('-r', '--recompile', type=bool,
                        default=False, help='recompile vsm')
    args = parser.parse_args()

    file = open('bulos.txt', 'r')
    sample_corpus = file.readlines()
    launch_query(
        sample_corpus, args.query[0], precission=args.precission, recompile_corpus=args.recompile)
    file.close()
