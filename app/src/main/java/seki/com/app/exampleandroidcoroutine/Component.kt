package seki.com.app.exampleandroidcoroutine

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ClientModule::class]
)
interface Component {
    fun inject(mainActivity: MainActivity)
}