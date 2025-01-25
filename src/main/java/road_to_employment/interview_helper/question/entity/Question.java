package road_to_employment.interview_helper.question.entity;

import jakarta.persistence.*;
import lombok.*;
import road_to_employment.interview_helper.directory.entity.Directory;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directory_id", referencedColumnName = "id", nullable = false)
    private Directory directory;
}
