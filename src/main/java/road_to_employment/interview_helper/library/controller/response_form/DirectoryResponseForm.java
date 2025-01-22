package road_to_employment.interview_helper.library.controller.response_form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import road_to_employment.interview_helper.library.service.response.DirectoryResponse;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class DirectoryResponseForm {
    private final Long id;
    private final String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private final LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private final LocalDateTime updateDate;

    public static DirectoryResponseForm from(DirectoryResponse directoryResponse) {
        return new DirectoryResponseForm(
                directoryResponse.getId(),
                directoryResponse.getName(),
                directoryResponse.getCreateDate(),
                directoryResponse.getUpdateDate());
    }
}
