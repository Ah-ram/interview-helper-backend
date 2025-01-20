package road_to_employment.interview_helper.question.entity;

import lombok.Getter;

@Getter
public enum Category {
    TECHNICAL_SKILL("기술 역량"),
    PROJECT_EXPERIENCE("프로젝트 경험"),
    PROBLEM_SOLVING("문제 해결"),
    COMMUNICATION("커뮤니케이션"),
    INTRODUCTION("자기 소개 및 동기");

    private final String value;

    Category(String value) {
        this.value = value;
    }
}