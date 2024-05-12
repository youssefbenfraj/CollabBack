package tn.esprit.espritcollabbackend.payload.request.response;

import java.util.List;

public class UserInfoResponse {
    private long idUser;
    private String username;
    private String email;
    private List<String> roles;

    public UserInfoResponse(long idUser, String username, String email, List<String> roles) {
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }
}
