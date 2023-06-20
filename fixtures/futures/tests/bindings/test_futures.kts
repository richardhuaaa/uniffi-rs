import uniffi.fixture.futures.*
import kotlinx.coroutines.*
import kotlin.system.*

// init UniFFI to get good measurements after that
runBlocking {
    val time = measureTimeMillis {
        alwaysReady()
    }

    println("init time: ${time}ms")
}

// Test fallible function/method.
runBlocking {
    val time1 = measureTimeMillis {
        try {
            fallibleMe(false)
            assert(true)
        } catch (exception: Exception) {
            assert(false) // should never be reached
        }
    }

    print("fallible function (with result): ${time1}ms")
    assert(time1 < 100)
    println(" ... ok")

    val time2 = measureTimeMillis {
        try {
            fallibleMe(true)
            assert(false) // should never be reached
        } catch (exception: Exception) {
            assert(true)
        }
    }

    print("fallible function (with exception): ${time2}ms")
    assert(time2 < 100)
    println(" ... ok")
}
