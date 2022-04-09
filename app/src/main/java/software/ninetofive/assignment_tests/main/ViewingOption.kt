package software.ninetofive.assignment_tests.main

enum class ViewingOption {
    SHOW_NAME {
        override fun toString() = "SHOW_NAME"
    },
    DATE {
        override fun toString() = "DATE"
    },
    NOTHING {
        override fun toString() = "NOTHING"
    }
}