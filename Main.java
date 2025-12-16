import java.util.*;
import java.io.*;
import java.nio.Buffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


class User {  
    private long user_Id;    
    private String email;
    private String password;
    private String nickname;
    private LocalDateTime createdAt;  

    public User(long userId, String email, String password, String nickname) {
        this.user_Id = userId;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.createdAt = LocalDateTime.now();
    }

    // 기존 데이터 불러올 때 사용
    public User(long userId, String email, String password, String nickname, LocalDateTime createdAt) {
        this.user_Id = userId;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.createdAt = createdAt;
    }

    public long getUserId() {
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
        System.out.println("새 계정이 생성되었습니다. " + this.user_Id);
        return true;
    }

    public String getNickname() {
        return this.nickname;
    }
    }

class PetCareService {
    private List<User> userList = new ArrayList<>();
    private long nextUserId = 1;  //카운터 부여
    private static final String FILE_NAME = "users.csv";  //엑셀데이터 저장 파일

     public User registerUser(String email, String password, String nickname) {
        if (isEmailExists(email)) {
           throw new IllegalArgumentException ("이미 사용 중인 이메일입니다" + email);  //throw new = 예외처리
        }     

        User newUser = new User(nextUserId++, email, password, nickname);
        userList.add(newUser);
        
        return newUser;
    }
    //이메일 중복 확인
    private boolean isEmailExists(String email) {
        return userList.stream().anyMatch(user-> user.getEmail().equalsIgnoreCase(email));
    }

    public void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : userList) {
                String line = String.format("%d,%s,%s,%s,%s",
                user.getUserId(), user.getEmail(), user.getPassword(), user.getNickname(), user.getCreatedAt());
                writer.write(line);
                writer.newLine();
            }
                System.out.println("***" + userList.size() + "명의 사용자 정보가 파일에 저장되었습니다.***" );
        } catch (IOException e) {
            System.err.println("사용자 데이터를 저장하는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    public void loadUsers() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("저장된 사용자 데이터가 없습니다.");
            return;
        }
        long maxId = 0;
        int count = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                
                if (parts.length == 5) {
                    try {
                    long userId = Long.parseLong(parts[0].trim());
                    String email = parts[1].trim();
                    String password = parts[2].trim();
                    String nickname = parts[3].trim();
                    LocalDateTime createdAt = LocalDateTime.parse(parts[4].trim());

                    User loadedUser = new User(userId, email, password, nickname, createdAt);
                    userList.add(loadedUser);
                    count++;
                    
                    if(userId > maxId) {
                        maxId = userId;
                    }
                } catch (NumberFormatException | DateTimeParseException e) {
                        System.err.println("잘못된 데이터 형식으로 오류가 발생했습니다: " + line);
                    }
                
        }
    }
            nextUserId = maxId + 1;
            System.out.println("***" + count + "명의 사용자 정보가 파일에서 로드되었습니다.***");

        } catch (IOException e) {
            System.err.println("사용자 데이터를 로드하는 중 오류가 발생했습니다: " + e.getMessage());
        }
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
        registeredUser.registerUser(); //user 클래스 내부 메서드 호출

        System.out.println("회원가입이 완료되었습니다!\n");
    
        } catch (IllegalArgumentException e) {
            System.out.println("\n--회원가입 오류-- " );
            System.out.println(e.getMessage());
        }
        
        finally {
            if(scanner != null) {
            scanner.close();
        }
        }
    }
}