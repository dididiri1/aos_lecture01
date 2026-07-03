---
description: "충돌 방지를 위해 commit 또는 stash 후 원격 변경사항을 가져옵니다"
argument-hint: "[commit|stash]"
allowed-tools:
  [
    "Bash(git pull:*)",
    "Bash(git fetch:*)",
    "Bash(git status:*)",
    "Bash(git branch:*)",
    "Bash(git rev-parse:*)",
    "Bash(git log:*)",
    "Bash(git diff:*)",
    "Bash(git stash:*)",
    "Bash(git remote:*)",
  ]
---

# Claude 명령어: Pull

작업 트리가 dirty일 때 발생할 수 있는 충돌을 방지하기 위해, **commit + pull** 또는 **stash + pull** 방식으로 원격 변경사항을 안전하게 가져옵니다.

## 사용법

```
/git:pull
/git:pull commit
/git:pull stash
```

- 인자 없이 호출하면 작업 트리 상태를 확인해 자동으로 추천
    - clean: 바로 `git pull`
    - dirty: `commit` / `stash` / 취소 중 선택 안내
- `commit`: 변경사항을 먼저 커밋(`/git:commit` 위임)한 뒤 pull
- `stash`: 변경사항을 임시 보관 후 pull → 성공 시 자동 `stash pop`

## 프로세스

1. **현재 브랜치 확인**
    - `git rev-parse --abbrev-ref HEAD`
2. **업스트림 확인**
    - `git rev-parse --abbrev-ref @{u}` 로 추적 브랜치 존재 여부 확인
    - 없으면 안내 후 종료(원격 추적 없는 브랜치는 pull 불가)
3. **보호 브랜치 경고**
    - 현재 브랜치가 `main` 또는 `master`이면 사용자에게 경고하고 진행 여부 재확인
4. **작업 트리 상태 확인**
    - `git status --porcelain` 으로 dirty/clean 판단
5. **원격 갱신 미리보기**
    - `git fetch` 실행
    - `git log HEAD..@{u} --oneline` 으로 가져올 커밋 표시
    - 가져올 커밋이 없으면 pull 생략 후 종료
6. **모드 분기 및 실행** (아래 모드별 흐름 참조)
7. **결과 확인**
    - `git status` 로 최종 상태 확인

## `commit` 모드 흐름

1. **dirty인 경우**
    - 사용자에게 **`/git:commit` 을 먼저 실행할 것을 안내**하고 본 커맨드 종료
    - (커밋 메시지 품질 유지를 위해 자체적으로 커밋을 만들지 않음)
2. **clean인 경우** (또는 커밋 후 재실행)
    - `git pull` 실행
3. `git status` 로 결과 검증

## `stash` 모드 흐름

1. **dirty인 경우**
    - `git stash push -u -m "git:pull 임시 보관"` (untracked 포함)
2. **clean인 경우**
    - stash 단계 생략
3. `git pull` 실행
4. stash가 있었다면 `git stash pop`
    - **pop 성공** → 완료
    - **pop 충돌** → stash를 **drop하지 않고 보존**, 충돌 파일 목록과 `git status` 결과, `git stash drop` 안내를 사용자에게 출력
5. `git status` 로 결과 검증

## 자동 추천 (인자 없음) 흐름

1. **clean** → 안내 후 바로 `git pull`
2. **dirty** → 사용자에게 다음 중 선택을 요청
    - `commit` (commit + pull)
    - `stash` (stash + pull, 자동 pop)
    - 취소
3. 선택된 모드 흐름으로 실행

## 안전 규칙

- pull 전 **항상 작업 트리 상태를 검사**하고 dirty일 때는 모드를 명확히 한 뒤 진행
- `--rebase`, `--force`, `--ff-only` 등은 **사용자가 명시적으로 요청한 경우에만** 사용
- `--no-verify` 로 훅 우회 **금지**
- `main` / `master` 브랜치에서 pull 시 **항상 경고** 후 재확인
- 업스트림이 없는 브랜치에서는 pull 시도하지 않음
- 가져올 커밋이 없으면 pull 생략
- **stash pop 충돌 시 stash 를 자동으로 drop 하지 않음** — 사용자가 직접 해결 후 `git stash drop` 하도록 안내
- pull 실패 시 원인(인증·네트워크·정책 등)을 보고하고, 강제 갱신·재설정 같은 우회 수단은 임의로 사용하지 않음

## 참고사항

- 본 커맨드는 commit 을 직접 생성하지 않음 — **커밋 메시지 품질 유지를 위해 항상 `/git:commit` 위임**
- 충돌이 발생하면 즉시 사용자에게 보고하고 **자동 해결을 시도하지 않음**
- 기본 pull 동작은 git 의 기본값(`fetch + merge`) — 별도 전략을 지정하려면 `git pull --rebase` 등을 직접 실행 권장
- pull 은 원격 변경을 로컬에 병합하는 작업이므로, 진행 전 항상 미리보기를 사용자에게 보여주고 보수적으로 진행
