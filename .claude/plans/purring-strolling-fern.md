# CListViewAdapter NPE 수정

## Context

`MainActivity`에서 `mainListView.adapter = CListViewAdapter(dataList)`로 연결한 뒤 앱을 실행하면
`CListViewAdapter.kt:36`의 `date!!.text = ...`에서 `NullPointerException`이 발생한다.

원인은 `CListViewAdapter.kt:29`의 null 체크가 반대로 작성된 것:

```kotlin
if (convertView !== null) {
    convertView = LayoutInflater.from(parent?.context).inflate(R.layout.clistview_item, parent, false)
}
```

리스트 첫 렌더링 시 재사용할 뷰가 없어 `convertView`가 `null`로 들어오는데, 조건이 `!== null`이라
이 경우 inflate를 건너뛰어 `convertView`가 계속 `null`로 남는다. 그래서 `date`, `memo`의
`findViewById` 결과도 `null`이 되고, `date!!.text`에서 NPE가 터진다.

## Fix

`app/src/main/java/com/kangmin/myfirstfile/CListViewAdapter.kt:29`

```kotlin
if (convertView == null) {
    convertView = LayoutInflater.from(parent?.context).inflate(R.layout.clistview_item, parent, false)
}
```

한 줄(조건 반전)만 수정하면 된다. 다른 로직은 변경하지 않는다.

## Verification

- 앱을 빌드/실행하여 리스트뷰가 있는 화면(운동 메모 저장 후 목록 표시)에 진입했을 때 더 이상
  크래시가 발생하지 않고, 저장된 `date`/`memo` 텍스트가 정상적으로 표시되는지 확인한다.
