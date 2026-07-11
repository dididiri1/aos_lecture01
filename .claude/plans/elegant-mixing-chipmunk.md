# 예제 모음 메뉴 화면(MenuActivity) 추가

## Context

`practice-workflow.md` 규칙에 따라 예제를 완성할 때마다 별도 Activity로 분리해왔고, 현재 `Bts1Activity`, `ImageListActivity`(+`ImageInsideMainActivity`), `DiceActivity`, `ListViewActivity`, `SentenceMainActivity`(+`SentenceActivity`), `RVActivity`까지 7개가 쌓였다. 사용자가 "예제가 많아져서 헷갈린다"며, 버튼 여러 개로 각 예제 화면으로 바로 이동할 수 있는 메뉴 화면을 만들어 정리하고 싶어함.

## 설계

**새 `MenuActivity`를 예제 목록의 진입점(허브)으로 만든다.**

- 버튼은 "진입점" Activity만 나열한다. 하위 상세 화면은 제외:
  - `ImageInsideMainActivity`는 `ImageListActivity`에서 `intent.putExtra("data", ...)`로만 정상 동작하므로 제외 (`ImageListActivity`가 진입점)
  - `SentenceActivity`는 `SentenceMainActivity`의 버튼으로만 이동하므로 제외 (`SentenceMainActivity`가 진입점)
- 최종 버튼 목록 (7개):
  1. `MainActivity` — "연습중 (MainActivity)"
  2. `Bts1Activity` — "BTS 연습"
  3. `ImageListActivity` — "이미지 목록"
  4. `DiceActivity` — "주사위 굴리기"
  5. `ListViewActivity` — "ListView + 커스텀 어댑터"
  6. `SentenceMainActivity` — "명언 (랜덤 + 전체 리스트)"
  7. `RVActivity` — "RecyclerView 연습"
- 버튼마다 XML/코드를 반복 작성하지 않고, `(라벨, Activity 클래스)` 리스트를 만들어 `MenuActivity.kt`에서 반복문으로 `Button`을 동적 생성 후 `LinearLayout`(ScrollView로 감쌈)에 추가. 새 예제가 늘어나도 리스트에 한 줄만 추가하면 되게 함.
- `SplashActivity.kt`가 3초 뒤 이동하는 대상을 `MainActivity` → `MenuActivity`로 변경. 앱 실행 시 메뉴가 먼저 뜨고, 필요하면 "연습중 (MainActivity)" 버튼으로 들어가는 구조로 정리.

## 파일 변경

- **신규** `app/src/main/java/com/kangmin/myfirstfile/MenuActivity.kt`
  - `(라벨, KClass) 리스트` → `container.addView(Button(...).apply { setOnClickListener { startActivity(Intent(this@MenuActivity, cls)) } })` 패턴
- **신규** `app/src/main/res/layout/activity_menu.xml`
  - `ScrollView` > `LinearLayout(id=menuContainer, orientation=vertical)` 뼈대만 (버튼은 코드에서 채움)
- **수정** `app/src/main/AndroidManifest.xml`
  - `<activity android:name=".MenuActivity" android:exported="false" android:windowSoftInputMode="adjustResize" />` 추가 (기존 항목들과 동일 패턴)
- **수정** `app/src/main/java/com/kangmin/myfirstfile/SplashActivity.kt`
  - `Intent(this, MainActivity::class.java)` → `Intent(this, MenuActivity::class.java)`
- **수정** `.claude/plans/practice-workflow.md`
  - "지금까지 분리된 예제 Activity" 목록에 `RVActivity` 추가 (기존에 누락돼 있었음)
  - 새 규칙 한 줄 추가: 예제를 별도 Activity로 분리할 때 `MenuActivity.kt`의 버튼 목록에도 추가한다

## 검증

- `./gradlew assembleDebug`로 빌드 확인
- (가능하면) 앱 실행 후 Splash → Menu 화면 전환, 버튼 7개 클릭 시 각각 올바른 화면으로 이동하는지 확인
