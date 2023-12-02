package com.example.nowandroid.feature.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nowandroid.R

@Preview
@Composable
fun UserInputPreview(
) {
    UserInput(onMessageSent = {})
}

@Composable
fun UserInput(
    onMessageSent: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    var textState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    var textFieldFocusState by remember { mutableStateOf(false) }

    Surface {
        Column(modifier = modifier) {
            UserInputText(
                onTextChanged = { textState = it },
                textFieldValue = textState,
                focusState = textFieldFocusState,
                onTextFieldFocus = {
                    textFieldFocusState = it
                }
            )
        }
    }
}

@Composable
fun UserInputText(
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChanged: (TextFieldValue) -> Unit,
    textFieldValue: TextFieldValue,
    onTextFieldFocus: (Boolean) -> Unit,
    focusState: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            UserInputTextField(
                textFieldValue = textFieldValue,
                onTextChanged = onTextChanged,
                keyboardType = keyboardType,
                focusState = focusState,
                onTextFieldFocus = onTextFieldFocus
            )
        }
    }
}

@Composable
private fun BoxScope.UserInputTextField(
    textFieldValue: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit,
    keyboardType: KeyboardType,
    focusState: Boolean,
    onTextFieldFocus: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    var lastFocusState by remember { mutableStateOf(false) }

    BasicTextField(
        value = textFieldValue,
        onValueChange = { onTextChanged(it) },
        modifier = modifier
            .padding(32.dp)
            .align(Alignment.CenterStart)
            .onFocusChanged { state ->
                if (lastFocusState != state.isFocused) {
                    onTextFieldFocus(state.isFocused)
                }
                lastFocusState = state.isFocused
            },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Send
        ),
        maxLines = 1,
    )

    if (textFieldValue.text.isEmpty() && !focusState) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 32.dp),
            text = stringResource(R.string.message),
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
        )
    }
}