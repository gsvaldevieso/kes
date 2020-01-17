package Model

import com.valdevieso.Event
import java.math.BigDecimal

class DepositPerformed(val amount: BigDecimal): Event() {
}