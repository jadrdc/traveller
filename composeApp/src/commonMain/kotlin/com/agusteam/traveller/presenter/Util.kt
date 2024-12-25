package com.agusteam.traveller.presenter

import com.agusteam.traveller.domain.models.CategoryModel
import com.agusteam.traveller.domain.models.TripDetailsModel
import com.agusteam.traveller.domain.models.TripModel
import com.agusteam.traveller.domain.models.TripProviderModel


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
    TripModel(name = "Trudille", price = "23,430", categoryList = categories),
    TripModel(
        name = "Playa Fronton",
        price = "2,430",
        categoryList = categories.take(2)
    ),
    TripModel(
        name = "Valle de Dios",
        price = "2,000",
        categoryList = categories.drop(2)
    ),
    TripModel(
        name = "Pico Duarte",
        price = "22,000",
        categoryList = listOf(categories.first())
    ),
    TripModel(name = "Valle Nuevo", price = "2,300"),
    TripModel(
        name = "Los Cacaos",
        price = "6,300",
        categoryList = listOf(categories[1], categories[3])
    ),
    TripModel(
        name = "Playa Fronton",
        price = "2,430",
        categoryList = categories.take(2)
    ),
    TripModel(
        name = "Valle de Dios",
        price = "2,000",
        categoryList = categories.drop(2)
    ),
    TripModel(
        name = "Pico Duarte",
        price = "22,000",
        categoryList = listOf(categories.first())
    ),
    TripModel(name = "Valle Nuevo", price = "2,300"),
    TripModel(
        name = "Los Cacaos",
        price = "6,300",
        categoryList = listOf(categories[1], categories[3])
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
        "Super Staff entrenado en Primeros Auxilios en Lugares Remotos“))",
    )
}

fun getProvider(): TripProviderModel {
    return TripProviderModel(
        upcomingTrips = createShoppingItems(createCategories()),
        address = "Calle, Av. San Vicente de Paúl Megacentro, Santo Domingo Este 11504",
        phone = "809-945-3434",
        email = "email@email.com",
        categoryModel = createCategories().filter { it.description != "Populares" },
        name = "AgustTrip",
        startingPoint = "4 months",
        avatarUrl = "https://picsum.photos/200/300",
        registeredItems = "48",
        currentItems = "23",
        description = "Descubre el mundo con [Nombre de la Empresa]. Somos expertos en crear experiencias únicas para cada viajero, diseñando itinerarios personalizados que combinan aventura, confort y cultura. Desde escapadas románticas hasta viajes en grupo o aventuras familiares, nos aseguramos de que cada detalle esté cuidadosamente planificado para que disfrutes al máximo.\n" +
                "\n" +
                "Conectamos a nuestros clientes con los destinos más impresionantes, ofreciendo servicios de calidad, atención personalizada y precios competitivos. ¡Haz realidad tus sueños de viaje con nosotros!"
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