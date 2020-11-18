package kim.minecraft.citycore.data.economic.internal

import kim.minecraft.citycore.data.economic.Currency

class EconomyResponse(val currency: Currency,
                      val amount: Double,
                      val balance: Double,
                      val type: ResponseType,
                      val errorMessage: String) {

    constructor(currency: Currency,
                amount: Double,
                balance: Double) : this(currency, amount, balance, ResponseType.SUCCESS, "")

    enum class ResponseType {
        FAILURE,
        NOT_IMPLEMENTED,
        SUCCESS
    }
}