package com.agusteam.traveller.presenter

import com.agusteam.traveller.domain.models.CategoryModel
import com.agusteam.traveller.domain.models.TripModel
import com.agusteam.traveller.domain.models.TripProviderModel
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.math.roundToInt

fun formatMoney(amount: String): String {
    return try {
        val parts = amount.split(".")
        val integerPart = parts[0].reversed().chunked(3).joinToString(",").reversed()
        val decimalPart = if (parts.size > 1) parts[1].padEnd(2, '0').take(2) else "00"
        "$integerPart.$decimalPart"
    } catch (e: Exception) {
        "Invalid amount"
    }
}

fun formatInstant(
    instant: Instant,
    zoneId: TimeZone = TimeZone.currentSystemDefault()
): String {
    // Convert Instant to LocalDateTime in the provided TimeZone
    val dateTime = instant.toLocalDateTime(zoneId)

    // Define the months in Spanish
    val monthsInSpanish = listOf(
        "Ene", "Feb", "Mar", "Abr", "May", "Jun",
        "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"
    )

    // Extract day, month, year, hour, and minute
    val day = dateTime.date.dayOfMonth
    val month = monthsInSpanish[dateTime.date.monthNumber - 1]
    val year = dateTime.date.year
    val hour = dateTime.time.hour
    val minute = dateTime.time.minute

    // Determine AM/PM and format hour (12-hour clock)
    val ampm = if (hour < 12) "AM" else "PM"
    val hour12 = if (hour % 12 == 0) 12 else hour % 12
    val minuteFormatted = if (minute < 10) "0$minute" else minute.toString()

    // Construct the formatted string
    return "$day $month $year ${hour12}:${minuteFormatted} $ampm"
}

fun getTimePeriodUnit(month: Int): String {
    val result = if (month in 0..11) {
        month.toDouble()  // Convert month to Double for fractional support
    } else {
        month / 12.0  // Divide by 12 to get the number of years as a double
    }
    val roundedResult = (result * 10.0).roundToInt() / 10.0

    return roundedResult.toString()  // Format the result to 1 decimal point
}

fun formatDateRange(
    start: Instant,
    end: Instant,
    zoneId: TimeZone = TimeZone.currentSystemDefault()
): String {
    // Convert Instant to LocalDate in the provided TimeZone
    val startDate = start.toLocalDateTime(zoneId).date
    val endDate = end.toLocalDateTime(zoneId).date

    // Define the months in Spanish manually
    val monthsInSpanish = listOf(
        "Ene", "Feb", "Mar", "Abr", "May", "Jun",
        "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"
    )

    // Extract day, month, and year from start and end dates
    val startDay = startDate.dayOfMonth
    val startMonth = monthsInSpanish[startDate.monthNumber - 1]
    val startYear = startDate.year

    val endDay = endDate.dayOfMonth
    val endMonth = monthsInSpanish[endDate.monthNumber - 1]
    val endYear = endDate.year

    // Return the formatted range string
    return "$startDay $startMonth - $endDay $endMonth $startYear"
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
    TripModel(name = "Trudille", categoryList = categories, id = ""),
    TripModel(
        name = "Playa Fronton",
        categoryList = categories.take(2),
        id = ""
    ),
    TripModel(
        name = "Valle de Dios",
        categoryList = categories.drop(2),
        id = ""
    ),
    TripModel(
        name = "Pico Duarte",
        categoryList = categories.firstOrNull()?.let { listOf(it) } ?: listOf(),
        id = ""),
    TripModel(name = "Valle Nuevo", id = ""),
    TripModel(
        name = "Los Cacaos",
        categoryList = if (categories.size >= 4) listOf(categories[1], categories[3]) else listOf(),
        id = ""
    ),
    TripModel(
        name = "Playa Fronton",
        categoryList = categories.take(2),
        id = ""
    ),
    TripModel(
        name = "Valle de Dios",
        categoryList = categories.drop(2),
        id = ""
    ),
    TripModel(
        name = "Pico Duarte",
        categoryList = listOf(categories.first()),
        id = ""
    ),
    TripModel(name = "Valle Nuevo", id = ""),
    TripModel(
        name = "Los Cacaos",
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

fun getGalleryPhoto(): List<String> {
    return listOf(
        "https://picsum.photos/200/300",
        "https://picsum.photos/300/300",
        "https://picsum.photos/400/300",
        "https://picsum.photos/500/300"
    )
}

const val URL1 = "http://127.0.0.1:9000/"
const val URL = "http://10.0.2.2:9000/"