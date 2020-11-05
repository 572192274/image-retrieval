package extra;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class loadFile {
    public  ArrayList<String> txt2String(File file){
        ArrayList<String> result =new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.add(s.toString());
             }
            br.close();
            }catch(Exception e){
            e.printStackTrace();
            }
        return result;
    }
    public String getFilename(File file,int i)
    {
        ArrayList<String> result = txt2String(file);
        String filename= result.get(i).split(",")[3];

        return filename;
    }
    public String getId(File file,int i)
    {
        ArrayList<String> result=txt2String(file);
        String id=result.get(i).split(",")[0];
        if(id.startsWith("\uFEFF"))
        {
            id=id.replace("\uFEFF","");
        }
        return id;
    }


}
