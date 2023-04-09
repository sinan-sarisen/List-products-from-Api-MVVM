package com.sinansarisen.demo.service.model

import android.os.Parcel
import android.os.Parcelable

data class ProductDetailResponse(
    val result: ResultDetails
)

data class ResultDetails(
    val badge: String,
    val countOfPrices: Int,
    val freeShipping: Boolean,
    val imageUrl: String,
    val lastUpdate: String,
    val mkName: String,
    val price: Double,
    val productName: String,
    val rating: Double,
    val storageOptions: List<String>
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.createStringArrayList()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(badge)
        parcel.writeInt(countOfPrices)
        parcel.writeByte(if (freeShipping) 1 else 0)
        parcel.writeString(imageUrl)
        parcel.writeString(lastUpdate)
        parcel.writeString(mkName)
        parcel.writeDouble(price)
        parcel.writeString(productName)
        parcel.writeDouble(rating)
        parcel.writeStringList(storageOptions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResultDetails> {
        override fun createFromParcel(parcel: Parcel): ResultDetails {
            return ResultDetails(parcel)
        }

        override fun newArray(size: Int): Array<ResultDetails?> {
            return arrayOfNulls(size)
        }
    }
}