package file_manage;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

interface set_file_path {
     void setPath(String path);
     String getPath();
}
abstract class set_file_password{
    String password;
    abstract void setPassword(String password);
    abstract String getPassword();
}
class set_file extends set_file_password implements set_file_path{
    private String path;
    private String password;
    public void setPath(String path){
        try {
            File file = new File(path);
            if (!file.isFile()) {
                System.out.println("파일이 존재하지 않습니다. ");
                this.path = null;
            } else if (file.isDirectory()) {
                System.out.println("폴더는 등록이 불가합니다!");
                this.path = null;
            } else {
                this.path = path;
                System.out.println("경로가 정상적으로 설정되었습니다.");
            }
        }catch (NullPointerException n){
            System.out.println("잘못 입력하셨습니다.");
        }
    }
    public String getPath(){
        if(this.path != null){
            return this.path;
        }
        else{
            System.out.println("파일 경로 설정을 해주세요.");
            return null;
        }
    }
    public void setPassword(String password) {
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
        print();
        while(true) {
            while (true) {
                System.out.println("1. 파일 경로 설정");
                System.out.println("2. 파일 잠금");
                System.out.println("3. 파일 잠금 해제");
                System.out.println("4. 파일 정보");
                System.out.println("5. 파일 찾기");
                System.out.println("6. 종료");
                System.out.print(">>> ");
                try {                                           //숫자 이외의 값이 들어왔을때에 대한 예외처리
                    choice = sc.nextInt();
                    break;
                } catch (InputMismatchException in) {
                    sc = new Scanner(System.in);
                    System.out.println("잘못 입력하셨습니다.");
                }
            }

            if (!(choice >= 1 && choice <= 6)) {                //1~6사이의 값에 대한 예외처리
                System.out.println("잘못 입력하셨습니다.");
                continue;
            }
            if (choice == 1) {
                path();
                System.out.println("파일의 경로를 입력해주세요.");
                System.out.print(">>> ");
                path = sc.next();
                try {                                           //경로 설정에 대한 예외처리
                    fs.setPath(path);
                } catch (Exception e) {
                    System.out.println("파일을 찾을 수 없습니다.");
                }
            } else if (choice == 2) {
                if (fs.getPath() != null) {
                    lock();
                    System.out.println("비밀번호를 입력해주세요. (16자리)");
                    System.out.print(">>> ");
                    password = sc.next();
                    if(password.length() != 16){
                        System.out.println("비밀번호는 16자리여야 합니다!");
                        continue;
                    }
                    fs.setPassword(password);

                    File_lock fl = new File_lock();
                    fl.encrypt_file(fs.getPassword(),fs.getPath());
                    System.out.println("파일 잠금이 성공적으로 되었습니다.");
                }
            }
            else if (choice == 3) {
                if (fs.getPath() != null) {
                    unlock();
                    System.out.println("비밀번호를 입력해주세요.");
                    System.out.print(">>> ");
                    password = sc.next();
                    fs.setPassword(password);

                    File_lock fl = new File_lock();
                    if(fl.decrypt_file(fs.getPassword(),fs.getPath()) == 0) {
                        System.out.println("비밀번호가 올바르지 않습니다.");
                    }
                    else{
                        System.out.println("파일 잠금 해제가 성공적으로 되었습니다.");
                    }

                }
            }
            else if (choice == 4) {
                if (fs.getPath() != null) {
                    info();
                    File_info fi = new File_info();
                    System.out.println("경로로 설정한 파일의 정보입니다.");
                    fi.file_info(fs.getPath());
                }
            }
            else if (choice == 5) {
                find();
                File_show fsh = new File_show();
                path = System.getProperty("user.dir");
                fsh.file_show(path);
            }
            else {
                System.out.println("종료합니다.");
                System.exit(0);
            }
        }
    }
    private static void print(){
        System.out.print(" ________  __  __                  __       __                                                   \n" +
                "/        |/  |/  |                /  \\     /  |                                                  \n" +
                "$$$$$$$$/ $$/ $$ |  ______        $$  \\   /$$ |  ______   _______    ______    ______    ______  \n" +
                "$$ |__    /  |$$ | /      \\       $$$  \\ /$$$ | /      \\ /       \\  /      \\  /      \\  /      \\ \n" +
                "$$    |   $$ |$$ |/$$$$$$  |      $$$$  /$$$$ | $$$$$$  |$$$$$$$  | $$$$$$  |/$$$$$$  |/$$$$$$  |\n" +
                "$$$$$/    $$ |$$ |$$    $$ |      $$ $$ $$/$$ | /    $$ |$$ |  $$ | /    $$ |$$ |  $$ |$$    $$ |\n" +
                "$$ |      $$ |$$ |$$$$$$$$/       $$ |$$$/ $$ |/$$$$$$$ |$$ |  $$ |/$$$$$$$ |$$ \\__$$ |$$$$$$$$/ \n" +
                "$$ |      $$ |$$ |$$       |      $$ | $/  $$ |$$    $$ |$$ |  $$ |$$    $$ |$$    $$ |$$       |\n" +
                "$$/       $$/ $$/  $$$$$$$/       $$/      $$/  $$$$$$$/ $$/   $$/  $$$$$$$/  $$$$$$$ | $$$$$$$/ \n" +
                "                                                                             /  \\__$$ |          \n" +
                "                                                                             $$    $$/           \n" +
                "                                                                              $$$$$$/            \n");
    }
    private static void path(){
        System.out.println(" _______    ______   ________  __    __ \n" +
                "/       \\  /      \\ /        |/  |  /  |\n" +
                "$$$$$$$  |/$$$$$$  |$$$$$$$$/ $$ |  $$ |\n" +
                "$$ |__$$ |$$ |__$$ |   $$ |   $$ |__$$ |\n" +
                "$$    $$/ $$    $$ |   $$ |   $$    $$ |\n" +
                "$$$$$$$/  $$$$$$$$ |   $$ |   $$$$$$$$ |\n" +
                "$$ |      $$ |  $$ |   $$ |   $$ |  $$ |\n" +
                "$$ |      $$ |  $$ |   $$ |   $$ |  $$ |\n" +
                "$$/       $$/   $$/    $$/    $$/   $$/ \n");
    }
    private static void lock(){
        System.out.println(" __         ______    ______   __    __ \n" +
                "/  |       /      \\  /      \\ /  |  /  |\n" +
                "$$ |      /$$$$$$  |/$$$$$$  |$$ | /$$/ \n" +
                "$$ |      $$ |  $$ |$$ |  $$/ $$ |/$$/  \n" +
                "$$ |      $$ |  $$ |$$ |      $$  $$<   \n" +
                "$$ |      $$ |  $$ |$$ |   __ $$$$$  \\  \n" +
                "$$ |_____ $$ \\__$$ |$$ \\__/  |$$ |$$  \\ \n" +
                "$$       |$$    $$/ $$    $$/ $$ | $$  |\n" +
                "$$$$$$$$/  $$$$$$/   $$$$$$/  $$/   $$/ \n");
    }
    private static void unlock(){
        System.out.println(" __    __  __    __  __         ______    ______   __    __ \n" +
                "/  |  /  |/  \\  /  |/  |       /      \\  /      \\ /  |  /  |\n" +
                "$$ |  $$ |$$  \\ $$ |$$ |      /$$$$$$  |/$$$$$$  |$$ | /$$/ \n" +
                "$$ |  $$ |$$$  \\$$ |$$ |      $$ |  $$ |$$ |  $$/ $$ |/$$/  \n" +
                "$$ |  $$ |$$$$  $$ |$$ |      $$ |  $$ |$$ |      $$  $$<   \n" +
                "$$ |  $$ |$$ $$ $$ |$$ |      $$ |  $$ |$$ |   __ $$$$$  \\  \n" +
                "$$ \\__$$ |$$ |$$$$ |$$ |_____ $$ \\__$$ |$$ \\__/  |$$ |$$  \\ \n" +
                "$$    $$/ $$ | $$$ |$$       |$$    $$/ $$    $$/ $$ | $$  |\n" +
                " $$$$$$/  $$/   $$/ $$$$$$$$/  $$$$$$/   $$$$$$/  $$/   $$/ \n");
    }
    private static void info(){
        System.out.println(" ______  __    __  ________  ______  \n" +
                "/      |/  \\  /  |/        |/      \\ \n" +
                "$$$$$$/ $$  \\ $$ |$$$$$$$$//$$$$$$  |\n" +
                "  $$ |  $$$  \\$$ |$$ |__   $$ |  $$ |\n" +
                "  $$ |  $$$$  $$ |$$    |  $$ |  $$ |\n" +
                "  $$ |  $$ $$ $$ |$$$$$/   $$ |  $$ |\n" +
                " _$$ |_ $$ |$$$$ |$$ |     $$ \\__$$ |\n" +
                "/ $$   |$$ | $$$ |$$ |     $$    $$/ \n" +
                "$$$$$$/ $$/   $$/ $$/       $$$$$$/  \n");
    }
    private static void find(){
        System.out.println(" ________  ______  __    __  _______  \n" +
                "/        |/      |/  \\  /  |/       \\ \n" +
                "$$$$$$$$/ $$$$$$/ $$  \\ $$ |$$$$$$$  |\n" +
                "$$ |__      $$ |  $$$  \\$$ |$$ |  $$ |\n" +
                "$$    |     $$ |  $$$$  $$ |$$ |  $$ |\n" +
                "$$$$$/      $$ |  $$ $$ $$ |$$ |  $$ |\n" +
                "$$ |       _$$ |_ $$ |$$$$ |$$ |__$$ |\n" +
                "$$ |      / $$   |$$ | $$$ |$$    $$/ \n" +
                "$$/       $$$$$$/ $$/   $$/ $$$$$$$/ \n");
    }
}


