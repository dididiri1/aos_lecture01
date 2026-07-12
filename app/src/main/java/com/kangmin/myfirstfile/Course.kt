package com.kangmin.myfirstfile

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
