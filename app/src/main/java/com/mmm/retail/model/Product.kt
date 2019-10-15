package com.mmm.retail.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cart")
data class Product(
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    var name: String = "",
    @SerializedName("price")
    @Expose
    @ColumnInfo(name = "price")
    var price: Int = 0,
    @SerializedName("image")
    @Expose
    @ColumnInfo(name = "image")
    var image: String = "",
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "id")
    var productId: String = "",
    @SerializedName("category")
    @Expose
    @ColumnInfo(name = "category")
    var category: String = "",
    @SerializedName("quantity")
    @Expose
    @ColumnInfo(name = "quantity")
    var quantity: Int = 0
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()) {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(price)
        parcel.writeString(image)
        parcel.writeString(productId)
        parcel.writeString(category)
        parcel.writeInt(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}
