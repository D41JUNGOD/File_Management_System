package file_manage;

import java.io.File;

public class File_show{
    public void file_show(String path) throws Exception{
        File dir=new File(path);
        File[] files=dir.listFiles();

        for(int i=0;i<files.length;i++){
            File file=files[i];

            if(file.isFile()){
                System.out.println("[파일] "+file.getCanonicalPath());
            }
            else if(file.isDirectory()){
                System.out.println("[디렉토리] "+file.getCanonicalPath());
                try{
                    file_show(file.getCanonicalPath());
                    }catch(Exception e){
                }
            }

            }
    }
}
