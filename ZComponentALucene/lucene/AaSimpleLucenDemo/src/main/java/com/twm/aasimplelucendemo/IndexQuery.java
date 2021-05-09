package com.twm.aasimplelucendemo;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class IndexQuery {

   public String query(String item) throws IOException, ParseException {
       // 待查找的索引库
       File indexFile= new File("D:/Project/SpringBootDemos/ZComponentALucene/lucene/Zresource/createIndexDB");
       Directory directory = FSDirectory.open(indexFile.toPath());

       // 索引读取工具
       IndexReader reader = DirectoryReader.open(directory);

       // 索引搜索工具
       IndexSearcher searcher = new IndexSearcher(reader);

       // 创建查询解析器,两个参数：默认要查询的字段的名称，分词器（注意这里不查询的字段名要和前面定义的一致）
       QueryParser parser = new QueryParser("userName", new IKAnalyzer());

       //如果想同时匹配多个
       //QueryParser parser = new MultiFieldQueryParser(new String[]{"field1", "field2"}, new IKAnalyzer());

       // 创建查询对象
       Query query = parser.parse(item);

       // 搜索数据,两个参数：查询条件对象要查询的最大结果条数
       // 返回的结果是 按照匹配度排名得分前N名的文档信息（包含查询到的总条数信息、所有符合条件的文档的编号信息）。
       TopDocs topDocs = searcher.search(query, 10);
       // 获取总条数
       System.out.println("本次搜索共找到" + topDocs.totalHits + "条数据");

       // 获取得分文档对象（ScoreDoc）数组.SocreDoc中包含：文档的编号、文档的得分
       ScoreDoc[] scoreDocs = topDocs.scoreDocs;

       StringBuilder ans = new StringBuilder();

       for (ScoreDoc scoreDoc : scoreDocs) {
           // 取出文档编号
           int docID = scoreDoc.doc;
           // 根据编号去找文档
           Document doc = reader.document(docID);
           System.out.println("id: " + doc.get("id"));
           System.out.println("userName: " + doc.get("userName"));
           System.out.println("info: " + doc.get("info"));
           // 取出文档得分
           System.out.println("得分： " + scoreDoc.score);
           ans.append(doc.get("userName"));
           ans.append("\n  ");
           ans.append(doc.get("info"));
       }
       return ans.toString();
   }

}
