package dev.vexor

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun App() {
    Column {
        var text by remember { mutableStateOf("Text") }
        TextField(text, { text = it })

        val interactionSource = remember { MutableInteractionSource() }
        val hovered by interactionSource.collectIsHoveredAsState()

        val background by animateColorAsState(
            if(hovered) Color(50, 50, 50)
            else Color(120, 120, 120)
        )

        Box(Modifier.clickable(indication = null, interactionSource = interactionSource) {
                println("Button was clicked!")
        }.background(background, RoundedCornerShape(10.dp))
        ) {
            Text("Hello, world!", modifier = Modifier.padding(8.dp))
        }

        Box(Modifier.weight(1f)) {
            val state = rememberLazyListState()

            LazyColumn(state = state, modifier = Modifier.width(200.dp).fillMaxHeight()) {
                items(100) {
                    Text("Item $it")
                }
            }

            VerticalScrollbar(
                rememberScrollbarAdapter(state),
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight()
            )
        }
    }
}