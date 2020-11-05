package net.semanticmetadata.lire.sampleapp;

import net.semanticmetadata.lire.DocumentBuilder;
import net.semanticmetadata.lire.ImageSearchHits;
import net.semanticmetadata.lire.ImageSearcher;
import net.semanticmetadata.lire.ImageSearcherFactory;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * User: Mathias Lux, mathias@juggle.at
 * Date: 25.05.12
 * Time: 12:19
 */
public class Searcher {
    public static void main(String[] args) throws IOException {
        // Checking if arg[0] is there and if it is an image.
        BufferedImage img = null;
        boolean passed = false;
        if (args.length > 0) {
            File f = new File(args[0]);
            if (f.exists()) {
                try {
                    img = ImageIO.read(f);//获取待检索图像
                    passed = true;
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
        if (!passed) {
            System.out.println("No image given as first argument.");
            System.out.println("Run \"Searcher <query image>\" to search for <query image>.");
            System.exit(1);
        }

        IndexReader ir = DirectoryReader.open(FSDirectory.open(new File("index")));
        ImageSearcher searcher = ImageSearcherFactory.createCEDDImageSearcher(20);//CEDD算法
        //ImageSearcher searcher = ImageSearcherFactory.createFCTHImageSearcher(20);
        ImageSearchHits hits = searcher.search(img, ir);//提供的图像与数据库里的图像进行特征匹配
        int count=0;
        for (int i = 0; i < hits.length(); i++) {


                String fileName = hits.doc(i).getValues(DocumentBuilder.FIELD_NAME_IDENTIFIER)[0];
                System.out.println(hits.score(i) + ": \t" + fileName);
                if(fileName.equals("10001"))
                {
                    count++;
                }


        }
        float rate=count;
        System.out.println(rate/20);
    }
}
