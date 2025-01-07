package road_to_employment.interview_helper.user_profile.entity;

import jakarta.persistence.*;
import lombok.Getter;
import road_to_employment.interview_helper.user.entity.User;

@Entity
@Getter
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String picture;

    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public UserProfile() {}

    public UserProfile(String name, String email, String picture, User user) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.user = user;
    }
}
