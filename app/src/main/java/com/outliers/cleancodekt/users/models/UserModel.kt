package com.outliers.cleancodekt.users.models

import android.os.Parcel
import android.os.Parcelable

data class UserModel(var id: String?, var name: String?, var username: String?, var email: String?,
                     var phone: String?, var website: String?, val addressModel: AddressModel?,
                     val companyModel: CompanyModel?) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(AddressModel::class.java.classLoader),
            parcel.readParcelable(CompanyModel::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(username)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeString(website)
        parcel.writeParcelable(addressModel, flags)
        parcel.writeParcelable(companyModel, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}