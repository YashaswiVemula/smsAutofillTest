# smsAutofillTest
Contains implementation to test Compose AutofillType smsOtpCode.

AutofillType for UserName, Password works fine, but AutofillType.SmsOtpCode does not work.

Issues - 

1. There is inconsistency in showing `Autofill code from messages` when composable view is focused.
2. For OutlinedTextField  `Autofill code from messages` isn't showing at all. It shows only  `Autofill code from messages` refer to attached videos. It works fine for other Autofilltypes.
3. When the `Autofill code from messages` shown and clicked on it, it does not paste code from messages into composable view, looks like it is not reading code at all.


https://user-images.githubusercontent.com/18373899/218170671-efd50ee1-07d5-40d1-8643-badc8f5191a5.mov



https://user-images.githubusercontent.com/18373899/218170688-42f9e1e1-70ec-4f92-a566-2eef6965109d.mov

