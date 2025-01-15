package road_to_employment.interview_helper.library.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import road_to_employment.interview_helper.directory.entity.Directory;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class DirectoryResponse {
    private final Long id;
    private final String name;
    private final LocalDateTime updateDate;

    public static DirectoryResponse from(Directory directory) {
        return new DirectoryResponse(
                directory.getId(),
                directory.getName(),
                directory.getUpdateDate());
    }
}
