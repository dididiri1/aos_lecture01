# 예제 연습 워크플로우

## 핵심 규칙

- **`MainActivity.kt` + `activity_main.xml` = 초기 상태 (연습 시작점 템플릿)**
- 새 예제는 항상 `MainActivity`에서 연습한다.
- 예제가 완성되면:
  1. 완성된 예제를 **별도의 Activity**로 분리한다 (예: `ListViewActivity`, `DiceActivity`).
  2. 분리한 Activity를 `AndroidManifest.xml`에 등록한다.
  3. 분리한 Activity가 다른 화면에서만 호출되는 상세 화면이 아니라 그 예제의 **진입점**이라면, `MenuActivity.kt`의 `examples` 목록에도 한 줄 추가한다.
  4. **`MainActivity`와 `activity_main.xml`을 아래 초기 상태로 리셋한다.**
- 리셋된 `MainActivity`에서 다음 예제를 연습한다.
- 앱 실행 흐름: `SplashActivity` → `MenuActivity`(예제 목록 허브) → 버튼으로 각 예제 Activity 이동. `MainActivity`도 "연습중" 버튼으로 메뉴에서 갈 수 있다.

## 완성 예제 → 별도 Activity 분리 절차

1. `app/src/main/java/com/kangmin/myfirstfile/XxxActivity.kt` 생성 (Main의 로직 이동)
2. `app/src/main/res/layout/activity_xxx.xml` 생성 (Main의 레이아웃 이동)
3. `AndroidManifest.xml`에 `<activity android:name=".XxxActivity" ... />` 등록
4. `MainActivity.kt` / `activity_main.xml`을 초기 상태로 되돌리기
5. `./gradlew assembleDebug` 로 빌드 확인

## 초기 상태 원본 (git commit 995bf55)

### MainActivity.kt

```kotlin
package com.kangmin.myfirstfile

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image1 = findViewById<ImageView>(R.id.btnImage1);
        image1.setOnClickListener {
            Toast.makeText(this, "1번 이미지 클릭!", Toast.LENGTH_LONG).show();
        }
    }
}
```

### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:tools="http://schemas.android.com/tools"

    tools:context=".MainActivity"
    android:orientation="vertical">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <ImageView
            android:id="@+id/btnImage1"
            android:src="@drawable/react1"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:contentDescription="@null" />

        <ImageView
            android:src="@drawable/react2"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:contentDescription="@null" />

        <ImageView
            android:src="@drawable/react3"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:contentDescription="@null" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="120dp">

        <ImageView
            android:src="@drawable/react1"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:contentDescription="@null" />

        <ImageView
            android:src="@drawable/react2"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:contentDescription="@null" />

        <ImageView
            android:src="@drawable/react3"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:contentDescription="@null" />
    </LinearLayout>

</LinearLayout>
```

> 초기 상태로 되돌릴 때: `git checkout 995bf55 -- app/src/main/java/com/kangmin/myfirstfile/MainActivity.kt app/src/main/res/layout/activity_main.xml`

## 지금까지 분리된 예제 Activity

- `ListViewActivity` — ListView + 커스텀 어댑터 연습
- `DiceActivity` — 주사위 굴리기 게임
- `Bts1Activity`
- `ImageListActivity` / `ImageInsideMainActivity` — 이미지 목록
- `SentenceMainActivity` / `SentenceActivity` — 명언 랜덤 표시 + 전체 명언 카드 리스트
- `RVActivity` — RecyclerView + 커스텀 어댑터 연습
- `MenuActivity` — 위 예제들로 이동하는 버튼 목록 메뉴 화면 (Splash 다음 진입점)
