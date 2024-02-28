package com.carlosprieto.randomuser.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.carlosprieto.randomuser.R
import com.carlosprieto.randomuser.view.components.TopBar
import com.carlosprieto.randomuser.view.utils.NavigationUtils
import java.util.Locale

@Composable
fun HomeView(navController: NavHostController) {

    val homeViewViewModel: HomeViewViewModel = viewModel()
    val users by homeViewViewModel.users.collectAsState()
    val titleString = stringResource(R.string.contactsTitle)

    Scaffold(
        topBar = {
            //Custom AppBar
            TopBar(
                titleText = titleString,
                onBackPress = { /* ... */ },

                )
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(users) { user ->
                UserCard(
                    imageUrl = user.picture.thumbnail,
                    name = "${user.name.first} ${user.name.last}",
                    email = user.email,
                    onClick = {
                        val route = NavigationUtils.createUserDetailsRoute(user)
                        navController.navigate(route)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun UserCard(
    imageUrl: String?,
    name: String,
    email: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Profile image",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(2.dp, LightGray, CircleShape)
            )
            Spacer(Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = email, color = Gray, fontSize = 16.sp)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Details",
                modifier = Modifier.size(24.dp)
            )
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 90.dp)
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = LightGray
        )
    }

}
