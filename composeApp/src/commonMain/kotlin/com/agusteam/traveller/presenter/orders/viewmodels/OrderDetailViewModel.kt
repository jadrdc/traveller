package com.agusteam.traveller.presenter.orders.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agusteam.traveller.presenter.getIncludedServices
import com.agusteam.traveller.presenter.shopping.state.TripDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrderDetailViewModel : ViewModel() {
    private var _state = MutableStateFlow(TripDetailState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                details = _state.value.details.copy(
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
                ),
                title = "Trudille",

                )
        }

    }
}