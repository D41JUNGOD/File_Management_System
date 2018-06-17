import java.io.File;
import java.util.Scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class File_Manage_Main {
    public static void main(String[] args) {
        //File mfile = new File("C:\\Users\\oonja\\Desktop\\File_Management_System\\File_Management\\src\\test.txt");
        BasicFileAttributes attrb = null;
        String p;
        Path path = Paths.get("C:\\Users\\oonja\\Desktop\\File_Management_System\\File_Management\\src\\test.txt");

        try {
            attrb = Files.readAttributes(path, BasicFileAttributes.class);

            System.out.println("----------- 파일 전체 속성 -------------");
            System.out.printf("파일 크기                   : %d \n", attrb.size()); // 단위는 바이트
            System.out.printf("파일 생성 날짜            : %s \n", attrb.creationTime());
            System.out.printf("파일 마지막 읽은 날짜 : %s \n", attrb.lastAccessTime());
            System.out.printf("파일 마지막 수정일     : %s \n", attrb.lastModifiedTime());

            System.out.printf("디렉토리 입니까?      %s \n", attrb.isDirectory());
            System.out.printf("파일 입니까?         %s \n", attrb.isRegularFile());
            System.out.printf("심볼릭 링크 입니까?    %s \n", attrb.isSymbolicLink());
            System.out.printf("Other?             %s \n", attrb.isOther());
            System.out.println();

            ///////////////////////
            // 각 개별 속성 가져오기
            ///////////////////////
            System.out.println("----------- 파일 개별 속성 -------------");
            // 파일 크기 속성
            long size = (Long) Files.getAttribute(path, "basic:size", NOFOLLOW_LINKS);
            System.out.printf("파일 크기                   : %d \n", size); // 단위는 바이트

            // 파일 생성 날짜
            FileTime creationTime = (FileTime)Files.getAttribute(path, "basic:creationTime", NOFOLLOW_LINKS);
            System.out.printf("파일 생성 날짜            : %s \n", creationTime);

            // 파일 마지막 읽은 날짜
            FileTime lastAccessTime = (FileTime)Files.getAttribute(path, "basic:lastAccessTime", NOFOLLOW_LINKS);
            System.out.printf("파일 마지막 읽은 날짜  : %s \n", lastAccessTime);

            // 파일 마지막 수정일
            FileTime lastModifiedTime = (FileTime) Files.getAttribute(path, "basic:lastModifiedTime", NOFOLLOW_LINKS);
            System.out.printf("파일 마지막 수정일      : %s \n", lastModifiedTime);

            // 디렉토리 인지
            boolean isDirectory = (Boolean) Files.getAttribute(path, "basic:isDirectory", NOFOLLOW_LINKS);
            System.out.printf("디렉토리 입니까?      %s \n", isDirectory);

            // 파일 인지
            boolean isRegularFile = (Boolean) Files.getAttribute(path, "basic:isRegularFile", NOFOLLOW_LINKS);
            System.out.printf("파일 입니까?         %s \n", isRegularFile);

            // 심볼릭 링크
            boolean isSymbolicLink = (Boolean) Files.getAttribute(path, "basic:isSymbolicLink", NOFOLLOW_LINKS);
            System.out.printf("심볼릭 링크 입니까?     %s \n", isSymbolicLink);

            // other
            boolean isOther = (Boolean) Files.getAttribute(path, "basic:isOther", NOFOLLOW_LINKS);
            System.out.printf("Other?             %s \n", isOther);


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
