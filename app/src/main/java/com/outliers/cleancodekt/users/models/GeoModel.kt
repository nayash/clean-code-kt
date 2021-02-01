package com.outliers.cleancodekt.users.models

import android.os.Parcel
import android.os.Parcelable

data class GeoModel(var lat: String?, var lng: String?): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(lat)
        parcel.writeString(lng)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GeoModel> {
        override fun createFromParcel(parcel: Parcel): GeoModel {
            return GeoModel(parcel)
        }

        override fun newArray(size: Int): Array<GeoModel?> {
            return arrayOfNulls(size)
        }
    }

}