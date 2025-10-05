package com.example.shoppinglist

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.component.ItemInput
import com.example.shoppinglist.component.SearchInput
import com.example.shoppinglist.components.ShoppingList
import com.example.shoppinglist.component.Title

@Composable
fun ShoppingListScreen(
    items: List<String>,
    searchQuery: String,
    newItemText: String,
    onQueryChange: (String) -> Unit,
    onNewItemTextChange: (String) -> Unit,
    onAddItem: () -> Unit
) {
    val filteredItems by remember(searchQuery, items) {
        derivedStateOf {
            if (searchQuery.isBlank()) {
                items
            } else {
                items.filter { it.contains(searchQuery, ignoreCase = true) }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Title()
        Spacer(modifier = Modifier.height(16.dp))
        ItemInput(
            text = newItemText,
            onTextChange = onNewItemTextChange,
            onAddItem = onAddItem
        )
        Spacer(modifier = Modifier.height(16.dp))
        SearchInput(
            query = searchQuery,
            onQueryChange = onQueryChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        ShoppingList(items = filteredItems)
    }
}