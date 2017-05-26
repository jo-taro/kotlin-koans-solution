package iv_properties

import util.TODO
import util.doc34
import kotlin.reflect.KProperty

class LazyPropertyUsingDelegates(val initializer: () -> Int) {
    val lazyValue: Int by Delegate(initializer)
}

class Delegate(initializer: () -> Int) {
    private var value: Int ? = null
    private var initializer: (() -> Int) ? = initializer

    operator fun getValue(lazyPropertyUsingDelegates: LazyPropertyUsingDelegates, property: KProperty<*>): Int {
        if ( value == null  ) {
            value = initializer!!()
            initializer = null
        }
        return value as Int
    }
}


fun todoTask34(): Lazy<Int> = TODO(
    """
        Task 34.
        Read about delegated properties and make the property lazy by using delegates.
    """,
    documentation = doc34()
)
