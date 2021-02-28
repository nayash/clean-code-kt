package com.outliers.cleancodekt.users

import android.os.Build
import com.outliers.cleancodekt.users.ui.UsersActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk=[Build.VERSION_CODES.O_MR1])
class UsersActivityTest {

    lateinit var controller: ActivityController<UsersActivity>

    @Before
    fun init(){
        controller = Robolectric.buildActivity(UsersActivity::class.java)
        controller.setup()
    }

    @Test
    fun sameViewModelAfterConfigChange(){
        val vm1 = controller.get().usersViewModel
        val adapter1 = controller.get().adapter
        val activity1 = controller.get()
        // RuntimeEnvironment.setQualifiers("land")
        // controller.get().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        controller.get().recreate()
        val vm2 = controller.get().usersViewModel
        val adapter2 = controller.get().adapter
        println("test1"+activity1.toString()+","+controller.get().toString())
        assert(activity1 != controller.get())
        assert(vm1 == vm2) // assert that same view model is used
        assert(adapter1 != adapter2)
    }
}