package com.example.searchapp.search


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp



@Composable
fun SearchScreen(viewModel: SearchScreenViewModel) {

    var searchText by remember { mutableStateOf("") }
    val words by viewModel.words.observeAsState(emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var newWord by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start){
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                viewModel.searchWord(searchText)
            },
            label = { Text("Search") },
            placeholder = { Text("Type to search...") }
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = "Pre-added Dictionary Words" , fontWeight = FontWeight.Bold )
        LazyColumn {
            items(words) { word ->
                Text(word)
            }
        }
        FloatingActionButton(onClick = {
            showDialog = true
        }, modifier = Modifier.align(Alignment.End)) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                newWord = ""
            },
            title = { Text("Add a new word to dictionary") },
            text = {
                TextField(
                    value = newWord,
                    onValueChange = { newWord = it },
                    label = { Text("New Word") }
                )
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.addingWord(newWord)
                    showDialog = false
                    newWord = ""
                }) {
                    Text("Add")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showDialog = false
                    newWord = ""
                }) {
                    Text("Cancel")
                }
            }
        )
    }
}