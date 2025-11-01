import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

class User {
    private long user_Id;
    private String email;
    private String password;
    private String nickname;
    private LocalDateTime createdAt;

    public User(long user_Id, String email, String password, String nickname) {
        this.user_Id = user_Id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.createdAt = createdAt.now();
    }

    public long getuser_id() {
        return user_Id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "회원 ID : " + user_Id + ", \n이메일 : " + email + ", \n닉네임 : " + nickname + ", \n가입일 : " + createdAt;
    }

    public boolean registerUser() {
        System.out.println("새 계정이 생성되었습니다. " + this.user_id);
        return true;
    }

    public String getNickname() {
        return this.nickname;
    }

}

class PetCareService {
    private List<User> userList = new ArrayList<>();
    private long nextuser_id = 1; // 카운터 부여

    public User registerUser(String email, String password, String nickname) {
        if (isEmailExists(email)) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다" + email); // throw new = 예외처리
        }

        User newUser = new User(nextuser_id++, email, password, nickname);
        userList.add(newUser);

        return newUser;
    }

    // 이메일 중복 확인
    private boolean isEmailExists(String email) {
        return userList.stream().anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

}

public class Main {
    public static void main(String[] args) {
        PetCareService petCareService = new PetCareService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("회원가입\n");

        try {
            System.out.println("이메일을 입력하세요 : \n");
            String email = scanner.nextLine();

            System.out.println("비밀번호를 입력하세요 : \n");
            String password = scanner.nextLine();

            System.out.println("닉네임을 입력하세요 : \n");
            String nickname = scanner.nextLine();

            User registeredUser = petCareService.registerUser(email, password, nickname);

            System.out.println("회원가입이 완료되었습니다!\n");

        } catch (IllegalArgumentException e) {
            System.out.println("회원가입 오류 : " + e.getMessage());
        }

        finally {
            scanner.close();
        }

    }
}