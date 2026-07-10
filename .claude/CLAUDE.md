# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 언어 및 커뮤니케이션 규칙

- **기본 응답 언어**: 한국어
- **코드 주석**: 한국어로 작성
- **커밋 메시지**: 한국어로 작성
- **문서화**: 한국어로 작성
- **변수명/함수명**: 영어 (코드 표준 준수)

## Plan 작성 규칙

- **plan은 항상 `.claude/plans/` 폴더에 파일로 생성한다** (예: `.claude/plans/<주제>.md`).
- 예제 연습 워크플로우는 `.claude/plans/practice-workflow.md` 참고:
  - `MainActivity.kt` + `activity_main.xml` = 초기 상태 템플릿
  - 예제 완성 시 → 별도 Activity로 분리 → 매니페스트 등록 → Main을 초기 상태로 리셋 → 다음 예제 연습
