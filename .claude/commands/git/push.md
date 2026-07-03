---
description: "안전 점검을 거쳐 현재 브랜치를 원격 저장소에 푸시합니다"
allowed-tools:
  [
    "Bash(git push:*)",
    "Bash(git status:*)",
    "Bash(git branch:*)",
    "Bash(git rev-parse:*)",
    "Bash(git log:*)",
    "Bash(git remote:*)",
  ]
---

# Claude 명령어: Push

안전 점검을 거쳐 현재 브랜치를 원격 저장소에 푸시합니다.

## 사용법

```
/git:push
/git:push <원격명> <브랜치명>
```

- 인자 없이 호출하면 현재 브랜치를 추적 원격으로 푸시
- 인자가 있으면 지정한 원격/브랜치로 푸시

## 프로세스

1. **현재 브랜치 확인**
    - `git rev-parse --abbrev-ref HEAD`
2. **업스트림 확인**
    - `git rev-parse --abbrev-ref @{u}` 로 추적 브랜치 존재 여부 확인
    - 없으면 `git push -u origin <branch>` 사용을 사용자에게 안내 후 동의 시 진행
3. **보호 브랜치 경고**
    - 현재 브랜치가 `main` 또는 `master`인 경우 사용자에게 경고하고 진행 여부 재확인
4. **푸시 대상 미리보기**
    - 업스트림 있음: `git log @{u}..HEAD --oneline`
    - 업스트림 없음: `git log -10 --oneline`
    - 푸시할 커밋이 없으면 푸시하지 않고 종료
5. **푸시 실행**
    - 사용자 최종 확인 후 `git push` (또는 `git push -u origin <branch>`) 실행
6. **결과 확인**
    - `git status`로 브랜치가 원격과 동기화되었는지 확인

## 안전 규칙

- `--force` 와 `--force-with-lease`는 **사용자가 명시적으로 요청한 경우에만** 사용
- `--no-verify`로 훅 우회 **금지**
- `main`/`master` 브랜치 푸시 시 **항상 경고** 후 재확인
- 신규 브랜치 최초 푸시는 자동으로 `-u origin <브랜치명>` 사용
- 푸시할 커밋이 없으면 푸시하지 않음
- 푸시 실패 시 원인을 보고하고, 강제 갱신 같은 우회 수단은 임의로 사용하지 않음

## 참고사항

- 언커밋/미스테이지 변경이 있어도 푸시는 가능하지만 사용자에게 알림
- 푸시 후 PR/MR 생성은 별도 명령(예: `gh pr create`)으로 분리
- 푸시는 외부에 영향을 주는 비가역 작업이므로 보수적으로 진행
