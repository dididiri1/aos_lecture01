# 강좌 목록 앱 — 데이터 채우기 + 디자인 개선

## Context

현재 `MainActivity`는 `practice-workflow.md`에 따라 Fragment + Navigation(트롯 가수 앱 패턴)을 연습 중인 상태다 (`trot_nav.xml`, `Singer1/2/3Fragment`). 각 프래그먼트의 RecyclerView 리스트는 플레이스홀더 문자열 `"a", "b", "c"`를 보여주고 있고, 하단 이동 버튼 3개(`react1~3.png`)도 늘어난 `fitXY` 이미지라 화면이 허전하다.

`drawable`에 있는 `react1~5.png`를 실제로 열어 확인해보니 전부 이정환 강사의 강의/도서 썸네일이었다(라이브 특강, Next.js 강의, React.js 강의, Next.js 도서, 프론트엔드 로드맵). 사용자는 이 이미지들을 근거로 리스트를 실제 강좌명으로 채우고, 전체적으로 "허접한" 디자인을 강좌 목록 앱답게 다듬어달라고 요청했다.

이 연습은 나중에 별도 Activity로 분리될 예정(`practice-workflow.md` 절차)이므로, **클래스/파일명(Singer1Fragment, SingerAdapter 등)은 그대로 유지**하고 데이터·레이아웃만 손본다.

## 이미지 → 강좌 데이터 매핑

drawable을 직접 확인한 결과:

| 이미지 | 내용 | 배지 |
|---|---|---|
| `react1.png` | "How to Make Overflowing Knowledge Your Own" (2025.01.16 LIVE) | LIVE |
| `react2.png` | Next.js 강의 | 3K |
| `react3.png` | "React.js : All-in-One" | 10K |
| `react4.png` | "한 입 크기로 잘라먹는 Next.js" 도서 표지 | BOOK |
| `react5.png` | "한 입 크기로 잘라먹는 프론트엔드" 로드맵 소개 | ROADMAP |

RecyclerView 리스트는 이 5개 강좌를 모두 보여주도록 채운다(하단 3개 아이콘은 기존처럼 화면 전환 버튼 역할 그대로 유지, 건드리지 않음).

## 구현 계획

### 1. 데이터 모델 추가 — `Course.kt` (신규)

```kotlin
data class Course(
    val thumbnailResId: Int,
    val title: String,
    val badge: String
)

object CourseData {
    fun courses(): List<Course> = listOf(
        Course(R.drawable.react1, "흘러넘치는 지식, 내 것으로 만드는 법", "LIVE"),
        Course(R.drawable.react2, "한 입 크기로 잘라먹는 Next.js", "3K"),
        Course(R.drawable.react3, "React.js : All-in-One", "10K"),
        Course(R.drawable.react4, "한 입 크기로 잘라먹는 Next.js (도서)", "BOOK"),
        Course(R.drawable.react5, "한 입 크기로 잘라먹는 프론트엔드 로드맵", "ROADMAP"),
    )
}
```
3개 프래그먼트가 완전히 동일한 리스트를 만들던 코드를 한 곳으로 모아 중복을 없앤다.

### 2. `SingerAdapter.kt` — Course 바인딩으로 변경

- 생성자 타입을 `MutableList<String>` → `MutableList<Course>`로 변경.
- `ViewHolder.bindItems`에서 썸네일 이미지(`ImageView.setImageResource`), 배지 텍스트, 제목 텍스트를 바인딩.

### 3. `Singer1/2/3Fragment.kt` — 리스트 채우기

`list.add("a")` 등 3줄을 `CourseData.courses().toMutableList()` 한 줄로 교체. 나머지 네비게이션 로직은 그대로 둔다.

### 4. `singer_item.xml` — 카드형 아이템으로 재설계

기존의 밋밋한 `TextView` 한 줄짜리 아이템을 `MaterialCardView`(이미 `material` 의존성 포함되어 있음) 기반 카드로 교체:
- 왼쪽: 둥근 모서리 썸네일 `ImageView` (`clipToOutline` + 라운드 배경 drawable)
- 오른쪽: 배지 칩(`TextView` + `bg_badge_chip.xml` 알약 모양 배경) + 굵은 제목 텍스트
- 카드 배경/스트로크/엘리베이션으로 리스트에 입체감 부여, `selectableItemBackground`로 터치 피드백 추가

새 drawable 2개 필요: `bg_course_thumbnail.xml`(썸네일 라운드 처리용), `bg_badge_chip.xml`(배지 알약 배경).

### 5. `fragment_singer1/2/3.xml` — 헤더 & 하단 네비 통일

세 파일이 지금 스타일이 제각각(1번만 색/크기 지정, 2·3번은 플레이스홀더 문구)인 것을 통일:
- 상단 헤더 바: 짙은 남색 배경 + 흰 볼드 타이틀. 세 화면을 구분할 수 있게 탭마다 다른 라벨 사용 — 1번 "전체 강좌", 2번 "인기 강좌", 3번 "신규 & 라이브" (실제 화면 전환이 눈에 보이도록; 리스트 내용 자체는 5개 강좌로 동일)
- RecyclerView: 헤더/하단 네비 사이 영역, 배경색 지정, 카드 간 여백이 잘 보이도록 패딩 추가
- 하단 네비 바: 지금처럼 늘어난 `fitXY` 이미지 3개가 아니라, 고정 크기 + 라운드 모서리 + 흰 배경 바 + elevation을 준 아이콘 버튼 형태로 교체 (새 drawable `bg_nav_icon.xml` 사용). 클릭 리스너 대상 id(`imageBtn1/2/3`)는 그대로 유지.

### 6. `colors.xml` — 강좌 앱용 색상 추가

`course_bg`, `course_header_bg`, `course_card_bg`, `course_card_stroke`, `course_title_text`, `course_badge_bg`, `course_badge_text` 등, 짙은 남색 헤더 + 블루 포인트 배지 톤으로 강의 썸네일과 어울리게 구성. 기존 `sentence_*` 등 다른 예제용 색상은 건드리지 않음.

## 건드리지 않는 것

- `MainActivity.kt` / `activity_main.xml` (practice-workflow 상 최종적으로 리셋될 대상, 지금 손댈 필요 없음)
- `trot_nav.xml`의 네비게이션 그래프 구조, 클릭 리스너 로직
- 클래스/파일명 (Singer1Fragment, SingerAdapter 등 — 나중에 Activity 분리 시 그대로 재사용)

## 검증

- `./gradlew assembleDebug`로 컴파일 확인
- 가능하면 에뮬레이터/기기에 설치해 3개 탭을 오가며 카드 리스트와 하단 네비 디자인이 의도대로 보이는지 육안 확인
