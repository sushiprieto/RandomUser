package com.carlosprieto.randomuser

import com.carlosprieto.randomuser.data.RestResult
import com.carlosprieto.randomuser.data.UserHandlerInterface
import com.carlosprieto.randomuser.data.dto.Coordinates
import com.carlosprieto.randomuser.data.dto.Dob
import com.carlosprieto.randomuser.data.dto.ID
import com.carlosprieto.randomuser.data.dto.Info
import com.carlosprieto.randomuser.data.dto.Location
import com.carlosprieto.randomuser.data.dto.Login
import com.carlosprieto.randomuser.data.dto.Name
import com.carlosprieto.randomuser.data.dto.Picture
import com.carlosprieto.randomuser.data.dto.Registered
import com.carlosprieto.randomuser.data.dto.Results
import com.carlosprieto.randomuser.data.dto.Street
import com.carlosprieto.randomuser.data.dto.Timezone
import com.carlosprieto.randomuser.data.dto.User
import com.carlosprieto.randomuser.view.home.HomeViewViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewViewModel
    private val userHandler: UserHandlerInterface = mockk(relaxed = true)

    @Before
    fun setUp() {
        viewModel = HomeViewViewModel()
    }

    @Test
    fun `getUsers success`() = runBlocking {
        val mockUsers = listOf(mockUser1)
        val apiResponse = Results(mockUsers, Info("test", 1, 1, "1.0"))
        coEvery { userHandler.getUsers(any()) } returns RestResult.Success(apiResponse.results)

        viewModel.getUsers(10)

        val emittedUsers = mutableListOf<List<User>>()
        val job = launch {
            viewModel.users.toList(emittedUsers)
        }

        delay(2000)

        assertTrue(emittedUsers.isNotEmpty(), "Can not be empty")
        assertNotNull(emittedUsers[0], "can not be null")

        job.cancel()
    }

    @Test
    fun `getUsers error`() = runBlocking {
        val exception = Exception("Error getting users")
        coEvery { userHandler.getUsers(any()) } returns RestResult.Error(exception)

        viewModel.getUsers(20)
    }


    private val mockUser1 = User(
        gender = "test",
        name = Name(title = "test", first = "test", last = "test"),
        location = Location(
            street = Street(number = 123, name = "test"),
            city = "test",
            state = "test",
            country = "test",
            postcode = "test",
            coordinates = Coordinates(latitude = "2134234", longitude = "1234234"),
            timezone = Timezone(offset = "234324", description = "test")
        ),
        email = "test",
        login = Login(
            uuid = "3242314231432",
            username = "test",
            password = "test",
            salt = "test",
            md5 = "test",
            sha1 = "test",
            sha256 = "test"
        ),
        dob = Dob(date = "test", age = 42),
        registered = Registered(date = "test", age = 17),
        phone = "234234234",
        cell = "234234",
        id = ID(name = "SSN", value = "123-45-6789"),
        picture = Picture(
            large = "test",
            medium = "test",
            thumbnail = "test"
        ),
        nat = "test"
    )
}