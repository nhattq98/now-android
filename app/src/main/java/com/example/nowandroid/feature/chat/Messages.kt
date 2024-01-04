package com.example.nowandroid.feature.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nowandroid.R
import com.example.nowandroid.data.Message

@Composable
fun Messages(
    messages: List<Message>,
    scrollState: LazyListState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        LazyColumn(
            state = scrollState,
            reverseLayout = true,
            modifier = Modifier.fillMaxSize()
        ) {
            items(messages) {
                MessageRow(message = it)
            }
        }
    }

}

@Composable
fun MessageRow(message: Message) {
    val isMe = message.isMe

    if (isMe) {
        MessageUserRow(message = message)
    } else {
        MessageBotRow(message = message)
    }
}

@Composable
fun MessageBotRow(
    message: Message
) {
    Row(
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        val imageModifier = Modifier
            .clickable { }
            .padding(horizontal = 8.dp)
            .size(42.dp)
            .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            .border(3.dp, MaterialTheme.colorScheme.surface, CircleShape)
            .clip(CircleShape)
            .align(Alignment.Top)
        Image(
            modifier = imageModifier,
            painter = painterResource(id = message.avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        AuthorAndTextMessage(message = message, isMe = false, modifier = Modifier.weight(1f))
    }
}

@Composable
fun MessageUserRow(
    message: Message
) {
    Row(
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        BubbleChat(isMe = true, message = message)

        val imageModifier = Modifier
            .clickable { }
            .padding(horizontal = 8.dp)
            .size(42.dp)
            .border(1.5.dp, MaterialTheme.colorScheme.tertiary, CircleShape)
            .border(3.dp, MaterialTheme.colorScheme.surface, CircleShape)
            .clip(CircleShape)
            .align(Alignment.Top)
        Image(
            modifier = imageModifier,
            painter = painterResource(id = message.avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun AuthorAndTextMessage(
    message: Message,
    isMe: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        NameAndTimeStamp(msg = message)
        BubbleChat(isMe = isMe, message = message)
    }
}

@Composable
fun NameAndTimeStamp(msg: Message) {
    Row {
        Text(
            text = msg.getAuthorName(LocalContext.current),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .alignBy(LastBaseline)
                .paddingFrom(LastBaseline, after = 8.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = msg.timeStamp.toString(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.alignBy(LastBaseline),
        )
    }
}

private val BotChatBubbleShape = RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp)
private val UserChatBubbleShape = RoundedCornerShape(20.dp, 4.dp, 20.dp, 20.dp)

@Composable
fun BubbleChat(
    isMe: Boolean,
    message: Message,
) {
    val backgroundBubbleColor = if (isMe) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    Surface(
        color = backgroundBubbleColor,
        shape = if (isMe) UserChatBubbleShape else BotChatBubbleShape
    ) {
        Text(
            text = message.content,
            style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
            modifier = Modifier.padding(16.dp)
        )
    }
}


@Preview
@Composable
fun PreviewMessageBotRow() {
    val mockMsg = Message(
        content = "Hello AI Chat",
        timeStamp = System.currentTimeMillis(),
        isMe = false,
        avatar = R.drawable.ali
    )
    MessageRow(message = mockMsg)
}

@Preview
@Composable
fun PreviewMessageUserRow() {
    val mockMsg = Message(
        content = "Hello Ai Chat",
        timeStamp = System.currentTimeMillis(),
        isMe = true,
        avatar = R.drawable.ali
    )
    MessageRow(message = mockMsg)
}