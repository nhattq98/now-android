package com.example.nowandroid.feature.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nowandroid.R
import com.example.nowandroid.data.Message

@Composable
fun MessageRow(
    message: Message,
    isMe: Boolean,
) {
    val borderColor = if (isMe) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.tertiary
    }

    Row {
        val imageModifier = Modifier
            .clickable { }
            .padding(horizontal = 8.dp)
            .size(42.dp)
            .border(1.5.dp, borderColor, CircleShape)
            .border(3.dp, MaterialTheme.colorScheme.surface, CircleShape)
            .clip(CircleShape)
            .align(Alignment.Top)
        Image(
            modifier = imageModifier,
            painter = painterResource(id = message.avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        AuthorAndTextMessage(message = message, isMe = isMe, modifier = Modifier.weight(1f))
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
            text = msg.getAuthorName(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .alignBy(LastBaseline)
                .paddingFrom(LastBaseline, after = 8.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = msg.timeStamp,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.alignBy(LastBaseline),
        )
    }
}

private val ChatBubbleShape = RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp)

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
        shape = ChatBubbleShape
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
fun PreviewMessageRow() {
    val mockMsg = Message(
        content = "Hello AI Chat",
        timeStamp = "#",
        isMe = true,
        avatar = R.drawable.ali
    )
    MessageRow(message = mockMsg, isMe = true)
}