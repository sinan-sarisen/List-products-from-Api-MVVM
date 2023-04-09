package com.sinansarisen.demo.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

class Tools {
}

fun Double.toPrice(currency: Char): String? {
    var locale: Locale? = null
    if (currency == '₺' || currency == '€') {
        locale = Locale("fr", "FR")
    } else {
        locale = Locale("en", "EN")
    } //Add locales as per need.
    val sym = DecimalFormatSymbols(locale)
    sym.groupingSeparator = '.'
    val decimalFormat: DecimalFormat = NumberFormat.getNumberInstance(locale) as DecimalFormat
    decimalFormat.applyPattern("$currency ##,###.00")
    decimalFormat.decimalFormatSymbols = sym
    return decimalFormat.format(this)
}