package com.example.autofillsmsotpcodetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillNode
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalAutofill
import androidx.compose.ui.platform.LocalAutofillTree
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.autofillsmsotpcodetest.ui.theme.AutofillSmsOtpCodeTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutofillSmsOtpCodeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        AutofillSMSOutlinedTextField()
//                        AutofillSMSBasicTextField()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AutofillSMSOutlinedTextField() {
    var codeText by remember { mutableStateOf(TextFieldValue("")) }
    val onValueChange = { value: TextFieldValue -> codeText = value }
    Column {
        Text(text = "OutlinedTextField")
        OutlinedTextField(
            modifier = Modifier.padding(40.dp).fillMaxWidth().autofill(AutofillType.SmsOtpCode) {
                onValueChange.invoke(
                    TextFieldValue(
                        text = it
                    )
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            value = codeText,
            onValueChange = { codeText = it }
        )

        Text(modifier = Modifier.padding(40.dp).fillMaxWidth(), text = " Code entered: ${codeText.text}")
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AutofillSMSBasicTextField() {
    var codeText by remember { mutableStateOf(TextFieldValue("")) }
    val onValueChange = { value: TextFieldValue -> codeText = value }
    Column {
        Text(text = "BasicTextField")
        BasicTextField(
            modifier = Modifier.padding(40.dp).height(20.dp).fillMaxWidth().background(color = Color.Gray).autofill(AutofillType.SmsOtpCode) {
                onValueChange.invoke(
                    TextFieldValue(
                        text = it
                    )
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            value = codeText,
            onValueChange = { codeText = it }
        )

        Text(modifier = Modifier.padding(40.dp).fillMaxWidth(), text = " Code entered: ${codeText.text}")
    }
}

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.autofill(
    autofillTypes: AutofillType,
    onFill: ((String) -> Unit)
) = composed {
    val autofill = LocalAutofill.current
    val autofillNode = AutofillNode(onFill = onFill, autofillTypes = listOf(autofillTypes))
    LocalAutofillTree.current += autofillNode

    this.onGloballyPositioned {
        autofillNode.boundingBox = it.boundsInWindow()
    }.onFocusChanged { focusState ->
        autofill?.run {
            if (focusState.isFocused) {
                requestAutofillForNode(autofillNode)
            } else {
                cancelAutofillForNode(autofillNode)
            }
        }
    }
}
