package ipvc.estg.retrofit.api

data class User(
    val id: Int,
    val name: String, val email: String, val address: Address
)
data class Address(
    val city: String, val zipcode: String,
    val company: Company,
    val geo: Geo
)
data class Geo (
    val lat: String,
    val lng: String
)
data class Company ( val name: String )