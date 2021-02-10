package com.appservicesunlimited.anydiary.utils.extensions

import android.graphics.Typeface
import android.os.Build
import android.text.*
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import org.apache.commons.lang3.StringUtils
import java.util.*

fun String.toLowerEnglish() = this.toLowerCase(Locale.ENGLISH)

fun String.toUpperEnglish() = this.toUpperCase(Locale.ENGLISH)


fun String.removeSpecialCharacters(): String {
    var stringToReturn = this
    stringToReturn = stringToReturn.replace("#", " ")
    stringToReturn = stringToReturn.replace("%", " ")
    stringToReturn = stringToReturn.replace("&", " ")
    stringToReturn = stringToReturn.replace("{", " ")
    stringToReturn = stringToReturn.replace("}", " ")
    stringToReturn = stringToReturn.replace("\\", " ")
    stringToReturn = stringToReturn.replace("<", " ")
    stringToReturn = stringToReturn.replace(">", " ")
    stringToReturn = stringToReturn.replace("*", " ")
    stringToReturn = stringToReturn.replace("?", " ")
    stringToReturn = stringToReturn.replace("/", " ")
    stringToReturn = stringToReturn.replace("$", " ")
    stringToReturn = stringToReturn.replace("!", " ")
    stringToReturn = stringToReturn.replace("'", "")
    stringToReturn = stringToReturn.replace("\"", " ")
    stringToReturn = stringToReturn.replace(":", " ")
    stringToReturn = stringToReturn.replace("@", " ")
    stringToReturn = stringToReturn.replace("+", " ")
    stringToReturn = stringToReturn.replace("`", "")
    stringToReturn = stringToReturn.replace("|", " ")
    stringToReturn = stringToReturn.replace("=", " ")
    stringToReturn = stringToReturn.replace("‘", "")
    stringToReturn = stringToReturn.replace("_", " ")
    stringToReturn = stringToReturn.replace("•", " ")
    stringToReturn = stringToReturn.trim()
    stringToReturn = stringToReturn.replace("\\s{2,}".toRegex(), " ")
    stringToReturn =
        stringToReturn.replace("[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]".toRegex(), " ")
    stringToReturn = stringToReturn.removeMultipleBlankSpace()
    return stringToReturn
}

fun String.containsInvalidFolderCharacters(): Boolean {
    return this.contains("#") ||
            this.contains("%") ||
            this.contains("&") ||
            this.contains("{") ||
            this.contains("}") ||
            this.contains("\\") ||
            this.contains("<") ||
            this.contains(">") ||
            this.contains("*") ||
            this.contains("?") ||
            this.contains("/") ||
            this.contains("$") ||
            this.contains("!") ||
            this.contains("'") ||
            this.contains("\"") ||
            this.contains(":") ||
            this.contains("@") ||
            this.contains("+") ||
            this.contains("`") ||
            this.contains("|") ||
            this.contains("=") ||
            this.contains("‘") ||
            this.contains("‘") ||
            StringUtils.containsWhitespace(this)
}

fun String.removeMultipleBlankSpace(): String {
    return StringUtils.normalizeSpace(this)
}


