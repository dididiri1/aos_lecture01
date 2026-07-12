# singerRv 오류 - 확인 결과

## Context

`MainActivity.kt`에서 `findViewById<RecyclerView>(R.id.singerRv)`를 호출했지만 `activity_main.xml`(현재 `trot_nav`용 `FragmentContainerView`만 있음)에는 `singerRv`가 없어서 "does not contain a declaration with id singerRv" 오류가 났었습니다.

원인은 RecyclerView 연결 코드가 잘못된 위치(`MainActivity.kt`)에 있었던 것입니다. `singerRv`는 원래 `fragment_singer1.xml`에 정의된 id이고, 이 화면은 `Singer1Fragment`가 담당합니다.

## 현재 상태 확인

사용자가 이미 직접 수정을 완료했습니다:

- `Singer1Fragment.kt`의 `onCreateView`에서 `view.findViewById<RecyclerView>(R.id.singerRv)`로 올바르게 연결하고, `SingerAdapter(list)` + `LinearLayoutManager`를 설정함 ("a", "b", "c" 리스트).
- `MainActivity.kt`는 `setContentView(R.layout.activity_main)`만 남기고 잘못 들어갔던 RecyclerView 코드를 제거함.
- `activity_main.xml`은 그대로 `FragmentContainerView` + `trot_nav`를 유지 (Singer1/2/3 Fragment 이동 연습용).

이 조합은 서로 정합성이 맞습니다: `MainActivity` → `trot_nav` 시작점인 `Singer1Fragment` → `fragment_singer1.xml`의 `singerRv`에 어댑터 연결.

## 남은 작업

코드 수정은 필요 없습니다. `./gradlew assembleDebug`로 빌드가 성공하는지만 확인합니다.

## 검증

- `./gradlew assembleDebug` 실행 → BUILD SUCCESSFUL 확인.
- (선택) 앱 실행 후 메뉴 → MainActivity 진입 → Singer1Fragment에서 리스트("a","b","c")가 RecyclerView로 보이고, 이미지 버튼으로 Singer2/3Fragment 이동이 되는지 확인.
