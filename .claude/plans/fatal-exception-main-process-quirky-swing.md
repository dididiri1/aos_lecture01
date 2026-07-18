# MainActivity D-Day 계산 크래시 수정

## Context

`MainActivity`는 시작일/종료일을 `DatePickerDialog`로 선택해 두 날짜 사이의 일수를 `countText`에 표시하는 D-Day 계산기다. 종료일을 선택하면 앱이 `NumberFormatException: For input string: "2026 + 7 + 18"`로 즉시 크래시한다.

**원인** (`MainActivity.kt:37,58,61`):
```kotlin
startDate = "${year} + ${month + 1} + ${day}"   // "2026 + 7 + 18" 같은 표시용 문자열
...
endDate = "${year} + ${month + 1} + ${day}"
countText.text = ((endDate.toInt() - startDate.toInt() + 1)).toString()
```
`startDate`/`endDate`는 사람이 읽는 문자열("2026 + 7 + 18")일 뿐인데 이를 통째로 `String.toInt()`로 파싱하려고 해서 크래시한다. 애초에 문자열 두 개를 빼서 날짜 차이를 구하는 접근 자체가 성립하지 않는다.

**부수 버그**: 두 `onDateSet` 콜백 모두 콜백 파라미터 `dayOfMonth`를 쓰지 않고 `onCreate`의 바깥 변수 `day`(다이얼로그를 연 시점의 "오늘" 날짜)를 그대로 참조한다. 즉 DatePicker에서 일(day)을 다른 값으로 선택해도 무시되고 항상 오늘 날짜의 일(day)이 사용된다.

## Fix

`startDate`/`endDate`를 표시용 문자열 대신 실제 날짜 값(`Calendar`)으로 보관하고, 두 날짜 사이의 일수 차이는 `Calendar`의 `timeInMillis`로 계산한다.

`app/src/main/java/com/kangmin/myfirstfile/MainActivity.kt` 수정:

1. `startDate`/`endDate`의 타입을 `String` 대신 `Calendar?` (nullable, 초기값 `null`)로 변경.
2. 각 `onDateSet` 콜백에서 콜백 파라미터 `year`, `month`, `dayOfMonth`를 사용해 `GregorianCalendar(year, month, dayOfMonth)`를 만들어 해당 변수에 저장 (`day` 바깥 변수 참조 버그 함께 수정).
3. `endBtn`의 `onDateSet`에서:
   - `startDate`가 아직 `null`이면(시작일 미선택) 계산을 건너뛰고 조용히 리턴.
   - `startDate`가 있으면 `TimeUnit.MILLISECONDS.toDays(endCal.timeInMillis - startCal.timeInMillis) + 1` 로 일수 계산 후 `countText.text`에 반영.

## Verification

- `./gradlew assembleDebug`로 빌드 확인.
- 앱 실행 후: 시작일 버튼 → 임의 날짜 선택 → 종료일 버튼 → 시작일보다 뒤/앞/같은 날짜 각각 선택해 크래시 없이 올바른 일수(예: 당일 선택 시 1)가 표시되는지 확인.
- 종료일을 시작일보다 먼저 선택(시작일 미선택 상태)해도 크래시하지 않는지 확인.
