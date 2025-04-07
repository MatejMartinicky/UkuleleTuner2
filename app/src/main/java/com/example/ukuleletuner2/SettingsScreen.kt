package com.example.ukuleletuner2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("") //fix
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF66BB6A)
                ),
                navigationIcon = {
                    IconButton(onClick = { /* todo */ }) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color.White
                        )
                    }
                },

                actions = { /*todo*/}
            )
        },
        content = { paddingValues ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .padding(paddingValues)
            ) {

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 16.dp)
                        .padding(horizontal = 16.dp)
                ) {

                    Text(
                        text = "General",
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold

                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Tuning", color = Color.Gray)
                    Text(text = "G4-C4-E4-A4", color = Color.Gray, fontWeight = FontWeight.Thin)

                    Spacer(modifier = Modifier.height(16.dp))


                    Text(text = "Theme", color = Color.Gray)
                    Text(text = "Light", color = Color.Gray, fontWeight = FontWeight.Thin)

                    Spacer(modifier = Modifier.height(16.dp))


                    Text(text = "Node names", color = Color.Gray)
                    Text(text = "C-D-E-F-G-A-H", color = Color.Gray, fontWeight = FontWeight.Thin)

                    Spacer(modifier = Modifier.height(16.dp))


                    Text(text = "Language", color = Color.Gray)
                    Text(text = "English", color = Color.Gray, fontWeight = FontWeight.Thin)

                    Spacer(modifier = Modifier.height(16.dp))

                    Divider(    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .padding(vertical = 16.dp),
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Info",
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold

                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Author", color = Color.Gray)
                    Text(text = "Matej Martinicky", color = Color.Gray, fontWeight = FontWeight.Thin)

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Version", color = Color.Gray)
                    Text(text = "0.0.8 alfa", color = Color.Gray, fontWeight = FontWeight.Thin )

                    Spacer(modifier = Modifier.height(16.dp))

                    Divider(    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .padding(vertical = 16.dp),
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Contact",
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold

                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "e-mail", color = Color.Gray)
                    Text(text = "martinicky3@stud.uniza.sk", color = Color.Gray, fontWeight = FontWeight.Thin)
                }
            }
        }
    )
}