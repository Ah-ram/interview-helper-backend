package road_to_employment.interview_helper.library.controller.request_form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DirectoryCreateRequestForm {
    private final String userToken;
    private final String name;
}
