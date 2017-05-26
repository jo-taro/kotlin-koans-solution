package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int):  Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int =
        if (this.year < other.year) -1
        else if ( this.year > other.year ) 1
        else if ( this.month < other.month ) -1
        else if ( this.month > other.month ) 1
        else if ( this.dayOfMonth < other.dayOfMonth ) -1
        else if ( this.dayOfMonth > other.dayOfMonth ) 1
        else 0

}

operator fun MyDate.plus(interval: TimeInterval): MyDate {
    return this.addTimeIntervals(interval,1)
}

operator fun MyDate.plus(multiInterval: MultipleTimesInterval): MyDate {
    return this.addTimeIntervals(multiInterval.interval, multiInterval.n)
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(start = this, endInclusive = other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(t : Int): MultipleTimesInterval {
    return MultipleTimesInterval(interval = this, n = t)
}

data class MultipleTimesInterval(val interval: TimeInterval, val n: Int)


class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        val start = this.start
        val end   = this.endInclusive
        return object : Iterator<MyDate> {
            var currentDay = start
            override fun hasNext(): Boolean = currentDay <= end
            override fun next(): MyDate {
                val previousDay = currentDay
                currentDay = currentDay.nextDay()
                return previousDay
            }
        }
    }

}

operator fun DateRange.contains(d: MyDate): Boolean = this.start <= d && d <= this.endInclusive