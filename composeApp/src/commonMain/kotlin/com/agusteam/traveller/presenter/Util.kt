package com.agusteam.traveller.presenter

import com.agusteam.traveller.domain.models.CategoryModel
import com.agusteam.traveller.domain.models.TripDetailsModel
import com.agusteam.traveller.domain.models.TripModel
import com.agusteam.traveller.domain.models.TripProviderModel
import kotlin.math.roundToInt

fun getTimePeriodUnit(month: Int): String {
    val result = if (month in 0..11) {
        month.toDouble()  // Convert month to Double for fractional support
    } else {
        month / 12.0  // Divide by 12 to get the number of years as a double
    }
    val roundedResult = (result * 10.0).roundToInt() / 10.0

    return roundedResult.toString()  // Format the result to 1 decimal point
}

fun getTimePeriod(month: Int): String {
    return if (month == 0 || month in 2..11) {
        "Meses"
    } else if (month == 1) {
        "Mes"
    } else if (month == 12) {
        "Año"
    } else if (month > 12) {
        "Años"
    } else {
        ""
    }
}

fun formatMoney(amount: Int): String {
    val amountString = amount.toString()
    val formatted = buildString {
        // Reverse the string to process from the end
        val reversed = amountString.reversed()
        for (i in reversed.indices) {
            if (i > 0 && i % 3 == 0) append(",") // Add commas every 3 digits
            append(reversed[i])
        }
    }.reversed() // Reverse back to the correct order
    return "$$formatted.00" // Add the dollar sign and decimal part
}

fun formatPhone(phone: String): String {
    if (phone.length != 10 || phone.any { !it.isDigit() }) {
        return "XXX-XXX-XXXX"
        //throw IllegalArgumentException("Phone number must be exactly 10 digits.")
    }
    return "${phone.substring(0, 3)}-${phone.substring(3, 6)}-${phone.substring(6)}"
}


fun createCategories(): List<CategoryModel> = listOf(
    CategoryModel(
        isSelected = true,
        imageUrl = "",
        description = "Populares"
    ),
    CategoryModel(imageUrl = " Res.drawable.ic_mountain", description = "Montaña"),
    CategoryModel(imageUrl = "", description = "Camping"),
    CategoryModel(imageUrl = "", description = "Hiking"),
    CategoryModel(imageUrl = "es.drawable.ic_beach", description = "Playa")
)

fun createShoppingItems(categories: List<CategoryModel>) = listOf(
    TripModel(name = "Trudille", price = "23,430", categoryList = categories, id = ""),
    TripModel(
        name = "Playa Fronton",
        categoryList = categories.take(2),
        id = ""
    ),
    TripModel(
        name = "Valle de Dios",
        price = "2,000",
        categoryList = categories.drop(2),
        id = ""
    ),
    TripModel(
        name = "Pico Duarte",
        price = "22,000",
        categoryList = categories.firstOrNull()?.let { listOf(it) } ?: listOf(),
        id = ""),
    TripModel(name = "Valle Nuevo", price = "2,300", id = ""),
    TripModel(
        name = "Los Cacaos",
        price = "6,300",
        categoryList = if (categories.size >= 4) listOf(categories[1], categories[3]) else listOf(),
        id = ""
    ),
    TripModel(
        name = "Playa Fronton",
        price = "2,430",
        categoryList = categories.take(2),
        id = ""
    ),
    TripModel(
        name = "Valle de Dios",
        price = "2,000",
        categoryList = categories.drop(2),
        id = ""
    ),
    TripModel(
        name = "Pico Duarte",
        price = "22,000",
        categoryList = listOf(categories.first()),
        id = ""
    ),
    TripModel(name = "Valle Nuevo", price = "2,300", id = ""),
    TripModel(
        name = "Los Cacaos",
        price = "6,300",
        categoryList = listOf(categories[1], categories[3]),
        id = ""
    )
)

fun getIncludedServices(): List<String> {
    return listOf(
        "Almuerzo",
        "Botiquín de Primeros Auxilios",
        "Equipos de Canyoning",
        "Equipos de Protección (cascos y chalecos)”,“Guías profesionales",
        "Médico",
        "Opción comida vegetariana",
        "Refrigerio y bebidas no alcohólicas",
        "Seguro Médico de Aventura",
        "Servicio de Aeroambulancia",
        "Super Staff entrenado en Primeros Auxilios en Lugares Remotos",
    )
}

fun getProvider(): TripProviderModel {
    return TripProviderModel(
        id = "",
        name = "AgustTrip",
        image = "",
        registeredItems = "48",
        currentItems = "23",
        description = "Descubre el mundo con [Nombre de la Empresa]. Somos expertos en crear experiencias únicas para cada viajero, diseñando itinerarios personalizados que combinan aventura, confort y cultura. Desde escapadas románticas hasta viajes en grupo o aventuras familiares, nos aseguramos de que cada detalle esté cuidadosamente planificado para que disfrutes al máximo.\n" +
                "\n" +
                "Conectamos a nuestros clientes con los destinos más impresionantes, ofreciendo servicios de calidad, atención personalizada y precios competitivos. ¡Haz realidad tus sueños de viaje con nosotros!",
        phone = "809-945-3434",
        email = "email@email.com",
        address = "Calle, Av. San Vicente de Paúl Megacentro, Santo Domingo Este 11504",
        month = 2,
        avatarUrl = "https://picsum.photos/200/300",
        categoryModel = createCategories().filter { it.description != "Populares" },
        totalOfferedTrips = 60
    )
}

fun getShoppingItemsDetails(): TripDetailsModel {
    return TripDetailsModel(
        description = "Un viaje a las playas más exóticas de Samaná, en República Dominicana Comenzaremos nuestro recorrido en la impresionante Laguna Cristal donde nos daremos un chapuzón, aprovecharemos para tomar fotos y luego iremos a comer donde una señora de la comunidad de El Valle, un delicioso Buffet de campo.\n" +
                "\n" +
                "Después de reposar la comida, nos vamos hacia playa El Valle a montar campamento, disfrutar de una tarde libre y explorar todo lo que tenemos a nuestro alrededor. Aquí hay ríos en los dos extremos y un paisaje que sin duda la pone dentro de las diez playas más hermosas de República Dominicana.\n" +
                "\n" +
                "En la noche podremos apreciar el cielo estrellado y disfrutar de una fogata en buena compañía. Al otro día seguimos con la aventura, nos vamos en lanchas a tres playas que se han mantenido completamente virgen. La primera, Playa Ermitaño II, conocida a nivel internacional por la versión turca del famoso reality “Survivor”. Allí estaremos disfrutando parte de la mañana y luego visitaremos Playa Ermitaño I y Playa Onda.",
        arrivingTime = "23 Noviembre 2024  5:00AM",
        leavingTime = "25 Noviembre 2024  5:00AM",
        meetingPoint = "Barra Piantini - Plaza Andalucía I, Av. Gustavo Mejía Ricart con Avenida Abraham Lincoln, Santo Domingo",
        destiny = "Pedernales",
        includedServices = getIncludedServices(),
    )
}
fun getGalleryPhoto(): List<String> {
    return listOf(
        "https://picsum.photos/200/300",
        "https://picsum.photos/300/300",
        "https://picsum.photos/400/300",
        "https://picsum.photos/500/300"
    )
}
const val INITIAL_PAYMENT="$1000.0"
const val TOTAL_PAYMENT="$12,000.0"
const val PRICE = "2,430"
const val DATE_RANGE = "Aug 31 - Sep 5"
const val URL1 = "http://127.0.0.1:9000/"
const val URL = "http://10.0.2.2:9000/"