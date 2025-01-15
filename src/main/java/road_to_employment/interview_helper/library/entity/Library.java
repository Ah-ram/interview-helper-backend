package road_to_employment.interview_helper.library.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import road_to_employment.interview_helper.user.entity.User;

@Entity
@Getter
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public Library() {}

    public Library(User user) {
        this.user = user;
    }
}
