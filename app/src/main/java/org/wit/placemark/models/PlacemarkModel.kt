package org.wit.placemark.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlacemarkModel (var id: Long =0,
                           var title: String = "",
                           var image: String = "",
                           var description: String = "") : Parcelable {
}