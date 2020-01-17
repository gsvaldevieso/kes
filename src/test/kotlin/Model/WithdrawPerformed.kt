package Model

import com.valdevieso.Event
import java.math.BigDecimal

class WithdrawPerformed(val amount: BigDecimal): Event() {
}