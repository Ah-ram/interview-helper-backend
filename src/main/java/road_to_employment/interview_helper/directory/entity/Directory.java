package road_to_employment.interview_helper.directory.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import road_to_employment.interview_helper.library.entity.Library;

@Entity
@Getter
@Builder
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id", nullable = false)
    private Library library;

    public Directory(String name, Library library) {
        this.name = name;
        this.library = library;
    }

    public Directory() {}
}