fun String.replaceAccentedCharactersWithNormalEnglish(): String {
    var stringToReturn = this
    stringToReturn = stringToReturn.replace("¡", "!")
    stringToReturn = stringToReturn.replace("¿", "?")

    stringToReturn = stringToReturn.removeSpecialCharacters()

    stringToReturn = stringToReturn.replace("á", "a")
    stringToReturn = stringToReturn.replace("Á", "a")
    stringToReturn = stringToReturn.replace("à", "a")
    stringToReturn = stringToReturn.replace("À", "a")
    stringToReturn = stringToReturn.replace("â", "a")
    stringToReturn = stringToReturn.replace("Â", "a")
    stringToReturn = stringToReturn.replace("ä", "a")
    stringToReturn = stringToReturn.replace("Ä", "a")
    stringToReturn = stringToReturn.replace("ã", "a")
    stringToReturn = stringToReturn.replace("Ã", "a")
    stringToReturn = stringToReturn.replace("å", "a")
    stringToReturn = stringToReturn.replace("Å", "a")
    stringToReturn = stringToReturn.replace("Æ", "AE")
    stringToReturn = stringToReturn.replace("æ", "ae")
    stringToReturn = stringToReturn.replace("ç", "c")
    stringToReturn = stringToReturn.replace("Ç", "c")
    stringToReturn = stringToReturn.replace("é", "e")
    stringToReturn = stringToReturn.replace("É", "e")
    stringToReturn = stringToReturn.replace("è", "e")
    stringToReturn = stringToReturn.replace("È", "e")
    stringToReturn = stringToReturn.replace("ê", "e")
    stringToReturn = stringToReturn.replace("Ê", "e")
    stringToReturn = stringToReturn.replace("ë", "e")
    stringToReturn = stringToReturn.replace("Ë", "e")
    stringToReturn = stringToReturn.replace("í", "i")
    stringToReturn = stringToReturn.replace("Í", "i")
    stringToReturn = stringToReturn.replace("ì", "i")
    stringToReturn = stringToReturn.replace("Ì", "i")
    stringToReturn = stringToReturn.replace("î", "i")
    stringToReturn = stringToReturn.replace("Î", "i")
    stringToReturn = stringToReturn.replace("ï", "i")
    stringToReturn = stringToReturn.replace("Ï", "i")
    stringToReturn = stringToReturn.replace("ñ", "n")
    stringToReturn = stringToReturn.replace("Ñ", "n")
    stringToReturn = stringToReturn.replace("ó", "o")
    stringToReturn = stringToReturn.replace("Ó", "o")
    stringToReturn = stringToReturn.replace("ò", "o")
    stringToReturn = stringToReturn.replace("Ò", "o")
    stringToReturn = stringToReturn.replace("ô", "o")
    stringToReturn = stringToReturn.replace("Ô", "o")
    stringToReturn = stringToReturn.replace("ö", "o")
    stringToReturn = stringToReturn.replace("Ö", "o")
    stringToReturn = stringToReturn.replace("õ", "o")
    stringToReturn = stringToReturn.replace("Õ", "o")
    stringToReturn = stringToReturn.replace("ø", "o")
    stringToReturn = stringToReturn.replace("Ø", "O")
    stringToReturn = stringToReturn.replace("œ", "oe")
    stringToReturn = stringToReturn.replace("Œ", "OE")
    stringToReturn = stringToReturn.replace("ß", "ss")
    stringToReturn = stringToReturn.replace("ú", "u")
    stringToReturn = stringToReturn.replace("Ú", "u")
    stringToReturn = stringToReturn.replace("ù", "u")
    stringToReturn = stringToReturn.replace("Ù", "u")
    stringToReturn = stringToReturn.replace("û", "u")
    stringToReturn = stringToReturn.replace("Û", "u")
    stringToReturn = stringToReturn.replace("ü", "u")
    stringToReturn = stringToReturn.replace("Ü", "u")

    stringToReturn = stringToReturn.replace("Ð", "edh")
    stringToReturn = stringToReturn.replace("ð", "edh")
    return stringToReturn
}


fun CharSequence.bold(startIndex: Int = 0, endIndex: Int? = null): Spanned {
    val word: Spannable = SpannableString(this)
    word.setSpan(
        StyleSpan(Typeface.BOLD),
        startIndex,
        endIndex ?: this.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return word
}

fun CharSequence.underline(startIndex: Int = 0, endIndex: Int? = null): Spanned {
    val word: Spannable = SpannableString(this)
    word.setSpan(
        UnderlineSpan(),
        startIndex,
        endIndex ?: this.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return word
}

fun CharSequence.italic(startIndex: Int = 0, endIndex: Int? = null): Spanned {
    val word: Spannable = SpannableString(this)
    word.setSpan(
        StyleSpan(Typeface.ITALIC),
        startIndex,
        endIndex ?: this.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return word
}

fun String.toHTML(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(this)
    }
}

fun String.charsToInt(): Int {
    var intToReturn = 0
    this.toCharArray().forEach {
        val charNumber = Character.getNumericValue(it)
        intToReturn += charNumber
    }
    return intToReturn
}

fun String.captilizeFirstChar(): String {
    if (this.isNotEmpty()) {
        return this.first().toUpperCase() + this.substring(1, this.length)
    }
    return this
}


fun String.Companion.formatNew(format: String, vararg args: Any?): String {
    return try {
        String.format(format, *args)
    } catch (e: Exception) {
        return try {
            val list = args.filterNotNull().map { it.toString() }
            format.replace("%1\$s", list[0]).replace("%2\$s", list[1])
        } catch (e: Exception) {
            format.replace("%1\$s", "").replace("%2\$s", "") + " " + TextUtils.join(
                " ",
                args.filterNotNull().map { it.toString() })
        }
    }
}