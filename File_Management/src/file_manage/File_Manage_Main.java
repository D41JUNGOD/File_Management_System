package file_manage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import java.io.IOException;
import java.lang.String;

interface set_file_path {
     void setPath(String path);
     String getPath();
}
abstract class set_password{
    String password;
    abstract void setPassword(String password);
    abstract String getPassword();
}
class set_file extends set_password implements set_file_path{
    private String path;
    private String password;
    public void setPath(String path) throws Exception{
        this.path = path;
        File f = new File(this.path);
        this.path = f.getCanonicalPath();
    }
    public String getPath() {
        if(this.path != null){
            return this.path;
        }
        else{
            System.out.println("파일의 경로 설정을 해주세요.");
            return null;
        }
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword() {
        if(this.password != null){
            return this.password;
        }
        else{
            System.out.println("비밀번호 설정을 해주세요.");
            return null;
        }
    }
}
public class File_Manage_Main{
    public static void main(String[] args) throws Exception{
        String path,password;
        int choice;
        set_file fs = new set_file();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("1. 파일 경로 설정");
            System.out.println("2. 파일 잠금");
            System.out.println("3. 파일 잠금 해제");
            System.out.println("4. 파일 정보");
            System.out.println("5. 파일 찾기");
            choice = sc.nextInt();
            if(choice == 1){
                System.out.println("파일의 경로를 입력해주세요.");
                path = sc.next();
                fs.setPath(path);
            }
            else if(choice == 2){
                if(fs.getPath() != null){
                    System.out.println("비밀번호를 입력해주세요.");
                    password = sc.next();
                    fs.setPassword(password);
                }
            }
            else if(choice == 3){
                if(fs.getPath() != null){
                    System.out.println("비밀번호를 입력해주세요.");
                    password = sc.next();
                    fs.setPassword(password);
                }

            }
            else if(choice == 4){
                System.out.println("정보를 찾을 파일의 경로를 입력해주세요.");
                path = sc.next();
            }
            else if(choice == 5){
                //System.out.println("Working Directory = " + System.getProperty("user.dir"));
                File_show file_show = new File_show();
                path = System.getProperty("user.dir");
                file_show.file_show(path);

            }
        }
    }

}


