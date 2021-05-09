package com.twm.aasimplelucendemo;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/**
 * 索引库
 */
public class IndexRepository {

    public void creatIndexRepository() throws Exception{

        //把数据填充到JavaBean对象中
        User user = new User("1", "汤文明", "沉稳，魅力，平等待人的牛逼软件工程师。");

        //创建Document对象【导入的是Lucene包下的Document对象】
        Document document = new Document();

        //将JavaBean对象所有的属性值，均放到Document对象中去，属性名可以和JavaBean相同或不同
        /**
         * 向 Document对象加入一个字段
         * 参数一：字段名
         * 参数二：字段值
         * 参数三：是否要存储到原始记录表中
         *      YES表示是
         *      NO表示否
         * 参数四：是否需要将存储的数据拆分到词汇表中
         *      ANALYZED表示拆分
         *      NOT_ANALYZED表示不拆分
         *
         * */
        document.add(new TextField("id", user.getId(), Field.Store.YES));
        document.add(new TextField("userName", user.getName(), Field.Store.YES));
        document.add(new TextField("info", user.getInfo(), Field.Store.YES));

        /**
         *  创建IndexWriter对象
         */
        // 指定索引库存放的文件夹位置
        File indexFile= new File("D:/Project/SpringBootDemos/ZComponentALucene/lucene/Zresource/createIndexDB");
        Directory directory = FSDirectory.open(indexFile.toPath());

        // 使用标准的分词算法对原始记录表进行拆分
        Analyzer analyzer = new IKAnalyzer();

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        /**
         * IndexWriter将我们的document对象写到硬盘中
         *
         * 参数一：Directory d,写到硬盘中的目录路径是什么
         * 参数二：Analyzer a, 以何种算法来对document中的原始记录表数据进行拆分成词汇表
         * 参数三：MaxFieldLength mfl 最多将文本拆分出多少个词汇
         *
         * */
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        //将Document对象通过IndexWriter对象写入索引库中
        indexWriter.addDocument(document);

        //关闭IndexWriter对象
        indexWriter.close();

    }

    /**
     * 启动 -> 建索引库
     * @param args
     */
    public static void main(String[] args) {
        IndexRepository repository = new IndexRepository();
        try {
            repository.creatIndexRepository();
        }catch (Exception e){
            System.out.println("some error");
        }
    }

}
