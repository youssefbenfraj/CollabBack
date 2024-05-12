package tn.esprit.espritcollabbackend.dto;

public class UserLeaderboardDTO {

        private Long userId;
        private String username;
        private String imageUrl;
        private Long cycleCount;

        public UserLeaderboardDTO(Long userId, String username, String imageUrl, Long cycleCount) {
            this.userId = userId;
            this.username = username;
            this.imageUrl = imageUrl;
            this.cycleCount = cycleCount;
        }



    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getCycleCount() {
        return cycleCount;
    }

    public void setCycleCount(Long cycleCount) {
        this.cycleCount = cycleCount;
    }
}
