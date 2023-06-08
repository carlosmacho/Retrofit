package ipvc.estg.retrofit.api

data class Country(
    val name: Name, val capital: List<String>, val independent: Boolean, val currencies: Currency1, val idd: Idd
)
data class Name(
    val common: String,
    val official: String,
    //val nativeName: String
)

data class Currency1 (
    val EUR: Currency
)

data class Currency (
    val name: String,
    val Symbol: String
)
data class Idd (
    val root: String,
    val suffix: List<String>
    )