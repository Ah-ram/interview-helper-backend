package road_to_employment.interview_helper.user_profile.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import road_to_employment.interview_helper.user.entity.User;

@Entity
@Getter
@Setter
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String picture;
    private String nickname;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public UserProfile() {}

    public UserProfile(String email, String picture, String nickname, User user) {
        this.email = email;
        this.picture = picture;
        this.nickname = nickname;
        this.user = user;
    }
}
