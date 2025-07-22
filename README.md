# analyzer-solr
analyzer adapter for solr, we support Jieba, and stranford in the future


### Require

- Lucene9.7.0
- jieba1.0.3

### DEMO

#### 结巴分词

```xml

    <fieldType name="text_jieba" class="solr.TextField" positionIncrementGap="100">
      <analyzer type="index">
        <tokenizer class="analyzer.solr9.jieba.JiebaTokenizerFactory"  segMode="SEARCH"/>
        <filter class="solr.StopFilterFactory" ignoreCase="true" words="stopwords.txt" />
        <filter class="solr.LowerCaseFilterFactory"/>
        <filter class="solr.SnowballPorterFilterFactory" language="English"/>
      </analyzer>
      <analyzer type="query">
        <tokenizer class="analyzer.solr9.jieba.JiebaTokenizerFactory"  segMode="SEARCH"/>
        <filter class="solr.StopFilterFactory" ignoreCase="true" words="stopwords.txt" />
        <filter class="solr.SynonymFilterFactory" synonyms="synonyms.txt" ignoreCase="true" expand="true"/>
        <filter class="solr.LowerCaseFilterFactory"/>
        <filter class="solr.SnowballPorterFilterFactory" language="English"/>
      </analyzer>
    </fieldType>

```

#### 结巴分词--自定义词典

结巴自定义词典的格式

```
word freq pos
```
例如:
```
中国人 1000 n
```

```xml

    <fieldType name="text_jieba" class="solr.TextField" positionIncrementGap="100">
      <analyzer type="index">
        <tokenizer class="analyzer.solr9.jieba.JiebaTokenizerFactory"  segMode="SEARCH" userDict="/your_path_to_dict.txt"/>
        <filter class="solr.StopFilterFactory" ignoreCase="true" words="stopwords.txt" />
        <filter class="solr.LowerCaseFilterFactory"/>
        <filter class="solr.SnowballPorterFilterFactory" language="English"/>
      </analyzer>
      <analyzer type="query">
        <tokenizer class="analyzer.solr9.jieba.JiebaTokenizerFactory"  segMode="SEARCH" userDict="/your_path_to_dict.txt"/>
        <filter class="solr.StopFilterFactory" ignoreCase="true" words="stopwords.txt" />
        <filter class="solr.SynonymFilterFactory" synonyms="synonyms.txt" ignoreCase="true" expand="true"/>
        <filter class="solr.LowerCaseFilterFactory"/>
        <filter class="solr.SnowballPorterFilterFactory" language="English"/>
      </analyzer>
    </fieldType>

```

#### Stanford分词

```
          <fieldType name="text_stanford" class="solr.TextField" positionIncrementGap="100">
      <analyzer type="index">
        <tokenizer class="analyzer.solr9.stanford.StanfordTokenizerFactory"  modelDir="/Users/zhangcheng/Downloads/softwares/stanford-segmenter-2014-06-16/data" />
        <filter class="solr.StopFilterFactory" ignoreCase="true" words="stopwords.txt" />
        <filter class="solr.LowerCaseFilterFactory"/>
        <filter class="solr.SnowballPorterFilterFactory" language="English"/>
      </analyzer>
      <analyzer type="query">
        <tokenizer class="analyzer.solr9.stanford.StanfordTokenizerFactory"  modelDir="/Users/zhangcheng/Downloads/softwares/stanford-segmenter-2014-06-16/data" />
        <filter class="solr.StopFilterFactory" ignoreCase="true" words="stopwords.txt" />
        <filter class="solr.LowerCaseFilterFactory"/>
        <filter class="solr.SnowballPorterFilterFactory" language="English"/>
      </analyzer>
    </fieldType> 

```
