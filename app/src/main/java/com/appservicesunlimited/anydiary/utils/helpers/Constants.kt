package com.appservicesunlimited.anydiary.utils.helpers


object Constants {
    const val DEFAULT_PAGINATED_SIZE = 50


    enum class TextFieldType(val value: Int) {
        unknown(0) {
            override fun toString() = "unknown"
        },
        freeText(1) {
            override fun toString() = "freeText"
        };

        companion object {
            fun fromInt(value: Int) = values().first { it.value == value }
        }
    }

    enum class PointInTimeFieldType(val value: Int) {
        unknown(0) {
            override fun toString() = "unknown"
        },
        date(1) {
            override fun toString() = "date"
        },
        time(2) {
            override fun toString() = "time"
        },
        dateTime(3) {
            override fun toString() = "dateTime"
        };

        companion object {
            fun fromInt(value: Int) = values().first { it.value == value }
        }
    }

    enum class NumberFieldType(val value: Int) {
        unknown(0) {
            override fun toString() = "unknown"
        },
        integer(1) {
            override fun toString() = "integer"
        },
        decimal(2) {
            override fun toString() = "decimal"
        },
        currency(3) {
            override fun toString() = "currency"
        };

        companion object {
            fun fromInt(value: Int) = values().first { it.value == value }
        }
    }

    enum class OptionFieldType(val value: Int) {
        unknown(0) {
            override fun toString() = "unknown"
        },
        switch(1) {
            override fun toString() = "switch"
        },
        seekBar(2) {
            override fun toString() = "seekBar"
        },
        singleCheckList(3) {
            override fun toString() = "singleCheckList"
        };

        companion object {
            fun fromInt(value: Int) = values().first { it.value == value }
        }
    }

    enum class MultipleListFieldType(val value: Int) {
        unknown(0) {
            override fun toString() = "unknown"
        },
        multipleCheckList(1) {
            override fun toString() = "multipleCheckList"
        };

        companion object {
            fun fromInt(value: Int) = values().first { it.value == value }
        }
    }

    enum class RangeFieldType(val value: Int) {
        unknown(0) {
            override fun toString() = "unknown"
        },
        rangeBar(1) {
            override fun toString() = "rangeBar"
        };

        companion object {
            fun fromInt(value: Int) = values().first { it.value == value }
        }
    }
}