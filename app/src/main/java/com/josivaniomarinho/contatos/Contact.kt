package com.josivaniomarinho.contatos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize // Adicionado com um plugin no build.gradle:app ()
class Contact(
    var name: String,
    var phone: String,
    var photograph: String
): Parcelable