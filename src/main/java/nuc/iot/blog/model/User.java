package nuc.iot.blog.model;

/**
 * 用户类
 * */
public class User {
    private Integer id;
    private String password;
    private String name;
    private String email;
    private Long gmtCreate;
    private boolean isAdmin;
    private String bio;

    public User() {
    }

    public User(String password, String name, String email,
                Long gmtCreate, boolean isAdmin,
                String bio) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.gmtCreate = gmtCreate;
        this.isAdmin = isAdmin;
        this.bio = bio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", isAdmin=" + isAdmin +
                ", bio='" + bio + '\'' +
                '}';
    }
}
