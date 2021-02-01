package com.outliers.cleancodekt.users.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class AddressModel(var suite: String?, var street: String?, var city: String?,
                        var zipcode: String?,
                        @SerializedName("geo") var geoModel: GeoModel?): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(GeoModel::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(suite)
        parcel.writeString(street)
        parcel.writeString(city)
        parcel.writeString(zipcode)
        parcel.writeParcelable(geoModel, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddressModel> {
        override fun createFromParcel(parcel: Parcel): AddressModel {
            return AddressModel(parcel)
        }

        override fun newArray(size: Int): Array<AddressModel?> {
            return arrayOfNulls(size)
        }
    }
}