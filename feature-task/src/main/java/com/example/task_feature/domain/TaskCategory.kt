package com.example.task_feature.domain

enum class TaskCategory {
    URGENT,
    NONURGENT,
    LONGTIME,
    REPEATABLE,
    UNSPECIFIED;

    override fun toString(): String {
        return when(this){
            TaskCategory.URGENT -> "Срочное"
            TaskCategory.NONURGENT -> "Несрочное"
            TaskCategory.LONGTIME -> "Длительное"
            TaskCategory.REPEATABLE -> "Повторяющееся"
            TaskCategory.UNSPECIFIED -> "Не указана"
        }
    }
    companion object {
        fun fromString(category: String): TaskCategory {
            return when(category) {
                "Срочное" -> URGENT
                "Несрочное" -> NONURGENT
                "Длительное" -> LONGTIME
                "Повторяющееся" -> REPEATABLE
                "Не указана" -> TaskCategory.UNSPECIFIED
                else -> throw IllegalStateException("Unknown category: $category")
            }
        }
    }

}
