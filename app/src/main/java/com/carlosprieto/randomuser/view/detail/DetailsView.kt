package com.carlosprieto.randomuser.view.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.carlosprieto.randomuser.R
import com.carlosprieto.randomuser.view.components.TopBar
import com.carlosprieto.randomuser.view.utils.NavigationRoutes

@Composable
fun DetailsView (
    gender: String,
    firstName: String,
    lastName: String,
    thumbnail: String,
    email: String,
    phone: String,
    registerDate: String,
    navController: NavHostController
) {

    val userGender = stringResource(R.string.user_gender)
    val userName = stringResource(R.string.user_name)
    val userEmail = stringResource(R.string.user_email)
    val userRegistrationDate = stringResource(R.string.user_registrationdate)
    val userPhone = stringResource(R.string.user_phone)
    val userAddress = stringResource(R.string.user_address)

    val imageHeight = with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp / 5 }
    val profileImageSize = 80.dp
    val profileImageOffset = profileImageSize / 2
    val horizontalPadding = 50.dp

    //Localization and Language Management
    val context = LocalContext.current

    Box(modifier = Modifier.background(Color.White)) {
        Image(
            painter = painterResource(id = R.drawable.image_background),
            contentDescription = stringResource(R.string.img_background),
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight),
            contentScale = ContentScale.Crop
        )

        // TopAppBar
        TopBar(
            titleText = "$firstName $lastName",
            backgroundColor = Color.Transparent,
            contentColor = Color.White,
            onBackPress = { navController.navigate(NavigationRoutes.HomeView) })

        CircleImage(
            imageUrl = thumbnail,
            modifier = Modifier
                .offset(
                    x = (-profileImageOffset + horizontalPadding),
                    y = (imageHeight - profileImageOffset)
                )
                .size(profileImageSize)
        )

        IconRowBelowImage(
            imageHeight = imageHeight,
            onEditClick = { /* ... */ },
            onChangeClick = { /* ... */ }
        )

        LazyColumn(modifier = Modifier.padding(top = imageHeight + profileImageSize/2)) {
            item {

                DetailCard(
                    iconId = R.drawable.ic_account,
                    title = userName,
                    detail = "$firstName $lastName"
                )
                DetailCard(
                    iconId = R.drawable.ic_email,
                    title = userEmail,
                    detail = email
                )
                DetailCard(
                    iconId = if (gender == "male") R.drawable.ic_male else R.drawable.ic_female,
                    title = userGender,
                    detail = gender
                )
                DetailCard(
                    iconId = R.drawable.ic_date,
                    title = userRegistrationDate,
                    detail = registerDate
                )
                DetailCard(
                    iconId = R.drawable.ic_phone,
                    title = userPhone,
                    detail = phone
                )
                AddressSection(
                    mapPlaceholderId = R.drawable.ic_launcher_foreground,
                    title = userAddress)
            }
        }
    }
}




@Composable
fun DetailCard(
    @DrawableRes iconId: Int,
    title: String,
    detail: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = title,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(32.dp))
            Column {
                Text(text = title, color = Color.Gray, fontSize = 14.sp)
                Text(text = detail, fontWeight = FontWeight.Bold ,fontSize = 18.sp)
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 75.dp, end = 20.dp)
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.LightGray
        )
    }
}

@Composable
fun CircleImage(imageUrl: String, modifier: Modifier = Modifier) {
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = stringResource(R.string.img_profile),
        modifier = modifier
            .clip(CircleShape)
            .border(4.dp, Color.White, CircleShape)
    )
}

@Composable
fun IconRowBelowImage(imageHeight: Dp, onEditClick: () -> Unit, onChangeClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = imageHeight, end = 10.dp),
        horizontalArrangement = Arrangement.End
    ) {

        IconButton(onClick = onEditClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = stringResource(R.string.img_edit),
                tint = Color.Gray
            )
        }

        Spacer(modifier = Modifier.width(2.dp))
        IconButton(onClick = onChangeClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = stringResource(R.string.profile_edit),
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun AddressSection(
    @DrawableRes mapPlaceholderId: Int,
    title: String) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 75.dp)) {

        Text(
            text = title,
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        Image(
            painter = painterResource(id = mapPlaceholderId),
            contentDescription = stringResource(R.string.map_default),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, end = 16.dp)
                .aspectRatio(16f / 9f),
            contentScale = ContentScale.Crop
        )
    }
}