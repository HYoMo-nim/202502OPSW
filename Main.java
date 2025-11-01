import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


// class 앞에 public가 있어야 하는데 그러면 오류가 생겨서 일단 빼뒀습니다
class User {  
    private long user_id;    
    private String email;
    private String password;
    private String nickname;
    private LocalDateTime createdAt;  

    public User(long userId, String email, String password, String nickname) {
        this.user_id = userId;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.createdAt = createdAt.now();
    }

    public long getUserId() {
        return user_id;
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
        return "회원 ID : " + user_id + ", \n이메일 : " + email + ", \n닉네임 : " + nickname + ", \n가입일 : " + createdAt;
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
    private long nextUserId = 1;  //카운터 부여
    
    public User registerUser(String email, String password, String nickname) {
        if (isEmailExists(email)) {
           throw new IllegalArgumentException ("이미 사용 중인 이메일입니다" + email);     //throw new = 예외처리
        }     

        User newUser = new User(nextUserId++, email, password, nickname);
        userList.add(newUser);
        
        return newUser;
    }
    //이메일 중복 확인
    private boolean isEmailExists(String email) {
        return userList.stream().anyMatch(user-> user.getEmail().equalsIgnoreCase(email));
    }
    
}
class Breed {
    private long breed_id;
    private String breed_name;
    private String species;

    public Breed(long breed_id, String breed_name, String species) {
        this.breed_id = breed_id;
        this.breed_name = breed_name;
        this.species = species;
    }
    public long getBreed_id() { 
        return breed_id;
    }
    public String getBreed_name() { return breed_name;}
    public String getspecise() { return species;}
}

class Diease {
    private long diease_id;
    private String diease_name;
    private String description;
    private boolean is_gentic;

    public Diease(long diease_id, String diease_name, String description, boolean is_gentic) {
        this.diease_id = diease_id;
        this.diease_name = diease_name;
        this.description = description;
        this.is_gentic = is_gentic;
    }
    public long getDiease_id() {
        return diease_id;
    }
    public String getDiease_name() {
        return diease_name;
    }
    public String getDescription() {
        return description;
    }   
    public boolean isIs_gentic() {
        return is_gentic;
    }
}








// 메인에 내용 추가 
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