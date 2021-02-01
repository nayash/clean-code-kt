package com.outliers.cleancodekt.users.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CompanyModel(val name: String?,
                        @SerializedName("catchPhrase") val phrase: String?,
                        val bs: String?) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(phrase)
        parcel.writeString(bs)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CompanyModel> {
        override fun createFromParcel(parcel: Parcel): CompanyModel {
            return CompanyModel(parcel)
        }

        override fun newArray(size: Int): Array<CompanyModel?> {
            return arrayOfNulls(size)
        }
    }
}