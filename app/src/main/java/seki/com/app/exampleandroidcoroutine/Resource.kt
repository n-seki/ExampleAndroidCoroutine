package seki.com.app.exampleandroidcoroutine

sealed class Resource<out T> {

    object Loading: Resource<Nothing>()

    data class Success<T>(val value: T) : Resource<T>()

    data class Error<T>(val throwable: Throwable) : Resource<T>()

    companion object {
        fun <T> loading(): Resource<T> = Loading
        fun <T> success(value: T): Resource<T> = Success(value)
        fun <T> error(throwable: Throwable): Resource<T> = Error(throwable)
    }
}